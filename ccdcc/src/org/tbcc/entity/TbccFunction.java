package org.tbcc.entity;

/**
 * TbccFunction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccFunction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer projId;
	private String name;

	// Constructors

	/** default constructor */
	public TbccFunction() {
	}

	/** full constructor */
	public TbccFunction(Integer projId, String name) {
		this.projId = projId;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjId() {
		return this.projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}