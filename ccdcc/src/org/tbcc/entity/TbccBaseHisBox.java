package org.tbcc.entity;

import java.util.Date;

/**
 * �����������װ���е���ʷС��������ʵ��
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
	
	//��ʾ����״̬���ַ���
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
		if(this.latitude !=-300 ){
			 int du = (int)(this.latitude/100) ;
			 double fen = this.latitude -(du*100) ;
			 Double fen1 = this.latitude%100 ;			//String.valueOf(fen).substring(0,5)
			 String fen2 = fen1.toString().length()>5?fen1.toString().substring(0, 5):fen1.toString();
			targeStr =  String.valueOf(du)+"��" + fen2	+"\'"+(this.latitude_dir==0?"S":"N") ; 
		}
		return targeStr ;
	}


	//��ȡ����
	public String getLongitudeStr() {
		String targeStr ="----" ;
		if(this.longitude!=-300 ){
			int du = (int)(this.longitude/100) ;
			double fen = this.longitude-(du*100) ;
			Double fen1 = this.longitude%100 ;			//String.valueOf(fen).substring(0,5)
			String fen2 = fen1.toString().length()>5?fen1.toString().substring(0,5):fen1.toString();
			targeStr =  String.valueOf(du) + "��" + fen2 + "\'"+(this.longitude_dir==0?"E":"W") ;
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
	
	
	
}
