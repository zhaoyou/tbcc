package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorSet;

/**
 * �������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface CompressorSetDao {
	/**
	 * ����devId ��ȡ���鼯��
	 * @param devId	�豸Id
	 * @return
	 */
	List<TbccCompressorSet> getByDevId(Integer devId);
	
	/**
	 * ����һ��devId ��������ϵ��ַ������ϡ���ȡ���еĻ��鼯��
	 * @param str	eg: (12,13,14)
	 * @return
	 */
	List<TbccCompressorSet> getByCondition(String str) ;
	
	/**
	 * ���ݻ���ı�ʶId����ȡ������Ϣ
	 * @param id	����ı�ʶId
	 * @return
	 */
	TbccCompressorSet getById(Integer id) ;
}
