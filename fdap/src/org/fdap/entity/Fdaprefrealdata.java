package org.fdap.entity;

import java.util.Date;

/**
 * Fdaprefrealdata entity.
 * 
 * @author zhaoyou
 */

public class Fdaprefrealdata implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long realrefid;
	private Fdapaiinfo fdapaiinfo;
	private Double data;
	private Date time;
	private Integer isalarm;

	// Constructors

	/** default constructor */
	public Fdaprefrealdata() {
	}

	/** minimal constructor */
	public Fdaprefrealdata(Double data, Date time, Integer isalarm) {
		this.data = data;
		this.time = time;
		this.isalarm = isalarm;
	}

	/** full constructor */
	public Fdaprefrealdata(Fdapaiinfo fdapaiinfo, Double data, Date time,
			Integer isalarm) {
		this.fdapaiinfo = fdapaiinfo;
		this.data = data;
		this.time = time;
		this.isalarm = isalarm;
	}

	// Property accessors

	public Long getRealrefid() {
		return this.realrefid;
	}

	public void setRealrefid(Long realrefid) {
		this.realrefid = realrefid;
	}

	public Fdapaiinfo getFdapaiinfo() {
		return this.fdapaiinfo;
	}

	public void setFdapaiinfo(Fdapaiinfo fdapaiinfo) {
		this.fdapaiinfo = fdapaiinfo;
	}

	public Double getData() {
		return this.data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getIsalarm() {
		return this.isalarm;
	}

	public void setIsalarm(Integer isalarm) {
		this.isalarm = isalarm;
	}

}