package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;

/**
 * 这是历史冷库的数据访问接口
 * @author Administrator
 *
 */
public interface HisRefDao {
	/**
	 * 	根据冷库历史数据表、开始时间、结束时间、以及时间间隔查询冷库历史数据
	 * @param tableName			冷库历史数据表名
	 * @param startTime			开始时间
	 * @param endTime			结束时间
	 * @param value				时间间隔
	 * @return
	 */
	public List getHisRefData(String tableName,String startTime,String endTime,int value);

	/**
	 * 根据冷库历史数据表、开始时间、结束时间、已经时间间隔查询冷库历史数据
	 * @param tableName		冷库历史数据表
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param value			时间间隔
	 * @return
	 */
	public List<TbccBaseHisRef> getHisDataByTime(String tableName,String startTime,String endTime,int value) ;
	
	/**
	 * 根据冷库扩展历史数据表、开始时间、结束时间、时间间隔查询冷库历史数据
	 * @param tableName		冷库扩展历史数据表
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param value			时间间隔值
	 * @return
	 */
	public List<TbccBaseHisRef_Ex> getExHisDataByTime(String tableName,String startTime,String endTime,int value);
}
