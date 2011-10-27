package org.tbcc.dao;

import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这是冷库实时数据的访问接口
 * @author Administrator
 *
 */
public interface RealRefDao {
	/**
	 * 根据冷库工程标识Id，获取该工程的实时数据
	 * @param projectId		冷库工程标识Id
	 * @return
	 */
	public List<TbccBaseRealRef> getRealRefData(String projectId);
	
	/**
	 * 根据冷库工程编号，获取对应冷库工程对应的楼层信息
	 * @param projects	冷库工程编号集合
	 * @return
	 */
	public List<Map<String, Object>> getFloorInfo(List<String> projects);
	
	/**
	 * 根据工程编号、楼层类型、楼层编号获取冷库列表
	 * @param projectId			工程编号
	 * @param floorType			楼层类型
	 * @param floorNo			楼层编号
	 * @return					所属工程、楼层的冷库列表
	 */
	public List<TbccRefInfo> getRefByPidAndFloorInfo(String projectId,Integer floorType,Integer floorNo); 
	
	/**
	 * 根据冷库标示集合，获取对应冷库的所有探头列表
	 * @param refIds		冷库标示集合
	 * @return
	 */
	public List<TbccAiInfo> getAiByRefId(List<Long> refIds);
	
	
	/**
	 * 通过工程标示获取该工程的系统属性,默认配置在第一个设备上 netId = 1 
	 * @param projectId			工程标示Id
	 * @return					冷库实时数据集合
	 */
	public List<TbccBaseRealRef	> getRealRefSysData(String projectId);
}
