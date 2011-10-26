package org.fdap.util;



/**
 * Fdapaiinfo entity.
 * 
 * @author zhaoyou
 */

public class FdaprefAi implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aiguid;
	private Integer reid;
	private String name;
	private Integer selftype;
	private long refId;

	// Constructors

	/** default constructor */
	public FdaprefAi() {
	}

	public FdaprefAi(Integer reid, String name, Integer selftype,long refId) {
		this.reid = reid;
		this.name = name;
		this.selftype = selftype;
		this.refId = refId;
	}

	public String getAiguid() {
		return this.aiguid;
	}

	public void setAiguid(String aiguid) {
		this.aiguid = aiguid;
	}

	public Integer getReid() {
		return this.reid;
	}

	public void setReid(Integer reid) {
		this.reid = reid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSelftype() {
		return this.selftype;
	}

	public void setSelftype(Integer selftype) {
		this.selftype = selftype;
	}

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}
	
}