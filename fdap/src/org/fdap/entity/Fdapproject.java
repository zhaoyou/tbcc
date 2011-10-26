package org.fdap.entity;



/**
 * Fdapproject entity.
 * 
 * @author zhaoyou
 */

public class Fdapproject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long projectid;
	private Fdaporg fdaporg;
	private String name;
	private Integer type;
	private String remark;
	private String projectNO;

	// Constructors

	/** default constructor */
	public Fdapproject() {
	}

	public Fdapproject(Long projectid){
		this.projectid = projectid ;
	}
	
	/** minimal constructor */
	public Fdapproject(String name, Integer type) {
		this.name = name;
		this.type = type;
	}

	/** full constructor */
	public Fdapproject(Fdaporg fdaporg, String name, Integer type,
			String remark,String projectNO) {
		this.fdaporg = fdaporg;
		this.name = name;
		this.type = type;
		this.remark = remark;
		this.projectNO = projectNO;
	}

	// Property accessors

	public Long getProjectid() {
		return this.projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public Fdaporg getFdaporg() {
		return this.fdaporg;
	}

	public void setFdaporg(Fdaporg fdaporg) {
		this.fdaporg = fdaporg;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjectNO() {
		return projectNO;
	}

	public void setProjectNO(String projectNO) {
		this.projectNO = projectNO;
	}


}