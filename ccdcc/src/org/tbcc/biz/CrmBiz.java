package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccCrm;

/**
 * �ͻ���ϵ��ҵ��ӿ�
 * @author Administrator
 *
 */
public interface CrmBiz {
		/**
		 * ���ݻ����ı�ʶId����ȡ������Ŀͻ�
		 * @param hid		�ͻ�������ʶId
		 * @return
		 */
		public List<TbccCrm> getByHid(String hid) ;
}
