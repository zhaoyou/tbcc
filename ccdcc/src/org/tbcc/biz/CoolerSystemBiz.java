package org.tbcc.biz;

import org.tbcc.entity.cool.TbccCcapSystemRealData;

/**
 * ����ϵͳҵ����
 * @author Administrator
 *
 */
public interface CoolerSystemBiz {
		/**
		 * ������⹤�̵ı�ʶ���ϡ���ȡ����ϵͳʵʱ����
		 * @param str		eg: (1002,1004)
		 * @return		
		 */
		public TbccCcapSystemRealData	getByProjectId(String[] str) ;
}
