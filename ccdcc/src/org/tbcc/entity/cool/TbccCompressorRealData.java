package org.tbcc.entity.cool;

/**
 * TbccCompressorRealData entity.压缩机实时数据实体
 * 
 * @author administrator
 */

public class TbccCompressorRealData implements java.io.Serializable {

	// Fields

	private Integer id;
	//修改了实体类、对应的是视图
	//private Integer cId ;
	private String name ;
	private Integer lowpresState;
	private Integer highpresState;
	private Double  exhaustValue;
	private Integer oilpresState;
	private Integer lowpresAlarm;
	private Integer highpresAlarm;
	private Integer exhaustAlarm;
	private Integer oilpresAlarm;
	
	
	private Integer overloadState ;
	private Integer overloadAlarm ;
	
	private Integer activeState ;
	

	// Constructors

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
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

	/** default constructor */
	public TbccCompressorRealData() {
	}

	/** full constructor */
	public TbccCompressorRealData(String name,
			Integer lowpresState, Integer highpresState, Double exhaustValue,
			 Integer oilpresState, Integer lowpresAlarm,
			Integer highpresAlarm, Integer exhaustAlarm, 
			Integer oilpresAlarm,Integer activeState ) {
		this.name = name;
		this.lowpresState = lowpresState;
		this.highpresState = highpresState;
		this.exhaustValue = exhaustValue;
		this.oilpresState = oilpresState;
		this.lowpresAlarm = lowpresAlarm;
		this.highpresAlarm = highpresAlarm;
		this.exhaustAlarm = exhaustAlarm;
		this.oilpresAlarm = oilpresAlarm;
		this.activeState = activeState ;

	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLowpresState() {
		return this.lowpresState;
	}

	public void setLowpresState(Integer lowpresState) {
		this.lowpresState = lowpresState;
	}

	public Integer getHighpresState() {
		return this.highpresState;
	}

	public void setHighpresState(Integer highpresState) {
		this.highpresState = highpresState;
	}

	public Double getExhaustValue() {
		return this.exhaustValue;
	}

	public void setExhaustValue(Double exhaustValue) {
		this.exhaustValue = exhaustValue;
	}

	public Integer getOilpresState() {
		return this.oilpresState;
	}

	public void setOilpresState(Integer oilpresState) {
		this.oilpresState = oilpresState;
	}

	public Integer getLowpresAlarm() {
		return this.lowpresAlarm;
	}

	public void setLowpresAlarm(Integer lowpresAlarm) {
		this.lowpresAlarm = lowpresAlarm;
	}

	public Integer getHighpresAlarm() {
		return this.highpresAlarm;
	}

	public void setHighpresAlarm(Integer highpresAlarm) {
		this.highpresAlarm = highpresAlarm;
	}

	public Integer getExhaustAlarm() {
		return this.exhaustAlarm;
	}

	public void setExhaustAlarm(Integer exhaustAlarm) {
		this.exhaustAlarm = exhaustAlarm;
	}

	public Integer getOilpresAlarm() {
		return this.oilpresAlarm;
	}

	public void setOilpresAlarm(Integer oilpresAlarm) {
		this.oilpresAlarm = oilpresAlarm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Integer getCId() {
//		return cId;
//	}
//
//	public void setCId(Integer id) {
//		cId = id;
//	}

}