package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccDataRoles;
import org.tbcc.entity.TbccFunction;
import org.tbcc.entity.TbccHqRoles;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.entity.TbccUser;

/**
 * ����ӿ���Ҫ�����������û���
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 	�����û��ı�ʶ����Id�����û�����
	 * @param id		�û��ı�ʶid
	 * @return
	 */
	public TbccUser get(Long id);
	
	/**
	 * �����û�������ȡ���е��û�����
	 * @param uname			�û���
	 * @return				�û����󼯺�
	 */
	public List<TbccUser> getByName(String uname) ;
	
	/**
	 * �����ܲ��û��ı�ʶId�����û�����
	 * @param id		�ܲ��û��ı�ʶId
	 * @return			
	 */
	public TbccHqUser	getHqUser(Long id) ;
	
	/**
	 * �����ܲ��û������֣���ȡ�ܲ��û��ļ���
	 * @param uname		�ܲ��û���
	 * @return
	 */
	public List<TbccHqUser> getByHqName(String uname) ;
	
	/**
	 * �����˺�����ȡ���˻��µ����з�֧�û�
	 * @param clientId		�˻���ʶId
	 * @return
	 */
	public List<TbccUser> getBranchByClientId(Long clientId)  ;
	
	
	/**
	 * �����˻�����ȡ���˻��µ������ܲ��û�
	 * @param clientId		�˻���ʶId
	 * @return
	 */
	public List<TbccHqUser> getHqByClientId(Long clientId);
	
	
	
	/***ouyang implement starting***********************************************************/
	public boolean addUser(TbccUser user);
	
	public boolean delUser(Long id[]);
	
	public boolean upUser(TbccUser user);
	
	public List<TbccUser> getUserByClientId(Long clientId);
	
	public List<TbccFunction> getUserFunction(TbccBaseUser user);
	
	public List<TbccDataRoles> getDataRolesByBranchId(Long branchId);
	
	public TbccDataRoles getDataRolesById(int id);
	
	public List<TbccUser> getSysAdmin();
	
	public TbccBranchType getBranchById(Long id);
	
	
	/*
	 * configHquser
	 */
	public TbccHqUser getHqUserById(Long id);
	
	public TbccHqRoles getHqRoleById(Long HqRoleId);
	 
	public List<TbccHqUser> getHqUserbyClientId(Long clientId);
	
	public boolean addHqUser(TbccHqUser user);
	
	public boolean delHqUser(Long id[]);
	
	public boolean upHqUser(TbccHqUser user);
	
	public TbccHqUser getHqUserByClientIdAndUserName(Long clientId,String name);
	
	public List<TbccHqRoles> getHqRolesByHqId(Long HqId);
	
	public List<TbccFunction> getHqUserFunction(TbccBaseUser user, Long branchId);
	
	
/***ouyang implement ended*************************************************************/	
	
}
