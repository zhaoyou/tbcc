package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccMultiCompressorRealData;

/**
 * ��������ʵʱ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface RealMulCompressorDao {
	/**
	 * ���ݻ���ı�ʶ��ȡ�û����ʵʱ����
	 * @param csId		����ı�ʶID
	 * @return
	 */
	List<TbccMultiCompressorRealData> getByCsId(Integer csId) ;
}
