package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.UserBiz;
import org.tbcc.dao.UserDao;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccDataRoles;
import org.tbcc.entity.TbccFunction;
import org.tbcc.entity.TbccHqRoles;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.entity.TbccUser;

/**
 * 用户操作的实体类
 * @author Administrator
 *
 */
public class UserBizImpl implements UserBiz {

	private UserDao userdao = null ;
	
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	public TbccUser getUserByName(String name,String password) {
		List<TbccUser> userList = userdao.getByName(name);
		if(userList!=null && userList.size()>0){
			TbccUser user = userList.get(0);
				if(user.getUpassword().equals(password)){
					return user ;
				}
		}
		return null;
	}
	
	public TbccUser getById(Long id) {
		if(id==null || id.equals(""))
			return null ;
		return this.userdao.get(id);
	}
	
	public String getPower(List<TbccFunction> list) {
		StringBuffer sb = new StringBuffer("");
		if(list==null || list.size()==0)
			return "" ;
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getName()) ;
			if(i!=list.size()-1)
				sb.append(",") ;
		}	
		return sb.toString();
	}
	
	
	public TbccHqUser getByHqId(Long id) {
		if(id==null || id.equals(""))
			return null ;
		return userdao.getHqUser(id);
	}

	public List<TbccHqUser> getByHqName(String name) {
		if(name==null || name.equals(""))
			return null ;
		return userdao.getByHqName(name);
	}
	
	
	public List<TbccHqUser> getHqUserByClientId(Long clientId) {
		if(clientId==null || clientId.equals(""))
			return null ;
		return userdao.getHqByClientId(clientId);
	}
	
	public List<TbccUser> getBranchUserByClientId(Long clientId) {
		if(clientId==null || clientId.equals(""))
				return null	 ;
		return userdao.getBranchByClientId(clientId);
	}
	
	
	/***ouyang implement starting***********************************************************/
	public boolean addUser(TbccUser user) {
		// TODO Auto-generated method stub
		return userdao.addUser(user);
	}

	public boolean delUser(Long[] id) {
		// TODO Auto-generated method stub
		return userdao.delUser(id);
	}

	public List<TbccDataRoles> getDataRolesByBranchId(Long branchId) {
		// TODO Auto-generated method stub
		return userdao.getDataRolesByBranchId(branchId);
	}

	public boolean upUser(TbccUser user) {
		return userdao.upUser(user);
	}

	public List<TbccUser> getUserByClientId(Long clientId) {
		return userdao.getUserByClientId(clientId);
	}

	public List<TbccFunction> getUserFunction(TbccBaseUser user) {
		// TODO Auto-generated method stub
		return userdao.getUserFunction(user);
	}

	public TbccDataRoles getDataRolesById(int id) {
		// TODO Auto-generated method stub
		return userdao.getDataRolesById(id);
	}

	public List<TbccUser> getSysAdmin() {
		// TODO Auto-generated method stub
		return userdao.getSysAdmin();
	}
	
	public TbccBranchType getBranchById(Long id){
		return userdao.getBranchById(id);
	}
	
	/**
	 * HqUser
	 */
	public boolean addHqUser(TbccHqUser user) {
		// TODO Auto-generated method stub
		return userdao.addHqUser(user);
	}

	public boolean delHqUser(Long[] id) {
		// TODO Auto-generated method stub
		return userdao.delHqUser(id);
	}

	public TbccHqRoles getHqRoleById(Long HqRoleId) {
		// TODO Auto-generated method stub
		return userdao.getHqRoleById(HqRoleId);
	}

	public List<TbccHqRoles> getHqRolesByHqId(Long HqId) {
		// TODO Auto-generated method stub
		return userdao.getHqRolesByHqId(HqId);
	}

	public TbccHqUser getHqUserByClientIdAndUserName(Long clientId, String name) {
		// TODO Auto-generated method stub
		return userdao.getHqUserByClientIdAndUserName(clientId, name);
	}

	public TbccHqUser getHqUserById(Long id) {
		// TODO Auto-generated method stub
		return userdao.getHqUserById(id);
	}

	public List<TbccFunction> getHqUserFunction(TbccBaseUser user, Long branchId) {
		// TODO Auto-generated method stub
		return userdao.getHqUserFunction(user, branchId);
	}

	public List<TbccHqUser> getHqUserbyClientId(Long clientId) {
		// TODO Auto-generated method stub
		return userdao.getHqUserbyClientId(clientId);
	}

	public boolean upHqUser(TbccHqUser user) {
		// TODO Auto-generated method stub
		return userdao.upHqUser(user);
	}

	
	/***ouyang implement ended***********************************************************/





	

	

}
