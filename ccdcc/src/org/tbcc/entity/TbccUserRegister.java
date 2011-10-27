package org.tbcc.entity;

import java.io.Serializable;

/**
 * 用户注册信息实体
 * @author zhoukuanwei
 *
 */
public class TbccUserRegister implements Serializable {
	public static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String rname;
	private Integer sex;
	//职位
	private String position;
	//行业
	private String profession;
	private String companyname;
	private String companyAddress;
	
	private String email;
	//电话
	private String phone;
	//传真
	private String fax;
	//手机
	private String mobilephone;
	private int isdeal;
	
	private String remark;
	public TbccUserRegister(){
		super();
	}
	public TbccUserRegister(Long id,String rname,Integer sex,String position,String profession,String companyname,
			String companyAddress,String email,String phone,String fax,String mobilephone,int isdeal,String remark){
		super();
		this.id = id;
		this.rname = rname;
		this.sex = sex;
		this.position = position;
		this.profession = profession;
		this.companyname = companyname;
		this.companyAddress = companyAddress;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.mobilephone = mobilephone;
		this.isdeal = isdeal;
		this.remark = remark;
	}
	
	public String getRname() {
		return rname;
	}
	
	public String getPosition() {
		return position;
	}
	public String getProfession() {
		return profession;
	}
	public String getCompanyname() {
		return companyname;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public String getPhone() {
		return phone;
	}
	public String getFax() {
		return fax;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIsdeal() {
		return isdeal;
	}
	public void setIsdeal(int isdeal) {
		this.isdeal = isdeal;
	}
}
