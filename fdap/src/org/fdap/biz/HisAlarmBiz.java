package org.fdap.biz;

import java.util.List;

import org.fdap.entity.FdapHisAlarm;
import org.fdap.util.AlarmStatisticsTip;


/**
 * 历史报警业务访问接口
 * @author zhoukuanwei
 *
 */
public interface HisAlarmBiz {
	
	/**
	 * 根据时间、探头类型和报警级别，分页查询历史报警数据
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param startrow         	查询历史报警开始位置
	 * @param pagesize			查询历史报警每页的条数
	 * @param oid	         	企业Id
	 * @param aiType			探头类型
	 * @param alarmLevel		报警级别
	 * @param projectType		存储类型（工程类型）
	 * @return
	 */
	public List<FdapHisAlarm> getHisAlarm(String tableName,String startTime,String endTime,Integer startrow,Integer pagesize,String oid,String aiType,String alarmLevel,String projectType);
	
	/**
	 * 根据时间、探头类型和报警级别,获取满足条件的历史报警数据的总条数
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param aiType			探头类型
	 * @param alarmLevel		报警级别
	 * @param oid	         	企业Id
	 * @param projectType		存储类型（工程类型）
	 * @return
	 */
	public Integer getHisAlarmCount(String tableName,String startTime,String endTime,String aiType,String alarmLevel,String oid,String projectType);
	
	/**
	 * 获取某企业的历史报警数据总条数
	 * @param oid         机构(或企业)Id
	 * @return
	 */
	public List<AlarmStatisticsTip> getAlarmCounts(String oid);
}
