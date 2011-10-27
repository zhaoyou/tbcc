package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tbcc.biz.AiInfoBiz;
import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.DevTypeBiz;
import org.tbcc.biz.HisRefBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.dao.HisRefDao;
import org.tbcc.dao.RefInfoDao;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccDevType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;
import org.tbcc.util.BuildTable;
import org.tbcc.util.Calc;
import org.tbcc.util.ObjectToHistory;



/**
 * �����ʷ������ݲ�����ҵ��ʵ����
 * @author Administrator
 *
 */
public class HisRefBizImpl implements HisRefBiz {

	/**
	 * ��spring����ע�������ʷ��������
	 */
	private HisRefDao hisRefDao = null ;
	
	private RefInfoBiz refinfoBiz = null ;
	
	private AiInfoBiz aiinfoBiz = null ;
	
	private BranchBiz branchBiz = null ;
	
	private ObjectToHistory objToHis = null ;
	
	private Calc calc = null ;
	
	private DevTypeBiz devTypeBiz = null ;
	
	
	
	public void setDevTypeBiz(DevTypeBiz devTypeBiz) {
		this.devTypeBiz = devTypeBiz;
	}

	public void setCalc(Calc calc) {
		this.calc = calc;
	}

	public void setObjToHis(ObjectToHistory objToHis) {
		this.objToHis = objToHis;
	}

	public void setBranchBiz(BranchBiz branchBiz) {
		this.branchBiz = branchBiz;
	}

	public void setHisRefDao(HisRefDao hisRefDao) {
		this.hisRefDao = hisRefDao;
	}
	
	public void setRefinfoBiz(RefInfoBiz refinfoBiz) {
		this.refinfoBiz = refinfoBiz;
	}

	public void setAiinfoBiz(AiInfoBiz aiinfoBiz) {
		this.aiinfoBiz = aiinfoBiz;
	}
	
	
	
	public List<TbccRefInfo> getRefListByBranchId(Long branchId) {
		
		TbccBranchType branchType = this.branchBiz.getById(branchId);
		
		if(branchType!=null){
			List<TbccRefInfo> refList = new ArrayList<TbccRefInfo>();
			String proId = "";
		if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
			//����ȡ����ĿId
			for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
				if(tbccPrjType.getProjectType().equals(ProjectBiz.REF)){
					proId = tbccPrjType.getProjectId() ;
					break ; 
				}
					
			}
			refList = this.refinfoBiz.getRefListByPrj(proId);		//������ĿId����ȡ���е������Ŀ
			return refList ;
		}
	}
		return null;
	}
	
	
	public List<TbccAiInfo> getAiListByProperty(Long id) {
		TbccRefInfo refinfo = this.refinfoBiz.getById(id);
		return this.aiinfoBiz.getRefAiList(refinfo.getProjectId(),refinfo.getNetid().toString(), refinfo.getRefid().toString());
	}

	
	public List<Map> getHisRefInfo(Long id, String startTime, String endTime,
			String type, String value) {
		
		TbccRefInfo refinfo = this.refinfoBiz.getById(id);
		//��ȡ����
		String tableName = BuildTable.toHisRefTable(refinfo.getProjectId(), refinfo.getNetid().toString());
		int total = Integer.parseInt(value);
		
		//��ȡ��ʱ������
		if(type.equals("1")){			//����Сʱ
			total = 3600 * total ;
		}else if(type.equals("2"))		//�������
			total = 60 * total ;
		
		//��ȡ���������netId ����ʷ����
		List list = this.hisRefDao.getHisRefData(tableName, startTime, endTime, total); 
		List<Map> refDataList = objToHis.objectToRefCurve(list);
		return refDataList;
	}
	
	
	//*******************�ϰ汾�ֿ��ȡ��ʷ����******************
	
	
	private List<TbccBaseHisRef> getHisRefData(Long id, String startTime, String endTime, String type,
			String value) {
		TbccRefInfo refinfo = this.refinfoBiz.getById(id);
		//��ȡ����
		String tableName = BuildTable.toHisRefTable(refinfo.getProjectId(), refinfo.getNetid().toString());
		
		int total = Integer.parseInt(value);
		
		//��ȡ��ʱ������
		if(type.equals("1")){			//����Сʱ
			total = 3600 * total ;
		}else if(type.equals("2"))		//�������
			total = 60 * total ;
		
		/**
		 * �˴��޸���ʵ�ַ�ʽ��ֱ����ʵ���෵��û���ٴ�ת��
		 */
		//��ȡ���������netId ����ʷ����
		//List list = this.hisRefDao.getHisRefData(tableName, startTime, endTime, total); 
	    //List<TbccBaseHisRef> refDataList = objToHis.objectToRef(list);
		
		List<TbccBaseHisRef> refDataList = this.hisRefDao.getHisDataByTime(tableName, startTime, endTime, total);   
				
		return refDataList;
	}
	
	
	
	/**
	 * ֮ǰ�汾��ȡ�ֿ���ʷ����
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	private List<Object> getHisInfo(Long id,String startTime,String endTime,String type,String value) {
		
		List<TbccAiInfo> aiList = this.getAiListByProperty(id);			//��ȡ��������ص�̽ͷ
		
		List<TbccBaseHisRef> refDataList = this.getHisRefData(id, startTime, endTime, type, value);		//��ȡ��������ص���ʷ����

		//���û���ҵ���Ӧ��̽ͷ���򷵻�nullֵ,���Ǻ������ӵĶ���
		if(aiList==null || aiList.size()==0)
				return null ;
		
		
		List<Integer> tportList = new ArrayList<Integer>();		//����ʢ��ai���Ƶ�ʵ���¶ȶ˿�
		
		List<Integer> hportList = new ArrayList<Integer>(); 	//����ʢ��ai ���Ƶ�ʵ��ʪ�ȶ˿�
		
		List<Object> result = new ArrayList<Object>(3);			//���ڱ��淵�ص����ݰ�װ����
		
		//�ֱ��ȡ�¶Ⱥ�ʪ�ȶ˿�
		for (TbccAiInfo tbccAiInfo : aiList) {		
			if(tbccAiInfo.getPortNo()>9)
				hportList.add(tbccAiInfo.getPortNo());
			else
				tportList.add(tbccAiInfo.getPortNo());				
		}

		
		List<String[]> aa = new ArrayList<String[]>();				//�Զ���ҳ����ʾ�ı�
		
	
		
		//��ȡ����
		if(refDataList!=null && refDataList.size()>0){
			for (TbccBaseHisRef tbccBaseHisRef : refDataList) {
				String [] data =
					calc.calcRef(aiList.get(0).getRefid(), tbccBaseHisRef, tportList, hportList);  //������
				aa.add(data);
			}
		}
	
		result.add(aiList);			//�������е�̽ͷ
		result.add(aa);				//�������н�����ַ���
		result.add(calc.calcHisRefData(aa, aiList.size()));		//����ĳ�е����ֵ����Сֵ��ƽ��ֵ��������		
		return result;
	
	}
	
	

	
	
	
	
	
	
	//***********************�ֿ����ģ����ʷ���ݲ�ѯ********************************
	
	
	private List<TbccBaseHisRef_Ex> getHisRefData_ex(Long id, String startTime,
			String endTime, String type, String value) {
		
		//��֤����
		if(id==null || id.equals("") || startTime==null || startTime.equals("")|| endTime==null || endTime.equals("")||
				type==null || type.equals("")|| value==null || value.equals("")){
			return null ;
		}
		
		//��ȡ������
		TbccRefInfo refInfo = this.refinfoBiz.getById(id);
		
		//��ȡ����ģ��ı���
		String tableName = BuildTable.toHisRef_ExTable(refInfo.getProjectId(), refInfo.getNetid());
		
		//��ȡ�����������
		
		Integer total = Integer.parseInt(value);
		
		//��ȡ��ʱ������
		if(type.equals("1")){			//hour
			total = 3600 * total ;
			
		}else if(type.equals("2"))		//minutes
			total = 60 * total ;
		
		
		return this.hisRefDao.getExHisDataByTime(tableName, startTime, endTime, total);
	}

	
	/**
	 * ��ȡ��չ�豸�Ĳֿ����ʷ����
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	private List<Object> getHisRefData_ex_result(Long id,String startTime,String endTime,String type,String value) {
		
		//��֤����
		if(id==null ||id.equals("")|| startTime==null || startTime.equals("") || endTime==null || endTime.equals("") ||
				type==null || type.equals("") || value==null || value.equals("")){
			return null ;
		}
		
		
		//��ȡ̽ͷ��ʵʱ����
		
		List<TbccAiInfo> aiList = this.aiinfoBiz.getListByRid(id);
		List<TbccBaseHisRef_Ex> refExDataList = this.getHisRefData_ex(id, startTime, endTime, type, value);
	
		
		if(aiList==null || aiList.size()==0){
			return null ;
		}
		
		//������������¶ȡ�ʪ��̽ͷ��Ӧ��ʵ�ʶ˿ںš��Լ����صĽ��
		 List<Integer> tportList = new ArrayList<Integer>() ;
		 List<Integer> rhportList = new ArrayList<Integer>() ;
		 
		 List<Object> resultList = new ArrayList<Object>(); 
		 
		 List<String[]> aa = new ArrayList<String[]>();
		 
		 //�����ȡ��ʪ��̽ͷ
		 for (TbccAiInfo tbccAiInfo : aiList) {
			if(tbccAiInfo.getDataType().equals(AiInfoBiz.T)){
				tportList.add(tbccAiInfo.getPortNo());
			}else if(tbccAiInfo.getDataType().equals(AiInfoBiz.RH)){
				rhportList.add(tbccAiInfo.getPortNo());
			}
		}
		 
		 //����ʱ�䡢��ʪ�ȡ�ƽ���������С��ʾֵ
		 if(refExDataList!=null && refExDataList.size()!=0){
			 for (TbccBaseHisRef_Ex tbccBaseHisRef_Ex : refExDataList) {
				String [] data = calc.calcExRefDataInfo(aiList.get(0).getRefid(), tbccBaseHisRef_Ex, tportList, rhportList);
				aa.add(data) ;
			}
		 }
		 
		
		 
		 
		 resultList.add(aiList) ;
		 resultList.add(aa);
		 resultList.add(calc.calcHisRefData(aa, aiList.size())) ;
		 	
		return resultList;
	}

	
	
	
	public List<Object> getRefHisData(Long id, String startTime,
			String endTime, String type, String value) {
		
		//��֤����
		if(id==null || id.equals("") || startTime==null || startTime.equals("") || endTime==null || endTime.equals("")||
				type==null || type.equals("") || value==null || value.equals("")){
			return null ;
		}
		
		//���������Ϣ��ȡ��Ӧ���豸����
		TbccRefInfo refInfo = this.refinfoBiz.getById(id);
		if(refInfo==null)
			return null ;
		
		TbccDevType devType = this.devTypeBiz.getById(refInfo.getDevId());
		
		//���Ϊ��չ�豸���������չ  getHisRefData_ex_result
		if(devType.getDevType().equals(DevTypeBiz.EXAPP)){
		
			return this.getHisRefData_ex_result(id, startTime, endTime, type, value);
		//���Ϊ֮ǰ���ϰ汾�������֮ǰ�ķ��� 	getHisInfo
		}else{
			return this.getHisInfo(id, startTime, endTime, type, value);
		}
	}
	
}
