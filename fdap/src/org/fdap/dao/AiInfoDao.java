package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapaiinfo;

/**
 * 探头的数据访问类
 * @author zhaoyou
 *
 */
public interface AiInfoDao {
	
		/**
		 *  通过探头标识id，获取探头信息
		 * @param aiGuid		探头标识id
		 * @return				
		 */
		public Fdapaiinfo queryByGuid(String aiGuid);
		
		/**
		 * 通过冷库标识id，获取该冷库下的所有探头信息
		 * @param refId		冷库标识id
		 * @return
		 */
		public List<Fdapaiinfo> queryByRefId(Long refId);
		
		/**
		 * 通过仓库工程标示，获取所有的仓库探头
		 * @param projectIds	仓库工程标示id集合
		 * @return
		 */
		public List<Fdapaiinfo> queryRefAiByProjectIds(List<Long> projectIds);
}
