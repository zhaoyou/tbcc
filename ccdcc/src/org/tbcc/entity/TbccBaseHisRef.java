package org.tbcc.entity;

import java.util.Date;

import org.tbcc.util.MyUtil;

/**
 * 这个类用来封装历史冷库数据
 * 需要特别注意的是、冷库历史时间原先的updateTime为历史时间、现在改为了hDate.
 * updateTime为数据更新的时间、系统更改了查询的字段、显示字段、以及历史数据绘图的几个功能点
 * @author Administrator
 *
 */
public class TbccBaseHisRef implements java.io.Serializable {
	
	private Integer id ;
	private Date updateTime ;
	private Double ai1 ;
	private Double ai2 ;
	private Double ai3 ;
	private Double ai4 ;
	private Double ai5 ;
	private Double ai6 ;
	private Double ai7 ;
	private Double ai8 ;
	private Double ai9 ;
	private Double ai10 ;
	private Double ai11 ;
	private Double ai12 ;
	private Integer alarmStatus_ref1 ;
	private Integer alarmStatus_ref2 ;
	private Integer alarmStatus_ref3 ;
	private Integer alarmStatus_ref4 ;
	
	private Date hdate ;
	
	
	public TbccBaseHisRef(Integer id, Date updateTime, Double ai1, Double ai2,
			Double ai3, Double ai4, Double ai5, Double ai6, Double ai7,
			Double ai8, Double ai9, Double ai10, Double ai11, Double ai12,
			Integer alarmStatus_ref1, Integer alarmStatus_ref2,
			Integer alarmStatus_ref3, Integer alarmStatus_ref4,Date  hdate) {
		super();
		this.id = id;
		this.updateTime = updateTime;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.ai5 = ai5;
		this.ai6 = ai6;
		this.ai7 = ai7;
		this.ai8 = ai8;
		this.ai9 = ai9;
		this.ai10 = ai10;
		this.ai11 = ai11;
		this.ai12 = ai12;
		this.alarmStatus_ref1 = alarmStatus_ref1;
		this.alarmStatus_ref2 = alarmStatus_ref2;
		this.alarmStatus_ref3 = alarmStatus_ref3;
		this.alarmStatus_ref4 = alarmStatus_ref4;
		this.hdate = hdate ;
	}


	public TbccBaseHisRef(){

	}
	
	
	public String getUpdateStr(){
		return	MyUtil.getToString(this.getHdate());
		/**
		 * 原先是用updateTime字段作为查询条件，现在已经是用Hdate字段了
		 */
		//return MyUtil.getToString(this.getUpdateTime());
	}
	
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Double getAi1() {
		return ai1;
	}
	public void setAi1(Double ai1) {
		this.ai1 = ai1;
	}
	public Double getAi2() {
		return ai2;
	}
	public void setAi2(Double ai2) {
		this.ai2 = ai2;
	}
	public Double getAi3() {
		return ai3;
	}
	public void setAi3(Double ai3) {
		this.ai3 = ai3;
	}
	public Double getAi4() {
		return ai4;
	}
	public void setAi4(Double ai4) {
		this.ai4 = ai4;
	}
	public Double getAi5() {
		return ai5;
	}
	public void setAi5(Double ai5) {
		this.ai5 = ai5;
	}
	public Double getAi6() {
		return ai6;
	}
	public void setAi6(Double ai6) {
		this.ai6 = ai6;
	}
	public Double getAi7() {
		return ai7;
	}
	public void setAi7(Double ai7) {
		this.ai7 = ai7;
	}
	public Double getAi8() {
		return ai8;
	}
	public void setAi8(Double ai8) {
		this.ai8 = ai8;
	}
	public Double getAi9() {
		return ai9;
	}
	public void setAi9(Double ai9) {
		this.ai9 = ai9;
	}
	public Double getAi10() {
		return ai10;
	}
	public void setAi10(Double ai10) {
		this.ai10 = ai10;
	}
	public Double getAi11() {
		return ai11;
	}
	public void setAi11(Double ai11) {
		this.ai11 = ai11;
	}
	public Double getAi12() {
		return ai12;
	}
	public void setAi12(Double ai12) {
		this.ai12 = ai12;
	}
	public Integer getAlarmStatus_ref1() {
		return alarmStatus_ref1;
	}
	public void setAlarmStatus_ref1(Integer alarmStatus_ref1) {
		this.alarmStatus_ref1 = alarmStatus_ref1;
	}
	public Integer getAlarmStatus_ref2() {
		return alarmStatus_ref2;
	}
	public void setAlarmStatus_ref2(Integer alarmStatus_ref2) {
		this.alarmStatus_ref2 = alarmStatus_ref2;
	}
	public Integer getAlarmStatus_ref3() {
		return alarmStatus_ref3;
	}
	public void setAlarmStatus_ref3(Integer alarmStatus_ref3) {
		this.alarmStatus_ref3 = alarmStatus_ref3;
	}
	public Integer getAlarmStatus_ref4() {
		return alarmStatus_ref4;
	}
	public void setAlarmStatus_ref4(Integer alarmStatus_ref4) {
		this.alarmStatus_ref4 = alarmStatus_ref4;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getHdate() {
		return this.hdate;
	}


	public void setHdate(Date date) {
		this.hdate = date;
	}
	
	
	
	
}
