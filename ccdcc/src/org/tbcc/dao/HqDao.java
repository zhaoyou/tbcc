package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccHqType;

/**
 * 总部操作的数据访问接口
 * @author Administrator
 *
 */
public interface HqDao {
	
	/**
	 * 根据标识主键获取总部对象
	 * @param id		总部标识Id
	 * @return
	 */
	public TbccHqType	get(Long id) ;
	
	
	
	/**
	 * 根据标识Id，获取该总部下级的所有总部信息
	 * @param parentId		上级总部标识Id
	 * @return
	 */
	public List<TbccHqType> getByParentId(Long parentId) ;
	
	/**
	 * 获取所有的总部信息
	 * @return
	 */
	public List<TbccHqType> getAll();
	
	/**
	 * 	根据总部用户的标识Id，获取与该总部相关联的所有分支
	 * @param hqId		总部标识Id
	 * @return
	 */
	public List<TbccBranchType>	getBranchByHqId(Long hqId) ;
	
}
