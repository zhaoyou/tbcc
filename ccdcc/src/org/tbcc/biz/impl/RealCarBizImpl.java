package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RealCarBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.dao.RealCarDao;
import org.tbcc.entity.TbccBaseRealCar;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccClient;
import org.tbcc.entity.TbccPrjType;

/**
 * ���ǲ����ƶ�����ʵʱ���ݵ�ҵ��ʵ����
 * @author Administrator
 *
 */
public class RealCarBizImpl implements RealCarBiz {
	
	/**
	 * ��springע�� ����������
	 */
	private BranchDao branchDao = null ;
	
	/**
	 *  ��springע�� ʵʱ���ز�����
	 */
	private RealCarDao realCarDao = null ;
	
	
	/**
	 * ��springע�� ����ҵ������ӿ�
	 */
	
	private ProjectBiz projectBiz = null ;
	
	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}


	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}


	public void setRealCarDao(RealCarDao realCarDao) {
		this.realCarDao = realCarDao;
	}
	
	

	
	
	public List<TbccBaseRealCar> getRealData(Long branchId) {
		
		if(branchId==null || branchId.equals(""))
			return null ;
		
		List<String> carIds = this.projectBiz.getCarProjectIds(branchId) ;
		
		if(carIds==null || carIds.size()==0)
			return null ;
		
		StringBuffer sb = new StringBuffer("(");
		
		for(int i =0 ;i<carIds.size();i++){
			sb.append("'"+carIds.get(i)+"'") ;
			if(i!=carIds.size()-1)
				sb.append(",") ;
		}
		
		sb.append(")") ;
		
		return this.realCarDao.getRealCars(sb.toString());
		
//		TbccBranchType branchType = this.branchDao.get(branchId);
//		
//		
//		if(branchType!=null){
//			
//			String condition = "(" ;     //�����ѯ���ַ���
//			
//			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
//				//�Ѹû����µ������ƶ����ؼ��뵽����
//				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
//					if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR))
//						condition += "'"+tbccPrjType.getProjectId()+"'," ;
//				}
//				
//				condition = condition.substring(0, condition.length()-1) ;  //ȥ�����һ��,
//				condition +=")" ;											//�������һ������)
//				
//				//��ȡ���еĳ�������
//				
//				List<TbccBaseRealCar> realCarList = this.realCarDao.getRealCars(condition);
//					
//				return realCarList ;	
//			}
//		}
//		return null;
	}


	public TbccBaseRealCar getDataToMap(String projectId) {
		if(projectId==null || projectId.equals(""))
				return null ;
		
		List<TbccBaseRealCar> list = realCarDao.getRealCar(projectId) ;
		
		if (list ==null || list.size()==0){
			return null  ;
		}
		
		return list.get(0) ;
	}
	
}
