package org.fdap.biz;

import org.fdap.entity.Fdapphone;
/**
 * 电话配置业务逻辑接口
 * @author KW_zhou
 *
 */
public interface PhoneConfigBiz {
	
	/**
	 * 获取电话信息
	 * @return
	 */
	public Fdapphone getPhone();
	
	
	/**
	 * 保存电话信息
	 * @param phoneinfo		电话信息
	 * @return
	 */
	public boolean savePhone(Fdapphone phoneinfo);
}
