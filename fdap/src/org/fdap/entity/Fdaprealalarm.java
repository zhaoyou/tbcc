package org.fdap.entity;

import java.util.Date;

import org.fdap.util.DateUtil;

/**
 * Fdaprealalarm entity.
 * 
 * @author zhaoyou
 */

public class Fdaprealalarm implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long realalarmid;
	private Fdapaiinfo fdapaiinfo;
	private Double alarmdata;
	private Date time;
	private Integer isrealalarm;
	private Integer alarmlevel;
	private String alarmtype;
	private Double alarmstandard;
	
	private Long orgId ;
	
	//为了报警显示，探头所在冷库的名字
	private String refName ;
	
	//为了显示报警的日期格式
	@SuppressWarnings("unused")
	private String alarmDate ;
	
	private String aiName;
	
	public String getAlarmDate(){
		return DateUtil.getToString(this.getTime());
	}
	
	

	// Constructors

	public String getRefName() {
		return  refName ;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** default constructor */
	public Fdaprealalarm() {
	}

	/** minimal constructor */
	public Fdaprealalarm(Double alarmdata, Date time, Integer isrealalarm,
			Integer alarmlevel, Double alarmstandard) {
		this.alarmdata = alarmdata;
		this.time = time;
		this.isrealalarm = isrealalarm;
		this.alarmlevel = alarmlevel;
		this.alarmstandard = alarmstandard;
	}

	/** full constructor */
	public Fdaprealalarm(Fdapaiinfo fdapaiinfo, Double alarmdata, Date time,
			Integer isrealalarm, Integer alarmlevel, String alarmtype,
			Double alarmstandard) {
		this.fdapaiinfo = fdapaiinfo;
		this.alarmdata = alarmdata;
		this.time = time;
		this.isrealalarm = isrealalarm;
		this.alarmlevel = alarmlevel;
		this.alarmtype = alarmtype;
		this.alarmstandard = alarmstandard;
	}

	// Property accessors

	public Long getRealalarmid() {
		return this.realalarmid;
	}

	public void setRealalarmid(Long realalarmid) {
		this.realalarmid = realalarmid;
	}

	public Fdapaiinfo getFdapaiinfo() {
		return this.fdapaiinfo;
	}

	public void setFdapaiinfo(Fdapaiinfo fdapaiinfo) {
		this.fdapaiinfo = fdapaiinfo;
	}

	public Double getAlarmdata() {
		return this.alarmdata;
	}

	public void setAlarmdata(Double alarmdata) {
		this.alarmdata = alarmdata;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getIsrealalarm() {
		return this.isrealalarm;
	}

	public void setIsrealalarm(Integer isrealalarm) {
		this.isrealalarm = isrealalarm;
	}

	public Integer getAlarmlevel() {
		return this.alarmlevel;
	}

	public void setAlarmlevel(Integer alarmlevel) {
		this.alarmlevel = alarmlevel;
	}

	public String getAlarmtype() {
		return this.alarmtype;
	}

	public void setAlarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}

	public Double getAlarmstandard() {
		return this.alarmstandard;
	}

	public void setAlarmstandard(Double alarmstandard) {
		this.alarmstandard = alarmstandard;
	}


	public String getAiName() {
		return aiName;
	}

	public void setAiName(String aiName) {
		this.aiName = aiName;
	}
	
}