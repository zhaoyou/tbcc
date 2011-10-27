package org.tbcc.entity.cool;

/**
 * TbccMultiCompressorRealData entity.并联机组实时数据
 * 
 * @author administrator
 */

public class TbccMultiCompressorRealData implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer csId ;
	private Integer suctionState;
	private Integer lowliquidState;
	private Integer outageState;
	private Integer suctionAlarm;
	private Integer lowliquidAlarm;
	private Integer outageAlarm;

	// Constructors

	/** default constructor */
	public TbccMultiCompressorRealData() {
	}

	/** full constructor */
	public TbccMultiCompressorRealData(Integer csId,
			Integer suctionState, Integer lowliquidState, Integer outageState,
			Integer suctionAlarm, Integer lowliquidAlarm, Integer outageAlarm) {
		this.csId = csId;
		this.suctionState = suctionState;
		this.lowliquidState = lowliquidState;
		this.outageState = outageState;
		this.suctionAlarm = suctionAlarm;
		this.lowliquidAlarm = lowliquidAlarm;
		this.outageAlarm = outageAlarm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSuctionState() {
		return this.suctionState;
	}

	public void setSuctionState(Integer suctionState) {
		this.suctionState = suctionState;
	}

	public Integer getLowliquidState() {
		return this.lowliquidState;
	}

	public void setLowliquidState(Integer lowliquidState) {
		this.lowliquidState = lowliquidState;
	}

	public Integer getOutageState() {
		return this.outageState;
	}

	public void setOutageState(Integer outageState) {
		this.outageState = outageState;
	}

	public Integer getSuctionAlarm() {
		return this.suctionAlarm;
	}

	public void setSuctionAlarm(Integer suctionAlarm) {
		this.suctionAlarm = suctionAlarm;
	}

	public Integer getLowliquidAlarm() {
		return this.lowliquidAlarm;
	}

	public void setLowliquidAlarm(Integer lowliquidAlarm) {
		this.lowliquidAlarm = lowliquidAlarm;
	}

	public Integer getOutageAlarm() {
		return this.outageAlarm;
	}

	public void setOutageAlarm(Integer outageAlarm) {
		this.outageAlarm = outageAlarm;
	}

	public Integer getCsId() {
		return csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	
	
	
	
}