package org.tbcc.entity.config;

import java.io.Serializable;

/**
 * 这是车载温湿度上下限报警配置表
 * @author zhaoyou
 *
 */
public class TbccParaVehicleAlarm implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private Long parentId ;
	
	private Double  tprealarm_high ;
	private Byte	tprealarm_highValid ;
	private Short	tprealarm_highDelay ;
	private Byte	tprealarm_highDelayValid ;
	
	private Double	tprealarm_low ;
	private Byte	tprealarm_lowValid ;
	private Short	tprealarm_lowDelay ;
	private Byte	tprealarm_lowDelayValid ;
	
	private Double  hprealarm_high ;
	private Byte	hprealarm_highValid ;
	private Short	hprealarm_highDelay ;
	private Byte	hprealarm_highDelayValid ;

	private Double	hprealarm_low ;
	private Byte	hprealarm_lowValid ;
	private Short	hprealarm_lowDelay ;
	private Byte	hprealarm_lowDelayValid ;
	
	
	private Double  talarm_high ;
	private Byte	talarm_highValid ;
	private Short	talarm_highDelay ;
	private Byte	talarm_highDelayValid ;
	
	private Double	talarm_low ;
	private Byte	talarm_lowValid ;
	private Short	talarm_lowDelay ;
	private Byte	talarm_lowDelayValid ;
	
	private Double  halarm_high ;
	private Byte	halarm_highValid ;
	private Short	halarm_highDelay ;
	private Byte	halarm_highDelayValid ;

	private Double	halarm_low ;
	private Byte	halarm_lowValid ;
	private Short	halarm_lowDelay ;
	private Byte	halarm_lowDelayValid ;
	
	
	public TbccParaVehicleAlarm(){
		super();
	}
	
	
	
	public TbccParaVehicleAlarm(Long id, Long parentId, Double tprealarm_high,
			Byte tprealarm_highValid, Short tprealarm_highDelay,
			Byte tprealarm_highDelayValid, Double tprealarm_low,
			Byte tprealarm_lowValid, Short tprealarm_lowDelay,
			Byte tprealarm_lowDelayValid, Double hprealarm_high,
			Byte hprealarm_highValid, Short hprealarm_highDelay,
			Byte hprealarm_highDelayValid, Double hprealarm_low,
			Byte hprealarm_lowValid, Short hprealarm_lowDelay,
			Byte hprealarm_lowDelayValid, Double talarm_high,
			Byte talarm_highValid, Short talarm_highDelay,
			Byte talarm_highDelayValid, Double talarm_low,
			Byte talarm_lowValid, Short talarm_lowDelay,
			Byte talarm_lowDelayValid, Double halarm_high,
			Byte halarm_highValid, Short halarm_highDelay,
			Byte halarm_highDelayValid, Double halarm_low,
			Byte halarm_lowValid, Short halarm_lowDelay,
			Byte halarm_lowDelayValid) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.tprealarm_high = tprealarm_high;
		this.tprealarm_highValid = tprealarm_highValid;
		this.tprealarm_highDelay = tprealarm_highDelay;
		this.tprealarm_highDelayValid = tprealarm_highDelayValid;
		this.tprealarm_low = tprealarm_low;
		this.tprealarm_lowValid = tprealarm_lowValid;
		this.tprealarm_lowDelay = tprealarm_lowDelay;
		this.tprealarm_lowDelayValid = tprealarm_lowDelayValid;
		this.hprealarm_high = hprealarm_high;
		this.hprealarm_highValid = hprealarm_highValid;
		this.hprealarm_highDelay = hprealarm_highDelay;
		this.hprealarm_highDelayValid = hprealarm_highDelayValid;
		this.hprealarm_low = hprealarm_low;
		this.hprealarm_lowValid = hprealarm_lowValid;
		this.hprealarm_lowDelay = hprealarm_lowDelay;
		this.hprealarm_lowDelayValid = hprealarm_lowDelayValid;
		this.talarm_high = talarm_high;
		this.talarm_highValid = talarm_highValid;
		this.talarm_highDelay = talarm_highDelay;
		this.talarm_highDelayValid = talarm_highDelayValid;
		this.talarm_low = talarm_low;
		this.talarm_lowValid = talarm_lowValid;
		this.talarm_lowDelay = talarm_lowDelay;
		this.talarm_lowDelayValid = talarm_lowDelayValid;
		this.halarm_high = halarm_high;
		this.halarm_highValid = halarm_highValid;
		this.halarm_highDelay = halarm_highDelay;
		this.halarm_highDelayValid = halarm_highDelayValid;
		this.halarm_low = halarm_low;
		this.halarm_lowValid = halarm_lowValid;
		this.halarm_lowDelay = halarm_lowDelay;
		this.halarm_lowDelayValid = halarm_lowDelayValid;
	}



	public Double getTprealarm_high() {
		return tprealarm_high;
	}
	public void setTprealarm_high(Double tprealarm_high) {
		this.tprealarm_high = tprealarm_high;
	}
	public Byte getTprealarm_highValid() {
		return tprealarm_highValid;
	}
	public void setTprealarm_highValid(Byte tprealarm_highValid) {
		this.tprealarm_highValid = tprealarm_highValid;
	}
	public Short getTprealarm_highDelay() {
		return tprealarm_highDelay;
	}
	public void setTprealarm_highDelay(Short tprealarm_highDelay) {
		this.tprealarm_highDelay = tprealarm_highDelay;
	}
	public Byte getTprealarm_highDelayValid() {
		return tprealarm_highDelayValid;
	}
	public void setTprealarm_highDelayValid(Byte tprealarm_highDelayValid) {
		this.tprealarm_highDelayValid = tprealarm_highDelayValid;
	}
	public Double getTprealarm_low() {
		return tprealarm_low;
	}
	public void setTprealarm_low(Double tprealarm_low) {
		this.tprealarm_low = tprealarm_low;
	}
	public Byte getTprealarm_lowValid() {
		return tprealarm_lowValid;
	}
	public void setTprealarm_lowValid(Byte tprealarm_lowValid) {
		this.tprealarm_lowValid = tprealarm_lowValid;
	}
	public Short getTprealarm_lowDelay() {
		return tprealarm_lowDelay;
	}
	public void setTprealarm_lowDelay(Short tprealarm_lowDelay) {
		this.tprealarm_lowDelay = tprealarm_lowDelay;
	}
	public Byte getTprealarm_lowDelayValid() {
		return tprealarm_lowDelayValid;
	}
	public void setTprealarm_lowDelayValid(Byte tprealarm_lowDelayValid) {
		this.tprealarm_lowDelayValid = tprealarm_lowDelayValid;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}



	public Double getHprealarm_high() {
		return hprealarm_high;
	}



	public void setHprealarm_high(Double hprealarm_high) {
		this.hprealarm_high = hprealarm_high;
	}



	public Byte getHprealarm_highValid() {
		return hprealarm_highValid;
	}



	public void setHprealarm_highValid(Byte hprealarm_highValid) {
		this.hprealarm_highValid = hprealarm_highValid;
	}



	public Short getHprealarm_highDelay() {
		return hprealarm_highDelay;
	}



	public void setHprealarm_highDelay(Short hprealarm_highDelay) {
		this.hprealarm_highDelay = hprealarm_highDelay;
	}



	public Byte getHprealarm_highDelayValid() {
		return hprealarm_highDelayValid;
	}



	public void setHprealarm_highDelayValid(Byte hprealarm_highDelayValid) {
		this.hprealarm_highDelayValid = hprealarm_highDelayValid;
	}



	public Double getHprealarm_low() {
		return hprealarm_low;
	}



	public void setHprealarm_low(Double hprealarm_low) {
		this.hprealarm_low = hprealarm_low;
	}



	public Byte getHprealarm_lowValid() {
		return hprealarm_lowValid;
	}



	public void setHprealarm_lowValid(Byte hprealarm_lowValid) {
		this.hprealarm_lowValid = hprealarm_lowValid;
	}



	public Short getHprealarm_lowDelay() {
		return hprealarm_lowDelay;
	}



	public void setHprealarm_lowDelay(Short hprealarm_lowDelay) {
		this.hprealarm_lowDelay = hprealarm_lowDelay;
	}



	public Byte getHprealarm_lowDelayValid() {
		return hprealarm_lowDelayValid;
	}



	public void setHprealarm_lowDelayValid(Byte hprealarm_lowDelayValid) {
		this.hprealarm_lowDelayValid = hprealarm_lowDelayValid;
	}



	public Double getTalarm_high() {
		return talarm_high;
	}



	public void setTalarm_high(Double talarm_high) {
		this.talarm_high = talarm_high;
	}



	public Byte getTalarm_highValid() {
		return talarm_highValid;
	}



	public void setTalarm_highValid(Byte talarm_highValid) {
		this.talarm_highValid = talarm_highValid;
	}



	public Short getTalarm_highDelay() {
		return talarm_highDelay;
	}



	public void setTalarm_highDelay(Short talarm_highDelay) {
		this.talarm_highDelay = talarm_highDelay;
	}



	public Byte getTalarm_highDelayValid() {
		return talarm_highDelayValid;
	}



	public void setTalarm_highDelayValid(Byte talarm_highDelayValid) {
		this.talarm_highDelayValid = talarm_highDelayValid;
	}



	public Double getTalarm_low() {
		return talarm_low;
	}



	public void setTalarm_low(Double talarm_low) {
		this.talarm_low = talarm_low;
	}



	public Byte getTalarm_lowValid() {
		return talarm_lowValid;
	}



	public void setTalarm_lowValid(Byte talarm_lowValid) {
		this.talarm_lowValid = talarm_lowValid;
	}



	public Short getTalarm_lowDelay() {
		return talarm_lowDelay;
	}



	public void setTalarm_lowDelay(Short talarm_lowDelay) {
		this.talarm_lowDelay = talarm_lowDelay;
	}



	public Byte getTalarm_lowDelayValid() {
		return talarm_lowDelayValid;
	}



	public void setTalarm_lowDelayValid(Byte talarm_lowDelayValid) {
		this.talarm_lowDelayValid = talarm_lowDelayValid;
	}



	public Double getHalarm_high() {
		return halarm_high;
	}



	public void setHalarm_high(Double halarm_high) {
		this.halarm_high = halarm_high;
	}



	public Byte getHalarm_highValid() {
		return halarm_highValid;
	}



	public void setHalarm_highValid(Byte halarm_highValid) {
		this.halarm_highValid = halarm_highValid;
	}



	public Short getHalarm_highDelay() {
		return halarm_highDelay;
	}



	public void setHalarm_highDelay(Short halarm_highDelay) {
		this.halarm_highDelay = halarm_highDelay;
	}



	public Byte getHalarm_highDelayValid() {
		return halarm_highDelayValid;
	}



	public void setHalarm_highDelayValid(Byte halarm_highDelayValid) {
		this.halarm_highDelayValid = halarm_highDelayValid;
	}



	public Double getHalarm_low() {
		return halarm_low;
	}



	public void setHalarm_low(Double halarm_low) {
		this.halarm_low = halarm_low;
	}



	public Byte getHalarm_lowValid() {
		return halarm_lowValid;
	}



	public void setHalarm_lowValid(Byte halarm_lowValid) {
		this.halarm_lowValid = halarm_lowValid;
	}



	public Short getHalarm_lowDelay() {
		return halarm_lowDelay;
	}



	public void setHalarm_lowDelay(Short halarm_lowDelay) {
		this.halarm_lowDelay = halarm_lowDelay;
	}



	public Byte getHalarm_lowDelayValid() {
		return halarm_lowDelayValid;
	}



	public void setHalarm_lowDelayValid(Byte halarm_lowDelayValid) {
		this.halarm_lowDelayValid = halarm_lowDelayValid;
	}
	
	
	
	
	
	
}
