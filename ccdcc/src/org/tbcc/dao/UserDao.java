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
 * 这个接口主要是用来操作用户的
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 	根据用户的标识主键Id加载用户对象
	 * @param id		用户的标识id
	 * @return
	 */
	public TbccUser get(Long id);
	
	/**
	 * 根据用户名，获取所有的用户对象
	 * @param uname			用户名
	 * @return				用户对象集合
	 */
	public List<TbccUser> getByName(String uname) ;
	
	/**
	 * 根据总部用户的标识Id加载用户对象
	 * @param id		总部用户的标识Id
	 * @return			
	 */
	public TbccHqUser	getHqUser(Long id) ;
	
	/**
	 * 根据总部用户的名字，获取总部用户的集合
	 * @param uname		总部用户名
	 * @return
	 */
	public List<TbccHqUser> getByHqName(String uname) ;
	
	/**
	 * 根据账号名获取该账户下的所有分支用户
	 * @param clientId		账户标识Id
	 * @return
	 */
	public List<TbccUser> getBranchByClientId(Long clientId)  ;
	
	
	/**
	 * 根据账户名获取该账户下的所有总部用户
	 * @param clientId		账户标识Id
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
