package org.tbcc.entity;

/**
 * DIDO信号输入输出 TbccDiDoInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccDiDoInfo implements java.io.Serializable {




	private Long id;   //标示主键
	private String projectId; //工程Id
	private Integer netid;		//设备Id
	private Integer refid;		//冷库Id
	private Integer portType;   //端口类型
	private Integer seqPortNo;  //冷库的端口(1-16)
	
	private Integer dataType;  //数据类型
	private Integer portNo;		//实际控制的端口
	private String portName;	//端口名称
	
	
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