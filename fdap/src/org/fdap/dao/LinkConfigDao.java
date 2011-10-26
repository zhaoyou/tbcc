package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdaplink;

public interface LinkConfigDao {
	/**
	 * 根据菜单组标识ID，获取所有菜单信息的集合
	 * @param	ltid		菜单组信息的标识ID
	 * @return
	 */
	public abstract List<Fdaplink> queryLinkByType(Long ltid);
	
	/**
	 * 增加一个菜单信息
	 * @param 	link			需要增加的菜单信息
	 * @return
	 */
	public abstract boolean insertLink(Fdaplink link);
	
	/**
	 * 删除几个菜单信息
	 * @param 	delId		需要删除的菜单标识Id字符串数组
	 * @return
	 */
	public abstract void DelLink(Long delId) throws Exception;
	
	/**
	 * 更新一个菜单信息
	 * @param 	link			需要更新的菜单信息
	 * @return
	 */
	public abstract void updateLink(Fdaplink link) throws Exception;
	
	/**
	 * 根据菜单标识id获取菜单信息
	 * @param 	id		菜单信息标识
	 * @return
	 */
	public abstract Fdaplink queryById(Long lid);
	
	
	/**
	 * 根据菜单组标识ID获取该菜单组所有菜单的条数
	 * @param ltid			菜单组信息标识
	 * @return
	 */
	public abstract Long querycouts(Long ltid);
	
	
	/**
	 * 获取所有菜单信息的集合
	 * @return
	 */
	public abstract List<Fdaplink> queryAllLink();
}
