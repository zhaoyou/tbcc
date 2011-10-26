package org.fdap.entity;

public class FdapConfigInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String refName;
	private String aiName;
	private Long oid;
	private Integer reid;
	private String projectNo;
	private Integer type;
	
	public FdapConfigInfo(){super();}
	
	public FdapConfigInfo(String refName,String aiName,Integer reid,String projectNo,Long oid,Integer type){
		super();
		this.refName = refName;
		this.aiName = aiName;
		this.reid = reid;
		this.projectNo = projectNo;
		this.oid = oid;
		this.type = type;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getAiName() {
		return aiName;
	}

	public void setAiName(String aiName) {
		this.aiName = aiName;
	}

	public Integer getReid() {
		return reid;
	}

	public void setReid(Integer reid) {
		this.reid = reid;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
