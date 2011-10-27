package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.tbcc.biz.HisBoxBiz;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.dao.HisBoxDao;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BuildTable;
import org.tbcc.util.Calc;
import org.tbcc.util.MyUtil;
import org.tbcc.util.ObjectToHistory;

/**
 * ��ʷС�����ҵ��ʵ�ֽӿ�
 * @author Administrator
 *
 */
public class HisBoxBizImpl implements HisBoxBiz {

	/**
	 * ��springע��
	 */
	private HisBoxDao hisboxDao = null ;
	
	private ObjectToHistory objtoHis = null ;
	
	private BranchDao branchDao = null ;
	
	private Calc calc = null ;
	
	
	public void setCalc(Calc calc) {
		this.calc = calc;
	}


	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}


	public void setObjtoHis(ObjectToHistory objtoHis) {
		this.objtoHis = objtoHis;
	}


	public void setHisboxDao(HisBoxDao hisboxDao) {
		this.hisboxDao = hisboxDao;
	}

	
	/**
	 * ����������ȡС�������ʷ����
	 */
	public List<TbccBaseHisBox> getByProperty(String proId, String startTime,
			String endTime, String interval, String value) {
		
//		int seconds = 0 ,interval = Integer.parseInt(value);
//		
//		//�ж�ʱ�������,�������ʱ��ļ������ 1(hour) 2(minute) 3(seconds)
//		if(type.equals("1")){
//			seconds = interval * 3600 ;
//		}else if(type.equals("2")){
//			seconds = interval * 60 ;
//		}else{
//			seconds = interval ;
//		}
		
		int total = Integer.parseInt(interval) * Integer.parseInt(value);
		
		//�жϵ�һ����¼��ʱ���ǲ��Ƿ�������ѽ
		startTime = MyUtil.getValid(startTime);
		
		List list = hisboxDao.getHisBoxData(BuildTable.toHisBoxTable(proId), startTime, endTime,total);
				
		List<TbccBaseHisBox> boxList =  objtoHis.objectToBox(list) ;
			
		return boxList;
	}

	/**
	 *  ���ݷ�֧Id����ȡ���е�С������Ŀ
	 */
	
	public List<TbccPrjType> getBoxPrjByBranchID(Long branchId) {
		
		TbccBranchType branchType = this.branchDao.get(branchId);
		
		if(branchType!=null){
				List<TbccPrjType> BoxprjList = new ArrayList<TbccPrjType>();
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//�Ѹû����µ�����С������Ŀ���뵽����
				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
					if(tbccPrjType.getProjectType().equals(ProjectBiz.BOX))
						BoxprjList.add(tbccPrjType);
				}
				return BoxprjList ;
			}
		}
		return null;
	}

	
	/**
	 * Ϊ�˽���ӳټ��أ�Ϊ����ʷ���ߵķ���
	 */
	public List<TbccPrjType> getBoxPrjProxy(Long branchId) {
		TbccBranchType branchType = this.branchDao.get(branchId);
		
		if(branchType!=null){
				List<TbccPrjType> BoxprjList = new ArrayList<TbccPrjType>();
				TbccPrjType p = null ;
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//�Ѹû����µ�����С������Ŀ���뵽����
				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
					if(tbccPrjType.getProjectType().equals(ProjectBiz.BOX)){
						
						p = new TbccPrjType();
						
						p.setProjectId(tbccPrjType.getProjectId()) ;
						p.setProjectName(tbccPrjType.getProjectName());
						
						BoxprjList.add(p);
					}
						
				}
				return BoxprjList ;
			}
		}
		return null;
	}

	/**
	 *  ���ݼ�¼��ȡ���ֵ����Сֵ��ƽ��ֵ
	 */
	public Object[] getCalcValue(List<TbccBaseHisBox> boxList) {
		return calc.calcHisBoxData(boxList);
	}

}
