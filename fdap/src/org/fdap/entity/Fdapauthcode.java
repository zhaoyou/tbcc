package org.fdap.entity;

/**
 * Fdapauthcode entity.
 * 
 * @author zhaoyou
 */

public class Fdapauthcode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer authid;
	private Fdaporg fdaporg;
	private String code;
	private String remark;

	// Constructors

	/** default constructor */
	public Fdapauthcode() {
	}

	/** minimal constructor */
	public Fdapauthcode(String code) {
		this.code = code;
	}

	/** full constructor */
	public Fdapauthcode(Fdaporg fdaporg, String code, String remark) {
		this.fdaporg = fdaporg;
		this.code = code;
		this.remark = remark;
	}

	// Property accessors

	public Integer getAuthid() {
		return this.authid;
	}

	public void setAuthid(Integer authid) {
		this.authid = authid;
	}

	public Fdaporg getFdaporg() {
		return this.fdaporg;
	}

	public void setFdaporg(Fdaporg fdaporg) {
		this.fdaporg = fdaporg;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}