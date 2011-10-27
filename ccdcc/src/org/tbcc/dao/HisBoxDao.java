package org.tbcc.dao;

import java.util.List;

/**
 * 小批零的数据访问接口
 * @author Administrator
 *
 */
public interface HisBoxDao {
	/**
	 * 根据小批零的历史数据表、启停的开始时间、结束时间、以及查询间隔获取小批零历史数据
	 * @param tableName			小批零历史数据表
	 * @param startTime			启停记录的开始时间
	 * @param endTime			启停记录的结束时间
	 * @param value				查询的间隔
	 * @return
	 */
	public List getHisBoxData(String tableName ,String startTime,String endTime,int value ) ;
}
