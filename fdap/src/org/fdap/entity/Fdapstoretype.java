package org.fdap.entity;



/**
 * Fdapstoretype entity.
 * 
 * @author zhaoyou
 */

public class Fdapstoretype implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer storeid;
	private String name;
	private Double templowerlimit;
	private Integer templowerdelay;
	private Double tempminlowerlimit;
	private Integer tempminlowerdelay;
	private Double temphighlimit;
	private Integer temphighdelay;
	private Double tempmaxhighlimit;
	private Integer tempmaxhighdelay;
	private Double humlowerlimit;
	private Integer humlowerdelay;
	private Double humhighlimit;
	private Integer humhighdelay;
	private String remark;

	// Constructors

	/** default constructor */
	public Fdapstoretype() {
	}
	
	public Fdapstoretype(Integer storeid,String name){
		this.storeid = storeid ;
		this.name = name ;
	}

	/** minimal constructor */
	public Fdapstoretype(String name, Double templowerlimit,
			Integer templowerdelay, Double tempminlowerlimit,
			Integer tempminlowerdelay, Double temphighlimit,
			Integer temphighdelay, Double tempmaxhighlimit,
			Integer tempmaxhighdelay, Double humlowerlimit,
			Integer humlowerdelay, Double humhighlimit, Integer humhighdelay) {
		this.name = name;
		this.templowerlimit = templowerlimit;
		this.templowerdelay = templowerdelay;
		this.tempminlowerlimit = tempminlowerlimit;
		this.tempminlowerdelay = tempminlowerdelay;
		this.temphighlimit = temphighlimit;
		this.temphighdelay = temphighdelay;
		this.tempmaxhighlimit = tempmaxhighlimit;
		this.tempmaxhighdelay = tempmaxhighdelay;
		this.humlowerlimit = humlowerlimit;
		this.humlowerdelay = humlowerdelay;
		this.humhighlimit = humhighlimit;
		this.humhighdelay = humhighdelay;
	}

	/** full constructor */
	public Fdapstoretype(String name, Double templowerlimit,
			Integer templowerdelay, Double tempminlowerlimit,
			Integer tempminlowerdelay, Double temphighlimit,
			Integer temphighdelay, Double tempmaxhighlimit,
			Integer tempmaxhighdelay, Double humlowerlimit,
			Integer humlowerdelay, Double humhighlimit, Integer humhighdelay,
			String remark) {
		this.name = name;
		this.templowerlimit = templowerlimit;
		this.templowerdelay = templowerdelay;
		this.tempminlowerlimit = tempminlowerlimit;
		this.tempminlowerdelay = tempminlowerdelay;
		this.temphighlimit = temphighlimit;
		this.temphighdelay = temphighdelay;
		this.tempmaxhighlimit = tempmaxhighlimit;
		this.tempmaxhighdelay = tempmaxhighdelay;
		this.humlowerlimit = humlowerlimit;
		this.humlowerdelay = humlowerdelay;
		this.humhighlimit = humhighlimit;
		this.humhighdelay = humhighdelay;
		this.remark = remark;
	}

	// Property accessors

	public Integer getStoreid() {
		return this.storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTemplowerlimit() {
		return this.templowerlimit;
	}

	public void setTemplowerlimit(Double templowerlimit) {
		this.templowerlimit = templowerlimit;
	}

	public Integer getTemplowerdelay() {
		return this.templowerdelay;
	}

	public void setTemplowerdelay(Integer templowerdelay) {
		this.templowerdelay = templowerdelay;
	}

	public Double getTempminlowerlimit() {
		return this.tempminlowerlimit;
	}

	public void setTempminlowerlimit(Double tempminlowerlimit) {
		this.tempminlowerlimit = tempminlowerlimit;
	}

	public Integer getTempminlowerdelay() {
		return this.tempminlowerdelay;
	}

	public void setTempminlowerdelay(Integer tempminlowerdelay) {
		this.tempminlowerdelay = tempminlowerdelay;
	}

	public Double getTemphighlimit() {
		return this.temphighlimit;
	}

	public void setTemphighlimit(Double temphighlimit) {
		this.temphighlimit = temphighlimit;
	}

	public Integer getTemphighdelay() {
		return this.temphighdelay;
	}

	public void setTemphighdelay(Integer temphighdelay) {
		this.temphighdelay = temphighdelay;
	}

	public Double getTempmaxhighlimit() {
		return this.tempmaxhighlimit;
	}

	public void setTempmaxhighlimit(Double tempmaxhighlimit) {
		this.tempmaxhighlimit = tempmaxhighlimit;
	}

	public Integer getTempmaxhighdelay() {
		return this.tempmaxhighdelay;
	}

	public void setTempmaxhighdelay(Integer tempmaxhighdelay) {
		this.tempmaxhighdelay = tempmaxhighdelay;
	}

	public Double getHumlowerlimit() {
		return this.humlowerlimit;
	}

	public void setHumlowerlimit(Double humlowerlimit) {
		this.humlowerlimit = humlowerlimit;
	}

	public Integer getHumlowerdelay() {
		return this.humlowerdelay;
	}

	public void setHumlowerdelay(Integer humlowerdelay) {
		this.humlowerdelay = humlowerdelay;
	}

	public Double getHumhighlimit() {
		return this.humhighlimit;
	}

	public void setHumhighlimit(Double humhighlimit) {
		this.humhighlimit = humhighlimit;
	}

	public Integer getHumhighdelay() {
		return this.humhighdelay;
	}

	public void setHumhighdelay(Integer humhighdelay) {
		this.humhighdelay = humhighdelay;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}