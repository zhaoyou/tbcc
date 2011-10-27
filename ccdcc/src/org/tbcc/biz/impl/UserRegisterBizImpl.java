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
 * �û�ע��ҵ��ʵ����
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
	            //�����Ự  
	            Session session = Session.getInstance(p);
	            Message msg = new MimeMessage(session); //������Ϣ
	   
	            msg.setFrom(new InternetAddress("service@thermoberg.com")); //������
	            msg.setRecipient(Message.RecipientType.TO,  
	                             new InternetAddress(client)); //�ռ���
	   
	            msg.setSentDate(new Date()); // ��������
	            msg.setSubject("�Ϻ�˼��Դ�����Ƽ����޹�˾"); // ����
	            msg.setText("ע��ɹ�����л��ʹ��˼��Դ�����������磬�ͷ�������Ա���ᾡ��������ϵ����ͨ˼��Դ�������������ʻ���лл!"); //����  
	            // �ʼ�������������֤
	            Transport tran = session.getTransport("smtp");
	            tran.connect("smtp.thermoberg.com", "service", "102429jia");
	            
	            tran.sendMessage(msg, msg.getAllRecipients()); // ���� 
	            System.out.println("�ʼ����ͳɹ�");
	   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }  
	}
	
}
