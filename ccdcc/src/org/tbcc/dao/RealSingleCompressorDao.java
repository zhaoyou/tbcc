package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccSingleCompressorRealData;

/**
 * ��������ʵʱ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface RealSingleCompressorDao {
	/**
	 * ������������ı�ʶId����ȡ���������ʵʱ����
	 * @param cId
	 * @return
	 */
	public List<TbccSingleCompressorRealData>	get(Integer cId) ;
}
