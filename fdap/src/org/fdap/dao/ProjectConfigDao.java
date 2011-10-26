package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapproject;

/**
 * 工程配置数据访问接口
 * @author zhaoyou
 *
 */
public interface ProjectConfigDao {
		/**
		 * 根据企业标识oid获取企业下的所有工程集合
		 * @param oid			企业标识oid
		 * @return
		 */
		public List<Fdapproject>		queryByOid(Long oid) ;
		
		/**
		 * 通过工程的标识Id
		 * @param projectId
		 * @return
		 */
		public Fdapproject					queryById(Long projectId) ;
		
		/**
		 * 增加一个工程
		 * @param project		需要增加的工程
		 */
		public void 							insertFdapProject(Fdapproject project) ;
		
		/**
		 * 根据一个工程标识Ｉｄ，删除一个工程
		 * @param projectId	需要删除的工程标识projectId
		 */
		public void 							deleteById(Long projectId) ;
		
		/**
		 * 批量删除工程
		 * @param ids			需要删除的工程标识Id集合
		 */
		public void 							deleteByIds(List<Long> ids);
		
		/**
		 * 更新一个工程
		 * @param project		一个需要更新的项目
		 */
		public void 							updateFdapProject(Fdapproject project) ;
		
		/**
		 * 根据企业标识Id，删除企业对应的工程信息
		 * @param oid			企业标识id 
		 */
		public void 							deleteByOid(Long oid) ;
		
		
		/**
		 * 判断工程名是否同名
		 * @param name		需要验证的工程名
		 * @param oid		企业标识oid
		 * @return
		 */
		public Long queryExistsProject(String name,String oid);
		
		
		/**
		 * 判断工程编号是否已存在
		 * @param projectNO		车载的工程编号
		 * @return
		 */
		public Long queryExistsProjectNO(String projectNO);
}


