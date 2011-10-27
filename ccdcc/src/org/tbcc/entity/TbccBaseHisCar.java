package org.tbcc.entity;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * 这个实体主要用来封装历史移动车载数据
 * @author Administrator
 *
 */
public class TbccBaseHisCar implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	//后面加启停表时加的字段
	private Long id ;
	private Long parentId ;
	
	private Date updateTime ;
	private Double ai1;
	private Double ai2;
	private Double ai3;
	private Double ai4;
	private Integer latitude_dir;
	private Double latitude;
	private Integer longitude_dir ;
	private Double longitude;
	private Integer alarmStatus;
	
	
	private Integer unloadStatus;

	private Integer histAlarmStorageType ;

	private Integer gpsStorageType ;

	private Integer histDataStorageType;

	private Integer unloadStorageType;

	private Integer histAlarmData;
	
	
	//显示报警状态的字符串
	private String alarmStatusStr ;
	
	private Double[] ais;
	
	//表示是否已经处理历史报警
	private int flag;
	
	public TbccBaseHisCar(){super();}
	
	public TbccBaseHisCar(Long id ,Long parentId , Date updateTime , Double ai1, Double ai2, Double ai3,
			Double ai4, Integer latitude_dir, Double latitude, Integer longitude_dir ,Double longitude,
			Integer alarmStatus, Integer unloadStatus, Integer histAlarmStorageType, Integer gpsStorageType 
			,Integer histDataStorageType, Integer unloadStorageType, Integer histAlarmData){
		super();
		this.id = id;
		this.parentId = parentId;
		this.updateTime = updateTime;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.latitude_dir = latitude_dir;
		this.latitude  =latitude;
		this.longitude_dir = longitude_dir;
		this.longitude = longitude;
		this.alarmStatus = alarmStatus;
		this.unloadStatus = unloadStatus;
		this.histAlarmStorageType = histAlarmStorageType;
		this.gpsStorageType = gpsStorageType;
		this.histDataStorageType = histDataStorageType;
		this.unloadStorageType = unloadStorageType;
		this.histAlarmData = histAlarmData;
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
	public Integer getLatitude_dir() {
		return latitude_dir;
	}
	public void setLatitude_dir(Integer latitude_dir) {
		this.latitude_dir = latitude_dir;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getLongitude_dir() {
		return longitude_dir;
	}
	public void setLongitude_dir(Integer longitude_dir) {
		this.longitude_dir = longitude_dir;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(Integer alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	
	
	/**
	 * 下面的定义是为了封装我们的计算
	 */
	
	
	//定义两个个变量用来控制页面输出经纬度信息
	
	private String latitudeStr ="";  //纬度
	
	private String longitudeStr = "" ;  //经度

	// Constructors纬度数据
	//纬度数据格式说明:ddmm.mmmm（度分）格式,
	//其中dd表示度,mm.mmmm表示分, 例如:1234.5678为12度34.5678分
	
	
	//经度数据经度数据格式说明:dddmm.mmmm（度分）格式,
	//其中ddd表示度,mm.mmmm表示分, 例如:12345.6789为123度45.6789分


	//获取纬度
	public String getLatitudeStr() {
		String targeStr = "----" ;
		try {
			if(this.latitude !=-300 ){
				 int du = (int)(this.latitude/100) ;
				 Double fen = this.latitude -(du*100) ;
				 Double fen1 = this.latitude%100 ;			//String.valueOf(fen).substring(0,5)
				 String fen2 = fen1.toString().length()>5?fen1.toString().substring(0, 5):fen1.toString();
				targeStr =  String.valueOf(du)+"°" + fen2	+"\'"+(this.latitude_dir==0?"S":"N") ; 
			}
		} catch (Exception e) {
			System.out.println("纬度格式错误..."+this.latitude);
			return targeStr  ;
		}
		
		return targeStr ;
	}


	//获取经度
	public String getLongitudeStr() {
		String targeStr ="----" ;
		
		try {
			if(this.longitude!=-300 ){
				int du = (int)(this.longitude/100) ;
				Double fen = this.longitude-(du*100) ;
				Double fen1 = this.longitude%100 ;			//String.valueOf(fen).substring(0,5)
				String fen2 = fen1.toString().length()>5?fen1.toString().substring(0,5):fen1.toString();
				targeStr =  String.valueOf(du) + "°" + fen2 + "\'"+(this.longitude_dir==0?"E":"W") ;
			}
		} catch (Exception e) {
			System.out.println("经度格式错误..."+this.longitude);
			return targeStr ;
		}
		
		return targeStr;
	}

	
	
	
	//定义三个变量用来计算同一时刻的最小值、最大值、平均值
	
	private double minAi ;
	private double maxAi ;
	private double avgAi ;

	/**
	 * 获取最小值
	 * @return
	 */
	public double getMinAi() {		
		double min = 300 ;		
			if(this.getAi1()!=-300){
				if(min>this.getAi1())
					min = this.getAi1() ;	
			} 
					
			if(this.getAi2()!=-300){				
		    	if(min>this.getAi2())
					min = this.getAi2() ;		
			}
			
			if(this.getAi3()!=-300){	
				if(min>this.getAi3())
					min = this.getAi3() ;		
			}

		return min==300?min*-1:min;
	}
	
	
	/**
	 * 获取最大值
	 * @return
	 */
	public double getMaxAi() {
		double max = -300 ;		
		if(this.getAi1()!=-300){
			if(max<this.getAi1())
				max = this.getAi1() ;	
		} 
				
		if(this.getAi2()!=-300){				
	    	if(max<this.getAi2())
	    		max = this.getAi2() ;		
		}
		
		if(this.getAi3()!=-300){	
			if(max<this.getAi3())
				max = this.getAi3() ;		
		}
	   return max;
	}
	
	
	/**
	 *  获取平均值
	 * @return
	 */
	public double getAvgAi() {
		int count = 0 ;    //统计数据的有效性
		double avg = -300 ,sum = 0;
		
		
		if(this.getAi1()!=-300){
			count++ ;
			sum +=this.getAi1() ;
		} 
				
		if(this.getAi2()!=-300){				
	    	count ++ ;
	    	sum+=this.getAi2() ;
		}
		
		if(this.getAi3()!=-300){	
			count ++ ;
			sum +=this.getAi3() ;
		}
		
		if(count!=0){
			avg = sum/count ;
		}
		return avg;
	}
	public void setLatitudeStr(String latitudeStr) {
		this.latitudeStr = latitudeStr;
	}
	public void setLongitudeStr(String longitudeStr) {
		this.longitudeStr = longitudeStr;
	}
	public void setMinAi(double minAi) {
		this.minAi = minAi;
	}
	public void setMaxAi(double maxAi) {
		this.maxAi = maxAi;
	}
	public void setAvgAi(double avgAi) {
		this.avgAi = avgAi;
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
	
	
	/**
	 * 为了获取报警状态在页面中显示的状态设计
	 * @return
	 */
	public String getAlarmStatusStr() {
		String str = ""	 ;
		if(this.getAlarmStatus().equals(0)){
			str = "<font color='orange'>预警</font>" ;
		}else if (this.getAlarmStatus().equals(1)){
			str = "<font color='red'>报警</font>" ;
		}else{
			str = "正常" ;
		}
		return str;
	}
	public Integer getUnloadStatus() {
		return unloadStatus;
	}
	public Integer getHistAlarmStorageType() {
		return histAlarmStorageType;
	}
	public Integer getGpsStorageType() {
		return gpsStorageType;
	}
	public Integer getHistDataStorageType() {
		return histDataStorageType;
	}
	public Integer getUnloadStorageType() {
		return unloadStorageType;
	}
	public Integer getHistAlarmData() {
		return histAlarmData;
	}
	public void setUnloadStatus(Integer unloadStatus) {
		this.unloadStatus = unloadStatus;
	}
	public void setHistAlarmStorageType(Integer histAlarmStorageType) {
		this.histAlarmStorageType = histAlarmStorageType;
	}
	public void setGpsStorageType(Integer gpsStorageType) {
		this.gpsStorageType = gpsStorageType;
	}
	public void setHistDataStorageType(Integer histDataStorageType) {
		this.histDataStorageType = histDataStorageType;
	}
	public void setUnloadStorageType(Integer unloadStorageType) {
		this.unloadStorageType = unloadStorageType;
	}
	public void setHistAlarmData(Integer histAlarmData) {
		this.histAlarmData = histAlarmData;
	}
	
	private String histAlarmDataStr;
	public String getHistAlarmDataStr(){
		return Integer.toBinaryString(histAlarmData);
	}

	public Double[] getAis(){
		return new Double[]{ai1,ai2,ai3,ai4};
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
