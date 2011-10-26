package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapproject;

/**
 * 工程配置的数据访问接口
 * @author zhaoyou
 *
 */
public interface ProjectConfigBiz {
		/**
		 * 根据企业标识oid获取该企业对应的工程列表
		 * @param oid		企业标识oid
		 * @return
		 */
		public List<Fdapproject> 		getByOid(Long oid) ;
		
		/**
		 * 根据工程标识Id获取工程信息
		 * @param projectId		工程标识Id
		 * @return
		 */
		public Fdapproject 					getById(Long projectId);
		
		/**
		 * 添加一个工程信息
		 * @param project			 需要保存的工程信息			
		 * @return
		 */
		public	boolean 						addProject(Fdapproject project);
		
		/**
		 * 更新一个工程信息
		 * @param project			需要更新的工程信息
		 * @return
		 */
		public boolean 						updateProject(Fdapproject project);
		
		/**
		 * 批量删除工程信息
		 * @param ids			需要删除的工程标识Id集合
		 * @return
		 */
		public boolean 						delProjectByIds(String[] ids);
		
		/**
		 *  获取机构企业列表
		 * @return
		 */
		public String 							getOrgTree();
		
		/**
		 * 判断工程名是否同名
		 * @param name		需要验证的工程名
		 * @param oid		企业标识oid
		 * @return
		 */
		public boolean getExistsProject(String name,String oid);
		
		
		
		/**
		 * 判断工程编号是否已存在
		 * @param projectNO		车载的工程编号
		 * @return
		 */
		public boolean getExistsProjectNO(String projectNO);
		
}
