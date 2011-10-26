package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapref;

/**
 * 冷库的数据访问接口
 * @author zhaoyou
 *
 */
public interface RefDao {
		
		/**
		 * 根据冷库的标识Id，获取冷库的信息
		 * @param refId		 冷库标识Id
		 * @return
		 */
		public Fdapref queryByRefId(Long refId);
		
		/**
		 * 根据工程标识Id，获取对应下的所有冷库
		 * @param projectId		工程标识id
		 * @return
		 */
		public List<Fdapref> queryByProjectId(Long projectId);
		
		/**
		 * 根据企业Id,以及工程类型,获取该企业下的冷库信息
		 * @param oid 		企业标识Id
		 * @param type		工程类型
		 */
		public abstract List<Fdapref> queryRefByOidAndType(String oid,Integer type);
		
		
		/**
		 * 根据一个企业下的所有工程标示Id集合，或冷库集合
		 * @param projectIds		工程标示id集合
		 * @return
		 */
		public List<Fdapref> queryByProjectIds(List<Long> projectIds);
}
