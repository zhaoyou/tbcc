package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBranchType;

/**
 * 这是一个操作机构的接口
 * @author Administrator
 *
 */
public interface BranchDao {
	
	/**
	 * 根据分支标识Id，加载该分支对象
	 * @param branchId		分支标识id
	 * @return
	 */
	public TbccBranchType get(Long branchId);
	
	/**
	 * 根据分支标识Id集合 获取分支机构的集合
	 * @param condition	eg: (12,13,13)
	 * @return
	 */
	public List<TbccBranchType> getByIds(String condition) ;
	
}
