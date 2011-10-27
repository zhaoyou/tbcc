package org.tbcc.entity;

/**
 * TbccTransactionRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccTransactionRoles implements java.io.Serializable {


	private String roleName;
	private String roleId;

	/** default constructor */
	public TbccTransactionRoles() {
	}
	
	
	
	public TbccTransactionRoles(String roleName, String roleId) {
		super();
		this.roleName = roleName;
		this.roleId = roleId;
	}




	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
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
		if (!(other instanceof TbccTransactionRoles))
			return false;
		TbccTransactionRoles castOther = (TbccTransactionRoles) other;
		if(this.getRoleId().equals(castOther.getRoleId()))
				return true ;
		return false ;
	}

	public int hashCode() {
		return this.getRoleId().hashCode() ;
	}

}