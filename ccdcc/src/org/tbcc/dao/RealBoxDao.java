package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealBox;

/**
 * ʵʱС�������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface RealBoxDao {
	
	/**
	 * ����С�����ʶId���ϣ���ȡС����ʵʱ���ݼ���
	 * @param condition	eg: 	(12,13,13)
	 * @return
	 */
	public  List<TbccBaseRealBox> getRealboxData(String condition) ;
}
