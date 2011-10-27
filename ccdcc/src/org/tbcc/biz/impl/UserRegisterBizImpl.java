package org.tbcc.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;  

import org.tbcc.biz.UserRegisterBiz;
import org.tbcc.dao.UserRegisterDao;
import org.tbcc.entity.TbccUserRegister;
/**
 * 用户注册业务实现类
 * @author zhoukuanwei
 *
 */
public class UserRegisterBizImpl implements UserRegisterBiz {
	private UserRegisterDao userregisterdao;
	
	public UserRegisterDao getUserregisterdao() {
		return userregisterdao;
	}

	public void setUserregisterdao(UserRegisterDao userregisterdao) {
		this.userregisterdao = userregisterdao;
	}

	
	public boolean addregister(TbccUserRegister ruser) {
		boolean b = userregisterdao.addregister(ruser);
		if(b){
			sendMail(ruser.getEmail());
		}
		return b;
	}

	
	public boolean getExists(String rname,String companyname) {
		List<TbccUserRegister> rlist = userregisterdao.queryUserRegisterByName(rname,companyname);
		if(rlist==null||rlist.size()==0){
			return false;
		}
		return true;
	}
	
	
	public void sendMail(String client){
		try {
	           Properties p = new Properties();
	            p.put("mail.smtp.auth", "true");
	            p.put("mail.transport.protocol", "smtp");
	            p.put("mail.smtp.host", "smtp.thermoberg.com");
	            p.put("mail.smtp.port", "25");
	            //建立会话  
	            Session session = Session.getInstance(p);
	            Message msg = new MimeMessage(session); //建立信息
	   
	            msg.setFrom(new InternetAddress("service@thermoberg.com")); //发件人
	            msg.setRecipient(Message.RecipientType.TO,  
	                             new InternetAddress(client)); //收件人
	   
	            msg.setSentDate(new Date()); // 发送日期
	            msg.setSubject("上海思博源冷链科技有限公司"); // 主题
	            msg.setText("注册成功，感谢您使用思博源冷链服务网络，客服中心人员将会尽快与您联系，开通思博源冷链服务中心帐户，谢谢!"); //内容  
	            // 邮件服务器进行验证
	            Transport tran = session.getTransport("smtp");
	            tran.connect("smtp.thermoberg.com", "service", "102429jia");
	            
	            tran.sendMessage(msg, msg.getAllRecipients()); // 发送 
	            System.out.println("邮件发送成功");
	   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }  
	}
	
}
