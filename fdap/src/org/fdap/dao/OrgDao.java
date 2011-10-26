package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdaporg;

/**
 * 机构数据访问接口
 * @author zhaoyou
 *
 */
public interface OrgDao {
		
		/**
		 * 通过机构企业标识id获取机构信息
		 * @param oid			机构企业标识Id
		 * @return
		 */
		public Fdaporg queryByOid(Long oid);
	
		/**
		 * 通过机构的标识id，获取该机构的下级机构Id
		 * @param   parentId	机构标识Id
		 * @return
		 */
		public List<Fdaporg> 	queryByParentId(Long parentId) ;
		
		/**
		 * 根据机构标识Id，获取该机构下的机构(不包括企业）标识
		 * @param parentId				机构标识Id
		 * @return
		 */
		public List<Long> 		queryLowerOrgByParentId(Long parentId);
		
		/**
		 * 查询所有的有效的机构和企业
		 * @return
		 */
		public List<Fdaporg> queryAll();
		
		/**
		 * 获取机构的下级机构列表(不包括企业)
		 * @param parentId
		 * @return
		 */
		//public List<Fdaporg> queryLowerOrgListByParentId(Long parentId);
		
}
