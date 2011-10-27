package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccAirCoolerRealData;

/**
 * ����ҵ��ӿ�
 * @author Administrator
 *
 */
public interface AirCoolerBiz {
	/**
	 * ���ݻ���ı�ʶId����ȡ������ʵʱ����
	 * @param csId ����ı�ʶId
	 * @return
	 */
	public List<TbccAirCoolerRealData> getBycsId(Integer csId) ;
	
	/**
	 *  ���������ı�ʶ���ϡ���ȡ������ʵʱ����
	 * @param ids		�����ı�ʾ���ϸ�ʽ�ϸ�ƥ�� 12,13,15
	 * @return
	 */
	public List<TbccAirCoolerRealData> getByAids(String ids)  ;
	
	/**
	 * ����ѹ������ı�ʶId����ȡ�û����µ�����������ʶ
	 * @param csId		ѹ������ı�ʶ
	 * @return
	 */
	public List<Integer> getIdsBycsId(Integer csId) ;
	
}
