package org.tbcc.entity;

/**
 * TbccPrjPara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccPrjPara implements java.io.Serializable {

	// Fields

	private String projectId;
	private Integer rdReqInval;
	private Integer rdValidPeriod;
	private Integer raValidPeriod;
	private Integer histSaveInval;
	private Integer tcpRetransInval;
	private Integer tcpRetransNum;
	private Integer hbStartTime;
	private Integer hbReTransInval;
	private Integer hbReTransNum;
	private Integer reDialInval;
	
	private Integer histUploadEnable ;


	// Constructors

	/** default constructor */
	public TbccPrjPara() {
		super();
	}

	
	
	public TbccPrjPara( String projectId, Integer rdReqInval,
			Integer rdValidPeriod, Integer raValidPeriod,
			Integer histSaveInval, Integer tcpRetransInval,
			Integer tcpRetransNum, Integer hbStartTime, Integer hbReTransInval,
			Integer hbReTransNum, Integer reDialInval,Integer histUploadEnable) {
		super();
		this.projectId = projectId;
		this.rdReqInval = rdReqInval;
		this.rdValidPeriod = rdValidPeriod;
		this.raValidPeriod = raValidPeriod;
		this.histSaveInval = histSaveInval;
		this.tcpRetransInval = tcpRetransInval;
		this.tcpRetransNum = tcpRetransNum;
		this.hbStartTime = hbStartTime;
		this.hbReTransInval = hbReTransInval;
		this.hbReTransNum = hbReTransNum;
		this.reDialInval = reDialInval;
		this.histUploadEnable = histUploadEnable ;
	}



	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	

	public Integer getRdReqInval() {
		return this.rdReqInval;
	}

	public void setRdReqInval(Integer rdReqInval) {
		this.rdReqInval = rdReqInval;
	}

	public Integer getRdValidPeriod() {
		return this.rdValidPeriod;
	}

	public void setRdValidPeriod(Integer rdValidPeriod) {
		this.rdValidPeriod = rdValidPeriod;
	}

	public Integer getRaValidPeriod() {
		return this.raValidPeriod;
	}

	public void setRaValidPeriod(Integer raValidPeriod) {
		this.raValidPeriod = raValidPeriod;
	}

	public Integer getHistSaveInval() {
		return this.histSaveInval;
	}

	public void setHistSaveInval(Integer histSaveInval) {
		this.histSaveInval = histSaveInval;
	}

	public Integer getTcpRetransInval() {
		return this.tcpRetransInval;
	}

	public void setTcpRetransInval(Integer tcpRetransInval) {
		this.tcpRetransInval = tcpRetransInval;
	}

	public Integer getTcpRetransNum() {
		return this.tcpRetransNum;
	}

	public void setTcpRetransNum(Integer tcpRetransNum) {
		this.tcpRetransNum = tcpRetransNum;
	}

	public Integer getHbStartTime() {
		return this.hbStartTime;
	}

	public void setHbStartTime(Integer hbStartTime) {
		this.hbStartTime = hbStartTime;
	}

	public Integer getHbReTransInval() {
		return this.hbReTransInval;
	}

	public void setHbReTransInval(Integer hbReTransInval) {
		this.hbReTransInval = hbReTransInval;
	}

	public Integer getHbReTransNum() {
		return this.hbReTransNum;
	}

	public void setHbReTransNum(Integer hbReTransNum) {
		this.hbReTransNum = hbReTransNum;
	}

	public Integer getReDialInval() {
		return this.reDialInval;
	}

	public void setReDialInval(Integer reDialInval) {
		this.reDialInval = reDialInval;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccPrjPara))
			return false;
		TbccPrjPara castOther = (TbccPrjPara) other;
		if(this.getProjectId().equals(castOther.getProjectId()))
				return true ;
		return false ;
	}

	
	public int hashCode() {
		return this.getProjectId().hashCode() ;
	}



	public Integer getHistUploadEnable() {
		return histUploadEnable;
	}



	public void setHistUploadEnable(Integer histUploadEnable) {
		this.histUploadEnable = histUploadEnable;
	}

}