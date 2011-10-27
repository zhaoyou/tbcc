package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.dao.HisCarDao;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BuildTable;
import org.tbcc.util.Calc;
import org.tbcc.util.MyUtil;
import org.tbcc.util.ObjectToHistory;

/**
 * ���ǲ����ƶ�������ʷ���ݵ�ʵ����
 * @author Administrator
 *
 */
public class HisCarBizImpl implements HisCarBiz {

	private HisCarDao hiscarDao = null ;
	
	private ObjectToHistory objToHis = null ;
	
	private BranchDao branchDao = null ;
	
	private Calc calc = null ;
	
	private ProjectBiz projectBiz = null ;
	
	/**
	 * ��springע�빤�̲�������
	 * @param projectBiz
	 */
	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}

	/**
	 * ��springע������ͳ������ֵ��
	 * @param calc
	 */
	public void setCalc(Calc calc) {
		this.calc = calc;
	}

	/**
	 * ��springע���֧�������ݷ�����
	 * @param branchDao
	 */
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	/**
	 * ��springע������ת����
	 * @param objToHis
	 */
	public void setObjToHis(ObjectToHistory objToHis) {
		this.objToHis = objToHis;
	}

	/**
	 *  ��springע���ƶ�������ʷ���ݵ����ݷ�����
	 * @param hiscarDao
	 */
	public void setHiscarDao(HisCarDao hiscarDao) {
		this.hiscarDao = hiscarDao;
	}

	
	/**
	 *  ���ݲ�ѯʱ��Σ�������ʷ����
	 */
	public List<TbccBaseHisCar> getHisCarByProperty(String proId,String startTime,String endTime,String interval,String value) {
		
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
		
		
		/**
		 * ������ԭ��ʵ�ֵķ�ʽ
		 */
		//��ȡ���ֵ�ͼ��
		int seconds = Integer.parseInt(interval)*Integer.parseInt(value);
		
		//����Ҳ�ж�һ������ͣʱ����
		startTime =MyUtil.getValid(startTime);
		
		List list = hiscarDao.getHisCarData(BuildTable.tohisCarTable(proId), startTime, endTime,seconds);
				
		List<TbccBaseHisCar> carList = MyUtil.distinctData(objToHis.objectToCar(list));
			
		return carList;
	}

	
	/**
	 * ���ݻ���Id����ȡ�û����µ������ƶ�������Ŀ
	 */
	public List<TbccPrjType> getCarPrjList(Long branchId) {
		
		if(branchId==null || branchId.equals(""))
			return null ;
		
		return this.projectBiz.getCarProjListBybId(branchId) ;
		
		
//		TbccBranchType branchType = this.branchDao.get(branchId);
//		
//		if(branchType!=null){
//				List<TbccPrjType> carprjList = new ArrayList<TbccPrjType>();
//			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
//				//�Ѹû����µ������ƶ����ؼ��뵽����
//				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
//					if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR))
//						carprjList.add(tbccPrjType);
//				}
//				return carprjList ;
//			}
//		}
//		return null;
	}
	
	
	/**
	 * ���ݼ�������ͳ����Сֵ�����ֵ��ƽ��ֵ
	 */
	public Object[] getCalcValue(List<TbccBaseHisCar> carList) {
		return calc.calcHisCarData(carList);
	}
	
	/**
	 * ���Ҳ��Ϊ�˽���ӳټ��صģ����ݻ���Id��ȡ���е��ƶ�����
	 */
	public List<TbccPrjType> getCarPrjListProxy(Long branchId) {
       TbccBranchType branchType = this.branchDao.get(branchId);
		
		if(branchType!=null){
				List<TbccPrjType> carprjList = new ArrayList<TbccPrjType>();
				TbccPrjType p = null ;
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//�Ѹû����µ������ƶ����ؼ��뵽����
				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
					if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR)){
							p = new TbccPrjType();
							p.setProjectId(tbccPrjType.getProjectId());
							p.setProjectName(tbccPrjType.getProjectName());
							carprjList.add(p);
					}
					
				}
				return carprjList ;
			}
		}
		return null;
	}
	
	public List<TbccBaseHisCar> getByParentId(String projectId, Long parentId) {
		if(projectId ==null || projectId.equals("") || parentId==null || parentId.equals("")){
			return null ;
		}
		//��ȡ������ʷ�켣�ط�����
		return this.hiscarDao.getHisCarByStartUp(BuildTable.tohisCarTable(projectId), parentId);
	}

	public List<TbccBaseHisCar> getByParentIdAndId(String projectId,
			Long parentId, Integer id) {
		return this.hiscarDao.getHisCarByStartUpAndId(BuildTable.tohisCarTable(projectId), parentId, id);
	}

	
	public List<TbccBaseHisCar> getHisCarBySidAndTime(String proId,
			String startTime, String endTime, String type, Integer value,
			Long sid) {	
		
			if(proId==null || proId.equals("")|| startTime==null || startTime.equals("")||endTime==null||
					endTime.equals("")|| type==null || type.equals("") || value==null || value.equals("")){
				return null ;
			}
			
		
		//��ȡ���ֵ�ͼ��
		int seconds = Integer.parseInt(type)*value;
		
		//����Ҳ�ж�һ������ͣʱ����
		startTime =MyUtil.getValid(startTime);
		
		//List list = hiscarDao.getHisCarData(BuildTable.tohisCarTable(proId), startTime, endTime,seconds);
		
		List<TbccBaseHisCar> list = 
			hiscarDao.getHisCarDataBySidAndTime(BuildTable.tohisCarTable(proId), startTime, endTime, seconds, sid) ;
		
		List<TbccBaseHisCar> carList = MyUtil.distinctData(list);
		
		return carList;
	}

}
