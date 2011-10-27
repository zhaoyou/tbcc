package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.ClientBiz;
import org.tbcc.dao.ClientDao;
import org.tbcc.entity.TbccClient;

/**
 * �����ͻ����͵�ҵ��ʵ����
 * @author Administrator
 *
 */
public class ClientBizImpl implements ClientBiz {
	
	/**
	 * ��springע��ͻ����ݷ��ʽӿ�
	 */
	private ClientDao clientDao = null ;
	
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	
	
	public List<TbccClient> getByName(String clientName) {
		return this.clientDao.getByClientName(clientName);
	}

}
