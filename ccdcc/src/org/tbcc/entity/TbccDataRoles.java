package org.tbcc.entity;

/**
 * Êý¾Ý½ÇÉ« TbccDataRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccDataRoles implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Long branchId ;

	public TbccDataRoles(Integer roleId, String roleName,Long branchId) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.branchId  = branchId ;
	}



	/** default constructor */
	public TbccDataRoles() {
		super();
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccDataRoles))
			return false;
		TbccDataRoles castOther = (TbccDataRoles) other;
		if(castOther.getRoleId().equals(this.getRoleId()))
			return true ;
		return false ;
	}

	public int hashCode() {
		return this.getRoleId().hashCode() ;
	}



	public Long getBranchId() {
		return branchId;
	}



	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

}