package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccDataRoles;
import org.tbcc.entity.TbccFunction;
import org.tbcc.entity.TbccHqRoles;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.entity.TbccUser;

/**
 * �û�������ҵ��ӿ�
 * @author Administrator
 *
 */
public interface UserBiz {
	
	/**
	 * ͨ���û���ʶId����ȡ���û�����Ϣ
	 * @param id	�û���ʶId
	 * @return
	 */
	public TbccUser getById(Long id);
	
	/**
	 * ͨ���û����û����������룬��ȡ�û���Ϣ
	 * @param name	�û���
	 * @param password	����
	 * @return
	 */
	public TbccUser getUserByName(String name,String password) ;
	
	/**
	 * �����ܲ��û��ı�ʶId����ȡ�ܲ��û���Ϣ
	 * @param id		�ܲ��û���ʶId
	 * @return
	 */
	public TbccHqUser	getByHqId(Long id) ;
	
	/**
	 * �����ܲ��û�����ȡ���е��ܲ��û���Ϣ
	 * @param name	�ܲ��û���
	 * @return
	 */
	public List<TbccHqUser> getByHqName(String name) ;
	
	
	/**
	 * �����˻�����ȡ���˻��µ������ܲ��û�
	 * @param clientId		�˻���ʶId
	 * @return
	 */
	public List<TbccHqUser>	getHqUserByClientId(Long clientId);
	
	/**
	 * �����˻�����ȡ���˻��µ����з�֧�û�
	 * @param clientId		�˻���ʶId
	 * @return
	 */
	public List<TbccUser>	getBranchUserByClientId(Long clientId) ;
	
	/**
	 * �����û��Ĺ����б���ȡ�����б���ַ���
	 * @param list		�û�Ȩ�޵ļ���
	 * @return
	 */
	public String getPower(List<TbccFunction> list) ;
	
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
	
	/**
	 * HqUser
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
	/***ouyang implement ended***********************************************************/
}
