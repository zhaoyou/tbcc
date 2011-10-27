package org.tbcc.biz;

import org.tbcc.dao.ParamCarTransportDao;
import org.tbcc.entity.config.TbccParamVehicleTransport;
import org.tbcc.log.entity.TbccLog;

/**
 * ������������ҵ��ӿ�
 * @author zhaoyou
 *
 */


public interface ParamCarTransportBiz {
		
		/**
		 * ����һ������������Ϣ���õĲ��������ظó���������Ϣ�ı�ʶ
		 * @param projectId ���ع��̱�ʾ
		 * @param source ����������Ϣ���ַ��� eg:oldValue,newValue;oldValue,newValue
		 * ���ǰ��ֵû�з����仯��Ϊ N/A,N/A;��ʽ
		 *  pbeginAddress	
		 *  pendAddress		
		 *  pshipper	
		 *  pcarrier		
		 *  preceiver	 
		 * @param log			��־����
		 * @return				����������Ϣ��ʶ
		 */
		public Long addCarTransport
		(String projectId ,String source,TbccLog log);
		
		/**
		 * ���ݱ�ʶId����ȡ�����������
		 * @param id	��ʶId
		 * @return		������������ַ���
		 */
		public String getTransport(Long id) ;
		
		/**
		 *���һ����ʾ���²����������־
		 * @param log	
		 * @return
		 */
		public Integer addUpdateResultLog(TbccLog log);
		
		
}
