package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccUserRegister;
/**
 * �û�ע�����ݷ��ʽӿ�
 * @author zhoukuanwei
 *
 */
public interface UserRegisterDao {
	/**
	 * �û�ע��
	 * @param ruser			ע���û���Ϣ
	 * @return
	 */
	public boolean addregister(TbccUserRegister ruser);
	
	/**
	 * �����û�����ѯע���û���Ϣ
	 * @param rname			�û���
	 * @param companyname	��˾����
	 * @return				����ע���û���Ϣ����
	 */
	public List<TbccUserRegister> queryUserRegisterByName(String rname ,String companyname);
}
