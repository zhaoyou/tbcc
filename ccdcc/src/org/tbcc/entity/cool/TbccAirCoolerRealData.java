package org.tbcc.entity.cool;

/**
 * TbccAirCoolerRealData entity.	冷风机实时数据实体
 * 
 * @author administrator
 */

public class TbccAirCoolerRealData implements java.io.Serializable {

	// Fields

	private Integer id;
	//修改了实体、对应到视图
	//private Integer cId ;
	private String name ;
	private Integer defrostState;
	private Integer defrostAlarm;

	// Constructors

	/** default constructor */
	public TbccAirCoolerRealData() {
	}

	/** full constructor */
	public TbccAirCoolerRealData(String name,
			Integer defrostState, Integer defrostAlarm) {
		this.name = name;
		this.defrostState = defrostState;
		this.defrostAlarm = defrostAlarm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDefrostState() {
		return this.defrostState;
	}

	public void setDefrostState(Integer defrostState) {
		this.defrostState = defrostState;
	}

	public Integer getDefrostAlarm() {
		return this.defrostAlarm;
	}

	public void setDefrostAlarm(Integer defrostAlarm) {
		this.defrostAlarm = defrostAlarm;
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