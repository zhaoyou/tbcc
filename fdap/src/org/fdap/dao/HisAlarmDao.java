package org.fdap.dao;

import java.util.List;

import org.fdap.entity.FdapHisAlarm;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapref;

/**
 * 历史报警数据访问接口
 * @author zhoukuanwei
 *
 */
public interface HisAlarmDao {
	
	/**
	 * 根据企业标识Id，获取企业下所有探头信息列表
	 * @param oid         企业标识Id
	 * @return
	 */
	public List<Fdapaiinfo> queryAiinfoByOid(String oid);
	
	
	/**
	 * 根据企业标识Id，获取企业下所有探头信息列表
	 * @param oid         	企业标识Id
	 * @param projectType   工程类型
	 * @return
	 */
	public List<Fdapaiinfo> queryAiinfoByOidAndPType(String oid,String projectType);
	
	/**
	 * 根据企业标识Id，获取企业下所有冷库列表
	 * @param oid         企业标识Id
	 * @return
	 */
	public List<Fdapref> queryRefByOid(String oid);
	
	/**
	 * 根据时间，分页查询所有探头类型和所有报警级别的历史报警数据
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param startrow         	查询历史报警开始位置
	 * @param pagesize			查询历史报警每页的条数
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public List<FdapHisAlarm> queryHisAlarm(String tableName,String startTime,String endTime,Integer startrow,Integer pagesize,String[] aiIds);
	
	/**
	 * 根据时间和探头类型，分页查询所有报警级别的历史报警数据
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param startrow         	查询历史报警开始位置
	 * @param pagesize			查询历史报警每页的条数
	 * @param aiType			探头类型
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public List<FdapHisAlarm> queryHisAlarmByAiType(String tableName,String startTime,String endTime,Integer startrow,Integer pagesize,String aiType,String[] aiIds);

	/**
	 * 根据时间和报警级别，分页查询所有探头类型的历史报警数据
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param startrow         	查询历史报警开始位置
	 * @param pagesize			查询历史报警每页的条数
	 * @param alarmLevel		报警级别
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public List<FdapHisAlarm> queryHisAlarmByAlarmLevel(String tableName,String startTime,String endTime,Integer startrow,Integer pagesize,String alarmLevel,String[] aiIds);

	/**
	 * 根据时间、探头类型和报警级别，分页查询历史报警数据
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param startrow         	查询历史报警开始位置
	 * @param pagesize			查询历史报警每页的条数
	 * @param aiType			探头类型
	 * @param alarmLevel		报警级别
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public List<FdapHisAlarm> queryHisAlarmByAiTypeAndLevel(String tableName,String startTime,String endTime,Integer startrow,Integer pagesize,String aiType,String alarmLevel,String[] aiIds);

	/**
	 * 根据时间,获取所有探头类型和所有报警级别的历史报警数据的总条数
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public Integer queryHisAlarmCount(String tableName,String startTime,String endTime,String[] aiIds);
	
	/**
	 * 根据时间和探头类型,获取所有报警级别的历史报警数据的总条数
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param aiType			探头类型
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public Integer queryHisAlarmCountByAiType(String tableName,String startTime,String endTime,String aiType,String[] aiIds);
	
	/**
	 * 根据时间和报警级别,获取所有探头类型的历史报警数据的总条数
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param alarmLevel		报警级别
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public Integer queryHisAlarmCountByAlarmLevel(String tableName,String startTime,String endTime,String alarmLevel,String[] aiIds);
	
	/**
	 * 根据时间、探头类型和报警级别,获取历史报警数据的总条数
	 * @param tableName         历史报警数据表名
	 * @param startTime			查询历史报警的开始时间
	 * @param endTime         	查询历史报警的结束时间
	 * @param aiType			探头类型
	 * @param alarmLevel		报警级别
	 * @param aiIds				该企业下根据工程类型查询出的探头列表
	 * @return
	 */
	public Integer queryHisAlarmCountByAiTypeAndLevel(String tableName,String startTime,String endTime,String aiType,String alarmLevel,String[] aiIds);
	
	/**
	 * 获取某企业的历史报警数据总条数
	 * @param tableName         历史报警数据表名
	 * @return
	 */
	public Long queryAlarmCounts(String tableName);
	
	/**
	 * 获取某企业的历史报警数据总条数
	 * @param tableName         历史报警数据表名
	 * @param alarmLevel		报警的严重级别
	 * @return
	 */
	public Long queryAlarmCountsByLevel(String tableName,Integer alarmLevel);
	
}
