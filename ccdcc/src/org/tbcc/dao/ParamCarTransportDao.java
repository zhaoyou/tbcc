package org.tbcc.dao;

import org.tbcc.entity.config.TbccParamVehicleTransport;

/**
 * 车载运输信息配置数据访问接口
 * @author zhaoyou
 *
 */
public interface ParamCarTransportDao {
		/**
		 * 插入一条车载运输信息记录
		 * @param transport	
		 */
		public void insertCarTransporst(TbccParamVehicleTransport transport) ;
		
		/**
		 * 获取一条车载运输信息记录
		 * @param id		车载运输记录标识
		 * @return			车载运输对象
		 */
		public TbccParamVehicleTransport queryCarTransport(Long id) ;
}
