package org.tbcc.entity.cool;

import java.util.HashSet;
import java.util.Set;

/**
 * TbccCompressor entity.Ñ¹Ëõ»úÊµÌå
 * 
 * @author administrator
 */

public class TbccCompressor implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbccCompressorSet tbccCompressorSet;
	private String name;

	// Constructors

	/** default constructor */
	public TbccCompressor() {
	}

	/** minimal constructor */
	public TbccCompressor(TbccCompressorSet tbccCompressorSet, String name) {
		this.tbccCompressorSet = tbccCompressorSet;
		this.name = name;
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