package org.tbcc.entity;

/**
 * TbccRoleAndFunction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccRoleAndFunction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private Integer functionId;

	// Constructors

	/** default constructor */
	public TbccRoleAndFunction() {
	}

	/** full constructor */
	public TbccRoleAndFunction( Integer roleId,
			Integer functionId) {
		this.roleId = roleId;
		this.functionId = functionId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

}