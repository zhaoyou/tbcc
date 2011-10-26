package org.fdap.entity;

import java.io.Serializable;
import java.util.Date;

import org.fdap.util.DateUtil;

/**
 * 车载历史起停记录
 * @author zhaoyou
 *
 */
public class FdapStartUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long startUpId ;
	private Long refId ;
	private Date startTime ;
	private Date endTime ;
	private String carrier ;
	private Integer intervalValue ;
	
	private String startTimestr;
	private String endTimestr;
	
	public FdapStartUp(Long startUpId, Long refId, Date startTime,
			Date endTime, String carrier, Integer intervalValue) {
		super();
		this.startUpId = startUpId;
		this.refId = refId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.carrier = carrier;
		this.intervalValue = intervalValue;
	}


	public FdapStartUp() {
		super();
	}


	public Long getStartUpId() {
		return startUpId;
	}


	public void setStartUpId(Long startUpId) {
		this.startUpId = startUpId;
	}


	


	public Long getRefId() {
		return refId;
	}


	public void setRefId(Long refId) {
		this.refId = refId;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getCarrier() {
		return carrier;
	}


	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	public Integer getIntervalValue() {
		return intervalValue;
	}


	public void setIntervalValue(Integer intervalValue) {
		this.intervalValue = intervalValue;
	}


	public String getStartTimestr() {
		return DateUtil.getToString(this.getStartTime());
	}


	public void setStartTimestr(String startTimestr) {
		this.startTimestr = startTimestr;
	}


	public String getEndTimestr() {
		return DateUtil.getToString(this.getEndTime());
	}


	public void setEndTimestr(String endTimestr) {
		this.endTimestr = endTimestr;
	}
	
	
}
