package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdaporg;


/**
 * 机构业务数据访问接口
 * @author zhaoyou
 *
 */
public interface OrgBiz {
			
			/**
			 * 根据标识获取机构信息
			 * @param oid			标识Id
			 * @return
			 */
			public Fdaporg getByOid(Long oid) ;
			
			/**
			 * 根据上级标识Id，获取机构信息
			 * @param parentId			机构标识Id
			 * @return
			 */
			public List<Fdaporg> getByParentId(Long parentId);
			
			/**
			 * 获取机构的下级机构标识Id(包括企业)
			 * @param parentId
			 * @return
			 */
			public List<Long> getLowerOrg(Long parentId);
			
			/**
			 * 获取机构的下级机构列表(包括企业)
			 * @param parentId
			 * @return
			 */
			//public List<Fdaporg> getLowerOrgList(Long parentId);
			
			
}
