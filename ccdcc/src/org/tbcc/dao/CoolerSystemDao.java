package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCcapSystemRealData;

/**
 *  �������ϵͳ�����ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface CoolerSystemDao {
		
		/**
		 * ������⹤�̵ı�ʶ���ϣ���ȡ����ϵͳʵʱ����
		 * @param str	���̱�ʶId���� eg: (1003,1005)
		 * @return
		 */
		public List<TbccCcapSystemRealData>	getByProjectIds(String str) ;
}
