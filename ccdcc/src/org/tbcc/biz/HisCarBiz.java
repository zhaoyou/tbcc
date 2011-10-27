package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccHqType;
import org.tbcc.entity.TbccPrjType;

/**
 * 这是操作移动车载的业务接口
 * @author Administrator
 *
 */
public interface HisCarBiz {
	/**
	 * 代表南纬
	 */
	public static final int SOUTH = 0 ;
	/**
	 * 代表北纬
	 */
	public static final int NORTH = 1 ;
	/**
	 * 代表东经
	 */
	public static final int EAST = 0 ;
	/**
	 * 代表西经
	 */
	public static final int WEST =1 ;
	
	/**
	 * 代表车载处于预警状态
	 */
	public static final int PREALARM = 0 ;
	
	
	/**
	 * 代表移动车载处于报警状态
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * 代表移动车载处于正常状态
	 */
	public static final int NORMAL =2 ;
	
	
	public List<TbccBaseHisCar> getHisCarByProperty(String proId,String startTime,String endTime,String type,String value);
	
	public List<TbccPrjType> getCarPrjList(Long branchId);
	
	public List<TbccPrjType> getCarPrjListProxy(Long branchId);
	
	public Object[] getCalcValue(List<TbccBaseHisCar> carList) ;
	
	
	/**
	 * 根据工程标识，时间范围，起停记录Id，时间间隔获取车载历史数据
	 * @param proId			工程标示
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param type			间隔类型
	 * @param value			时间间隔值
	 * @param sid			起停记录的标示
	 * @return
	 */
	public List<TbccBaseHisCar> getHisCarBySidAndTime(String proId,String startTime,String endTime ,String type,Integer value,Long sid) ;
	
	/**
	 * 根据项目编号，以及历史启停记录的id，获取历史数据
	 * @param projectId			移动车载项目编号
	 * @param parentId			启停记录的编号
	 * @return					返回车载历史数据
	 */
	public List<TbccBaseHisCar> getByParentId(String projectId,Long parentId) ;
	
	/**
	 * 根据项目编号、已经启停记录的编号，当前的记录编号，获取后100条数据。暂时还没有使用
	 * @param projectId			移动车载项目编号
	 * @param parentId			启停记录的编号
	 * @param id				当前车载的记录编号
	 * @return					历史启停记录
	 */
	public List<TbccBaseHisCar> getByParentIdAndId(String projectId,Long parentId,Integer id);
}
