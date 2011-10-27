package org.tbcc.entity.cool;

/**
 * TbccSingleCompressorRealData entity.冷凝机组实时数据
 * 
 * @author administrator
 */

public class TbccSingleCompressorRealData implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer csId ;
	
	private Integer outageState ;
	private Integer outageAlarm ;
	
	private Integer overloadState ;
	private Integer overloadAlarm ;
	
	private Integer troubleState ;
	private Integer troubleAlarm ;

	// Constructors

	public Integer getOutageState() {
		return outageState;
	}

	public void setOutageState(Integer outageState) {
		this.outageState = outageState;
	}

	public Integer getOutageAlarm() {
		return outageAlarm;
	}

	public void setOutageAlarm(Integer outageAlarm) {
		this.outageAlarm = outageAlarm;
	}

	/** default constructor */
	public TbccSingleCompressorRealData() {
	}

	/** full constructor */
	public TbccSingleCompressorRealData(Integer 
			csId){
		this.csId = csId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCsId() {
		return csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	public Integer getOverloadState() {
		return overloadState;
	}

	public void setOverloadState(Integer overloadState) {
		this.overloadState = overloadState;
	}

	public Integer getOverloadAlarm() {
		return overloadAlarm;
	}

	public void setOverloadAlarm(Integer overloadAlarm) {
		this.overloadAlarm = overloadAlarm;
	}

	public Integer getTroubleState() {
		return troubleState;
	}

	public void setTroubleState(Integer troubleState) {
		this.troubleState = troubleState;
	}

	public Integer getTroubleAlarm() {
		return troubleAlarm;
	}

	public void setTroubleAlarm(Integer troubleAlarm) {
		this.troubleAlarm = troubleAlarm;
	}


}