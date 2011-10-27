package org.tbcc.entity.cool;

import java.util.HashSet;
import java.util.Set;

/**
 * TbccCcapDevType entity.制冷设备实体
 * 
 * @author administrator
 */

public class TbccCcapDevType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String projectId;
	private Short csNetId;
	private Short devType;


	// Constructors

	/** default constructor */
	public TbccCcapDevType() {
	}

	/** minimal constructor */
	public TbccCcapDevType(String projectId, Short csNetId, Short devType) {
		this.projectId = projectId;
		this.csNetId = csNetId;
		this.devType = devType;
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Short getCsNetId() {
		return this.csNetId;
	}

	public void setCsNetId(Short csNetId) {
		this.csNetId = csNetId;
	}

	public Short getDevType() {
		return this.devType;
	}

	public void setDevType(Short devType) {
		this.devType = devType;
	}

}