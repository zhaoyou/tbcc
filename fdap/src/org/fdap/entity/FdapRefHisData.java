package org.fdap.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * entity 冷库探头历史数据
 * @author zhaoyou
 *
 */
public class FdapRefHisData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long refHisId ;
	private String aiGuid ;
	private Double data ;
	private Integer isAlarm ;
	private Date	time ;
	
	
	
	public FdapRefHisData() {
		super();
	}



	public FdapRefHisData(Long refHisId, String aiGuid, Double data,
			Integer isAlarm, Date time) {
		super();
		this.refHisId = refHisId;
		this.aiGuid = aiGuid;
		this.data = data;
		this.isAlarm = isAlarm;
		this.time = time;
	}



	public Long getRefHisId() {
		return refHisId;
	}



	public void setRefHisId(Long refHisId) {
		this.refHisId = refHisId;
	}



	


	public String getAiGuid() {
		return aiGuid;
	}



	public void setAiGuid(String aiGuid) {
		this.aiGuid = aiGuid;
	}



	public Double getData() {
		return data;
	}



	public void setData(Double data) {
		this.data = data;
	}



	public Integer getIsAlarm() {
		return isAlarm;
	}



	public void setIsAlarm(Integer isAlarm) {
		this.isAlarm = isAlarm;
	}



	public Date getTime() {
		return time;
	}



	public void setTime(Date time) {
		this.time = time;
	}
	
	

}
