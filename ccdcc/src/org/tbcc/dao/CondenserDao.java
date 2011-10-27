package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCondenserRealData;

/**
 * ������ʵʱ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface CondenserDao {
	/**
	 * �����������ı�ʶId��ȡ����������ʵʱ����
	 * @param cid	�������ı�ʶId
	 * @return
	 */
	public List<TbccCondenserRealData> getByCid(Integer cid);
	
	/**
	 * ����������ȡһ����������ʵʱ�������
	 * @param str	�����������������ı�ʶ eg: (12,13,14)
	 * @return
	 */
	public List<TbccCondenserRealData> getByCondition(String str) ;
	
	/**
	 *  ����ѹ������ı�ʶId����ȡ�û����µ��������ı�ʶ����
	 * @param csId		ѹ�����ı�ʶId
	 * @return
	 */
	public List<Integer>	getIdsByCsId(Integer csId);
}
