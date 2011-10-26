package org.fdap.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * entity 历史报警
 * @author zhaoyou
 *
 */
public class FdapHisAlarm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long hisAlarmId ;
	private Fdapaiinfo fdapaiinfo;
	private Double alarmData ;
	private Date	startTime ;
	private Date	endTime ;
	private Integer alarmLevel ;
	private String alarmType ;
	private Double alarmStandard  ;
	private Integer flag ;
	
	
	private Long lastTime;
	
	
	public FdapHisAlarm(Long hisAlarmId, Fdapaiinfo fdapaiinfo, Double alarmData,
			Date startTime, Date endTime, Integer alarmLevel, String alarmType,
			Double alarmStandard, Integer flag) {
		super();
		this.hisAlarmId = hisAlarmId;
		this.fdapaiinfo = fdapaiinfo;
		this.alarmData = alarmData;
		this.startTime = startTime;
		this.endTime = endTime;
		this.alarmLevel = alarmLevel;
		this.alarmType = alarmType;
		this.alarmStandard = alarmStandard;
		this.flag = flag;
	}
	
	
	
	public FdapHisAlarm() {
		super();
	}



	public Long getHisAlarmId() {
		return hisAlarmId;
	}
	public void setHisAlarmId(Long hisAlarmId) {
		this.hisAlarmId = hisAlarmId;
	}

	
	public Fdapaiinfo getFdapaiinfo() {
		return fdapaiinfo;
	}

	public void setFdapaiinfo(Fdapaiinfo fdapaiinfo) {
		this.fdapaiinfo = fdapaiinfo;
	}

	public Double getAlarmData() {
		return alarmData;
	}
	public void setAlarmData(Double alarmData) {
		this.alarmData = alarmData;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public Double getAlarmStandard() {
		return alarmStandard;
	}
	public void setAlarmStandard(Double alarmStandard) {
		this.alarmStandard = alarmStandard;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public Long getLastTime() {
		lastTime=(endTime.getTime()-startTime.getTime())/1000;
		return lastTime;
	}
	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}
	
}
