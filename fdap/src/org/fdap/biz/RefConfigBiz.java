package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapref;

/**
 * 冷库配置业务接口
 * @author zhaoyou
 *
 */
public interface RefConfigBiz {
	
	/**
	 * 通过工程标识Id获取冷库列表
	 * @param projectId		工程列表
	 * @return
	 */
	public List<Fdapref>			getByProjectId(Long projectId);

	/**
	 * 根据冷库标识获取冷库信息
	 * @param refId			冷库标识Id
	 * @return
	 */
	public Fdapref 					getById(Long refId);
	
	/**
	 * 增加一个冷库
	 * @param ref			需要添加的冷库信息
	 * @return
	 */
	public boolean 					addRef(Fdapref ref);
	
	/**
	 * 更新一个冷库信息
	 * @param ref			 需要更新的冷库信息
	 * @return
	 */
	public boolean 					updateRef(Fdapref	 ref);
	
	/**
	 * 批量删除冷库
	 * @param refIds		需要删除的冷库标识id集合
	 * @return
	 */
	public boolean 					delRef(String[] refIds);
	
	
	/**
	 * 判断冷库名是否同名
	 * @param name		需要验证的冷库名
	 * @param projectid		工程标识projectid
	 * @return
	 */
	public boolean getExistsRef(String name,String projectid);
	
	
	/**
	 * 更新一个冷库下所有探头的上下限
	 * @param refId			 需要更新探头上下限的冷库标识
	 * @param storeid		 需要更新探头上下限的冷库的存储类型标识
	 * @return
	 */
	public boolean 					updateRefAi(Long refId,Long storeid);
	
}
