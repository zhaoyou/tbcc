package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapaiinfo;

/**
 * 探头配置数据接口
 * @author zhaoyou
 *
 */
public interface AiConfigDao {
	
		/**
		 * 根据冷库标识refId获取冷库下的所有探头列表
		 * @param refId				冷库标识
		 * @return
		 */
		public List<Fdapaiinfo> queryByRefId(Long refId) ;
		
		/**
		 *  根据探头标识aiGuid获取探头的信息
		 * @param aiGuid			探头标识
		 * @return
		 */
		public Fdapaiinfo queryByAiguid(String aiGuid) ;
		
		/**
		 * 保存一个探头信息
		 * @param aiInfo			需要保存的探头信息
		 * @return
		 */
		public boolean insertAi(Fdapaiinfo aiInfo) ;
		
		/**
		 * 更新一个探头信息
		 * @param aiInfo			需要更新的探头信息
		 */
		public void updateAi(Fdapaiinfo aiInfo) throws Exception;
		
//		/**
//		 * 更新一个湿度探头的信息
//		 * @param aiInfo			需要更新的探头信息
//		 */
//		public void updateHumAi(Fdapaiinfo aiInfo) throws Exception;
//		
//		/**
//		 * 更新一个温度探头的信息
//		 * @param aiInfo			需要更新的探头信息
//		 */
//		public void updateTempAi(Fdapaiinfo aiInfo) throws Exception;
		
		/**
		 * 删除一个探头信息
		 * @param aiGuid			需要删除的探头标识aiGuid
		 */
		public void deleteAi(String aiGuid) throws Exception ;

		
		/**
		 * 获取该企业下探头的最大reid
		 * @param oid			企业标识Id
		 * @return
		 */
		public abstract Integer queryMaxReid(Long oid,Integer preid);
		
		/**
		 * 通过企业标识Id，删除该企业下的所有探头信息
		 * @param oid			企业标识Id
		 * @return
		 */
		public void deleteAiByOid(Long oid);
		
		/**
		 * 通过工程标识Id，删除整个工程的探头信息
		 * @param projectId		工程标识id
		 */
		public void deleteAiByProjectId(Long projectId) ;
		
		/**
		 * 通过冷库标识Id，删除整个冷库的探头信息
		 * @param refId			冷库标识id
		 */
		public void deleteAiByRefId(Long refId) ;
		
		/**
		 * 获取该企业下的探头集合
		 * @param oid			企业标识Id
		 * @return
		 */
		public abstract List<Fdapaiinfo> queryAiByOid(Long oid);
}
