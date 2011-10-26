package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapuser;

/**
 * 系统管理员配置访问接口
 * @author zhoukuanwei
 *
 */
public interface SysadminDao {
	
	/**
	 * 获取系统管理员信息列表
	 * @return
	 */
	public abstract List<Fdapuser> getSysadmin();
	
	/**
	 * 修改系统管理员信息的密码
	 * @param  user			修改密码后的用户信息对象
	 * @return
	 */
	public boolean upUser(Fdapuser user);
}
