package org.tbcc.entity;

/**
 * TbccNumberType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccNumberType implements java.io.Serializable {

	// Fields



	private Long id;
	private String projectId;
	private String alarmNum;
	private Integer numberType;
	private Integer telType;
	
	// Constructors

	/** default constructor */
	public TbccNumberType() {
	}


	public TbccNumberType(Integer numberType, Integer telType, Long id,
			String projectId, String alarmNum) {
		super();
		this.numberType = numberType;
		this.telType = telType;
		this.id = id;
		this.projectId = projectId;
		this.alarmNum = alarmNum;
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

	public String getAlarmNum() {
		return this.alarmNum;
	}

	public void setAlarmNum(String alarmNum) {
		this.alarmNum = alarmNum;
	}

	public Integer getNumberType() {
		return this.numberType;
	}

	public void setNumberType(Integer numberType) {
		this.numberType = numberType;
	}

	public Integer getTelType() {
		return this.telType;
	}

	public void setTelType(Integer telType) {
		this.telType = telType;
	}
	
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccNumberType))
			return false;
		TbccNumberType castOther = (TbccNumberType) other;
		if(this.getId().equals(castOther.getId()))
				return true ;
		return false ;

	}
	
	public int hashCode() {
		return this.getId().intValue() ;
	}

}