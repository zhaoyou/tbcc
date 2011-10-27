package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccUserRegister;
/**
 * 用户注册数据访问接口
 * @author zhoukuanwei
 *
 */
public interface UserRegisterDao {
	/**
	 * 用户注册
	 * @param ruser			注册用户信息
	 * @return
	 */
	public boolean addregister(TbccUserRegister ruser);
	
	/**
	 * 根据用户名查询注册用户信息
	 * @param rname			用户名
	 * @param companyname	公司名称
	 * @return				返回注册用户信息集合
	 */
	public List<TbccUserRegister> queryUserRegisterByName(String rname ,String companyname);
}
