package org.fdap.biz;

import org.fdap.entity.Fdapuser;


/**
 * 工程人员配置业务访问接口
 * @author zhoukuanwei
 *
 */
public interface EngineerBiz {
	/**
	 * 获取工程人员用户信息
	 * @return
	 */
	public abstract Fdapuser getEngineer();
	
	/**
	 * 修改工程人员用户的密码
	 * @param password			用户密码
	 * @return
	 */
	public abstract boolean updateEngineer(Fdapuser engineer);
}
