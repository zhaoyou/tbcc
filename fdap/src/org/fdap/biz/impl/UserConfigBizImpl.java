package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.UserConfigBiz;
import org.fdap.dao.UserConfigDao;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdaprole;
import org.fdap.entity.Fdapuser;

/**
 * 用户配置的业务实现类
 * @author zhoukuanwei
 *
 */
public class UserConfigBizImpl implements UserConfigBiz {
	private UserConfigDao userconfigdao;
	
	public UserConfigDao getUserconfigdao() {
		return userconfigdao;
	}

	public void setUserconfigdao(UserConfigDao userconfigdao) {
		this.userconfigdao = userconfigdao;
	}

	public boolean updateUser(Fdapuser fuser) {
		return userconfigdao.updateUser(fuser);
	}

	public boolean addUser(Fdapuser fuser) {
		return userconfigdao.addUser(fuser);
	}

	public Fdaprole getRoleByRid(String rid) {
		return userconfigdao.getRoleByRid(rid);
	}

	public List<Fdapuser> getOrguser(String oid) {
		return userconfigdao.getOrgUser(oid);
	}

	public boolean delUser(String[] delId) {
		return userconfigdao.delUser(delId);
	}
	
	public Fdapuser getUserById(String userid) {
		return userconfigdao.getUserById(userid);
	}

	public String getOrgTree() {
		StringBuffer sb=new StringBuffer("");
		List<Fdaporg> orglist=userconfigdao.getAllOrg();
		
		for(Fdaporg fo : orglist){
			if(fo.getParentId()==-1){
				sb.append("d.add("+fo.getOid()+","+fo.getParentId()+",'"+fo.getName()+"',\"javascript:getuser("+fo.getOid()+",'"+fo.getName()+"')\");\n");
				addTree(orglist,fo.getOid(),sb);
				break;
			}
		}
		
		return sb.toString();
	}
	
	public StringBuffer addTree(List<Fdaporg> orglist,Long parentid,StringBuffer sb){
		for(Fdaporg fo : orglist){
			if(fo.getParentId().longValue()==parentid.longValue()){
				sb.append("d.add("+fo.getOid()+","+parentid+",'"+fo.getName()+"',\"javascript:getuser("+fo.getOid()+",'"+fo.getName()+"')\");\n");
				addTree(orglist,fo.getOid(),sb);
			}
		}
		return sb;
	}
}
