package org.fdap.entity;

import java.util.Date;

/**
 * Fdaprefactive entity.
 * 
 * @author zhaoyou
 */

public class Fdaprefactive implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer refactiveid;
	private Fdapref fdapref;
	private Integer refactivestate;
	private Date refactivetime;

	// Constructors

	/** default constructor */
	public Fdaprefactive() {
	}

	/** minimal constructor */
	public Fdaprefactive(Integer refactivestate, Date refactivetime) {
		this.refactivestate = refactivestate;
		this.refactivetime = refactivetime;
	}

	/** full constructor */
	public Fdaprefactive(Fdapref fdapref, Integer refactivestate,
			Date refactivetime) {
		this.fdapref = fdapref;
		this.refactivestate = refactivestate;
		this.refactivetime = refactivetime;
	}

	// Property accessors

	public Integer getRefactiveid() {
		return this.refactiveid;
	}

	public void setRefactiveid(Integer refactiveid) {
		this.refactiveid = refactiveid;
	}

	public Fdapref getFdapref() {
		return this.fdapref;
	}

	public void setFdapref(Fdapref fdapref) {
		this.fdapref = fdapref;
	}

	public Integer getRefactivestate() {
		return this.refactivestate;
	}

	public void setRefactivestate(Integer refactivestate) {
		this.refactivestate = refactivestate;
	}

	public Date getRefactivetime() {
		return this.refactivetime;
	}

	public void setRefactivetime(Date refactivetime) {
		this.refactivetime = refactivetime;
	}

}