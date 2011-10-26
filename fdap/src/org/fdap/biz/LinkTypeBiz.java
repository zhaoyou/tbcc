package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdaplinktype;

public interface LinkTypeBiz {
	public static final int SIZE=4;
	
	/**
	 * 获取所有菜单组的集合
	 * @return
	 */
	public abstract List<Fdaplinktype> getAllLinktype();
	
	/**
	 * 增加一个菜单组信息
	 * @param 	linktype			需要增加的菜单组信息
	 * @return
	 */
	public abstract boolean addLinktype(Fdaplinktype linktype);
	
	/**
	 * 删除几个菜单组信息
	 * @param 	delId		需要删除的菜单组标识Id字符串数组
	 * @return
	 */
	public abstract boolean DelLinktype(String delId[]);
	
	/**
	 * 更新一个菜单组信息
	 * @param 	linktype			需要更新的菜单组信息
	 * @return
	 */
	public abstract boolean updateLinktype(Fdaplinktype linktype);
	
	/**
	 * 根据菜单组标识id获取菜单信息
	 * @param 	ltid		菜单组信息标识
	 * @return
	 */
	public abstract Fdaplinktype getById(Long ltid);
	
}
