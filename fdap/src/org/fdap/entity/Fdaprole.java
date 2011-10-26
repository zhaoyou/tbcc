package org.fdap.entity;


/**
 * Fdaprole entity.
 * 
 * @author zhaoyou
 */

public class Fdaprole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rid;
	private String name;
	private String remark;

	// Constructors

	/** default constructor */
	public Fdaprole() {
	}

	/** minimal constructor */
	public Fdaprole(String name) {
		this.name = name;
	}

	/** full constructor */
	public Fdaprole(String name, String remark) {
		this.name = name;
		this.remark = remark;
	
	}

	// Property accessors

	public Long getRid() {
		return this.rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
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