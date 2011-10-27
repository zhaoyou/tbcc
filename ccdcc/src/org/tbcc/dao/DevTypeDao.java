package org.tbcc.dao;

import org.tbcc.entity.TbccDevType;

/**
 * 设备数据访问接口
 * @author zhaoyou
 *
 */
public interface DevTypeDao {
	/**
	 * 通过标识Id，获取设备对象
	 * @param id
	 * @return
	 */
	public  TbccDevType getById(Long id) ;
}
