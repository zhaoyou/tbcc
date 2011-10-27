package org.tbcc.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbccPrjType entity.
 * 
 * @author zhaoyou
 */

public class TbccPrjType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private String projectId;
	private Integer projectType;
	private String projectName;
	private String projectCode;
	private String projectRelInfo;
	private String projectNote;
	
	private String projectAuthCode ;
	
	
	private Set<TbccBranchType> branchTypes = new HashSet<TbccBranchType>();

	public Set<TbccBranchType> getBranchTypes() {
		return branchTypes;
	}


	public void setBranchTypes(Set<TbccBranchType> branchTypes) {
		this.branchTypes = branchTypes;
	}


	/** default constructor */
	public TbccPrjType() {
	}

	
	public TbccPrjType(String projectId, Integer projectType,
			String projectName, String projectCode, String projectRelInfo,
			String projectNote) {
		super();
		this.projectId = projectId;
		this.projectType = projectType;
		this.projectName = projectName;
		this.projectCode = projectCode;
		this.projectRelInfo = projectRelInfo;
		this.projectNote = projectNote;
	}


	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public Integer getProjectType() {
		return this.projectType;
	}

	
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return this.projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectRelInfo() {
		return this.projectRelInfo;
	}

	public void setProjectRelInfo(String projectRelInfo) {
		this.projectRelInfo = projectRelInfo;
	}

	public String getProjectNote() {
		return this.projectNote;
	}

	public void setProjectNote(String projectNote) {
		this.projectNote = projectNote;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccPrjType))
			return false;
		TbccPrjType castOther = (TbccPrjType) other;
		if(castOther.getProjectId().equals(this.getProjectId()))
			return true ;
		
		return false ;
		
	}

	public int hashCode() {
		return this.getProjectId().hashCode() ;
	}


	public String getProjectAuthCode() {
		return projectAuthCode;
	}


	public void setProjectAuthCode(String projectAuthCode) {
		this.projectAuthCode = projectAuthCode;
	}

}