package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapphone;

public interface PhoneConfigDao {
	/**
	 * 获取电话信息
	 * @return
	 */
	public List<Fdapphone> queryPhone();
	
	
	/**
	 * 保存电话信息
	 * @param phoneinfo		电话信息
	 * @return
	 */
	public boolean updatePhone(Fdapphone phoneinfo);
}
