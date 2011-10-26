package org.fdap.entity;

public class Fdapphone {

	private static final long serialVersionUID = 1L;
	
	private Long phoneid;
	private String phonenumber;			//电话号码
	private String messagenumber;		//短信号码
	private String callcode;			//验证码
	private Integer phoneActive;		//是否拨打电话
	private Integer messageActive;		//是否发送短信
	
	public Fdapphone(){super();}
	
	public Fdapphone(Long phoneid,String phonenumber,String messagenumber,String callcode,Integer phoneActive,Integer messageActive){
		super();
		this.phoneid = phoneid;
		this.phonenumber = phonenumber;
		this.messagenumber = messagenumber;
		this.callcode = callcode;
		this.phoneActive = phoneActive;
		this.messageActive = messageActive;
	}

	public Long getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(Long phoneid) {
		this.phoneid = phoneid;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getMessagenumber() {
		return messagenumber;
	}

	public void setMessagenumber(String messagenumber) {
		this.messagenumber = messagenumber;
	}

	public String getCallcode() {
		return callcode;
	}

	public void setCallcode(String callcode) {
		this.callcode = callcode;
	}

	public Integer getPhoneActive() {
		return phoneActive;
	}

	public void setPhoneActive(Integer phoneActive) {
		this.phoneActive = phoneActive;
	}

	public Integer getMessageActive() {
		return messageActive;
	}

	public void setMessageActive(Integer messageActive) {
		this.messageActive = messageActive;
	}
	
}
