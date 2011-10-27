package org.tbcc.entity;


/**
 * AI�˿����ñ� TbccAiInfo entity.
 * 
 * @author administrator
 */

public class TbccAiInfo implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;			//��ʾ����   
	private String projectId;	//����Id
	private Integer netid;		//�豸Id
	private Integer refid;		//���Id
	private Integer seqPortNo;	//����ж˿ڵ�Id
	

	private Integer portNo;		//ʵ�ʿ��ƵĶ˿�
	private String portName;	//ʵ�ʿ��ƶ˿�����
	private Integer dataType;	//�˿�����
	
	private Long rid;			//�������ı�ʶId

	// Constructors

	public TbccAiInfo(Long id, String projectId,Long rid, Integer netid, Integer refid,
			Integer seqPortNo, Integer portNo, String portName, Integer dataType) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.rid = rid ;
		this.netid = netid;
		this.refid = refid;
		this.seqPortNo = seqPortNo;
		this.portNo = portNo;
		this.portName = portName;
		this.dataType = dataType;
	}





	public TbccAiInfo(Long id) {
		super();
		this.id = id;
	}





	/** default constructor */
	public TbccAiInfo() {
	}





	// Property accessors

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

	public Integer getSeqPortNo() {
		return this.seqPortNo;
	}

	public void setSeqPortNo(Integer seqPortNo) {
		this.seqPortNo = seqPortNo;
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

	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	
	public Long getRid() {
		return rid;
	}





	public void setRid(Long rid) {
		this.rid = rid;
	}





	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccAiInfo))
			return false;
		TbccAiInfo ta = (TbccAiInfo)other ;
		if(this.getId().equals(ta.getId()))
			return true ;
		return false ;
		
	}

	public int hashCode() {
	return  this.getId().intValue() ;
	}

}