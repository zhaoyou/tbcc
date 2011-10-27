package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccClient;

/**
 * 这是操作客户类型的业务接口
 * @author Administrator
 *
 */
public interface ClientBiz {
	/**
	 * 根据账户名，获取账号信息
	 * @param clientName		账号名
	 * @return
	 */
	public List<TbccClient> getByName(String clientName);
}
