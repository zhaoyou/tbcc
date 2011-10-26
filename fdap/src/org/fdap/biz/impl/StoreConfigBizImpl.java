package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.fdap.biz.StoreConfigBiz;
import org.fdap.dao.StoreConfigDao;
import org.fdap.entity.Fdapstoretype;

/**
 * 存储类型业务配置接口
 * @author zhaoyou
 *
 */
public class StoreConfigBizImpl implements StoreConfigBiz {
	
	private StoreConfigDao storeConfigDao = null ;
	
	

	public StoreConfigDao getStoreConfigDao() {
		return storeConfigDao;
	}

	public void setStoreConfigDao(StoreConfigDao storeConfigDao) {
		this.storeConfigDao = storeConfigDao;
	}

	@Override
	public boolean addStoreType(Fdapstoretype storeType) {
		
		//验证参数
		if(storeType==null){
			return false ;
		}
		//添加一个存储类型
		try {
			this.getStoreConfigDao().insertStoreType(storeType);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}

	@Override
	public boolean delStoreTypeByStoreIds(String[] storeIds) {
		
		//验证参数
		if(storeIds==null || storeIds.length==0){
			return false ;
		}
		//批量删除
		List<Integer> list = new ArrayList<Integer>();
		for (String string : storeIds) {
			list.add(new Integer(string));
		}
		try {
			//验证是否有冷库应用了相应的存储类型
			Long i = this.getStoreConfigDao().queryRefCountByStoreTypeId(list);
			if(i==null || i>0){
				return false ;
			}
			
			this.getStoreConfigDao().deleteStoreTypeByIds(list);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}

	@Override
	public List<Fdapstoretype> getAll() {
		return this.getStoreConfigDao().queryAllStoreType() ;
	}

	@Override
	public Fdapstoretype getByStoreId(Integer storeId) {
		
		//验证参数
		if(storeId==null || storeId.equals("")){
			return null ;
		}
		return this.getStoreConfigDao().queryById(storeId);
	}

	@Override
	public boolean updateStoreType(Fdapstoretype storeType) {
		//验证参数
		if(storeType==null ){
			return false ;
		}
		try {
			this.getStoreConfigDao().updateStoreType(storeType);
		} catch (Exception e) {
			return false ;
		}
			return true ;
	}

}
