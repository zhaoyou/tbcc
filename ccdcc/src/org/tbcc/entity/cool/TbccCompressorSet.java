package org.tbcc.entity.cool;

import java.util.HashSet;
import java.util.Set;

/**
 * TbccCompressorSet entity.机组实体
 * 
 * @author administrator
 */

public class TbccCompressorSet implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbccCcapDevType tbccCcapDevType;
	private String csName;
	private Short csType;
	private Short csCount;
	
	private Set<TbccAirCooler> tbccAirCoolers = new HashSet<TbccAirCooler>(0);
	private Set<TbccCompressor> tbccCompressors = new HashSet<TbccCompressor>(0);
	private Set<TbccCondenser> tbccCondensers = new HashSet<TbccCondenser>(0);


	// Constructors

	/** default constructor */
	public TbccCompressorSet() {
	}

	/** minimal constructor */
	public TbccCompressorSet(TbccCcapDevType tbccCcapDevType, String csName,
			Short csType, Short csCount) {
		this.tbccCcapDevType = tbccCcapDevType;
		this.csName = csName;
		this.csType = csType;
		this.csCount = csCount;
	}

	/** full constructor */
	public TbccCompressorSet(TbccCcapDevType tbccCcapDevType, String csName,
			Short csType, Short csCount, Set<TbccAirCooler> tbccAirCoolers,
			 Set<TbccCompressor> tbccCompressors,
			Set<TbccCondenser> tbccCondensers) {
		this.tbccCcapDevType = tbccCcapDevType;
		this.csName = csName;
		this.csType = csType;
		this.csCount = csCount;
		this.tbccAirCoolers = tbccAirCoolers;
		this.tbccCompressors = tbccCompressors;
		this.tbccCondensers = tbccCondensers;

	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbccCcapDevType getTbccCcapDevType() {
		return this.tbccCcapDevType;
	}

	public void setTbccCcapDevType(TbccCcapDevType tbccCcapDevType) {
		this.tbccCcapDevType = tbccCcapDevType;
	}

	public String getCsName() {
		return this.csName;
	}

	public void setCsName(String csName) {
		this.csName = csName;
	}

	public Short getCsType() {
		return this.csType;
	}

	public void setCsType(Short csType) {
		this.csType = csType;
	}

	public Short getCsCount() {
		return this.csCount;
	}

	public void setCsCount(Short csCount) {
		this.csCount = csCount;
	}

	public Set<TbccAirCooler> getTbccAirCoolers() {
		return this.tbccAirCoolers;
	}

	public void setTbccAirCoolers(Set<TbccAirCooler> tbccAirCoolers) {
		this.tbccAirCoolers = tbccAirCoolers;
	}


	public Set<TbccCompressor> getTbccCompressors() {
		return this.tbccCompressors;
	}

	public void setTbccCompressors(Set<TbccCompressor> tbccCompressors) {
		this.tbccCompressors = tbccCompressors;
	}

	public Set<TbccCondenser> getTbccCondensers() {
		return this.tbccCondensers;
	}

	public void setTbccCondensers(Set<TbccCondenser> tbccCondensers) {
		this.tbccCondensers = tbccCondensers;
	}


}