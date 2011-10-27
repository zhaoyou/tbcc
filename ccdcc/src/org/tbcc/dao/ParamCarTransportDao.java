package org.tbcc.dao;

import org.tbcc.entity.config.TbccParamVehicleTransport;

/**
 * ����������Ϣ�������ݷ��ʽӿ�
 * @author zhaoyou
 *
 */
public interface ParamCarTransportDao {
		/**
		 * ����һ������������Ϣ��¼
		 * @param transport	
		 */
		public void insertCarTransporst(TbccParamVehicleTransport transport) ;
		
		/**
		 * ��ȡһ������������Ϣ��¼
		 * @param id		���������¼��ʶ
		 * @return			�����������
		 */
		public TbccParamVehicleTransport queryCarTransport(Long id) ;
}
