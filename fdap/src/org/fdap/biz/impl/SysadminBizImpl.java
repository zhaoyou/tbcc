package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.SysadminBiz;
import org.fdap.dao.SysadminDao;
import org.fdap.entity.Fdapuser;

public class SysadminBizImpl implements SysadminBiz {

	private SysadminDao sysadmindao;
	
	public SysadminDao getSysadmindao() {
		return sysadmindao;
	}

	public void setSysadmindao(SysadminDao sysadmindao) {
		this.sysadmindao = sysadmindao;
	}

	public Fdapuser getSysadmin() {
		List<Fdapuser> sysadminlist=sysadmindao.getSysadmin();
		/*if(sysadminlist!=null&&sysadminlist.size()!=0){
			return sysadminlist.get(0);
		}*/
		return (sysadminlist!=null&&sysadminlist.size()!=0)?sysadminlist.get(0):null;
	}

	public boolean updateSysadmin(Fdapuser sysadminuser) {
		/*List<Fdapuser> sysadminlist=sysadmindao.getSysadmin();
		Fdapuser sysadmin=(sysadminlist!=null&&sysadminlist.size()!=0)?sysadminlist.get(0):null;
		if(sysadmin==null) return false;
		sysadmin.setPassword(password);*/
		return sysadmindao.upUser(sysadminuser);
	}
	
}
