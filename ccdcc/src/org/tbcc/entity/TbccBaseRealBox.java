package org.tbcc.entity;

import java.util.Date;

/**
 * 这个类主要用来实时的小批零的数据
 * @author Administrator
 *
 */
public class TbccBaseRealBox implements java.io.Serializable {
	private Long id ;
	private String projectId ;
	private Integer netId ;
	private Double ai1 ;
	private Integer latitude_dir ;
	private Double latitude ;
	private Integer longitude_dir;
	private Double longitude ;
	private Date updateTime ;
	private Integer runStatus;
	private Integer alarmStatus;
	private Integer connectStatus ;
	
	public TbccBaseRealBox(Long id, String projectId, Integer netId,
			Double ai1, Integer latitude_dir, Double latitude,
			Integer longitude_dir, Double longitude, Date updateTime,
			Integer runStatus, Integer alarmStatus, Integer connectStatus) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.netId = netId;
		this.ai1 = ai1;
		this.latitude_dir = latitude_dir;
		this.latitude = latitude;
		this.longitude_dir = longitude_dir;
		this.longitude = longitude;
		this.updateTime = updateTime;
		this.runStatus = runStatus;
		this.alarmStatus = alarmStatus;
		this.connectStatus = connectStatus;
	}

	public TbccBaseRealBox(){
		super();
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
	public Integer getNetId() {
		return netId;
	}
	public void setNetId(Integer netId) {
		this.netId = netId;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}
	public Integer getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(Integer alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public Integer getConnectStatus() {
		return connectStatus;
	}
	public void setConnectStatus(Integer connectStatus) {
		this.connectStatus = connectStatus;
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

	
}
