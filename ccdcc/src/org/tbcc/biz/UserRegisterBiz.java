package org.tbcc.biz;

import org.tbcc.entity.TbccUserRegister;

/**
 * �û�ע���ҵ��ӿ�
 * @author zhoukuanwei
 */
public interface UserRegisterBiz {
	/**
	 * �û�ע��
	 * @param ruser				ע���û���Ϣ
	 * @return					ע�����״̬�Ƿ�ɹ�
	 */
	public boolean addregister(TbccUserRegister ruser);
	/**
	 * �ж��û����Ƿ��Ѵ���
	 * @param rname				�û���
	 * @param companyname		��˾����
	 * @return					�Ƿ��Ѵ���:ture�Ѵ��ڣ�false��û��
	 */
	public boolean getExists(String rname,String companyname);
	
}
