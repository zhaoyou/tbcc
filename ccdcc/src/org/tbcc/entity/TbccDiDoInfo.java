package org.tbcc.entity;

/**
 * DIDO�ź�������� TbccDiDoInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccDiDoInfo implements java.io.Serializable {




	private Long id;   //��ʾ����
	private String projectId; //����Id
	private Integer netid;		//�豸Id
	private Integer refid;		//���Id
	private Integer portType;   //�˿�����
	private Integer seqPortNo;  //���Ķ˿�(1-16)
	
	private Integer dataType;  //��������
	private Integer portNo;		//ʵ�ʿ��ƵĶ˿�
	private String portName;	//�˿�����
	
	
	public TbccDiDoInfo(Long id, String projectId, Integer netid,
			Integer refid, Integer portType, Integer seqPortNo,
			Integer dataType, Integer portNo, String portName) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.netid = netid;
		this.refid = refid;
		this.portType = portType;
		this.seqPortNo = seqPortNo;
		this.dataType = dataType;
		this.portNo = portNo;
		this.portName = portName;
	}



	/** default constructor */
	public TbccDiDoInfo() {
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

	public Integer getRefid() {
		return this.refid;
	}

	public void setRefid(Integer refid) {
		this.refid = refid;
	}

	public Integer getPortType() {
		return this.portType;
	}

	public void setPortType(Integer portType) {
		this.portType = portType;
	}

	public Integer getSeqPortNo() {
		return this.seqPortNo;
	}

	public void setSeqPortNo(Integer seqPortNo) {
		this.seqPortNo = seqPortNo;
	}


	


	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getPortNo() {
		return this.portNo;
	}

	public void setPortNo(Integer portNo) {
		this.portNo = portNo;
	}

	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccDiDoInfo))
			return false;
		
		TbccDiDoInfo castOther = (TbccDiDoInfo) other;
		if(this.getId().equals(castOther.getId()))
			return true ;
		
		return false ;
		
	}

	public int hashCode() {
			return this.getId().intValue() ;
	}

}