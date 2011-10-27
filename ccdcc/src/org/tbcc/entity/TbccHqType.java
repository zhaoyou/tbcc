package org.tbcc.entity;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * �������ͱ� TbccHqType entity.
 * 
 * @author administrator
 */

public class TbccHqType implements java.io.Serializable {

	// Fields


	private Long hqId;	//�ܲ�Id
	private String hqName;	//�ܲ�����
	private String hqCode;	//�ܲ����
	private String hqNote;	//�ܲ���ע
	
	private Long hqParentId ;		//�ܲ���������ϼ��ܲ�Id
	
	private String hqDisplayName ;
	private Blob 	hqLogo ;
	private Integer hqLogoEnable ;
	
	private Boolean		logoIsLoad ;
	
	

	
	//�����ܲ��û��µ����з�֧�������ж�Զ�Ĺ�ϵ
	private Set<TbccBranchType> branchTypes = new HashSet<TbccBranchType>();

	/** default constructor */
	public TbccHqType() {
		super();
	}

	

	public TbccHqType(Long hqId, String hqName, String hqCode,
			String hqNode,Long hqParentId) {
		super();
		this.hqId = hqId;
		this.hqName = hqName;
		this.hqCode = hqCode;
		this.hqNote = hqNode;
		this.hqParentId = hqParentId ;
	}



	public Long getHqId() {
		return this.hqId;
	}

	public void setHqId(Long hqId) {
		this.hqId = hqId;
	}

	public String getHqName() {
		return this.hqName;
	}

	public void setHqName(String hqName) {
		this.hqName = hqName;
	}

	public String getHqCode() {
		return this.hqCode;
	}

	public void setHqCode(String hqCode) {
		this.hqCode = hqCode;
	}

	public String getHqNote() {
		return this.hqNote;
	}

	public void setHqNote(String hqNote) {
		this.hqNote = hqNote;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccHqType))
			return false;
		TbccHqType castOther = (TbccHqType) other;
		if(this.getHqId().equals(castOther.getHqId()))
				return true ;
		return false ;

	}

	public int hashCode() {
		return this.getHqId().hashCode();
	}



	public Set<TbccBranchType> getBranchTypes() {
		return branchTypes;
	}



	public void setBranchTypes(Set<TbccBranchType> branchTypes) {
		this.branchTypes = branchTypes;
	}



	public Long getHqParentId() {
		return hqParentId;
	}



	public void setHqParentId(Long hqParentId) {
		this.hqParentId = hqParentId;
	}



	public Boolean getLogoIsLoad() {
		return logoIsLoad;
	}



	public void setLogoIsLoad(Boolean logoIsLoad) {
		this.logoIsLoad = logoIsLoad;
	}



	public String getHqDisplayName() {
		return hqDisplayName;
	}



	public void setHqDisplayName(String hqDisplayName) {
		this.hqDisplayName = hqDisplayName;
	}



	public Blob getHqLogo() {
		return hqLogo;
	}



	public void setHqLogo(Blob hqLogo) {
		this.hqLogo = hqLogo;
	}



	public Integer getHqLogoEnable() {
		return hqLogoEnable;
	}



	public void setHqLogoEnable(Integer hqLogoEnable) {
		this.hqLogoEnable = hqLogoEnable;
	}
	
	

}