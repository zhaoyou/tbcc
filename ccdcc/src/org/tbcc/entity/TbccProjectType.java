package org.tbcc.entity;

/**
 * TbccProjectType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccProjectType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;

	// Constructors

	/** default constructor */
	public TbccProjectType() {
	}

	/** full constructor */
	public TbccProjectType(String type) {
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}