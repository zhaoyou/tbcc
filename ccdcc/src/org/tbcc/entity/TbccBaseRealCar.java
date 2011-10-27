package org.tbcc.entity;

import java.util.Date;

import org.tbcc.biz.RealCarBiz;
import org.tbcc.util.map.GetAddress;
import org.tbcc.util.map.GetAddress2;

/**
 * 这个类主要用来封装实时移动车载数据
 * @author Administrator
 *
 */
public class TbccBaseRealCar implements java.io.Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private String projectId ;
	private Integer netId ;
	private Double ai1 ;
	private Double ai2 ;
	private Double ai3 ;
	private Double ai4 ;
	private Integer latitude_dir ;
	private Double latitude;
	private Integer longitude_dir  ;
	private Double longitude;
	private Date updateTime;
	private Integer runStatus ;
	private Integer alarmStatus;
	private Integer connectStatus ;
	
	private Integer unloadStatus;
	private Integer speedStatus;
	private Double carSpeed;
	private Integer AlarmData;
	
	private String projectCode ;
	

	

	


	public String getProjectCode() {
		return projectCode;
	}


	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	public TbccBaseRealCar() {
		super();
	}


	public TbccBaseRealCar(Long id, String projectId, Integer netId,
			Double ai1, Double ai2, Double ai3, Double ai4,
			Integer latitude_dir, Double latitude, Integer longitude_dir,
			Double longitude, Date updateTime, Integer runStatus,
			Integer alarmStatus, Integer connectStatus,Integer unloadStatus,
			Integer speedStatus,Double carSpeed,Integer AlarmData) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.netId = netId;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.latitude_dir = latitude_dir;
		this.latitude = latitude;
		this.longitude_dir = longitude_dir;
		this.longitude = longitude;
		this.updateTime = updateTime;
		this.runStatus = runStatus;
		this.alarmStatus = alarmStatus;
		this.connectStatus = connectStatus;
		this.unloadStatus = unloadStatus;
		this.speedStatus = speedStatus;
		this.carSpeed = carSpeed;
		this.AlarmData = AlarmData;
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
		if(this.latitude==null)
			return targeStr ;
		try {
			if(this.latitude !=-300 ){
				 int du = (int)(this.latitude/100) ;
				 Double fen1 = this.latitude%100 ;			//String.valueOf(fen).substring(0,5)
				 String fen2 = fen1.toString().length()>5?fen1.toString().substring(0, 5):fen1.toString();
				 targeStr =  String.valueOf(du)+"°" + fen2	+"\'"+(this.latitude_dir==0?"S":"N") ; 
			}
		} catch (Exception e) {
			System.out.println("纬度格式错误:"+this.latitude);
			return targeStr ;
		}		
		return targeStr ;
	}


	//获取经度
	public String getLongitudeStr() {
		String targeStr ="----" ;
		if(this.longitude==null)
			return targeStr ;
		try {
			if(this.longitude!=-300 ){
				int du = (int)(this.longitude/100) ;
				Double fen1 = this.longitude%100 ;			//String.valueOf(fen).substring(0,5)
				String fen2 = fen1.toString().length()>5?fen1.toString().substring(0,5):fen1.toString();
				targeStr =  String.valueOf(du) + "°" + fen2 + "\'"+(this.longitude_dir==0?"E":"W") ;
			}
		} catch (Exception e) {
			System.out.println("经度格式错误..."+this.longitude);
			return  targeStr ;
		}
		
		return targeStr;
	}
	
	
	/**
	 * 这是为了扩展显示移动车载实时数据的时候，显示移动车载的名称即车牌号码,而设计的字段名
	 * 现在车载显示实时数据读取配置，查询视图、该字段已经存在视图中
	 */
	private String projectName ;
	
	
	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	//扩展地图显示功能	
	private String address ;
	
	/**
	 * 获取当前车载所在的地理位置
	 * @return
	 */
	public String getAddress() {
		
		if(this.getConnectStatus()==null || this.getLatitude()==null || this.getLongitude()==null)
			return "* * *" ;
		
		if(this.getConnectStatus().equals(RealCarBiz.CONNECTION)){
			
			if(this.latitude==-300 || this.longitude==-300){
				return "----" ;
			}else{
				
				//搜索地址时，不但要考虑经纬度，同时也必须考虑经纬度方向				
				int lng  = (int)(this.getLongitude()/100) ;
				double lng_xiao = (this.getLongitude()-(lng*100))/60 ;
				
				if(this.getLongitude_dir()==1)		//代表西经(1) ,默认东经 (0)
					lng_xiao = lng_xiao * -1 ;
				
				int lat = (int)(this.getLatitude()/100) ;
				double lat_xiao = (this.getLatitude()-(lat*100))/60;	
				
				if(this.getLatitude_dir()==0)		//代表南纬(默认0) 北纬 1
					lat_xiao = lat_xiao * -1 ;
				
				return GetAddress2.getAddress((lng+lng_xiao)+"", (lat+lat_xiao)+"");
			}
		}else{
			return "* * *" ;
		}
		
	}
	

	private String alarmdatastr;
	
	public String getAlarmdatastr(){
		return Integer.toBinaryString(AlarmData);
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


	public Integer getUnloadStatus() {
		return unloadStatus;
	}


	public Integer getSpeedStatus() {
		return speedStatus;
	}


	public Double getCarSpeed() {
		return carSpeed;
	}


	public Integer getAlarmData() {
		return AlarmData;
	}


	public void setUnloadStatus(Integer unloadStatus) {
		this.unloadStatus = unloadStatus;
	}


	public void setSpeedStatus(Integer speedStatus) {
		this.speedStatus = speedStatus;
	}


	public void setCarSpeed(Double carSpeed) {
		this.carSpeed = carSpeed;
	}


	public void setAlarmData(Integer alarmData) {
		AlarmData = alarmData;
	}
	
}
