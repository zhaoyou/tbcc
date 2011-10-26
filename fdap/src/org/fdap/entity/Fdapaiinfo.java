package org.fdap.entity;



/**
 * Fdapaiinfo entity.
 * 
 * @author zhaoyou
 */

public class Fdapaiinfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aiguid;
	private Fdapref fdapref;
	private Integer reid;
	private String name;
	private Integer selftype;
	private Integer location;
	private Double lowerlimit;
	private Integer lowerdelay;
	private Double minlowerlimit;
	private Integer minlowerdelay;
	private Double highlimit;
	private Integer highdelay;
	private Double maxhighlimit;
	private Integer maxhighdelay;

	// Constructors

	/** default constructor */
	public Fdapaiinfo() {
	}

	/** minimal constructor */
	public Fdapaiinfo(Integer reid, String name, Integer selftype,
			Double lowerlimit, Integer lowerdelay, Double highlimit,
			Integer highdelay) {
		this.reid = reid;
		this.name = name;
		this.selftype = selftype;
		this.lowerlimit = lowerlimit;
		this.lowerdelay = lowerdelay;
		this.highlimit = highlimit;
		this.highdelay = highdelay;
	}

	/** full constructor */
	public Fdapaiinfo(Fdapref fdapref, Integer reid, String name,
			Integer selftype, Integer location, Double lowerlimit,
			Integer lowerdelay, Double minlowerlimit, Integer minlowerdelay,
			Double highlimit, Integer highdelay, Double maxhighlimit,
			Integer maxhighdelay) {
		this.fdapref = fdapref;
		this.reid = reid;
		this.name = name;
		this.selftype = selftype;
		this.location = location;
		this.lowerlimit = lowerlimit;
		this.lowerdelay = lowerdelay;
		this.minlowerlimit = minlowerlimit;
		this.minlowerdelay = minlowerdelay;
		this.highlimit = highlimit;
		this.highdelay = highdelay;
		this.maxhighlimit = maxhighlimit;
		this.maxhighdelay = maxhighdelay;
	}

	// Property accessors

	public String getAiguid() {
		return this.aiguid;
	}

	public void setAiguid(String aiguid) {
		this.aiguid = aiguid;
	}

	public Fdapref getFdapref() {
		return this.fdapref;
	}

	public void setFdapref(Fdapref fdapref) {
		this.fdapref = fdapref;
	}

	public Integer getReid() {
		return this.reid;
	}

	public void setReid(Integer reid) {
		this.reid = reid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSelftype() {
		return this.selftype;
	}

	public void setSelftype(Integer selftype) {
		this.selftype = selftype;
	}

	public Integer getLocation() {
		return this.location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Double getLowerlimit() {
		return this.lowerlimit;
	}

	public void setLowerlimit(Double lowerlimit) {
		this.lowerlimit = lowerlimit;
	}

	public Integer getLowerdelay() {
		return this.lowerdelay;
	}

	public void setLowerdelay(Integer lowerdelay) {
		this.lowerdelay = lowerdelay;
	}

	public Double getMinlowerlimit() {
		return this.minlowerlimit;
	}

	public void setMinlowerlimit(Double minlowerlimit) {
		this.minlowerlimit = minlowerlimit;
	}

	public Integer getMinlowerdelay() {
		return this.minlowerdelay;
	}

	public void setMinlowerdelay(Integer minlowerdelay) {
		this.minlowerdelay = minlowerdelay;
	}

	public Double getHighlimit() {
		return this.highlimit;
	}

	public void setHighlimit(Double highlimit) {
		this.highlimit = highlimit;
	}

	public Integer getHighdelay() {
		return this.highdelay;
	}

	public void setHighdelay(Integer highdelay) {
		this.highdelay = highdelay;
	}

	public Double getMaxhighlimit() {
		return this.maxhighlimit;
	}

	public void setMaxhighlimit(Double maxhighlimit) {
		this.maxhighlimit = maxhighlimit;
	}

	public Integer getMaxhighdelay() {
		return this.maxhighdelay;
	}

	public void setMaxhighdelay(Integer maxhighdelay) {
		this.maxhighdelay = maxhighdelay;
	}



}