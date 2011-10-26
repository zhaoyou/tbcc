package org.fdap.entity;

/**
 * Fdapref entity.
 * 
 * @author zhaoyou
 */

public class Fdapref implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long refId;
	private Fdapproject fdapproject;
	private Fdapstoretype fdapstoretype;
	private String name;
	private Integer floorType;
	private Integer floorNo;
	private Integer isactive;
	private String remark;


	// Constructors

	/** default constructor */
	public Fdapref() {
	}

	/** minimal constructor */
	public Fdapref(String name, Integer isactive) {
		this.name = name;
		this.isactive = isactive;
	}

	/** full constructor */
	public Fdapref(Fdapproject fdapproject, Fdapstoretype fdapstoretype,
			String name, Integer floorType, Integer floorNo, Integer isactive,
			String remark) {
		this.fdapproject = fdapproject;
		this.fdapstoretype = fdapstoretype;
		this.name = name;
		this.floorType = floorType;
		this.floorNo = floorNo;
		this.isactive = isactive;
		this.remark = remark;
		
	}

	// Property accessors

	public Long getRefId() {
		return this.refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Fdapproject getFdapproject() {
		return this.fdapproject;
	}

	public void setFdapproject(Fdapproject fdapproject) {
		this.fdapproject = fdapproject;
	}

	public Fdapstoretype getFdapstoretype() {
		return this.fdapstoretype;
	}

	public void setFdapstoretype(Fdapstoretype fdapstoretype) {
		this.fdapstoretype = fdapstoretype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFloorType() {
		return this.floorType;
	}

	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
	}

	public Integer getFloorNo() {
		return this.floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public Integer getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}