package org.tbcc.entity;

/**
 * TbccUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccUser extends TbccBaseUser implements java.io.Serializable {


	
	//下面是关联对象属性

	private TbccDataRoles dataRoles ;

	

	/** default constructor */
	public TbccUser() {
		super();
	}

	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccUser))
			return false;
		TbccUser castOther = (TbccUser) other;
		if(castOther.getId().equals(this.getId()))
			return true ;
		return false ;
	}

	public int hashCode() {
		return this.getId().intValue() ;
	}


	public TbccDataRoles getDataRoles() {
		return dataRoles;
	}


	public void setDataRoles(TbccDataRoles dataRoles) {
		this.dataRoles = dataRoles;
	}
	
	
	
	
	public Integer getRoleId() {
		return this.getDataRoles().getRoleId();
	}
		
	public void setRoleId(Integer roleId) {
		this.dataRoles.setRoleId(roleId);
	}





}