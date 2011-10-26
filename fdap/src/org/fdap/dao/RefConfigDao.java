package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapref;

/**
 * 冷库配置数据接口
 * @author zhaoyou
 *
 */
public interface RefConfigDao {
		/**
		 * 根据工程标识projectId 获取该工程下的所有冷库信息
		 * @param projectId			工程标识
		 * @return
		 */
		public List<Fdapref> 			queryByProjectId (Long projectId) ;
		
		/**
		 * 根据冷库标识refId 获取冷库信息
		 * @param refId				冷库标识
		 * @return
		 */
		public Fdapref 					queryByRefId(Long refId) ;
		
		/**
		 * 保存一条冷库信息
		 * @param ref			需要保存的冷库对象
		 */
		public void 						insertRef(Fdapref ref) ;
		
		/**
		 * 更新一条冷库信息
		 * @param ref			需要更新的冷库对象
		 */
		public void 						updateRef(Fdapref ref) ;
		
		/**
		 * 删除一个冷库信息	
		 * @param refId			需要删除的冷库对象
		 */
		public void 						deleteRef(Long refId) ;
		
		/**
		 * 批量删除冷库信息
		 * @param ids			需要删除的冷库标识Id集合
		 */
		public void 						deleteRefByIds(List<Long> ids);
		
		/**
		 * 根据企业的标识id，删除该企业下的所有冷库信息
		 * @param oid			企业标识Id
		 */
		public void 						deleteByOid(Long oid) ;
		
		/**
		 * 根据工程标识Id，删除该工程下的所有冷库信息
		 * @param projectId	工程标识Id
		 */
		public void 						deleteByProjectId(Long projectId) ;
		
		
		/**
		 * 判断冷库名是否同名
		 * @param name		需要验证的冷库名
		 * @param projectid		工程标识projectid
		 * @return
		 */
		public Long queryExistsRef(String name,String projectid);
}
