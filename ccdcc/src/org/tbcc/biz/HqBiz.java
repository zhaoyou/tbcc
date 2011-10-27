package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccHqType;

/**
 * 总部的业务操作接口
 * @author Administrator
 *
 */
public interface HqBiz {
	
		/**
		 * 显示总部Logo 0
		 */
		public final Integer LogoEnable = 0 ;
		
		/**
		 * 不显示总部Logo(及显示默认Logo) 1
		 */
		public final Integer LogoDisable = 1 ;
	
		/**
		 * 根据总部的标识Id，获取总部信息
		 * @param id		总部标识Id
		 * @return
		 */
		public TbccHqType	getById(Long id) ;
		
		/**
		 * 根据总部标识Id，获取该总部下一级的总部信息
		 * @param parentId		总部的标识Id
		 * @return
		 */
		public List<TbccHqType> getByParentId(Long parentId) ;
		
		/**
		 * 获取所有的总部信息
		 * @return
		 */
		public List<TbccHqType> getAllHqType() ;
		
		/**
		 * 根据总部的标识Id，获取总部分支关系的tree
		 * @param id		总部的标识Id
		 * @return
		 */
		public String getHqBranchTree(Long id) ;
}
