package org.tbcc.biz;

import org.tbcc.dao.ParamCarTransportDao;
import org.tbcc.entity.config.TbccParamVehicleTransport;
import org.tbcc.log.entity.TbccLog;

/**
 * 车载运输配置业务接口
 * @author zhaoyou
 *
 */


public interface ParamCarTransportBiz {
		
		/**
		 * 增加一条车载运输信息配置的操作，返回该车载运输信息的标识
		 * @param projectId 车载工程标示
		 * @param source 包含车载信息的字符串 eg:oldValue,newValue;oldValue,newValue
		 * 如果前后值没有发生变化则为 N/A,N/A;格式
		 *  pbeginAddress	
		 *  pendAddress		
		 *  pshipper	
		 *  pcarrier		
		 *  preceiver	 
		 * @param log			日志对象
		 * @return				车载运输信息标识
		 */
		public Long addCarTransport
		(String projectId ,String source,TbccLog log);
		
		/**
		 * 根据标识Id，获取车载运输对象
		 * @param id	标识Id
		 * @return		车载运输对象字符串
		 */
		public String getTransport(Long id) ;
		
		/**
		 *添加一条表示更新操作结果的日志
		 * @param log	
		 * @return
		 */
		public Integer addUpdateResultLog(TbccLog log);
		
		
}
