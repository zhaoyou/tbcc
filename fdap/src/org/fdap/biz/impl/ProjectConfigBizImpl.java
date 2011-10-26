package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.fdap.biz.ProjectConfigBiz;
import org.fdap.dao.AiConfigDao;
import org.fdap.dao.OrgDao;
import org.fdap.dao.ProjectConfigDao;
import org.fdap.dao.RefConfigDao;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;

/**
 * 工程配置的业务实现类
 * @author zhaoyou
 *
 */
public class ProjectConfigBizImpl implements ProjectConfigBiz {
	
	private ProjectConfigDao projectConfigDao = null ;
	
	private OrgDao orgDao = null ;
	
	private RefConfigDao refConfigDao = null ;
	
	private AiConfigDao aiConfigDao  = null ;
	
	
	

	public RefConfigDao getRefConfigDao() {
		return refConfigDao;
	}

	public void setRefConfigDao(RefConfigDao refConfigDao) {
		this.refConfigDao = refConfigDao;
	}

	public AiConfigDao getAiConfigDao() {
		return aiConfigDao;
	}

	public void setAiConfigDao(AiConfigDao aiConfigDao) {
		this.aiConfigDao = aiConfigDao;
	}

	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public ProjectConfigDao getProjectConfigDao() {
		return projectConfigDao;
	}

	public void setProjectConfigDao(ProjectConfigDao projectConfigDao) {
		this.projectConfigDao = projectConfigDao;
	}

	@Override
	public boolean addProject(Fdapproject project) {
		//验证参数
		if(project==null){
			return false ;
		}
		try {
			this.getProjectConfigDao().insertFdapProject(project);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}

	@Override
	public boolean delProjectByIds(String[] ids) {
		//验证参数
		if(ids==null || ids.length==0){
			return false ;
		}
		List<Long> list = new ArrayList<Long>();
		try {
			for (String string : ids) {
				list.add(new Long(string));
				
				//删除探头
				this.getAiConfigDao().deleteAiByProjectId(new Long(string));
				//删除冷库
				this.getRefConfigDao().deleteByProjectId(new Long(string));
				
			}
			//删除机构
			this.getProjectConfigDao().deleteByIds(list);
		} catch (Exception e) {
			e.printStackTrace() ;
			return false ;
		}
		return true;
	}

	@Override
	public Fdapproject getById(Long projectId) {
		//验证参数
		if(projectId==null || projectId.equals("")){
			return null ;
		}
		return this.getProjectConfigDao().queryById(projectId);
	}

	@Override
	public List<Fdapproject> getByOid(Long oid) {
		if(oid==null ||oid.equals("")){
			return null ;
		}
		return this.getProjectConfigDao().queryByOid(oid);
	}

	@Override
	public boolean updateProject(Fdapproject project) {
		if(project==null){
			return false  ;
		}
		try {
			this.getProjectConfigDao().updateFdapProject(project);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}

	@Override
	public String getOrgTree() {
		StringBuffer sb =new StringBuffer();	
		//查询所有的机构
		List<Fdaporg> list = this.orgDao.queryAll() ;		
		if(list==null || list.size()==0){
			return sb.toString() ;
		}
		
		//构造机构树形菜单的字符串d.add(1,-1,'武汉药监局',"javascript:window.alert('top')") ;
		sb.append("var d = new dTree('d') ;") ;
		for (Fdaporg fdaporg : list) {
			if(fdaporg.getNodetype().toString().equals("2")){
				sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:window.goProjectList('"+fdaporg.getOid()+"');\") ;") ;
			}else{
				sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:window.clickJG("+fdaporg.getOid()+");\");") ;
			}
		}
		return sb.toString();
	}

	@Override
	public boolean getExistsProject(String name,String oid) {
		//检验参数
		if(name==null || name.equals("")||oid==null|oid.equals("")){
			return true ;
		}
		
		//检测账户名是否存在 
		Long exists = this.getProjectConfigDao().queryExistsProject(name,oid);
		if(exists==null || exists>0){
				return true ;
		}else{
				return false;
		}
	}

	@Override
	public boolean getExistsProjectNO(String projectNO) {
		//检验参数
		if(projectNO==null || projectNO.equals("")){
			return true ;
		}
		
		//检测账户名是否存在 
		Long exists = this.getProjectConfigDao().queryExistsProjectNO(projectNO);
		if(exists==null || exists>0){
				return true ;
		}else{
				return false;
		}
	}
	
}
