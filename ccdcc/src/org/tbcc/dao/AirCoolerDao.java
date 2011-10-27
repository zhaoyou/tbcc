package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccAirCoolerRealData;


/**
 *  ����ʵʱ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface AirCoolerDao {
		/**
		 * ���������ı�ʶId����ȡ������ʵʱ����
		 * @param cid		�����ı�ʶId
		 * @return
		 */
		public List<TbccAirCoolerRealData>	getByCid(Integer cid);
		
		/**
		 * ����һ�����������������ʶId����ȡһ������ʵʱ����
		 * @param str	eg:(12,13,14)
		 * @return
		 */
		public List<TbccAirCoolerRealData> getByCondition(String str);
		
		
		/**
		 * ����ѹ������ı�ʶId����ȡ�������豸�µ����������ı�ʶ
		 * @param csId			ѹ������ı�ʶId
		 * @return
		 */
		public List<Integer> getIdsByCsId(Integer csId) ;
}
