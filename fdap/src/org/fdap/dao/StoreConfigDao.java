package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapstoretype;

/**
 * 冷库存储类型配置数据接口
 * @author zhaoyou
 *
 */
public interface StoreConfigDao {
		/**
		 * 获取所有的存储类型
		 * @return
		 */
		public List<Fdapstoretype>	queryAllStoreType();
		
		/**
		 * 根据存储类型标识Id,获取存储对象
		 * @param storeId		存储标识id
		 * @return
		 */
		public Fdapstoretype				queryById(Integer storeId);
		
		/**
		 * 保存一个存储类型
		 * @param storeType		需要添加的存储对象
		 */
		public void 							insertStoreType(Fdapstoretype storeType);
		
		/**
		 * 更新一个存储类型
		 * @param storeType		需要更新的存储类型
		 */
		public void 							updateStoreType(Fdapstoretype storeType) ;
		
		/**
		 * 删除一个存储类型
		 * @param storeId			需要删除的存储类型标识storeId
		 */
		public void 							deleteStoreType(Integer storeId) ;
		
		/**
		 * 根据标识集合删除一些存储类型
		 * @param ids			需要删除的存储类型
		 */
		public void 							deleteStoreTypeByIds(List<Integer> ids);
		
		/**
		 * 根据存储类型的标识Id，获取该类型下的冷库个数
		 * @param ids			存储类型标识Id集合
		 * @return
		 */
		public Long 							queryRefCountByStoreTypeId(List<Integer> ids) ;
}
