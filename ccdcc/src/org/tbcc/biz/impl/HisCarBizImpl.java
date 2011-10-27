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
 * 这是操作移动车载历史数据的实现类
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
	 * 由spring注入工程操作对象
	 * @param projectBiz
	 */
	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}

	/**
	 * 由spring注入用来统计数据值的
	 * @param calc
	 */
	public void setCalc(Calc calc) {
		this.calc = calc;
	}

	/**
	 * 由spring注入分支机构数据访问类
	 * @param branchDao
	 */
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	/**
	 * 由spring注入属性转化类
	 * @param objToHis
	 */
	public void setObjToHis(ObjectToHistory objToHis) {
		this.objToHis = objToHis;
	}

	/**
	 *  由spring注入移动车载历史数据的数据访问类
	 * @param hiscarDao
	 */
	public void setHiscarDao(HisCarDao hiscarDao) {
		this.hiscarDao = hiscarDao;
	}

	
	/**
	 *  根据查询时间段，返回历史数据
	 */
	public List<TbccBaseHisCar> getHisCarByProperty(String proId,String startTime,String endTime,String interval,String value) {
		
//		int seconds = 0 ,interval = Integer.parseInt(value);
//		
//		//判断时间的类型,算出两个时间的间隔秒数 1(hour) 2(minute) 3(seconds)
//		if(type.equals("1")){
//			seconds = interval * 3600 ;
//		}else if(type.equals("2")){
//			seconds = interval * 60 ;
//		}else{
//			seconds = interval ;
//		}
		
		
		/**
		 * 上面是原先实现的方式
		 */
		//获取间隔值和间隔
		int seconds = Integer.parseInt(interval)*Integer.parseInt(value);
		
		//这里也判断一下了启停时间了
		startTime =MyUtil.getValid(startTime);
		
		List list = hiscarDao.getHisCarData(BuildTable.tohisCarTable(proId), startTime, endTime,seconds);
				
		List<TbccBaseHisCar> carList = MyUtil.distinctData(objToHis.objectToCar(list));
			
		return carList;
	}

	
	/**
	 * 根据机构Id，获取该机构下的所有移动车载项目
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
//				//把该机构下的所有移动车载加入到集合
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
	 * 根据集合数据统计最小值、最大值、平均值
	 */
	public Object[] getCalcValue(List<TbccBaseHisCar> carList) {
		return calc.calcHisCarData(carList);
	}
	
	/**
	 * 这个也是为了解决延迟加载的，根据机构Id获取所有的移动车载
	 */
	public List<TbccPrjType> getCarPrjListProxy(Long branchId) {
       TbccBranchType branchType = this.branchDao.get(branchId);
		
		if(branchType!=null){
				List<TbccPrjType> carprjList = new ArrayList<TbccPrjType>();
				TbccPrjType p = null ;
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//把该机构下的所有移动车载加入到集合
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
		//获取车载历史轨迹回放数据
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
			
		
		//获取间隔值和间隔
		int seconds = Integer.parseInt(type)*value;
		
		//这里也判断一下了启停时间了
		startTime =MyUtil.getValid(startTime);
		
		//List list = hiscarDao.getHisCarData(BuildTable.tohisCarTable(proId), startTime, endTime,seconds);
		
		List<TbccBaseHisCar> list = 
			hiscarDao.getHisCarDataBySidAndTime(BuildTable.tohisCarTable(proId), startTime, endTime, seconds, sid) ;
		
		List<TbccBaseHisCar> carList = MyUtil.distinctData(list);
		
		return carList;
	}

}
