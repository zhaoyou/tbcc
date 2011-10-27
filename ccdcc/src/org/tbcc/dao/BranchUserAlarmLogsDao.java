package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchUserAlarmLogs;

/**
 * 分支用户登录报警信息数据接口
 * @author zhaoyou
 *
 */
public interface BranchUserAlarmLogsDao {
	
	/**
	 * 插入一条报警日志
	 * @param logs
	 */
	public void insertLogs(TbccBranchUserAlarmLogs logs) ;
	
	/**
	 * 更新报警日志信息
	 * @param logs
	 */
	public void updateLogs(TbccBranchUserAlarmLogs logs);
	
	/**
	 * 获取所有日志的总数
	 * @param clientName
	 * @param userName
	 * @return
	 */
	public Integer getAllNumber(String clientName ,String userName ) ;
	
	/**
	 * 每次获取日志的条数
	 * @param clientName		账户名
	 * @param userName			用户名
	 * @param index				当前页数
	 * @param number			每页的条数
	 * @return
	 */
	public List<TbccBranchUserAlarmLogs> getLogs(String clientName,String userName,Integer index ,Integer number) ;
	

}
