package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.ClientBiz;
import org.tbcc.dao.ClientDao;
import org.tbcc.entity.TbccClient;

/**
 * 操作客户类型的业务实现类
 * @author Administrator
 *
 */
public class ClientBizImpl implements ClientBiz {
	
	/**
	 * 由spring注入客户数据访问接口
	 */
	private ClientDao clientDao = null ;
	
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	
	
	public List<TbccClient> getByName(String clientName) {
		return this.clientDao.getByClientName(clientName);
	}

}
