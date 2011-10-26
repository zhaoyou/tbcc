package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdaporg;

/**
 * 机构配置业务接口
 * @author zhaoyou
 *
 */
public interface OrgConfigBiz {
		
		/**
		 * 获取顶层机构的信息
		 * @return
		 */
		public Fdaporg getTopOrg() ;
		
		/**
		 * 更新顶层机构的信息
		 * @param topOrg		需要更新的顶层机构
		 * @return					成功 true 失败 false
		 */
		public boolean  updateTopOrg(Fdaporg topOrg);
		
		/**
		 * 获取机构的树形菜单 
		 * @return			树形菜单的字符串
		 */
		public String getOrgTree();
		
		/**
		 * 通过上级机构标识Id，获取下级机构列表
		 * @param parentId			上级机构标识Id
		 * @return
		 */
		public List<Fdaporg> getOrgList(Long parentId) ;
		
		/**
		 * 保存一个机构信息
		 * @param org		需要保存的机构信息
		 * @return				成功 true 失败 false
		 */
		public boolean addOrg(Fdaporg org);
		
		/**
		 * 更新一个机构信息
		 * @param org			需要更新的机构信息
		 * @return
		 */
		public boolean updateOrg(Fdaporg org);
		
		/**
		 * 根据检测账户名是否存在
		 * @param account		需要检测的账户名
		 * @return						true 存在  false 不存在
		 */
		public boolean getExistsAccount(String account);
		
		/**
		 * 删除一组机构
		 * @param orgIds		需要删除的机构标识id集合
		 * @return
		 */
		public boolean delOrg(String [] orgIds);
		
		/**
		 * 检测对应机构是否存在下级机构或企业
		 * @param ids		需要检测的
		 * @return
		 */
		public boolean getExistsLowerOrg(String[] ids) ;
		
		/**
		 * 检测对应机构是否已经存在要添加或更改的企业名称
		 * @param name		需要验证的企业名称
		 * @param parentOid		企业标识parentOid
		 * @return
		 */
		public boolean getExistsOrg(String name,String parentOid);
		
		/**
		 * 获取只有机构的的树形机构字符串
		 * @return			树形结构字符串
		 */
		public String getOnlyOrgTree();
		
}
