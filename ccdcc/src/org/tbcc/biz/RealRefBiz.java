package org.tbcc.biz;

import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这个是冷库实时数据业务接口
 * @author Administrator
 *
 */
public interface RealRefBiz {
	/**
	 * 代表冷库的运行状态为停止 1
	 */
	public static final int STOP  = 1 ;
	/**
	 * 代表冷库的运行状态为运行 2
	 */
	public static final int RUN = 2 ;
	/**
	 * 代表冷库的状态为预警 0
	 */
	public static final int PREALARM = 0 ;
	/**
	 * 代表冷库的状态为报警 1
	 */
	public static final int ALARM = 1 ;
	/**
	 * 代表冷库的状态为正常 2
	 */
	public static final int NORMAL = 2 ;
	
	
	/** 
	 * 代表冷库的链接状态为断开 1
	 */
	public static final int  DISCONNECTION = 1 ;
	
	
	/**
	 * 代表冷库的链接状态为已连接 2
	 */
	public static final int  CONNECTION = 2 ;
	
	/**
	 * 根据一个冷库工程的标识Id，获取该冷库的实时数据
	 * @param projectId		冷库标识
	 * @return
	 */
	public List<TbccBaseRealRef> getRealRefData(String projectId);
	
	/**
	 * 根据机构的标识Id，获取冷库工程的标识Id（这是原先针对一个分支下默认只有一个冷库工程的情况）
	 * @param branchId		分支的标识Id
	 * @return				冷库工程的标识Id
	 */
	public String getRefPrjId(Long branchId);
	
	/**
	 * 	根据机构的标识Id，获取机构分支对象
	 * @param branchId		机构的标识Id
	 * @return			 	返回机构对象
	 */
	public TbccBranchType getById(Long branchId) ;
	
	/**
	 * 根据不同的仓库工程，或所有仓库楼层信息
	 * @param list		仓库工程列表
	 * @return			返回工程列表、楼层编号、楼层类型、楼层名称
	 */
	public List<Map<String, Object>> getRefFloorInfo(List<TbccPrjType> list) ;
	
	/**
	 * 通过楼层信息获取冷库列表
	 * @param projectId			工程标示
	 * @param floorType			楼层类型
	 * @param floorNo			楼层编号
	 * @return					冷库列表
	 */
	public List<TbccRefInfo> getRefByFloorInfo(String projectId,Integer floorType,Integer floorNo) ;
	
	
	/**
	 * 根据冷库集合
	 * @param list		冷库集合
	 * @return			冷库对应的探头集合
	 */
	public List<TbccAiInfo> getAiByRef(List<TbccRefInfo> list) ;
	
	/**
	 * 通过工程标示Id，获取仓库的系统实时数据
	 * @param projectId		仓库工程标示
	 * @return				返回系统实时数据集合 连接状态、断电报警、声光报警
	 */
	public String[] getRealRefSystemData(String projectId) ;
	
	
	
}
