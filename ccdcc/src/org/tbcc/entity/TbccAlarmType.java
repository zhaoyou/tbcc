package org.tbcc.entity;

/**
 * ������������ TbccAlarmType entity.
 * 
 * @author administrator
 */

public class TbccAlarmType implements java.io.Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;		//��ʾId
	//private String projectId;	//����Id
	private Integer alarmCode;	//�豸�ϴ��ı�������

	private Integer alarmType;	//��������
	private Integer alarmPort;	//�����˿�
	private String alarmName;	//��������
	
	private Integer appType ;	//Ӧ������
	

	/** default constructor */
	public TbccAlarmType() {
	}

	

	public TbccAlarmType(Long id, Integer  appType, Integer alarmCode,
			Integer alarmType, Integer alarmPort, String alarmName) {
		super();
		this.id = id;
		this.appType = appType;
		this.alarmCode = alarmCode;
		this.alarmType = alarmType;
		this.alarmPort = alarmPort;
		this.alarmName = alarmName;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	



	public Integer getAlarmCode() {
		return alarmCode;
	}


	public void setAlarmCode(Integer alarmCode) {
		this.alarmCode = alarmCode;
	}

	public Integer getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public Integer getAlarmPort() {
		return this.alarmPort;
	}

	public void setAlarmPort(Integer alarmPort) {
		this.alarmPort = alarmPort;
	}

	public String getAlarmName() {
		return this.alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccAlarmType))
			return false;
		TbccAlarmType castOther = (TbccAlarmType) other;
		
		if(this.getId().equals(castOther.getId()))
			return true ;
		return false ;

		
	}

	public int hashCode() {
		return this.getId().intValue() ;
	}



	public Integer getAppType() {
		return appType;
	}



	public void setAppType(Integer appType) {
		this.appType = appType;
	}

}