package org.fdap.entity;

/**
 * FdaprolePower entity.
 * 
 * @author zhaoyou
 */

public class FdaprolePower implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long rpid;
	private Fdaprole fdaprole;
	private Fdappower fdappower;

	// Constructors

	/** default constructor */
	public FdaprolePower() {
	}

	/** full constructor */
	public FdaprolePower(Fdaprole fdaprole, Fdappower fdappower) {
		this.fdaprole = fdaprole;
		this.fdappower = fdappower;
	}

	// Property accessors

	public Long getRpid() {
		return this.rpid;
	}

	public void setRpid(Long rpid) {
		this.rpid = rpid;
	}

	public Fdaprole getFdaprole() {
		return this.fdaprole;
	}

	public void setFdaprole(Fdaprole fdaprole) {
		this.fdaprole = fdaprole;
	}

	public Fdappower getFdappower() {
		return this.fdappower;
	}

	public void setFdappower(Fdappower fdappower) {
		this.fdappower = fdappower;
	}

}