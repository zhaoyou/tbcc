package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccClient;

/**
 * ���ǲ����ͻ����͵�ҵ��ӿ�
 * @author Administrator
 *
 */
public interface ClientBiz {
	/**
	 * �����˻�������ȡ�˺���Ϣ
	 * @param clientName		�˺���
	 * @return
	 */
	public List<TbccClient> getByName(String clientName);
}
