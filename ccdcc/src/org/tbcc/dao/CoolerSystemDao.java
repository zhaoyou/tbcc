package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCcapSystemRealData;

/**
 *  这是冷库系统的数据访问接口
 * @author Administrator
 *
 */
public interface CoolerSystemDao {
		
		/**
		 * 根据冷库工程的标识集合，获取制冷系统实时数据
		 * @param str	工程标识Id集合 eg: (1003,1005)
		 * @return
		 */
		public List<TbccCcapSystemRealData>	getByProjectIds(String str) ;
}
