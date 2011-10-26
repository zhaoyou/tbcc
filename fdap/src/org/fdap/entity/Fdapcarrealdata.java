package org.fdap.entity;

import java.util.Date;

/**
 * Fdapcarrealdata entity.
 * 
 * @author zhaoyou
 */

public class Fdapcarrealdata implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long refId ;
	private Long projectId ;
	private String name ;
	private Integer aid1;
	private Integer aid2;
	private Integer aid3;
	private Double t1;
	private Double t2;
	private Double t3;
	private Double latitude;
	private Integer latitudeDir;
	private Double longitude;
	private Integer longitudeDir;
	private Date time;
	private Integer isalarm;

	private String projectNO;
	
	
	

	// Property accessors


	public Fdapcarrealdata() {
		super();
	}

	public Fdapcarrealdata(Long refId, Long projectId, String name,
			Integer aid1, Integer aid2, Integer aid3, Double t1, Double t2,
			Double t3, Double latitude, Integer latitudeDir, Double longitude,
			Integer longitudeDir, Date time, Integer isalarm , String projectNO) {
		super();
		this.refId = refId;
		this.projectId = projectId;
		this.name = name;
		this.aid1 = aid1;
		this.aid2 = aid2;
		this.aid3 = aid3;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.latitude = latitude;
		this.latitudeDir = latitudeDir;
		this.longitude = longitude;
		this.longitudeDir = longitudeDir;
		this.time = time;
		this.isalarm = isalarm;
		this.projectNO = projectNO;
	}

	public Integer getAid1() {
		return this.aid1;
	}

	public void setAid1(Integer aid1) {
		this.aid1 = aid1;
	}

	public Integer getAid2() {
		return this.aid2;
	}

	public void setAid2(Integer aid2) {
		this.aid2 = aid2;
	}

	public Integer getAid3() {
		return this.aid3;
	}

	public void setAid3(Integer aid3) {
		this.aid3 = aid3;
	}

	public Double getT1() {
		return this.t1;
	}

	public void setT1(Double t1) {
		this.t1 = t1;
	}

	public Double getT2() {
		return this.t2;
	}

	public void setT2(Double t2) {
		this.t2 = t2;
	}

	public Double getT3() {
		return this.t3;
	}

	public void setT3(Double t3) {
		this.t3 = t3;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getLatitudeDir() {
		return this.latitudeDir;
	}

	public void setLatitudeDir(Integer latitudeDir) {
		this.latitudeDir = latitudeDir;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getLongitudeDir() {
		return this.longitudeDir;
	}

	public void setLongitudeDir(Integer longitudeDir) {
		this.longitudeDir = longitudeDir;
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

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectNO() {
		return projectNO;
	}

	public void setProjectNO(String projectNO) {
		this.projectNO = projectNO;
	}

}