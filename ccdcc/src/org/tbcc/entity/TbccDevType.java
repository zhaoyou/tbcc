package org.tbcc.entity;

/**
 * �豸���������� TbccDevType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccDevType implements java.io.Serializable {

	// Fields

	private Long id;		//��ʾ����Id
	private String projectId;	//����Id
	private Integer netid;		//�豸Id
	private Integer devType;	//�豸����
	private Integer refNum;		//�豸�µ��������
	
	private Integer devAppType ;	//�������ӵĵ���Ӧ������

	public Integer getDevAppType() {
		return devAppType;
	}

	public void setDevAppType(Integer devAppType) {
		this.devAppType = devAppType;
	}

	public TbccDevType(Long id, String projectId, Integer netid,
			Integer devType, Integer refNum) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.netid = netid;
		this.devType = devType;
		this.refNum = refNum;
	}

	/** default constructor */
	public TbccDevType() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getNetid() {
		return this.netid;
	}

	public void setNetid(Integer netid) {
		this.netid = netid;
	}


	public Integer getDevType() {
		return this.devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

	public Integer getRefNum() {
		return this.refNum;
	}

	public void setRefNum(Integer refNum) {
		this.refNum = refNum;
	}
	
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccDevType))
			return false;
		TbccDevType castOther = (TbccDevType) other;
		if(this.getId().equals(castOther.getId()))
			return true ;
		return false ;
	}

	public int hashCode() {
		return this.getId().intValue() ;
	}

}