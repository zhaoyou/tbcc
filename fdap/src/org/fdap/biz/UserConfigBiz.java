package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdaprole;
import org.fdap.entity.Fdapuser;

/**
 * 用户配置业务接口
 * @author zhoukuanwei
 *
 */
public interface UserConfigBiz {
	/**
	 * 通过企业标识Id，获取该企业下所有非工程人员和非系统管理员的用户信息列表
	 * @param oid			企业标识id
	 * @return
	 */
	public abstract List<Fdapuser> getOrguser(String oid);
	
	/**
	 * 获取所有机构和企业的JS关系树
	 * @return
	 */
	public abstract String getOrgTree();
	
	/**
	 * 添加用户信息
	 * @param fuser			用户信息对象
	 * @return
	 */
	public abstract boolean addUser(Fdapuser fuser);
	
	/**
	 * 修改用户信息
	 * @param fuser			用户信息对象
	 * @return
	 */
	public abstract boolean updateUser(Fdapuser fuser);
	
	/**
	 * 根据系统角色rid，获取系统角色信息
	 * @param rid			系统角色标识ID
	 * @return
	 */
	public abstract Fdaprole getRoleByRid(String rid);
	
	/**
	 * 根据数组中的用户信息标识删除用户信息
	 * @param delId			用户信息标识的数组
	 * @return
	 */
	public abstract boolean delUser(String[] delId);
	
	/**
	 * 根据用户信息标识userid，获取用户信息
	 * @param userid			用户信息标识ID
	 * @return
	 */
	public abstract Fdapuser getUserById(String userid);
}
