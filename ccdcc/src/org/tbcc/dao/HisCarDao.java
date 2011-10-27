package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCar;

/**
 * 这是一个操作移动车载历史数据的接口
 * @author Administrator
 *
 */
public interface HisCarDao {
	/**
	 * 根据条件查询移动车载的历史数据
	 * @param tableName				车载对应的历史数据表
	 * @param startTime				开始时间
	 * @param endTime				结束时间
	 * @param value					车载历史数据集合
	 * @return
	 */
	public  List getHisCarData(String tableName,String startTime,String endTime,int value);  

	/**
	 * 根据起停记录标示Id，时间范围、时间间隔。获取车载历史数据
	 * @param tableName		车载历史数据表
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param value			时间间隔
	 * @param sid			起停记录标示
	 * @return
	 */
	public List<TbccBaseHisCar> getHisCarDataBySidAndTime(String tableName,String startTime,String endTime,int value,Long sid) ;
	
	
	/**
	 * 根据历史启停记录Id，和历史数据表名获取历史数据
	 * @param tableName		表名
	 * @param parentId		启停记录的标识id
	 * @return				车载历史数据集合
	 */
	public List<TbccBaseHisCar> getHisCarByStartUp(String tableName,Long parentId);
	
	/**
	 * 根据启停记录id、项目工程、以及当前车载历史数据记录编号获取数据
	 * @param tableName		表名
	 * @param parentId		启停记录的标识Id
	 * @param id			历史记录编号
	 * @return				车载历史数据集合
	 */
	public List<TbccBaseHisCar> getHisCarByStartUpAndId(String tableName,Long parentId,Integer id) ;

}
