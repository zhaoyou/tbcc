package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdaprefrealdata;

/**
 * 冷库实时数据访问接口 
 * @author zhaoyou
 *
 */
public interface RefRealDataDao {
	
	/**
	 * 通过仓库工程标示，获取仓库的所有探头的实时数据集合
	 * @param projectIds		仓库工程标示集合
	 * @return
	 */
	public List<Fdaprefrealdata	>	queryByProjectIds(List<Long> projectIds);
	

}
