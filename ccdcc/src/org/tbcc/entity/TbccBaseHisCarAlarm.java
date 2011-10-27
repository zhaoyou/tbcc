package org.tbcc.entity;

import java.util.Date;

public class TbccBaseHisCarAlarm implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date startTime;
	private Long parentId ;
	private String projectId ;
	
	private String cause;
	private String measure;
	private String dutier;
	private String remark;
	
	
	private Date endTime;
	private String events; 
	private int tvalue;
	
	public TbccBaseHisCarAlarm(){super();}
	
	public TbccBaseHisCarAlarm(Long id,Date startTime,Long parentId,String projectId,String cause,
			String measure,String dutier,String remark,Date endTime,String events,int tvalue){
		super();
		this.id = id;
		this.startTime = startTime;
		this.parentId = parentId;
		this.projectId = projectId;
		this.cause = cause;
		this.measure = measure;
		this.dutier = dutier;
		this.remark = remark;
		this.endTime = endTime;
		this.events = events;
		this.tvalue = tvalue;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Long getParentId() {
		return parentId;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getCause() {
		return cause;
	}

	public String getMeasure() {
		return measure;
	}

	public String getDutier() {
		return dutier;
	}

	public String getRemark() {
		return remark;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public void setDutier(String dutier) {
		this.dutier = dutier;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public int getTvalue() {
		return tvalue;
	}

	public void setTvalue(int tvalue) {
		this.tvalue = tvalue;
	}
	
}
