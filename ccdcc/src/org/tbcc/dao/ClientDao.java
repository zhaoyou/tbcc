package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccClient;

/**
 * 这是操作客户类型的数据接口
 * @author Administrator
 *
 */
public interface ClientDao {
	/**
	 * 根据账户名，获取账号名对应的账号
	 * @param clientName		账户名
	 * @return
	 */
	public List<TbccClient> getByClientName(String clientName) ;
}
