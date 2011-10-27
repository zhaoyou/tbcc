package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccRefInfo;

/**
 * 这是冷库信息的数据访问接口
 * @author Administrator
 *
 */
public interface RefInfoDao {
	/**
	 * 根据工程标识Id，获取该工程下的所有冷库列表
	 * @param projectId		工程标识Id
	 * @return
	 */
	public List<TbccRefInfo> getRefByProId(String projectId);
	
	/**
	 * 根据工程标识Id、网络设备Id、以及冷库编号获取某个冷库信息
	 * @param proId		工程标识Id
	 * @param netId		网络设备Id
	 * @param refId		冷库编号
	 * @return
	 */
	public List<TbccRefInfo> getRef(String proId,String netId,String refId);
	
	/**
	 * 根据主键加载冷库对象
	 * @param id	冷库的标识Id
	 * @return
	 */
	public TbccRefInfo get(Long id);
}
