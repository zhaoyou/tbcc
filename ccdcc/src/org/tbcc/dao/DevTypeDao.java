package org.tbcc.dao;

import org.tbcc.entity.TbccDevType;

/**
 * �豸���ݷ��ʽӿ�
 * @author zhaoyou
 *
 */
public interface DevTypeDao {
	/**
	 * ͨ����ʶId����ȡ�豸����
	 * @param id
	 * @return
	 */
	public  TbccDevType getById(Long id) ;
}
