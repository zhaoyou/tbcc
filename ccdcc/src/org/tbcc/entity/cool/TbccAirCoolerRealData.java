package org.tbcc.entity.cool;

/**
 * TbccAirCoolerRealData entity.	����ʵʱ����ʵ��
 * 
 * @author administrator
 */

public class TbccAirCoolerRealData implements java.io.Serializable {

	// Fields

	private Integer id;
	//�޸���ʵ�塢��Ӧ����ͼ
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