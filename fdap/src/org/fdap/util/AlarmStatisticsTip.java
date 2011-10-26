package org.fdap.util;

public class AlarmStatisticsTip {
	private Long  oid  ;
	
	private String orgName ;

	private String  msg  ;
	
	private String serious ;
	
	private String common ;
	
	private String light ;

	private String percent;
	
	public AlarmStatisticsTip(){}
	
	public AlarmStatisticsTip(Long oid,String orgName,String msg,String serious,String common,String light,String percent){
		super();
		this.oid = oid;
		this.orgName = orgName;
		this.msg = msg;
		this.serious = serious;
		this.common = common;
		this.light = light;
		this.percent = percent;
	}
	
	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSerious() {
		return serious;
	}

	public void setSerious(String serious) {
		this.serious = serious;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
	
}
