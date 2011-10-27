package org.tbcc.entity;

import java.io.Serializable;
import java.util.Date;

import org.tbcc.util.MyUtil;

/**
 * 分支用户登录记录报警状态实体类
 * @author zhaoyou
 *
 */
public class TbccBranchUserAlarmLogs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long logsId  ;
	private String clientName ;
	private String userName ;
	private String loginAddr ;
	private Date loginTime ;
	private Integer loginAlarmState ;
	private Date logoutTime ;
	private Integer logoutAlarmState ;
	
	
	
	public TbccBranchUserAlarmLogs() {
		super();
	}
	
	
	public TbccBranchUserAlarmLogs(Long logsId, String clientName,
			String userName, String loginAddr, Date loginTime,
			Integer loginAlarmState, Date logoutTime, Integer logoutAlarmState) {
		super();
		this.logsId = logsId;
		this.clientName = clientName;
		this.userName = userName;
		this.loginAddr = loginAddr;
		this.loginTime = loginTime;
		this.loginAlarmState = loginAlarmState;
		this.logoutTime = logoutTime;
		this.logoutAlarmState = logoutAlarmState;
	}
	
	public String getLoginTimeStr(){
		if(this.getLoginTime()==null)
			return "----" ;
		return MyUtil.getToString(this.getLoginTime()) ;
	}
	
	
	public String getLogoutTimeStr(){
		if(this.getLogoutTime()==null)
			return "----" ;
		return MyUtil.getToString(this.getLogoutTime());
	}
	
	public String getLoginAlarmStateStr(){
		Integer s = this.getLoginAlarmState() ;
		if(s==null || s.toString().equals("")){
			return "未知状态" ;
		}else if (s.toString().equals("1")){
			return "断开连接" ;
		}else if(s.toString().equals("2")){
			return "<font color='red'>报警</font>" ;
		}else{
			return "正常" ;
		}
	}
	
	public String getLogoutAlarmStateStr(){
		Integer s = this.getLogoutAlarmState() ;
		if(s==null || s.toString().equals("")){
			return "未知状态" ;
		}else if (s.toString().equals("1")){
			return "断开连接" ;
		}else if(s.toString().equals("2")){
			return "<font color='red'>报警</font>" ;
		}else{
			return "正常" ;
		}
	}
	public Long getLogsId() {
		return logsId;
	}
	public void setLogsId(Long logsId) {
		this.logsId = logsId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginAddr() {
		return loginAddr;
	}
	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getLoginAlarmState() {
		return loginAlarmState;
	}
	public void setLoginAlarmState(Integer loginAlarmState) {
		this.loginAlarmState = loginAlarmState;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Integer getLogoutAlarmState() {
		return logoutAlarmState;
	}
	public void setLogoutAlarmState(Integer logoutAlarmState) {
		this.logoutAlarmState = logoutAlarmState;
	}
	
	
	
	

}
