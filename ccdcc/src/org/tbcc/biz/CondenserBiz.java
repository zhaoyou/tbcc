package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCondenserRealData;

/**
 * ������ҵ��ӿ�
 * @author Administrator
 *
 */
public interface CondenserBiz {
		/**
		 * ���ݻ���ı�ʶId����ȡ��������ʵʱ����
		 * @param csId
		 * @return
		 */
		List<TbccCondenserRealData> getBycsId(Integer csId) ;
		
		/**
		 * �����������ı�ʶid���ϣ���ȡ��������ʵʱ����
		 * @param str	eg: 12,13,15
		 * @return
		 */
		List<TbccCondenserRealData> getByCids(String str) ;
		
		/**
		 * ����ѹ������ı�ʶId����ȡ�û����µ������������ı�ʶId����
		 * @param csId		�������ı�ʶid
		 * @return
		 */
		List<Integer> getIdsByCsid(Integer csId) ;
}
