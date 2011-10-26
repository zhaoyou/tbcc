package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapaiinfo;

/**
 * 探头配置业务接口
 * @author zhaoyou
 *
 */
public interface AiConfigBiz {
	/**
	 * 根据冷库标识refId获取冷库下的所有探头列表
	 * @param refId				冷库标识
	 * @return
	 */
	public abstract List<Fdapaiinfo> getByref(Long refId);
	
	/**
	 * 获取该企业下探头的最大reid
	 * @param oid			企业标识Id
	 * @return
	 */
	public abstract Integer getMaxReid(Long oid,Integer preid);
	
	
	/**
	 * 保存一个探头信息
	 * @param 	aiInfo			需要保存的探头信息
	 * @return
	 */
	public abstract boolean addAi(Fdapaiinfo aiinfo);
	
	/**
	 * 删除几个探头信息
	 * @param 	delAiguid		需要删除的探头aiguid字符串数组
	 * @return
	 */
	public abstract boolean DelAi(String delAiguid[]);
	
	
	/**
	 * 根据探头标识aiguid获取探头信息
	 * @param 	aiguid		探头标识
	 * @return
	 */
	public abstract Fdapaiinfo getByAiguid(String aiGuid) ;
	
	/**
	 * 更新一个探头信息
	 * @param 	aiinfo			需要更新的探头信息
	 * @return
	 */
	public abstract boolean updateAi(Fdapaiinfo aiinfo);
	
	/**
	 * 获取该企业下的探头集合
	 * @param oid			企业标识Id
	 * @return
	 */
	public abstract List<Fdapaiinfo> getAiByOid(Long oid);
}
