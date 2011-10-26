package org.fdap.entity;

/**
 * Fdapuser entity.
 * 
 * @author zhaoyou
 */

public class Fdapuser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long userid;
	private Fdaporg fdaporg;
	private Fdaprole fdaprole;
	private String name;
	private String password;
	private String remark;

	// Constructors

	/** default constructor */
	public Fdapuser() {
	}

	/** minimal constructor */
	public Fdapuser(String name) {
		this.name = name;
	}

	/** full constructor */
	public Fdapuser(Fdaporg fdaporg, Fdaprole fdaprole, String name,
			String password, String remark) {
		this.fdaporg = fdaporg;
		this.fdaprole = fdaprole;
		this.name = name;
		this.password = password;
		this.remark = remark;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Fdaporg getFdaporg() {
		return this.fdaporg;
	}

	public void setFdaporg(Fdaporg fdaporg) {
		this.fdaporg = fdaporg;
	}

	public Fdaprole getFdaprole() {
		return this.fdaprole;
	}

	public void setFdaprole(Fdaprole fdaprole) {
		this.fdaprole = fdaprole;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}