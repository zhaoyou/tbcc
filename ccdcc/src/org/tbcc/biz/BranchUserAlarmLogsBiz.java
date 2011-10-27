package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBranchUserAlarmLogs;

/**
 * 分支用户登录报警日志业务接口
 * @author zhaoyou
 *
 */
public interface BranchUserAlarmLogsBiz {
	
	/**
	 * 添加日志
	 * @param logs
	 */
	public void addLogs(TbccBranchUserAlarmLogs logs) ;
	
	/**
	 * 更新日志
	 * @param logs
	 */
	public void updateLogs(TbccBranchUserAlarmLogs logs) ;
	
	/**
	 * 获取整个分支下，仓库的报警状态
	 * @param branchId	分支标识Id
	 * @return
	 */
	public Integer getStoreSysAlarmState(Long branchId) ;
	
	/**
	 * 获取符合条件的日志的总条数
	 * @param clientName		账户名
	 * @param userName			用户名
	 * @return
	 */
	public Integer 	getAllLogsNumber(String clientName ,String userName) ;
	
	/**
	 * 分页获取某个用户的日志信息
	 * @param clientName		账户名
	 * @param userName			用户名
	 * @param index				当前页数
	 * @param number			每页的条数
	 * @return
	 */
	public List<TbccBranchUserAlarmLogs> getLogs(String clientName,String userName ,Integer index ,Integer number) ;
}
