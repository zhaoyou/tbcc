package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.DefaultDisplayBiz;
import org.fdap.dao.DefaultDisplayDao;
import org.fdap.dao.OrgDao;
import org.fdap.entity.Fdaporg;

public class DefaultDisplayBizImpl implements DefaultDisplayBiz {
	private OrgDao orgdao;
	private DefaultDisplayDao defaultdisplaydao;
	public OrgDao getOrgdao() {
		return orgdao;
	}

	public void setOrgdao(OrgDao orgdao) {
		this.orgdao = orgdao;
	}
	
	public DefaultDisplayDao getDefaultdisplaydao() {
		return defaultdisplaydao;
	}

	public void setDefaultdisplaydao(DefaultDisplayDao defaultdisplaydao) {
		this.defaultdisplaydao = defaultdisplaydao;
	}

	public String getTree() {
		StringBuffer sb = new StringBuffer();
		List<Fdaporg> orgList = this.getOrgdao().queryAll();
		if(orgList==null||orgList.size()==0){
			return sb.toString();
		}
		
		sb.append("var d = new dTree('d') ;") ;
		for(Fdaporg org : orgList){
			if(org.getNodetype()!=2)
				sb.append("d.add("+org.getOid()+","+org.getParentId()+",'"+org.getName()+"',\"javascript:godefault('"+org.getOid()+"','"+org.getName()+"','"+org.getIsshowmap()+"');\") ;");
		}
		
		return sb.toString();
	}
	
	public Fdaporg getByOid(Long oid) {
		
		//验证参数
		if(oid==null || oid.equals("")){
			return null ;
		}
		return this.getOrgdao().queryByOid(oid);
	}
	
	@Override
	public boolean updateOrg(Long oid,Integer isshowmap) {

		try {
			this.getDefaultdisplaydao().updateOrg(oid,isshowmap) ;
		} catch (Exception e) {
			return false ;
		}
		return true;
	}
}
