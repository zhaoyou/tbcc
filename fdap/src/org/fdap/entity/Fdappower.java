package org.fdap.entity;



/**
 * Fdappower entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Fdappower implements java.io.Serializable {

	// Fields

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pid;
	private String name;
	private String remark;

	// Constructors

	/** default constructor */
	public Fdappower() {
	}

	/** minimal constructor */
	public Fdappower(String name) {
		this.name = name;
	}

	/** full constructor */
	public Fdappower(String name, String remark) {
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}