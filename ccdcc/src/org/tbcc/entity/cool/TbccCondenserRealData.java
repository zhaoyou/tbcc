package org.tbcc.entity.cool;

/**
 * TbccCondenserRealData entity.	������ʵ��
 * 
 * @author administrator
 */

public class TbccCondenserRealData implements java.io.Serializable {

	// Fields

	private Integer id;
	//�޸���ʵ�塢��Ӧ������ͼ
	private String name ;
	//private Integer cId;
	private Integer pressureState;
	private Integer pressureAlarm;

	// Constructors

	/** default constructor */
	public TbccCondenserRealData() {
	}

	/** full constructor */
	public TbccCondenserRealData(String name,
			Integer pressureState, Integer pressureAlarm) {
		this.name = name;
		this.pressureState = pressureState;
		this.pressureAlarm = pressureAlarm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


//	public Integer getCId() {
//		return cId;
//	}
//
//	public void setCId(Integer id) {
//		cId = id;
//	}

	public Integer getPressureState() {
		return this.pressureState;
	}

	public void setPressureState(Integer pressureState) {
		this.pressureState = pressureState;
	}

	public Integer getPressureAlarm() {
		return this.pressureAlarm;
	}

	public void setPressureAlarm(Integer pressureAlarm) {
		this.pressureAlarm = pressureAlarm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}