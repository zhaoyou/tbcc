package org.tbcc.entity;

import java.io.Serializable;
import java.util.Date;

import org.tbcc.util.MyUtil;

/**
 * 仓库历史数据扩展实体
 * @author zhaoyou
 *
 */
public class TbccBaseHisRef_Ex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private Date updateTime ;
	private Date hdate ;
	
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
	private Double ai13 ;
	private Double ai14 ;
	private Double ai15 ;
	private Double ai16 ;
	private Double ai17 ;
	private Double ai18 ;
	private Double ai19 ;
	private Double ai20 ;
	private Double ai21 ;
	
	private Double ai22 ;
	private Double ai23 ;
	private Double ai24 ;
	private Double ai25 ;
	private Double ai26 ;
	private Double ai27 ;
	private Double ai28 ;
	private Double ai29 ;
	
	private Double ai30 ;
	private Double ai31 ;
	private Double ai32 ;
	
	private Integer ref1_RefAlarmState ;
	private Integer ref2_RefAlarmState ;
	private Integer ref3_RefAlarmState ;
	private Integer ref4_RefAlarmState ;
	private Integer ref5_RefAlarmState ;
	private Integer ref6_RefAlarmState ;
	private Integer ref7_RefAlarmState ;
	private Integer ref8_RefAlarmState ;
	private Integer ref9_RefAlarmState ;
	private Integer ref10_RefAlarmState ;
	private Integer ref11_RefAlarmState ;
	private Integer ref12_RefAlarmState ;
	private Integer ref13_RefAlarmState ;
	private Integer ref14_RefAlarmState ;
	private Integer ref15_RefAlarmState ;
	private Integer ref16_RefAlarmState ;
	
	
	
	
	
	public TbccBaseHisRef_Ex() {
		super();
	}
	
	
	public TbccBaseHisRef_Ex(Long id, Date updateTime, Date hdate, Double ai1,
			Double ai2, Double ai3, Double ai4, Double ai5, Double ai6,
			Double ai7, Double ai8, Double ai9, Double ai10, Double ai11,
			Double ai12, Double ai13, Double ai14, Double ai15, Double ai16,
			Double ai17, Double ai18, Double ai19, Double ai20, Double ai21,
			Double ai22, Double ai23, Double ai24, Double ai25, Double ai26,
			Double ai27, Double ai28, Double ai29, Double ai30, Double ai31,
			Double ai32, Integer ref1_RefAlarmState,
			Integer ref2_RefAlarmState, Integer ref3_RefAlarmState,
			Integer ref4_RefAlarmState, Integer ref5_RefAlarmState,
			Integer ref6_RefAlarmState, Integer ref7_RefAlarmState,
			Integer ref8_RefAlarmState, Integer ref9_RefAlarmState,
			Integer ref10_RefAlarmState, Integer ref11_RefAlarmState,
			Integer ref12_RefAlarmState, Integer ref13_RefAlarmState,
			Integer ref14_RefAlarmState, Integer ref15_RefAlarmState,
			Integer ref16_RefAlarmState) {
		super();
		this.id = id;
		this.updateTime = updateTime;
		this.hdate = hdate;
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
		this.ai13 = ai13;
		this.ai14 = ai14;
		this.ai15 = ai15;
		this.ai16 = ai16;
		this.ai17 = ai17;
		this.ai18 = ai18;
		this.ai19 = ai19;
		this.ai20 = ai20;
		this.ai21 = ai21;
		this.ai22 = ai22;
		this.ai23 = ai23;
		this.ai24 = ai24;
		this.ai25 = ai25;
		this.ai26 = ai26;
		this.ai27 = ai27;
		this.ai28 = ai28;
		this.ai29 = ai29;
		this.ai30 = ai30;
		this.ai31 = ai31;
		this.ai32 = ai32;
		this.ref1_RefAlarmState = ref1_RefAlarmState;
		this.ref2_RefAlarmState = ref2_RefAlarmState;
		this.ref3_RefAlarmState = ref3_RefAlarmState;
		this.ref4_RefAlarmState = ref4_RefAlarmState;
		this.ref5_RefAlarmState = ref5_RefAlarmState;
		this.ref6_RefAlarmState = ref6_RefAlarmState;
		this.ref7_RefAlarmState = ref7_RefAlarmState;
		this.ref8_RefAlarmState = ref8_RefAlarmState;
		this.ref9_RefAlarmState = ref9_RefAlarmState;
		this.ref10_RefAlarmState = ref10_RefAlarmState;
		this.ref11_RefAlarmState = ref11_RefAlarmState;
		this.ref12_RefAlarmState = ref12_RefAlarmState;
		this.ref13_RefAlarmState = ref13_RefAlarmState;
		this.ref14_RefAlarmState = ref14_RefAlarmState;
		this.ref15_RefAlarmState = ref15_RefAlarmState;
		this.ref16_RefAlarmState = ref16_RefAlarmState;
	}
	
	
	/**
	 * 或者日期格式
	 * @return
	 */
	public String getUpdateStr(){
		return MyUtil.getToString(this.getHdate());
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getHdate() {
		return hdate;
	}
	public void setHdate(Date hdate) {
		this.hdate = hdate;
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
	public Double getAi13() {
		return ai13;
	}
	public void setAi13(Double ai13) {
		this.ai13 = ai13;
	}
	public Double getAi14() {
		return ai14;
	}
	public void setAi14(Double ai14) {
		this.ai14 = ai14;
	}
	public Double getAi15() {
		return ai15;
	}
	public void setAi15(Double ai15) {
		this.ai15 = ai15;
	}
	public Double getAi16() {
		return ai16;
	}
	public void setAi16(Double ai16) {
		this.ai16 = ai16;
	}
	public Double getAi17() {
		return ai17;
	}
	public void setAi17(Double ai17) {
		this.ai17 = ai17;
	}
	public Double getAi18() {
		return ai18;
	}
	public void setAi18(Double ai18) {
		this.ai18 = ai18;
	}
	public Double getAi19() {
		return ai19;
	}
	public void setAi19(Double ai19) {
		this.ai19 = ai19;
	}
	public Double getAi20() {
		return ai20;
	}
	public void setAi20(Double ai20) {
		this.ai20 = ai20;
	}
	public Double getAi21() {
		return ai21;
	}
	public void setAi21(Double ai21) {
		this.ai21 = ai21;
	}
	public Double getAi22() {
		return ai22;
	}
	public void setAi22(Double ai22) {
		this.ai22 = ai22;
	}
	public Double getAi23() {
		return ai23;
	}
	public void setAi23(Double ai23) {
		this.ai23 = ai23;
	}
	public Double getAi24() {
		return ai24;
	}
	public void setAi24(Double ai24) {
		this.ai24 = ai24;
	}
	public Double getAi25() {
		return ai25;
	}
	public void setAi25(Double ai25) {
		this.ai25 = ai25;
	}
	public Double getAi26() {
		return ai26;
	}
	public void setAi26(Double ai26) {
		this.ai26 = ai26;
	}
	public Double getAi27() {
		return ai27;
	}
	public void setAi27(Double ai27) {
		this.ai27 = ai27;
	}
	public Double getAi28() {
		return ai28;
	}
	public void setAi28(Double ai28) {
		this.ai28 = ai28;
	}
	public Double getAi29() {
		return ai29;
	}
	public void setAi29(Double ai29) {
		this.ai29 = ai29;
	}
	public Double getAi30() {
		return ai30;
	}
	public void setAi30(Double ai30) {
		this.ai30 = ai30;
	}
	public Double getAi31() {
		return ai31;
	}
	public void setAi31(Double ai31) {
		this.ai31 = ai31;
	}
	public Double getAi32() {
		return ai32;
	}
	public void setAi32(Double ai32) {
		this.ai32 = ai32;
	}
	public Integer getRef1_RefAlarmState() {
		return ref1_RefAlarmState;
	}
	public void setRef1_RefAlarmState(Integer ref1_RefAlarmState) {
		this.ref1_RefAlarmState = ref1_RefAlarmState;
	}
	public Integer getRef2_RefAlarmState() {
		return ref2_RefAlarmState;
	}
	public void setRef2_RefAlarmState(Integer ref2_RefAlarmState) {
		this.ref2_RefAlarmState = ref2_RefAlarmState;
	}
	public Integer getRef3_RefAlarmState() {
		return ref3_RefAlarmState;
	}
	public void setRef3_RefAlarmState(Integer ref3_RefAlarmState) {
		this.ref3_RefAlarmState = ref3_RefAlarmState;
	}
	public Integer getRef4_RefAlarmState() {
		return ref4_RefAlarmState;
	}
	public void setRef4_RefAlarmState(Integer ref4_RefAlarmState) {
		this.ref4_RefAlarmState = ref4_RefAlarmState;
	}
	public Integer getRef5_RefAlarmState() {
		return ref5_RefAlarmState;
	}
	public void setRef5_RefAlarmState(Integer ref5_RefAlarmState) {
		this.ref5_RefAlarmState = ref5_RefAlarmState;
	}
	public Integer getRef6_RefAlarmState() {
		return ref6_RefAlarmState;
	}
	public void setRef6_RefAlarmState(Integer ref6_RefAlarmState) {
		this.ref6_RefAlarmState = ref6_RefAlarmState;
	}
	public Integer getRef7_RefAlarmState() {
		return ref7_RefAlarmState;
	}
	public void setRef7_RefAlarmState(Integer ref7_RefAlarmState) {
		this.ref7_RefAlarmState = ref7_RefAlarmState;
	}
	public Integer getRef8_RefAlarmState() {
		return ref8_RefAlarmState;
	}
	public void setRef8_RefAlarmState(Integer ref8_RefAlarmState) {
		this.ref8_RefAlarmState = ref8_RefAlarmState;
	}
	public Integer getRef9_RefAlarmState() {
		return ref9_RefAlarmState;
	}
	public void setRef9_RefAlarmState(Integer ref9_RefAlarmState) {
		this.ref9_RefAlarmState = ref9_RefAlarmState;
	}
	public Integer getRef10_RefAlarmState() {
		return ref10_RefAlarmState;
	}
	public void setRef10_RefAlarmState(Integer ref10_RefAlarmState) {
		this.ref10_RefAlarmState = ref10_RefAlarmState;
	}
	public Integer getRef11_RefAlarmState() {
		return ref11_RefAlarmState;
	}
	public void setRef11_RefAlarmState(Integer ref11_RefAlarmState) {
		this.ref11_RefAlarmState = ref11_RefAlarmState;
	}
	public Integer getRef12_RefAlarmState() {
		return ref12_RefAlarmState;
	}
	public void setRef12_RefAlarmState(Integer ref12_RefAlarmState) {
		this.ref12_RefAlarmState = ref12_RefAlarmState;
	}
	public Integer getRef13_RefAlarmState() {
		return ref13_RefAlarmState;
	}
	public void setRef13_RefAlarmState(Integer ref13_RefAlarmState) {
		this.ref13_RefAlarmState = ref13_RefAlarmState;
	}
	public Integer getRef14_RefAlarmState() {
		return ref14_RefAlarmState;
	}
	public void setRef14_RefAlarmState(Integer ref14_RefAlarmState) {
		this.ref14_RefAlarmState = ref14_RefAlarmState;
	}
	public Integer getRef15_RefAlarmState() {
		return ref15_RefAlarmState;
	}
	public void setRef15_RefAlarmState(Integer ref15_RefAlarmState) {
		this.ref15_RefAlarmState = ref15_RefAlarmState;
	}
	public Integer getRef16_RefAlarmState() {
		return ref16_RefAlarmState;
	}
	public void setRef16_RefAlarmState(Integer ref16_RefAlarmState) {
		this.ref16_RefAlarmState = ref16_RefAlarmState;
	}
	
	
}
