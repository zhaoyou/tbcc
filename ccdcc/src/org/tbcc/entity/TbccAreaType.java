package org.tbcc.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 分支类型表 TbccAreaType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccAreaType implements java.io.Serializable {




	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String areaId;	//区域Id
	private String areaName;	//区域名字
	
	
	//区域下的所有分支机构
	private Set<TbccBranchType> branchTypes = new HashSet<TbccBranchType>();
	
	public Set<TbccBranchType> getBranchTypes() {
		return branchTypes;
	}



	public void setBranchTypes(Set<TbccBranchType> branchTypes) {
		this.branchTypes = branchTypes;
	}



	/** default constructor */
	public TbccAreaType() {
		super();
	}

	
	
	public TbccAreaType(String areaName, String areaId) {
		super();
		this.areaName = areaName;
		this.areaId = areaId;
	}




	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccAreaType))
			return false;
		TbccAreaType castOther = (TbccAreaType) other;
		
		if(this.getAreaId().equals(castOther.getAreaId()))
			return true ;
		return false ;

	}

	public int hashCode() {
		 return this.getAreaId().hashCode();
	}

}