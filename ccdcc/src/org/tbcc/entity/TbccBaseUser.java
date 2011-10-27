package org.tbcc.entity;

import java.io.Serializable;

/**
 * 这是一个通用的用户类对象
 * @author Administrator
 *
 */
public class TbccBaseUser implements Serializable {
	
	
	private Long id;
	private String uname;
	private String upassword;
	

	private Integer roleId;
	//关联一个客户类
	private TbccClient client ;
	private Integer uenable;
	private String ucreated;
	
	public TbccBaseUser(){
		super();
	}
	
	
	public TbccBaseUser(Long id, String uname, String upassword,
			Integer roleId, TbccClient client, Integer uenable, String ucreated) {
		super();
		this.id = id;
		this.uname = uname;
		this.upassword = upassword;
		this.roleId = roleId;
		this.client = client;
		this.uenable = uenable;
		this.ucreated = ucreated;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public TbccClient getClient() {
		return client;
	}
	public void setClient(TbccClient client) {
		this.client = client;
	}
	public Integer getUenable() {
		return uenable;
	}
	public void setUenable(Integer uenable) {
		this.uenable = uenable;
	}
	public String getUcreated() {
		return ucreated;
	}
	public void setUcreated(String ucreated) {
		this.ucreated = ucreated;
	}
		
}
