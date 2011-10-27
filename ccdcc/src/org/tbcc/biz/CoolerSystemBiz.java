package org.tbcc.biz;

import org.tbcc.entity.cool.TbccCcapSystemRealData;

/**
 * 制冷系统业务类
 * @author Administrator
 *
 */
public interface CoolerSystemBiz {
		/**
		 * 根据冷库工程的标识集合、获取制冷系统实时数据
		 * @param str		eg: (1002,1004)
		 * @return		
		 */
		public TbccCcapSystemRealData	getByProjectId(String[] str) ;
}
