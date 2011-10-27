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
 * 用户操作的业务接口
 * @author Administrator
 *
 */
public interface UserBiz {
	
	/**
	 * 通过用户标识Id，获取该用户的信息
	 * @param id	用户标识Id
	 * @return
	 */
	public TbccUser getById(Long id);
	
	/**
	 * 通过用户的用户名，和密码，获取用户信息
	 * @param name	用户名
	 * @param password	密码
	 * @return
	 */
	public TbccUser getUserByName(String name,String password) ;
	
	/**
	 * 根据总部用户的标识Id，获取总部用户信息
	 * @param id		总部用户标识Id
	 * @return
	 */
	public TbccHqUser	getByHqId(Long id) ;
	
	/**
	 * 根据总部用户名获取所有的总部用户信息
	 * @param name	总部用户名
	 * @return
	 */
	public List<TbccHqUser> getByHqName(String name) ;
	
	
	/**
	 * 根据账户名获取该账户下的所有总部用户
	 * @param clientId		账户标识Id
	 * @return
	 */
	public List<TbccHqUser>	getHqUserByClientId(Long clientId);
	
	/**
	 * 根据账户名获取该账户下的所有分支用户
	 * @param clientId		账户标识Id
	 * @return
	 */
	public List<TbccUser>	getBranchUserByClientId(Long clientId) ;
	
	/**
	 * 根据用户的功能列表，获取功能列表的字符串
	 * @param list		用户权限的集合
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
