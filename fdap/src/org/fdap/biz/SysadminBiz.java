package org.fdap.biz;

import org.fdap.entity.Fdapuser;

/**
 * 企业下系统管理员配置业务访问接口
 * @author zhoukuanwei
 *
 */
public interface SysadminBiz {
	
	/**
	 * 获取系统管理员信息
	 * @return
	 */
	public abstract Fdapuser getSysadmin();
	
	/**
	 * 修改系统管理员信息的密码
	 * @param password			密码
	 * @return
	 */
	public abstract boolean updateSysadmin(Fdapuser sysadminuser);
}
