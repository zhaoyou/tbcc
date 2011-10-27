package org.tbcc.entity;

/**
 * TbccRefInfo entity.
 * 
 * @author Adminstrator
 */

public class TbccRefInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String projectId;
	private Integer netid;
	private Integer refid;
	private Integer realRefid;
	private String refName;
	private Integer refType;
	private Integer floorType;
	private Integer floorNo;
	private Long devId ;


	// Constructors

	/** default constructor */
	public TbccRefInfo() {
	}

	
	
	public TbccRefInfo(Long id, String projectId,Long devId, Integer netid, Integer refid,
			Integer realRefid, String refName, Integer refType,
			Integer floorType, Integer floorNo) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.devId = devId ;
		this.netid = netid;
		this.refid = refid;
		this.realRefid = realRefid;
		this.refName = refName;
		this.refType = refType;
		this.floorType = floorType;
		this.floorNo = floorNo;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getNetid() {
		return this.netid;
	}

	public void setNetid(Integer netid) {
		this.netid = netid;
	}

	public Integer getRefid() {
		return this.refid;
	}

	public void setRefid(Integer refid) {
		this.refid = refid;
	}



	public Integer getRealRefid() {
		return this.realRefid;
	}

	public void setRealRefid(Integer realRefid) {
		this.realRefid = realRefid;
	}

	public String getRefName() {
		return this.refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public Integer getRefType() {
		return this.refType;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
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
	
	public Long getDevId() {
		return devId;
	}



	public void setDevId(Long devId) {
		this.devId = devId;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccRefInfo))
			return false;
		
		TbccRefInfo castOther = (TbccRefInfo) other;
		if(castOther.getId().equals(this.getId()))
			return true ;

		return false ;
	}

	public int hashCode() {
		return this.getId().intValue() ;
	}

}