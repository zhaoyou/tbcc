package org.fdap.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.fdap.entity.FdapRefHisData;
import org.fdap.entity.Fdapaiinfo;

/**
 * 仓库冷库历史数据访问接口
 * @author zhoukuanwei
 *
 */
public interface RefHisDao {
	
	/**
	 * 根据时间和仓库冷库Id，获取所有仓库冷库历史数据
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<FdapRefHisData> queryRefHisAll(String tableName,String startTime,String endTime,String refId);
	
	/**
	 * 根据时间和仓库冷库Id，获得仓库冷库历史数据的总条数
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<BigInteger> queryRefHisCount(String tableName,String startTime,String endTime,String refId);
	
	/**
	 * 根据仓库冷库Id，获取该冷库下的探头列表
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<Fdapaiinfo> queryAiByRefId(String refId);
	
	/**
	 * 根据时间和仓库冷库Id，获取仓库冷库历史数据
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<FdapRefHisData> queryRefHisData(String tableName,String startTime,String endTime,String refId);

	/**
	 * 根据时间和仓库冷库Id，分页获取从小到大的时间刻
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param startrow         	查询历史数据开始位置
	 * @param pagesize			查询历史数据每页的条数
	 * @param refId				冷库Id
	 * @return
	 */
	public List<Date> queryTimeScope(String tableName, String startTime,String endTime, Integer startrow,Integer pagesize,String refId);

	/**
	 * 根据时间和仓库冷库Id，分页获取从小到大的时间刻
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param refId				冷库Id
	 * @return
	 */
	public List<Date> queryTimeScope_All(String tableName, String startTime,String endTime,String refId);

}
