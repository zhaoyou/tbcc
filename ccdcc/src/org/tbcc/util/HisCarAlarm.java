package org.tbcc.util;

import java.util.Date;

public class HisCarAlarm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long parentId;
	private String projectId;
	private Date startTime;
	
	public HisCarAlarm(){super();}
	
	public HisCarAlarm(Long id,Long parentId,String projectId,Date startTime){
		super();
		this.id = id;
		this.parentId = parentId;
		this.projectId = projectId;
		this.startTime = startTime;
	}

	public Long getId() {
		return id;
	}

	public Long getParentId() {
		return parentId;
	}

	public String getProjectId() {
		return projectId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}
