package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapuser;

/**
 * 工程人员配置访问接口
 * @author zhoukuanwei
 *
 */
public interface EngineerDao {
	
	/**
	 * 获取工程人员用户信息列表
	 * @return
	 */
	public abstract List<Fdapuser> getEngineer();
	
	/**
	 * 修改工程人员用户的密码
	 * @param user			修改密码后的用户信息对象
	 * @return
	 */
	public boolean upUser(Fdapuser user);
}
