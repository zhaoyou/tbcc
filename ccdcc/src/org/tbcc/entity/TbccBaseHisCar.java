package org.tbcc.entity;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * ���ʵ����Ҫ������װ��ʷ�ƶ���������
 * @author Administrator
 *
 */
public class TbccBaseHisCar implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	//�������ͣ��ʱ�ӵ��ֶ�
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
	
	
	//��ʾ����״̬���ַ���
	private String alarmStatusStr ;
	
	private Double[] ais;
	
	//��ʾ�Ƿ��Ѿ�������ʷ����
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
	 * ����Ķ�����Ϊ�˷�װ���ǵļ���
	 */
	
	
	//����������������������ҳ�������γ����Ϣ
	
	private String latitudeStr ="";  //γ��
	
	private String longitudeStr = "" ;  //����

	// Constructorsγ������
	//γ�����ݸ�ʽ˵��:ddmm.mmmm���ȷ֣���ʽ,
	//����dd��ʾ��,mm.mmmm��ʾ��, ����:1234.5678Ϊ12��34.5678��
	
	
	//�������ݾ������ݸ�ʽ˵��:dddmm.mmmm���ȷ֣���ʽ,
	//����ddd��ʾ��,mm.mmmm��ʾ��, ����:12345.6789Ϊ123��45.6789��


	//��ȡγ��
	public String getLatitudeStr() {
		String targeStr = "----" ;
		try {
			if(this.latitude !=-300 ){
				 int du = (int)(this.latitude/100) ;
				 Double fen = this.latitude -(du*100) ;
				 Double fen1 = this.latitude%100 ;			//String.valueOf(fen).substring(0,5)
				 String fen2 = fen1.toString().length()>5?fen1.toString().substring(0, 5):fen1.toString();
				targeStr =  String.valueOf(du)+"��" + fen2	+"\'"+(this.latitude_dir==0?"S":"N") ; 
			}
		} catch (Exception e) {
			System.out.println("γ�ȸ�ʽ����..."+this.latitude);
			return targeStr  ;
		}
		
		return targeStr ;
	}


	//��ȡ����
	public String getLongitudeStr() {
		String targeStr ="----" ;
		
		try {
			if(this.longitude!=-300 ){
				int du = (int)(this.longitude/100) ;
				Double fen = this.longitude-(du*100) ;
				Double fen1 = this.longitude%100 ;			//String.valueOf(fen).substring(0,5)
				String fen2 = fen1.toString().length()>5?fen1.toString().substring(0,5):fen1.toString();
				targeStr =  String.valueOf(du) + "��" + fen2 + "\'"+(this.longitude_dir==0?"E":"W") ;
			}
		} catch (Exception e) {
			System.out.println("���ȸ�ʽ����..."+this.longitude);
			return targeStr ;
		}
		
		return targeStr;
	}

	
	
	
	//��������������������ͬһʱ�̵���Сֵ�����ֵ��ƽ��ֵ
	
	private double minAi ;
	private double maxAi ;
	private double avgAi ;

	/**
	 * ��ȡ��Сֵ
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
	 * ��ȡ���ֵ
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
	 *  ��ȡƽ��ֵ
	 * @return
	 */
	public double getAvgAi() {
		int count = 0 ;    //ͳ�����ݵ���Ч��
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
	 * Ϊ�˻�ȡ����״̬��ҳ������ʾ��״̬���
	 * @return
	 */
	public String getAlarmStatusStr() {
		String str = ""	 ;
		if(this.getAlarmStatus().equals(0)){
			str = "<font color='orange'>Ԥ��</font>" ;
		}else if (this.getAlarmStatus().equals(1)){
			str = "<font color='red'>����</font>" ;
		}else{
			str = "����" ;
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
