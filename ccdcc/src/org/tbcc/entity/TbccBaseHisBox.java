package org.tbcc.entity;

import java.util.Date;

/**
 * 这个类用来封装所有的历史小批零数据实体
 * @author Administrator
 *
 */
public class TbccBaseHisBox  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id ;
	private Long parentId ;
	
	private Date updateTime ;
	private Double ai1 ;
	private Integer latitude_dir ;
	private Double	latitude ;
	private Integer longitude_dir ;
	private Double longitude ;
	private Integer	alarmStatus ;
	
	//显示报警状态的字符串
	private String alarmStatusStr ;
	
	
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
		if(this.latitude !=-300 ){
			 int du = (int)(this.latitude/100) ;
			 double fen = this.latitude -(du*100) ;
			 Double fen1 = this.latitude%100 ;			//String.valueOf(fen).substring(0,5)
			 String fen2 = fen1.toString().length()>5?fen1.toString().substring(0, 5):fen1.toString();
			targeStr =  String.valueOf(du)+"°" + fen2	+"\'"+(this.latitude_dir==0?"S":"N") ; 
		}
		return targeStr ;
	}


	//获取经度
	public String getLongitudeStr() {
		String targeStr ="----" ;
		if(this.longitude!=-300 ){
			int du = (int)(this.longitude/100) ;
			double fen = this.longitude-(du*100) ;
			Double fen1 = this.longitude%100 ;			//String.valueOf(fen).substring(0,5)
			String fen2 = fen1.toString().length()>5?fen1.toString().substring(0,5):fen1.toString();
			targeStr =  String.valueOf(du) + "°" + fen2 + "\'"+(this.longitude_dir==0?"E":"W") ;
		}
		return targeStr;
	}
	
	
	public void setLatitudeStr(String latitudeStr) {
		this.latitudeStr = latitudeStr;
	}
	public void setLongitudeStr(String longitudeStr) {
		this.longitudeStr = longitudeStr;
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
	
	
	
}
