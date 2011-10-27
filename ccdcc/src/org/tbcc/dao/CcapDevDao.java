package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCcapDevType;

/**
 * 制冷设备数据访问接口
 * @author Administrator
 *
 */
public interface CcapDevDao {
	/**
	 * 根据projectId ，获取设备集合
	 * @param projectId	工程Id
	 * @return
	 */
	List<TbccCcapDevType> getByProjectId(String projectId) ;
	
	/**
	 * 根据冷库工程集合，获取对应的制冷设备集合(解决多冷库工程的方案)
	 * @param str	eg: ('1002','1004')
	 * @return
	 */
	List<TbccCcapDevType> getByCondition(String str) ;
}
