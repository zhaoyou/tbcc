package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorRealData;

/**
 * ѹ������ҵ��ӿ�
 * @author Administrator
 *
 */
public interface CompressorBiz {
	/**
	 * ���ݻ���ı�ʶId����ȡѹ������ʵʱ����
	 * @param csId		����ı�ʶId
	 * @return
	 */
	List<TbccCompressorRealData> getBycsId(Integer csId) ;
	
	/**
	 * ����ѹ����ͷ�ı�ʶ���ϣ���ѹ������ʵʱ����
	 * @param str	eg: 12,13,14
	 * @return
	 */
	List<TbccCompressorRealData> getByCids(String str) ;
	
	/**
	 * ����ѹ������ı�ʶId����ȡ�û����µ����л�ͷ�ı�ʶId����
	 * @param csId		ѹ������ı�ʶId
	 * @return
	 */
	List<Integer> getIdsByCsId(Integer csId) ;
}
