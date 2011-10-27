package org.tbcc.entity.cool;

import java.util.HashSet;
import java.util.Set;

/**
 * TbccCondenser entity.ÀäÄýÆ÷ÊµÌå
 * 
 * @author administrator
 */

public class TbccCondenser implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbccCompressorSet tbccCompressorSet;
	private String name;
	private Integer netId ;

	// Constructors



	public Integer getNetId() {
		return netId;
	}

	public void setNetId(Integer netId) {
		this.netId = netId;
	}

	/** default constructor */
	public TbccCondenser() {
	}

	/** minimal constructor */
	public TbccCondenser(
			TbccCompressorSet tbccCompressorSet, String name,Integer netId) {
		this.tbccCompressorSet = tbccCompressorSet;
		this.name = name;
		this.netId = netId ;
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbccCompressorSet getTbccCompressorSet() {
		return this.tbccCompressorSet;
	}

	public void setTbccCompressorSet(TbccCompressorSet tbccCompressorSet) {
		this.tbccCompressorSet = tbccCompressorSet;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}