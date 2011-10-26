package org.fdap.entity;


/**
 * Fdaporg entity.
 * 
 * @author zhaoyou
 */

public class Fdaporg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long oid;
	private String name;
	private String account;
	private String telephone;
	private String email;
	private Long parentId;
	private Integer flag;
	private Integer nodetype;
	private Integer isshowmap;
	private String remark;
	
	private Fdapauthcode authcode ;
	
	


	// Constructors

	public Fdapauthcode getAuthcode() {
		return authcode;
	}

	public void setAuthcode(Fdapauthcode authcode) {
		this.authcode = authcode;
	}

	/** default constructor */
	public Fdaporg() {
	}

	public Fdaporg(Long oid){
		this.oid = oid ;
	}
	
	/** minimal constructor */
	public Fdaporg(String name, String account, Long parentId, Integer flag,
			Integer nodetype) {
		this.name = name;
		this.account = account;
		this.parentId = parentId;
		this.flag = flag;
		this.nodetype = nodetype;
	}

	/** full constructor */
	public Fdaporg(String name, String account, String telephone, String email,
			Long parentId, Integer flag, Integer nodetype,Integer isshowmap, String remark) {
		this.name = name;
		this.account = account;
		this.telephone = telephone;
		this.email = email;
		this.parentId = parentId;
		this.flag = flag;
		this.nodetype = nodetype;
		this.isshowmap = isshowmap;
		this.remark = remark;
	}

	// Property accessors

	public Long getOid() {
		return this.oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getNodetype() {
		return this.nodetype;
	}

	public void setNodetype(Integer nodetype) {
		this.nodetype = nodetype;
	}
	
	public Integer getIsshowmap() {
		return isshowmap;
	}

	public void setIsshowmap(Integer isshowmap) {
		this.isshowmap = isshowmap;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	

}