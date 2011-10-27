package org.tbcc.entity.config;

import java.io.Serializable;
import java.util.Date;



/**
 * 这是参数操作的实体
 * @author zhaoyou
 *
 */
public class TbccParamAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private String projectId ;
	private Byte funcType ;
	private Byte cmdType ;
	private Byte optStatus ;
	private Byte optFailReason ;
	private Date optTime ;
	
	
	public TbccParamAction(){
		super();
	}
	
	public TbccParamAction(Long id, String projectId, Byte funcType,
			Byte cmdType, Byte optStatus, Byte optFailReason, Date optTime) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.funcType = funcType;
		this.cmdType = cmdType;
		this.optStatus = optStatus;
		this.optFailReason = optFailReason ;
		this.optTime = optTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Byte getFuncType() {
		return funcType;
	}
	public void setFuncType(Byte funcType) {
		this.funcType = funcType;
	}
	public Byte getCmdType() {
		return cmdType;
	}
	public void setCmdType(Byte cmdType) {
		this.cmdType = cmdType;
	}
	public Byte getOptStatus() {
		return optStatus;
	}
	public void setOptStatus(Byte optStatus) {
		this.optStatus = optStatus;
	}
	public Date getOptTime() {
		return optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public Byte getOptFailReason() {
		return optFailReason;
	}

	public void setOptFailReason(Byte optFailReason) {
		this.optFailReason = optFailReason;
	}
	
}
