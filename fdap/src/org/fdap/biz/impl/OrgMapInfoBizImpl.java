package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.fdap.biz.OrgMapInfoBiz;
import org.fdap.dao.OrgDao;
import org.fdap.entity.Fdaporg;

public class OrgMapInfoBizImpl implements OrgMapInfoBiz {
	private OrgDao 	orgDao = null ;
	
	public OrgDao getOrgDao() {
		return orgDao;
	}
	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public String getOrgMapStr() {
		StringBuffer sb = new StringBuffer();
		List<Fdaporg> orglist = orgDao.queryAll();
		if(orglist==null||orglist.size()==0){
			return sb.toString();
		}
		
		sb.append("编号\t机构名称\t下级机构@") ;
		for(Fdaporg org:orglist){
			if(org.getNodetype()!=2){
				List<Fdaporg> branchlist = getBranchByParent(org.getOid(),orglist);
				if(branchlist!=null&&branchlist.size()!=0&&branchlist.get(0).getNodetype()!=2){
					sb.append(org.getOid()+"\t"+org.getName()+"\t");
					for(int i=0;i<branchlist.size();i++){
						sb.append(branchlist.get(i).getName());
						if(i!=(branchlist.size()-1)){
							sb.append(",");
						}else{
							sb.append("等");
						}
					}
					sb.append("@");
				}
			}
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
	public List<Fdaporg> getBranchByParent(Long parentId,List<Fdaporg> list){
		List<Fdaporg> orglist = new ArrayList<Fdaporg>();
		for(Fdaporg org:list){
			if(parentId.toString().equals(org.getParentId().toString())){
				orglist.add(org);
			}
		}
		return orglist;
	}
}
