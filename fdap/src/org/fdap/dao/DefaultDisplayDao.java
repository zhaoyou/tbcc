package org.fdap.dao;


public interface DefaultDisplayDao {
	/**
	 * 更新一个机构的默认显示方式
	 * @param oid			机构企业标识id
	 * @param isshowmap		是否地图显示
	 */
	public void updateOrg(Long oid ,Integer isShowMap);
}
