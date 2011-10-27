package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccPrjType;

/**
 * 这是项目操作业务接口
 * @author Administrator
 *
 */
public interface ProjectBiz {
	/**
	 * 代表该项目为冷库
	 */
	public static final int REF = 1 ;
	/**
	 * 代表该项目为移动车载
	 */
	public static final int CAR = 2 ;
	
	/**
	 * 代表该项目为小批零
	 */
	public static final int BOX = 3 ;
	
	/**
	 * 代表该项目为8DI单纯的制冷项目
	 */
	public static final int COOLER =  4 ;
	
	
	/**
	 * 代表项目为8DI制冷和冷库的混合
	 */
	
	public static final int REF_COOLER = 5 ;
	
	/**
	 * 代表该项目为24DI的独立制冷项目
	 */
	public static final int COOLER_BASE = 6 ;
	
	/**
	 * 代表该项目为24DI的制冷与冷库的混合
	 */
	public static final int REF_COOLER_BASE = 7 ;
	
	/**
	 * 根据分支机构的标识id，获取该分支下的所有工程
	 * @param branchId		分支标识
	 * @return				工程集合
	 */
	public List<TbccPrjType> getByBranchId(Long branchId) ;
	
	/**
	 *  根据工程标识Id，获取工程对象
	 * @param projectId		工程标识Id
	 * @return				工程对象
	 */
	public TbccPrjType getByProId(String projectId);
	
	/**
	 * 根据工程标识Id，获取工程对象，为了解决连接，延迟加载的问题(flex、地图)。
	 * @param projectId	工程标识ID
	 * @return			工程对象
	 */
	public TbccPrjType getByproIdProxy(String projectId);
	
	/**
	 * 根据机构分支标识、获取该机构下的所有冷库工程
	 * @param branchId		机构分支标识
	 * @return				冷库工程集合
	 */
	public List<TbccPrjType> getRefProjListBybId(Long branchId) ;
	
	/**
	 * 根据分支的标识Id，获取分支下的所有车载工程
	 * @param branchId		分支标识Id
	 * @return				车载工程集合
	 */
	public List<TbccPrjType> getCarProjListBybId(Long branchId) ;
	
	/**
	 * 根据分支标识Id，获取该分支下的所有车载工程编号列表
	 * @param branchId		分支标识Id
	 * @return
	 */
	public List<String> getCarProjectIds(Long branchId) ;
	
	/**
	 * 根据分支的标识Id，获取分支下的所有小批零工程
	 * @param branchId		分支标识Id
	 * @return				小批零工程集合
	 */
	public List<TbccPrjType> getBoxProjListBybId(Long branchId) ;
	
	
	/**
	 *  根据机构分支标识、获取该机构下的所有冷库工程标识,为了解决flex调用，延迟加载问题
	 * @param branchId		机构分支标识
	 * @return				冷库工程集合
	 */
	public List<TbccPrjType> getRefProjListProxyByBId(Long branchId);
	
	/**
	 * 根据分支机构标识Id，获取所有的冷库工程标识Id
	 * @param branchId		分支标识Id
	 * @return
	 */
	public List<String> getRefProjects(Long branchId);
	
	/**
	 * 根据机构的标识Id，获取该机构下的制冷工程(包括纯粹的制冷工程和冷库工程)
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getCoolerProjListBybid(Long branchId) ;
	
	/**
	 * 根据分支机构标识Id，获取所有的制冷工程加冷库工程的标识Id
	 * @param branchId	分支标识id
	 * @return
	 */
	public List<String>	getCoolerProjects(Long  branchId);
	
	
	public List<TbccPrjType> getHasImagesProjListBybId(Long branchId) ;
	
	

}
