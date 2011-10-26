package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdaplink;

public interface LinkConfigBiz {
	
	/**
	 * 根据菜单组标识ID，获取菜单信息的集合
	 * @param	ltid		菜单组信息的标识ID
	 * @return
	 */
	public abstract List<Fdaplink> getLinkByType(Long ltid);
	
	/**
	 * 增加一个菜单信息
	 * @param 	link			需要增加的菜单信息
	 * @return
	 */
	public abstract boolean addLink(Fdaplink link);
	
	/**
	 * 删除几个菜单信息
	 * @param 	delId		需要删除的菜单标识Id字符串数组
	 * @return
	 */
	public abstract boolean DelLink(String delId[]);
	
	/**
	 * 更新一个菜单信息
	 * @param 	link			需要更新的菜单信息
	 * @return
	 */
	public abstract boolean updateLink(Fdaplink link);
	
	/**
	 * 根据菜单标识id获取菜单信息
	 * @param 	id		菜单信息标识
	 * @return
	 */
	public abstract Fdaplink getById(Long lid);
	
	
	/**
	 * 判断菜单组中是否已经有4个菜单
	 * @param ltid		菜单组信息标识ID
	 * @return
	 */
	public abstract boolean isBeyond(Long ltid);
	
	
	/**
	 * 获取所有的菜单信息的集合
	 * @return
	 */
	public abstract List<Fdaplink> getAllLink();
}
