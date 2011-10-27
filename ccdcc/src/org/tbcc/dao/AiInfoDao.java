package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccAiInfo;

/**
 * 这是操作探头的数据访问接口
 * @author Administrator
 *
 */
public interface AiInfoDao {
	
	/**
	 * 根据冷库工程项目Id，获取该冷库项目的所有探头
	 * @param proId		冷库工程标识
	 * @return
	 */
	public List<TbccAiInfo> getAiListByproId(String proId);
	
	/**
	 * 根据冷库项目Id、网络netId、冷库标识Id获取某一个冷库的所有探头 
	 * @param proId			冷库工程标识Id
	 * @param netId			网络设备Id
	 * @param refId			冷库工程标识Id
	 * @return
	 */
	public List<TbccAiInfo> getRefAiList(String proId,String netId,String refId);
	
	/**
	 * 通过冷库标识id，获取该冷库的所有探头
	 * @param refId		冷库标识Id
	 * @return	
	 */
	public List<TbccAiInfo> getAiListByRid(Long rid);
}
