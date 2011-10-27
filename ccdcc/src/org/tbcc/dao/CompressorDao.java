package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorRealData;

/**
 * ѹ����ʵʱ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface CompressorDao {
	/**
	 *  ����ѹ�����ı�ʶId����ȡ��ѹ������ʵʱ����
	 * @param cid		ѹ�����ı�ʶId
	 * @return
	 */
	public List<TbccCompressorRealData> getByCid(Integer cid) ;
	
	/**
	 * ����һ��ѹ������ʶ���ϣ���ȡ��ѹ�������ϵ����� 
	 * @param str	�����������ַ��� eg: (12,13,14)
	 * @return
	 */
	public List<TbccCompressorRealData> getByCondition(String str) ;
	
	/**
	 * ����ѹ������ı�ʶId����ȡ��ѹ�������µ�����ѹ����ͷ�ı�ʶId����
	 * @param csId			ѹ�����ı�ʶId
	 * @return
	 */
	public List<Integer>	getIdsByCsId(Integer csId) ;
}
