package org.fdap.util;

/**
 * 自定义FdaprefData entity.
 * 
 * @author zhaoyou
 */

public class FdaprefData {

	private Long refId;
	private String name;
	private Integer floorType;
	private Integer floorNo;
	private Integer isactive;
	private String remark;


	// Constructors

	/** default constructor */
	public FdaprefData() {
	}

	/** minimal constructor */
	public FdaprefData(String name, Integer isactive) {
		this.name = name;
		this.isactive = isactive;
	}

	/** full constructor */
	public FdaprefData(String name, Integer floorType, Integer floorNo, Integer isactive,
			String remark) {
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