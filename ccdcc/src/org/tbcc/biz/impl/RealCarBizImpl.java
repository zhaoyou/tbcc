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
 * 这是操作移动车载实时数据的业务实现类
 * @author Administrator
 *
 */
public class RealCarBizImpl implements RealCarBiz {
	
	/**
	 * 由spring注入 机构操作类
	 */
	private BranchDao branchDao = null ;
	
	/**
	 *  由spring注入 实时车载操作类
	 */
	private RealCarDao realCarDao = null ;
	
	
	/**
	 * 由spring注入 工程业务操作接口
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
//			String condition = "(" ;     //定义查询的字符串
//			
//			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
//				//把该机构下的所有移动车载加入到集合
//				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
//					if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR))
//						condition += "'"+tbccPrjType.getProjectId()+"'," ;
//				}
//				
//				condition = condition.substring(0, condition.length()-1) ;  //去掉最后一个,
//				condition +=")" ;											//加上最后一个括号)
//				
//				//获取所有的车载数据
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
