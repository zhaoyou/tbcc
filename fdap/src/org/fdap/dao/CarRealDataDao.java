package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapcarrealdata;

/**
 * 车载实时数据访问接口
 * @author zhaoyou
 *
 */
public interface CarRealDataDao {
	
		/**
		 * 通过车载工程标识Id集合，获取所有的车载实时数据
		 * @param projectIds
		 * @return
		 */
		public List<Fdapcarrealdata> 	queryByProjectIds(List<Long> projectIds);

}
