package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccClient;

/**
 * ���ǲ����ͻ����͵����ݽӿ�
 * @author Administrator
 *
 */
public interface ClientDao {
	/**
	 * �����˻�������ȡ�˺�����Ӧ���˺�
	 * @param clientName		�˻���
	 * @return
	 */
	public List<TbccClient> getByClientName(String clientName) ;
}
