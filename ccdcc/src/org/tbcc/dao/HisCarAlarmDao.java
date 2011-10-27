package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisCarAlarm;

public interface HisCarAlarmDao {
	/**
	 * 根据起停记录标示Id，时间范围、时间间隔。获取车载历史报警数据
	 * @param tableName		车载历史数据表
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param sid			起停记录标示
	 * @return
	 */
	public List<TbccBaseHisCar> getHisCarAlarmBySidAndTime(String tableName,String startTime,String endTime,Long sid) ;
	
	/**
	 * 根据起停记录标示Id，开始时间，工程编号。获取车载历史报警处理信息
	 * @param projectId		工程编号
	 * @param startTime		开始时间
	 * @param sid			起停记录标示
	 * @return
	 */
	public List<TbccBaseHisCarAlarm> getHisAlarm(String projectId,Long sid);
	
	/**
	 * 插入历史报警处理信息
	 * @param caralarm		历史报警处理信息
	 * @return
	 */
	public boolean insertHisAlarm(TbccBaseHisCarAlarm caralarm);
	
	/**
	 * 更新历史报警处理信息
	 * @param id		历史报警处理信息标识ID
	 * @param cause		历史报警处理信息的报警原因
	 * @param measure	历史报警处理信息的处理措施
	 * @param dutier	历史报警处理信息的负责人
	 * @param remark	历史报警处理信息的备注
	 * @return
	 */
	public boolean updateHisAlarm(Long id,String cause,String measure,String dutier,String remark);
}
