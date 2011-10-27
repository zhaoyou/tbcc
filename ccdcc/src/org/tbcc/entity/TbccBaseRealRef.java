package org.tbcc.entity;

import java.util.Date;

import org.tbcc.biz.RealRefBiz;

/**
 * 这个类主要用来封装实时冷库数据
 * @author Administrator
 *
 */
public class TbccBaseRealRef implements java.io.Serializable{
	private Long id ;
	private String projectId  ;
	private Integer neiId  ;
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
	

	
	/**
	 * 后面是为了扩展兼容模块，实时数据的探头由原来的12个扩展到了32探头
	 * 如果是原来的设备类型 12(1-9温度探头、10-12为湿度探头)
	 * 			   兼容类型 32(1-16为温度探头、17-32为湿度探头)
	 */
	
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

	
	private Date updateTime ;
	private Integer runStatus_Ref1;
	private Integer runStatus_Ref2 ;
	private Integer runStatus_Ref3;
	private Integer runStatus_Ref4;
	
	private Integer alarmStatus_Ref1;
	private Integer alarmStatus_Ref2;
	private Integer alarmStatus_Ref3;
	private Integer alarmStatus_Ref4;
	
	
	private Integer connectStatus;
	
	
	private Integer alarmStatus_Door1 ;
	private Integer alarmStatus_Door2 ;
	private Integer alarmStatus_Door3 ;
	private Integer alarmStatus_Door4 ;
	
	private Integer alarmStatus_LostPower1 ;
	private Integer alarmStatus_LostPower2 ;
	private Integer alarmStatus_LostPower3 ;
	private Integer alarmStatus_LostPower4 ;
	
	
	private Integer alarmStatus_Sound1 ;
	private Integer alarmStatus_Sound2 ;
	private Integer alarmStatus_Sound3 ;
	private Integer alarmStatus_Sound4 ;
	
	
	
	// 1-16个冷库运行状态、报警状态
	private Integer runStatus_Ref5 ;
	private Integer runStatus_Ref6 ;
	private Integer runStatus_Ref7 ;
	private Integer runStatus_Ref8 ;
	private Integer runStatus_Ref9 ;
	private Integer runStatus_Ref10 ;
	private Integer runStatus_Ref11 ;
	private Integer runStatus_Ref12 ;
	private Integer runStatus_Ref13 ;
	private Integer runStatus_Ref14 ;
	private Integer runStatus_Ref15 ;
	private Integer runStatus_Ref16 ;
	
	
	private Integer alarmStatus_Ref5 ;
	private Integer alarmStatus_Ref6 ;
	private Integer alarmStatus_Ref7 ;
	private Integer alarmStatus_Ref8 ;
	private Integer alarmStatus_Ref9 ;
	private Integer alarmStatus_Ref10 ;
	private Integer alarmStatus_Ref11 ;
	private Integer alarmStatus_Ref12 ;
	private Integer alarmStatus_Ref13 ;
	private Integer alarmStatus_Ref14 ;
	private Integer alarmStatus_Ref15 ;
	private Integer alarmStatus_Ref16 ;
	
	//32个探头数据数组
	private Double[] allAi ;
	//1-16冷库报警状态数组
	private Integer[] allRefAlarmStatus;
	//1-16冷库运行状态数组
	private Integer[] allRefRunStatus;
	//4个库门状态、4个断电状态、4个声光状态放在一个数组
	private Integer[] allDLSStatus;
	
	public TbccBaseRealRef() {
		super();
	}

	public TbccBaseRealRef(Long id, String projectId, Integer neiId,
			Double ai1, Double ai2, Double ai3, Double ai4, Double ai5,
			Double ai6, Double ai7, Double ai8, Double ai9, Double ai10,
			Double ai11, Double ai12, Double ai13, Double ai14, Double ai15, Double ai16, 
			Double ai17, Double ai18, Double ai19, Double ai20, Double ai21, Double ai22, 
			Double ai23, Double ai24, Double ai25, Double ai26, Double ai27, Double ai28, 
			Double ai29, Double ai30, Double ai31, Double ai32,
			Date updateTime, Integer runStatus_Ref1,
			Integer runStatus_Ref2, Integer runStatus_Ref3,
			Integer runStatus_Ref4, Integer runStatus_Ref5, Integer runStatus_Ref6,
			Integer runStatus_Ref7, Integer runStatus_Ref8, Integer runStatus_Ref9,
			Integer runStatus_Ref10, Integer runStatus_Ref11, Integer runStatus_Ref12,
			Integer runStatus_Ref13, Integer runStatus_Ref14, Integer runStatus_Ref15,
			Integer runStatus_Ref16, Integer alarmStatus_Ref1,
			Integer alarmStatus_Ref2, Integer alarmStatus_Ref3,
			Integer alarmStatus_Ref4, Integer alarmStatus_Ref5, Integer alarmStatus_Ref6,
			Integer alarmStatus_Ref7, Integer alarmStatus_Ref8, Integer alarmStatus_Ref9,
			Integer alarmStatus_Ref10, Integer alarmStatus_Ref11, Integer alarmStatus_Ref12,
			Integer alarmStatus_Ref13, Integer alarmStatus_Ref14, Integer alarmStatus_Ref15,
			Integer alarmStatus_Ref16, Integer connectStatus,Integer alarmStatus_Door1 ,
			Integer alarmStatus_Door2 ,Integer alarmStatus_Door3 ,Integer alarmStatus_Door4,
			Integer alarmStatus_LostPower1 ,Integer alarmStatus_LostPower2,Integer alarmStatus_LostPower3,
			Integer alarmStatus_LostPower4 ,Integer alarmStatus_Sound1,Integer alarmStatus_Sound2,
			Integer alarmStatus_Sound3,Integer alarmStatus_Sound4 ) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.neiId = neiId;
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
		this.updateTime = updateTime;
		this.runStatus_Ref1 = runStatus_Ref1;
		this.runStatus_Ref2 = runStatus_Ref2;
		this.runStatus_Ref3 = runStatus_Ref3;
		this.runStatus_Ref4 = runStatus_Ref4;
		this.runStatus_Ref5 = runStatus_Ref5;
		this.runStatus_Ref6 = runStatus_Ref6;
		this.runStatus_Ref7 = runStatus_Ref7;
		this.runStatus_Ref8 = runStatus_Ref8;
		this.runStatus_Ref9 = runStatus_Ref9;
		this.runStatus_Ref10 = runStatus_Ref10;
		this.runStatus_Ref11 = runStatus_Ref11;
		this.runStatus_Ref12 = runStatus_Ref12;
		this.runStatus_Ref13 = runStatus_Ref13;
		this.runStatus_Ref14 = runStatus_Ref14;
		this.runStatus_Ref15 = runStatus_Ref15;
		this.runStatus_Ref16 = runStatus_Ref16;
		this.alarmStatus_Ref1 = alarmStatus_Ref1;
		this.alarmStatus_Ref2 = alarmStatus_Ref2;
		this.alarmStatus_Ref3 = alarmStatus_Ref3;
		this.alarmStatus_Ref4 = alarmStatus_Ref4;
		this.alarmStatus_Ref5 = alarmStatus_Ref5;
		this.alarmStatus_Ref6 = alarmStatus_Ref6;
		this.alarmStatus_Ref7 = alarmStatus_Ref7;
		this.alarmStatus_Ref8 = alarmStatus_Ref8;
		this.alarmStatus_Ref9 = alarmStatus_Ref9;
		this.alarmStatus_Ref10 = alarmStatus_Ref10;
		this.alarmStatus_Ref11 = alarmStatus_Ref11;
		this.alarmStatus_Ref12 = alarmStatus_Ref12;
		this.alarmStatus_Ref13 = alarmStatus_Ref13;
		this.alarmStatus_Ref14 = alarmStatus_Ref14;
		this.alarmStatus_Ref15 = alarmStatus_Ref15;
		this.alarmStatus_Ref16 = alarmStatus_Ref16;
		this.alarmStatus_Door1 = alarmStatus_Door1;
		this.alarmStatus_Door2 = alarmStatus_Door2;
		this.alarmStatus_Door3 = alarmStatus_Door3;
		this.alarmStatus_Door4 = alarmStatus_Door4;
		this.alarmStatus_LostPower1 = alarmStatus_LostPower1;
		this.alarmStatus_LostPower2 = alarmStatus_LostPower2;
		this.alarmStatus_LostPower3 = alarmStatus_LostPower3;
		this.alarmStatus_LostPower4 = alarmStatus_LostPower4;
		this.alarmStatus_Sound1 = alarmStatus_Sound1;
		this.alarmStatus_Sound2 = alarmStatus_Sound2;
		this.alarmStatus_Sound3 = alarmStatus_Sound3;
		this.alarmStatus_Sound4 = alarmStatus_Sound4;
		this.connectStatus = connectStatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public Integer getNeiId() {
		return neiId;
	}


	public void setNeiId(Integer neiId) {
		this.neiId = neiId;
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


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getRunStatus_Ref1() {
		return runStatus_Ref1;
	}


	public void setRunStatus_Ref1(Integer runStatus_Ref1) {
		this.runStatus_Ref1 = runStatus_Ref1;
	}


	public Integer getRunStatus_Ref2() {
		return runStatus_Ref2;
	}


	public void setRunStatus_Ref2(Integer runStatus_Ref2) {
		this.runStatus_Ref2 = runStatus_Ref2;
	}


	public Integer getRunStatus_Ref3() {
		return runStatus_Ref3;
	}


	public void setRunStatus_Ref3(Integer runStatus_Ref3) {
		this.runStatus_Ref3 = runStatus_Ref3;
	}


	public Integer getRunStatus_Ref4() {
		return runStatus_Ref4;
	}


	public void setRunStatus_Ref4(Integer runStatus_Ref4) {
		this.runStatus_Ref4 = runStatus_Ref4;
	}


	public Integer getAlarmStatus_Ref1() {
		return alarmStatus_Ref1;
	}


	public void setAlarmStatus_Ref1(Integer alarmStatus_Ref1) {
		this.alarmStatus_Ref1 = alarmStatus_Ref1;
	}


	public Integer getAlarmStatus_Ref2() {
		return alarmStatus_Ref2;
	}


	public void setAlarmStatus_Ref2(Integer alarmStatus_Ref2) {
		this.alarmStatus_Ref2 = alarmStatus_Ref2;
	}


	public Integer getAlarmStatus_Ref3() {
		return alarmStatus_Ref3;
	}


	public void setAlarmStatus_Ref3(Integer alarmStatus_Ref3) {
		this.alarmStatus_Ref3 = alarmStatus_Ref3;
	}


	public Integer getAlarmStatus_Ref4() {
		return alarmStatus_Ref4;
	}


	public void setAlarmStatus_Ref4(Integer alarmStatus_Ref4) {
		this.alarmStatus_Ref4 = alarmStatus_Ref4;
	}


	public Integer getConnectStatus() {
		return connectStatus;
	}


	public void setConnectStatus(Integer connectStatus) {
		this.connectStatus = connectStatus;
	}

	
	/**
	 * 定义下面的变量是用来控制页面输出的
	 */
	
	
	

	
	/**
	 * refId 代表的是第几号冷库
	 * reutn 代表状态1 代表报警、2代表正常、0不参与显示
	 * @param refId
	 * @return
	 */
//	public Integer getMyAlarm(Integer refId){
//		if(this.getConnectStatus().equals(RealRefBiz.DISCONNECTION))
//			return 0;
//		
//		//不管是哪个冷库都只有1报警、2正常
//		if(refId.equals(1))
//			return this.getAlarmStatus_Ref1();
//		else if(refId.equals(2))
//			return this.getAlarmStatus_Ref2() ;
//		else if(refId.equals(3))
//			return this.getAlarmStatus_Ref3() ;
//		else if(refId.equals(4))
//			return this.getAlarmStatus_Ref4();
//		else if(refId.equals(5))
//			return this.getAlarmStatus_Ref5() ;
//		else if(refId.equals(6))
//			return this.getAlarmStatus_Ref6() ;
//		else if(refId.equals(7))
//			return this.getAlarmStatus_Ref7() ;
//		else if(refId.equals(8))
//			return this.getAlarmStatus_Ref8() ;
//		else if(refId.equals(9))
//			return this.getAlarmStatus_Ref9() ;
//		else if(refId.equals(10))
//			return this.getAlarmStatus_Ref10() ;
//		else if(refId.equals(11))
//			return this.getAlarmStatus_Ref11() ;
//		else if(refId.equals(12))
//			return this.getAlarmStatus_Ref12() ;
//		else if(refId.equals(13))
//			return this.getAlarmStatus_Ref13() ;
//		else if(refId.equals(14))
//			return this.getAlarmStatus_Ref14() ;
//		else if(refId.equals(15))
//			return this.getAlarmStatus_Ref15() ;
//		else if (refId.equals(16))
//			return this.getAlarmStatus_Ref16();
//		else
//			return -1 ;
//	}
	
//	/**
//	 * refId 代表几号冷库
//	 * return 1代表停止、2代表运行、0断开连接不参与显示
//	 * @param refId
//	 * @return
//	 */
//	public Integer getMyState(Integer refId){
//		if(this.getConnectStatus().equals(RealRefBiz.DISCONNECTION))
//			return 0 ;
//		
//		//不管是哪个冷库1代表停止、2代表运行
//		if(refId.equals(1))
//			return this.getRunStatus_Ref1() ;
//		else if(refId.equals(2))
//			return this.getRunStatus_Ref2() ;
//		else if (refId.equals(3))
//			return this.getRunStatus_Ref3() ;
//		else if(refId.equals(4))
//			return this.getRunStatus_Ref4() ;
//		else if(refId.equals(5))
//			return this.getRunStatus_Ref5() ;
//		else if(refId.equals(6))
//			return this.getRunStatus_Ref6() ;
//		else if(refId.equals(7))
//			return this.getRunStatus_Ref7() ;
//		else if(refId.equals(8))
//			return this.getRunStatus_Ref8() ;
//		else if(refId.equals(9))
//			return this.getRunStatus_Ref9() ;
//		else if(refId.equals(10))
//			return this.getRunStatus_Ref10() ;
//		else if(refId.equals(11))
//			return this.getRunStatus_Ref11() ;
//		else if(refId.equals(12))
//			return this.getRunStatus_Ref12() ;
//		else if(refId.equals(13))
//			return this.getRunStatus_Ref13() ;
//		else if(refId.equals(14))
//			return this.getRunStatus_Ref14() ;
//		else if(refId.equals(15))
//			return this.getRunStatus_Ref15() ;
//		else if(refId.equals(16))
//			return this.getRunStatus_Ref16() ;
//		else	
//			return -1 ;
//	}


	public Integer getAlarmStatus_Door1() {
		return alarmStatus_Door1;
	}


	public void setAlarmStatus_Door1(Integer alarmStatus_Door1) {
		this.alarmStatus_Door1 = alarmStatus_Door1;
	}


	public Integer getAlarmStatus_Door2() {
		return alarmStatus_Door2;
	}


	public void setAlarmStatus_Door2(Integer alarmStatus_Door2) {
		this.alarmStatus_Door2 = alarmStatus_Door2;
	}


	public Integer getAlarmStatus_Door3() {
		return alarmStatus_Door3;
	}


	public void setAlarmStatus_Door3(Integer alarmStatus_Door3) {
		this.alarmStatus_Door3 = alarmStatus_Door3;
	}


	public Integer getAlarmStatus_Door4() {
		return alarmStatus_Door4;
	}


	public void setAlarmStatus_Door4(Integer alarmStatus_Door4) {
		this.alarmStatus_Door4 = alarmStatus_Door4;
	}


	public Integer getAlarmStatus_LostPower1() {
		return alarmStatus_LostPower1;
	}


	public void setAlarmStatus_LostPower1(Integer alarmStatus_LostPower1) {
		this.alarmStatus_LostPower1 = alarmStatus_LostPower1;
	}


	public Integer getAlarmStatus_LostPower2() {
		return alarmStatus_LostPower2;
	}


	public void setAlarmStatus_LostPower2(Integer alarmStatus_LostPower2) {
		this.alarmStatus_LostPower2 = alarmStatus_LostPower2;
	}


	public Integer getAlarmStatus_LostPower3() {
		return alarmStatus_LostPower3;
	}


	public void setAlarmStatus_LostPower3(Integer alarmStatus_LostPower3) {
		this.alarmStatus_LostPower3 = alarmStatus_LostPower3;
	}


	public Integer getAlarmStatus_LostPower4() {
		return alarmStatus_LostPower4;
	}


	public void setAlarmStatus_LostPower4(Integer alarmStatus_LostPower4) {
		this.alarmStatus_LostPower4 = alarmStatus_LostPower4;
	}


	public Integer getAlarmStatus_Sound1() {
		return alarmStatus_Sound1;
	}


	public void setAlarmStatus_Sound1(Integer alarmStatus_Sound1) {
		this.alarmStatus_Sound1 = alarmStatus_Sound1;
	}


	public Integer getAlarmStatus_Sound2() {
		return alarmStatus_Sound2;
	}


	public void setAlarmStatus_Sound2(Integer alarmStatus_Sound2) {
		this.alarmStatus_Sound2 = alarmStatus_Sound2;
	}


	public Integer getAlarmStatus_Sound3() {
		return alarmStatus_Sound3;
	}


	public void setAlarmStatus_Sound3(Integer alarmStatus_Sound3) {
		this.alarmStatus_Sound3 = alarmStatus_Sound3;
	}


	public Integer getAlarmStatus_Sound4() {
		return alarmStatus_Sound4;
	}


	public void setAlarmStatus_Sound4(Integer alarmStatus_Sound4) {
		this.alarmStatus_Sound4 = alarmStatus_Sound4;
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


	

	

	public Integer getRunStatus_Ref5() {
		return runStatus_Ref5;
	}


	public void setRunStatus_Ref5(Integer runStatus_Ref5) {
		this.runStatus_Ref5 = runStatus_Ref5;
	}


	public Integer getRunStatus_Ref6() {
		return runStatus_Ref6;
	}


	public void setRunStatus_Ref6(Integer runStatus_Ref6) {
		this.runStatus_Ref6 = runStatus_Ref6;
	}


	public Integer getRunStatus_Ref7() {
		return runStatus_Ref7;
	}


	public void setRunStatus_Ref7(Integer runStatus_Ref7) {
		this.runStatus_Ref7 = runStatus_Ref7;
	}


	public Integer getRunStatus_Ref8() {
		return runStatus_Ref8;
	}


	public void setRunStatus_Ref8(Integer runStatus_Ref8) {
		this.runStatus_Ref8 = runStatus_Ref8;
	}


	public Integer getRunStatus_Ref9() {
		return runStatus_Ref9;
	}


	public void setRunStatus_Ref9(Integer runStatus_Ref9) {
		this.runStatus_Ref9 = runStatus_Ref9;
	}


	public Integer getRunStatus_Ref10() {
		return runStatus_Ref10;
	}


	public void setRunStatus_Ref10(Integer runStatus_Ref10) {
		this.runStatus_Ref10 = runStatus_Ref10;
	}


	public Integer getRunStatus_Ref11() {
		return runStatus_Ref11;
	}


	public void setRunStatus_Ref11(Integer runStatus_Ref11) {
		this.runStatus_Ref11 = runStatus_Ref11;
	}


	public Integer getRunStatus_Ref12() {
		return runStatus_Ref12;
	}


	public void setRunStatus_Ref12(Integer runStatus_Ref12) {
		this.runStatus_Ref12 = runStatus_Ref12;
	}


	public Integer getRunStatus_Ref13() {
		return runStatus_Ref13;
	}


	public void setRunStatus_Ref13(Integer runStatus_Ref13) {
		this.runStatus_Ref13 = runStatus_Ref13;
	}


	public Integer getRunStatus_Ref14() {
		return runStatus_Ref14;
	}


	public void setRunStatus_Ref14(Integer runStatus_Ref14) {
		this.runStatus_Ref14 = runStatus_Ref14;
	}


	public Integer getRunStatus_Ref15() {
		return runStatus_Ref15;
	}


	public void setRunStatus_Ref15(Integer runStatus_Ref15) {
		this.runStatus_Ref15 = runStatus_Ref15;
	}


	public Integer getRunStatus_Ref16() {
		return runStatus_Ref16;
	}


	public void setRunStatus_Ref16(Integer runStatus_Ref16) {
		this.runStatus_Ref16 = runStatus_Ref16;
	}


	public Integer getAlarmStatus_Ref5() {
		return alarmStatus_Ref5;
	}


	public void setAlarmStatus_Ref5(Integer alarmStatus_Ref5) {
		this.alarmStatus_Ref5 = alarmStatus_Ref5;
	}


	public Integer getAlarmStatus_Ref6() {
		return alarmStatus_Ref6;
	}


	public void setAlarmStatus_Ref6(Integer alarmStatus_Ref6) {
		this.alarmStatus_Ref6 = alarmStatus_Ref6;
	}


	public Integer getAlarmStatus_Ref7() {
		return alarmStatus_Ref7;
	}


	public void setAlarmStatus_Ref7(Integer alarmStatus_Ref7) {
		this.alarmStatus_Ref7 = alarmStatus_Ref7;
	}


	public Integer getAlarmStatus_Ref8() {
		return alarmStatus_Ref8;
	}


	public void setAlarmStatus_Ref8(Integer alarmStatus_Ref8) {
		this.alarmStatus_Ref8 = alarmStatus_Ref8;
	}


	public Integer getAlarmStatus_Ref9() {
		return alarmStatus_Ref9;
	}


	public void setAlarmStatus_Ref9(Integer alarmStatus_Ref9) {
		this.alarmStatus_Ref9 = alarmStatus_Ref9;
	}


	public Integer getAlarmStatus_Ref10() {
		return alarmStatus_Ref10;
	}


	public void setAlarmStatus_Ref10(Integer alarmStatus_Ref10) {
		this.alarmStatus_Ref10 = alarmStatus_Ref10;
	}


	public Integer getAlarmStatus_Ref11() {
		return alarmStatus_Ref11;
	}


	public void setAlarmStatus_Ref11(Integer alarmStatus_Ref11) {
		this.alarmStatus_Ref11 = alarmStatus_Ref11;
	}


	public Integer getAlarmStatus_Ref12() {
		return alarmStatus_Ref12;
	}


	public void setAlarmStatus_Ref12(Integer alarmStatus_Ref12) {
		this.alarmStatus_Ref12 = alarmStatus_Ref12;
	}


	public Integer getAlarmStatus_Ref13() {
		return alarmStatus_Ref13;
	}


	public void setAlarmStatus_Ref13(Integer alarmStatus_Ref13) {
		this.alarmStatus_Ref13 = alarmStatus_Ref13;
	}


	public Integer getAlarmStatus_Ref14() {
		return alarmStatus_Ref14;
	}


	public void setAlarmStatus_Ref14(Integer alarmStatus_Ref14) {
		this.alarmStatus_Ref14 = alarmStatus_Ref14;
	}


	public Integer getAlarmStatus_Ref15() {
		return alarmStatus_Ref15;
	}


	public void setAlarmStatus_Ref15(Integer alarmStatus_Ref15) {
		this.alarmStatus_Ref15 = alarmStatus_Ref15;
	}


	public Integer getAlarmStatus_Ref16() {
		return alarmStatus_Ref16;
	}


	public void setAlarmStatus_Ref16(Integer alarmStatus_Ref16) {
		this.alarmStatus_Ref16 = alarmStatus_Ref16;
	}
	//直接返回32个AI探头数据的数组
	public Double[] getAllAi() {
		return new Double[]{ai1,ai2,ai3,ai4,ai5,ai6,ai7,ai8,ai9,ai10,ai11,ai12,ai13,ai14,ai15,ai16,ai17,ai18,ai19,ai20,ai21,ai22,ai23,ai24,ai25,ai26,ai27,ai28,ai29,ai30,ai31,ai32};
	}


	public void setAllAi(Double[] allAi) {
		this.allAi = allAi;
	}

	//直接返回16个报警状态的数组
	public Integer[] getAllRefAlarmStatus() {
		return new Integer[]{alarmStatus_Ref1,alarmStatus_Ref2,alarmStatus_Ref3,alarmStatus_Ref4,alarmStatus_Ref5,alarmStatus_Ref6,alarmStatus_Ref7,alarmStatus_Ref8,alarmStatus_Ref9,alarmStatus_Ref10,
				alarmStatus_Ref11,alarmStatus_Ref12,alarmStatus_Ref13,alarmStatus_Ref14,alarmStatus_Ref15,alarmStatus_Ref16};
	}


	public void setAllRefAlarmStatus(Integer[] allRefAlarmStatus) {
		this.allRefAlarmStatus = allRefAlarmStatus;
	}

	//直接返回16个运行状态的数组
	public Integer[] getAllRefRunStatus() {
		return new Integer[]{runStatus_Ref1,runStatus_Ref2,runStatus_Ref3,runStatus_Ref4,runStatus_Ref5,runStatus_Ref6,runStatus_Ref7,runStatus_Ref8,runStatus_Ref9,runStatus_Ref10,
				runStatus_Ref11,runStatus_Ref12,runStatus_Ref13,runStatus_Ref14,runStatus_Ref15,runStatus_Ref16};
	}


	public void setAllRefRunStatus(Integer[] allRefRunStatus) {
		this.allRefRunStatus = allRefRunStatus;
	}

	//直接返回4个库门状态、4个断电状态、4个声光状态组成的数组
	public Integer[] getAllDLSStatus() {
		return new Integer[]{alarmStatus_Door1,alarmStatus_Door2,alarmStatus_Door3,alarmStatus_Door4,alarmStatus_LostPower1,
				alarmStatus_LostPower2,alarmStatus_LostPower3,alarmStatus_LostPower4,alarmStatus_Sound1,alarmStatus_Sound2,
				alarmStatus_Sound3,alarmStatus_Sound4};
	}


	public void setAllDLSStatus(Integer[] allDLSStatus) {
		this.allDLSStatus = allDLSStatus;
	}
	
}
