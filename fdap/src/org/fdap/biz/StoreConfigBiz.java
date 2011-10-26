package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapstoretype;

/**
 * 存储类型配置业务接口
 * @author zhaoyou
 *
 */
public interface StoreConfigBiz {
	
	/**
	 * 获取所有的存储类型
	 * @return
	 */
	public List<Fdapstoretype>	getAll();
	
	/**
	 * 通过存储类型的标识Id获取存储类型
	 * @param storeId				存储类型标识Id
	 * @return				
	 */
	public Fdapstoretype 				getByStoreId(Integer storeId);
	
	/**
	 * 保存一个存储类型
	 * @param storeType			需要保存的存储类型
	 * @return							
	 */
	public boolean 						addStoreType(Fdapstoretype storeType);
	
	/**
	 * 更新一个存储类型
	 * @param storeType			需要更新的存储类型
	 * @return
	 */
	public boolean 						updateStoreType(Fdapstoretype storeType);
	
	/**
	 * 批量删除存储类型
	 * @param storeIds			存储类型的id集合
	 * @return
	 */
	public boolean 						delStoreTypeByStoreIds(String[] storeIds);

}
