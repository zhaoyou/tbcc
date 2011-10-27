package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccPrjType;

/**
 * 这个事项目操作接口
 * @author Administrator
 *
 */
public interface ProjectDao {
	/**
	 * 根据分支Id，获取所有项目集合
	 * @param branchId		分支标识id
	 * @return					工程集合
	 */
	public List<TbccPrjType> getByBranchId(Long branchId);
	
	/**
	 * 根据工程标识Id，获取工程对象
	 * @param projectId		工程标识Id
	 * @return				工程对象
	 */
	public TbccPrjType get(String projectId	) ;
	
	/**
	 * 根据分支标识Id，获取所有的冷库工程
	 * @param branchId		分支标识Id
	 * @return				所有的冷库工程列表
	 */
	public List<TbccPrjType> getRefProjList(Long branchId) ;
	
	/**
	 * 根据分支标识Id，获取所有的移动车载工程
	 * @param branchId		分支标识Id
	 * @return				所有的移动车载工程列表
	 */
	public List<TbccPrjType> getCarProjList(Long branchId) ;
	
	/**
	 * 根据分支标识Id，获取所有的车载工程编号集合
	 * @param branchId		分支标识Id
	 * @return				所有的车载工程标识Id列表
	 */
	public List<String>		getCarProjectIds(Long branchId) ;
	
	/**
	 * 根据分支标识Id，获取所有的小批零工程
	 * @param branchId		分支标识Id
	 * @return				所有的小批零工程列表
	 */
	public List<TbccPrjType> getBoxProjList(Long branchId) ;
	
	/**
	 * 根据分支标识Id，获取该分支对应的冷库工程编号集合
	 * @param branchId		分支标识Id
	 * @return
	 */
	public List<String> getRefProjectIds(Long branchId) ;
	
	/**
	 * 根据分支的标识Id，获取该分支的制冷工程列表
	 * (包括冷库工程(冷库工程中可能就有冷库工程也有制冷工程)和纯粹的制冷工程)
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getCoolerProjList(Long branchId);
	
	/**
	 * 根据分支标识Id，获取该分支对应制冷工程编号(冷库工程和纯粹的制冷工程)集合
	 * 后面又增加了冷库与制冷的混合工程组合
	 * @param branchId		分支标识Id
	 * @return
	 */
	public List<String>	getCoolerProjectIds(Long branchId);
	
	/**
	 * 根据分支标识，获取存在图层工程列表，(阳政实现)
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getHasImagesProjList( Long branchId) ;
	
	
	
	
}
