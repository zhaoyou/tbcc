package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdaplinktype;

public interface LinkTypeDao {
	/**
	 * 获取所有菜单组信息的集合
	 * @return
	 */
	public abstract List<Fdaplinktype> queryAllLinktype();
	
	/**
	 * 增加一个菜单组信息
	 * @param 	linktype			需要增加的菜单组信息
	 * @return
	 */
	public abstract boolean insertLinktype(Fdaplinktype linktype);
	
	/**
	 * 删除几个菜单组信息
	 * @param 	delId		需要删除的菜单组标识Id字符串数组
	 * @return
	 */
	public abstract void DelLinktype(Long delId) throws Exception;
	
	/**
	 * 更新一个菜单组信息
	 * @param 	linktype			需要更新的菜单组信息
	 * @return
	 */
	public abstract void updateLinktype(Fdaplinktype linktype) throws Exception;
	
	/**
	 * 根据菜单组标识id获取菜单组信息
	 * @param 	ltid		菜单组信息标识
	 * @return
	 */
	public abstract Fdaplinktype queryById(Long ltid);
	
	
}
