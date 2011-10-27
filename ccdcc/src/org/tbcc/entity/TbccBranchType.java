package org.tbcc.entity;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * TbccBranchType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccBranchType implements java.io.Serializable {

	// Fields

	private Long branchId;

	private Integer branchType;
	private String branchName;
	private String branchCode;
	private String branchRelInfo;
	private String branchNote;

	
	private String branchDisplayName ;
	private Blob branchLogo ;
	private Integer branchLogoEnable ;
	
	/**
	 * 图片是否已经加载 
	 */
	private Boolean logoIsLoad ;
	
	
	//该分支所对应的总部，和区域
	
	private Set<TbccHqType> hqTypes = new HashSet<TbccHqType>();
	
	private Set<TbccAreaType> areaTypes = new HashSet<TbccAreaType>();
	
	//该分支下的所有项目
	private Set<TbccPrjType> prjTypes = new HashSet<TbccPrjType>();

	// Constructors

	public Set<TbccHqType> getHqTypes() {
		return hqTypes;
	}



	public void setHqTypes(Set<TbccHqType> hqTypes) {
		this.hqTypes = hqTypes;
	}



	public Set<TbccAreaType> getAreaTypes() {
		return areaTypes;
	}



	public void setAreaTypes(Set<TbccAreaType> areaTypes) {
		this.areaTypes = areaTypes;
	}



	/** default constructor */
	public TbccBranchType() {
		super();
	}
	
	
	
	public TbccBranchType(Long branchId, Integer branchType,
			String branchName, String branchCode, String branchRelInfo,
			String branchNote) {
		super();
		this.branchId = branchId;
		this.branchType = branchType;
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.branchRelInfo = branchRelInfo;
		this.branchNote = branchNote;
	}




	public Long getBranchId() {
		return this.branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}


	public Integer getBranchType() {
		return this.branchType;
	}

	public void setBranchType(Integer branchType) {
		this.branchType = branchType;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchRelInfo() {
		return this.branchRelInfo;
	}

	public void setBranchRelInfo(String branchRelInfo) {
		this.branchRelInfo = branchRelInfo;
	}

	public String getBranchNote() {
		return this.branchNote;
	}

	public void setBranchNote(String branchNote) {
		this.branchNote = branchNote;
	}
	
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccBranchType))
			return false;
		TbccBranchType castOther = (TbccBranchType) other;
		if(this.getBranchId().equals(castOther.getBranchId()))
				return true ;
		return false ;
	}

	public int hashCode() {
		return this.branchId.hashCode() ;
	}



	public Set<TbccPrjType> getPrjTypes() {
		return prjTypes;
	}



	public void setPrjTypes(Set<TbccPrjType> prjTypes) {
		this.prjTypes = prjTypes;
	}



	public String getBranchDisplayName() {
		return branchDisplayName;
	}



	public void setBranchDisplayName(String branchDisplayName) {
		this.branchDisplayName = branchDisplayName;
	}



	public Blob getBranchLogo() {
		return branchLogo;
	}



	public void setBranchLogo(Blob branchLogo) {
		this.branchLogo = branchLogo;
	}



	public Integer getBranchLogoEnable() {
		return branchLogoEnable;
	}



	public void setBranchLogoEnable(Integer branchLogoEnable) {
		this.branchLogoEnable = branchLogoEnable;
	}



	public Boolean getLogoIsLoad() {
		return logoIsLoad;
	}



	public void setLogoIsLoad(Boolean logoIsLoad) {
		this.logoIsLoad = logoIsLoad;
	}

}