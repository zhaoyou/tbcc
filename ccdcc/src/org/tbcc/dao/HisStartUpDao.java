package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * 这个事启停操作的数据接口
 * @author Administrator
 *
 */
public interface HisStartUpDao {
	/**
	 * 根据移动终端历史启停表、开始时间、结束时间，获取启停记录
	 * @param tableName		终端历史启停表
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @return
	 */
	public List getStartUpList(String tableName,String startTime,String endTime);
	
	/**
	 * 根据移动终端的历史启停表、和标识主键获取启停记录
	 * @param tableName		终端历史启停表
	 * @param id			启停记录的标识id
	 * @return
	 */
	public List getStartUp(String tableName,long id) ;
	
	
	/**
	 * 根据移动终端的表名，和起始时间获取起停记录 version 2
	 * @param tableName		移动终端表名
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpListByTime(String tableName,String startTime,String endTime) ;
	
	/**
	 * 根据移动终端的表名 ，和起停记录的标识Id获取起停记录
	 * @param tableName
	 * @param id
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpById(String tableName,long id) ;
	
	
	/**
	 * 根据开始时间获取启停记录标识ID(项目FDAP在地图上看查看历史轨迹时需要用到的)
	 * @param tableName			根据移动终端表名和
	 * @param beginTime			启停记录开始时间
	 * @return
	 */
	public List<Long> queryStartupidByBeginTime(String tableName,String beginTime);
}
