package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapproject;

/**
 * 工程操作的数据访问接口
 * @author zhaoyou
 *
 */
public interface ProjectDao {
	
		/**
		 * 通过工程参数标识Id，获取工程对象
		 * @param projectId		工程标识Id
		 * @return
		 */
		public Fdapproject queryByProjectId(Long projectId);
	
		/**
		 * 通过企业标识Id，以及工程类型，获取工程集合
		 * @param oid		企业标识Id
		 * @param type		工程类型
		 * @return
		 */
		public List<Fdapproject> queryByOidAndType(Long oid,Integer type);
		
		/**
		 * 获取某个机构下的所有下级机构和企业
		 * (该方法，是利用企业、机构标识是自动增长，下级的机构或企业标识比上级的标识大)
		 * @param projectId
		 * @return
		 */
		
}
