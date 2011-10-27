package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCarAlarm;

public interface HisCarAlarmBiz {
	/**
	 * 根据工程标识，时间范围，起停记录Id获取车载历史报警数据
	 * @param proId			工程标示
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param sid			起停记录的标示
	 * @return
	 */
	public List<TbccBaseHisCarAlarm> insertAndGetHisCarAlarm(String proId,String startTime,String endTime ,Long sid) ;
	
	
	/**
	 * 根据工程标识，起停记录Id，获取车载历史报警数据
	 * @param proId			工程标示
	 * @param sid			起停记录的标示
	 * @return
	 */
	public List<TbccBaseHisCarAlarm> getHisCarAlarm(String proId,Long sid) ;
	
	
	/**
	 * 更新保存车载历史报警数据
	 * @param caralarmStr			需要更新修改的几个车载历史报警处理数据对象拼接的字符串
	 * @return
	 */
	public boolean updateHisCarAlarm(String caralarmStr) ;
}
