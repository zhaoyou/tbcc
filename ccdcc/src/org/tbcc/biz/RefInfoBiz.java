package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccRefInfo;

/**
 * 这是操作冷库的业务接口
 * @author Administrator
 *
 */
public interface RefInfoBiz {
	/**
	 * 冷库类型
	 */
	public static final int REFTYPE = 1 ;
	/**
	 * 阴凉间类型
	 */
	public static final int SHADYTYPE = 2 ;
	
	/**
	 * 楼层类型为楼上
	 */
	public static final int FLOORUP = 1 ;
	
	/**
	 * 楼层类型为楼下
	 */
	public static final int FLOORDOWN = 2 ;
	
	/**
	 * 根据冷库工程的标识Id，获取所有的冷库信息
	 * @param proId		冷库工程标识
	 * @return
	 */
	public List<TbccRefInfo> getRefListByPrj(String proId);
	
	/**
	 * 根据冷库工程标识Id，网络设备Id、冷库编号 获取某个冷库的信息
	 * @param proId		工程标识Id
	 * @param netId		网络设备Id
	 * @param refId		冷库编号
	 * @return
	 */
	public TbccRefInfo getRef(String proId,String netId,String refId);
	
	/**
	 * 根据冷库的标识Id，获取冷库的信息
	 * @param id	冷库的标识Id
	 * @return
	 */
	public TbccRefInfo getById(Long id);
}
