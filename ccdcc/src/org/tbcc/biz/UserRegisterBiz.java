package org.tbcc.biz;

import org.tbcc.entity.TbccUserRegister;

/**
 * 用户注册的业务接口
 * @author zhoukuanwei
 */
public interface UserRegisterBiz {
	/**
	 * 用户注册
	 * @param ruser				注册用户信息
	 * @return					注册操作状态是否成功
	 */
	public boolean addregister(TbccUserRegister ruser);
	/**
	 * 判断用户名是否已存在
	 * @param rname				用户名
	 * @param companyname		公司名称
	 * @return					是否已存在:ture已存在；false还没有
	 */
	public boolean getExists(String rname,String companyname);
	
}
