package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.fdap.biz.OrgConfigBiz;
import org.fdap.dao.AiConfigDao;
import org.fdap.dao.OrgConfigDao;
import org.fdap.dao.OrgDao;
import org.fdap.dao.ProjectConfigDao;
import org.fdap.dao.RefConfigDao;
import org.fdap.entity.Fdapauthcode;
import org.fdap.entity.Fdaporg;

/**
 * 机构配置的业务实现类
 * @author zhaoyou
 *
 */
public class OrgConfigBizImpl implements OrgConfigBiz {

	private OrgConfigDao orgConfigDao = null ;
	
	private OrgDao 	orgDao = null ;
	
	private ProjectConfigDao projectConfigDao = null ;
	
	private RefConfigDao refConfigDao = null ;
	
	private AiConfigDao aiConfigDao = null ;
		
	public ProjectConfigDao getProjectConfigDao() {
		return projectConfigDao;
	}
	public void setProjectConfigDao(ProjectConfigDao projectConfigDao) {
		this.projectConfigDao = projectConfigDao;
	}
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
	public OrgConfigDao getOrgConfigDao() {
		return orgConfigDao;
	}
	public void setOrgConfigDao(OrgConfigDao orgConfigDao) {
		this.orgConfigDao = orgConfigDao;
	}



	@Override
	public Fdaporg getTopOrg() {
		
		//获取机构集合
		List<Fdaporg> list = this.getOrgConfigDao().queryTopOrg() ;
		
		if(list==null || list.size()==0){
			return null ;
		}
		return list.get(0);
		
	}
	@Override
	public boolean updateTopOrg(Fdaporg topOrg) {
		//验证参数
		if(topOrg==null){
			return false ;
		}
		
		try {
			this.getOrgConfigDao().updateOrg(topOrg) ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return false ;
		}
	
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
			if(fdaporg.getNodetype()==2){
				sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:isOrgQ('"+fdaporg.getName()+"');\") ;") ;
			}else{
				sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:goLowerOrg('"+fdaporg.getOid()+"');\") ;") ;
			}
		}
		
		return sb.toString();
	}
	@Override
	public List<Fdaporg> getOrgList(Long parentId) {
		
		//验证参数
		if(parentId==null || parentId.equals("")){
			return null ;
		}
		
		return this.getOrgDao().queryByParentId(parentId);
	}
	@Override
	public boolean addOrg(Fdaporg org) {
		//验证参数
		if(org==null){
			return false ;
		}	
		try {
			this.getOrgConfigDao().insertOrg(org) ;
			//如果是企业，则需要创建动态表 ,  并且创建企业对应的授权码
			if(org.getNodetype().toString().equals("2")){
				this.getOrgConfigDao().createTable(org.getOid());
				Fdapauthcode authCode = new Fdapauthcode();
				authCode.setFdaporg(org) ;
				authCode.setCode(null) ;
				
				
				List<Object> codelist = this.getOrgConfigDao().queryAllCode();
				Integer max = 1000;
				Integer code = 0;
				if(codelist!=null&&codelist.size()!=0){
					for(Object obj : codelist){
						code = Integer.parseInt(obj.toString());
						if(code>max) max = code;
						System.out.println(obj.toString());
					}
				}
//				System.out.println("max:"+max);
				max=max+1;
				authCode.setCode(max.toString()) ;
				this.orgConfigDao.insertAuthCode(authCode) ;
				org.setAuthcode(authCode ) ;			//此处手动设置授权码
			}		
		} catch (Exception e) {
			e.printStackTrace() ;
			return false ;
		}
		
		return true ;	
	}
	@Override
	public boolean updateOrg(Fdaporg org) {

		//验证参数
		if(org==null){
			return false ;
		}
		try {
			this.orgConfigDao.updateOrg(org) ;
		} catch (Exception e) {
			return false ;
		}
		return true;
	}
	@Override
	public boolean getExistsAccount(String account) {
	
		//检验参数
		if(account==null || account.equals("")){
			return true ;
		}
		
		//检测账户名是否存在 
		Long exists = this.getOrgConfigDao().queryByName(account);
		if(exists==null || exists>0){
				return true ;
		}else{
				return false;
		}
	}
	@Override
	public boolean delOrg(String[] orgIds) {
		
		//验证参数
		if(orgIds==null || orgIds.length==0)
			return false ;
		List<Long > orgIdList = new ArrayList<Long>();
		try {
			for (String string : orgIds) {
				
				orgIdList.add(new Long(string)) ;
				
				//删除企业对应的动态表
				this.getOrgConfigDao().dropTable(new Long(string));
				
				//删除探头
				this.getAiConfigDao().deleteAiByOid(new Long(string));
				//删除冷库
				this.getRefConfigDao().deleteByOid(new Long(string));
				// 删除工程
				this.getProjectConfigDao().deleteByOid(new Long(string));
				
				//删除企业对应的授权码
				this.getOrgConfigDao().delAuthCode(new Long(string)) ;
				
			}
			//删除机构
			this.getOrgConfigDao().deleteOrgList(orgIdList);
			
		} catch (Exception e) {
			e.printStackTrace() ;
			return false ;
		}
		return true;
	}
	@Override
	public boolean getExistsLowerOrg(String[] ids) {
		//验证参数
		if(ids==null || ids.length==0){
			return true ;
		}
		
		List<Long> list = new ArrayList<Long>();
		
		for (String string : ids) {
			list.add(new Long(string)) ;
		}
		//获取是否存在下级机构
		Long flag = this.getOrgConfigDao().queryExistsLowerOrg(list) ;
		
		if(flag==null || flag>0){
			return true ;
		}
		
		return false;
	}
	
	@Override
	public boolean getExistsOrg(String name, String parentOid) {
		if(name==null||name.equals("")||parentOid==null||parentOid.equals("")){
			return true;
		}
		Long exists = orgConfigDao.queryExistsOrg(name, parentOid);
		if(exists==null||exists>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String getOnlyOrgTree() {
		List<Fdaporg> list = this.getOrgDao().queryAll() ;
		
		Map<Long,Integer> map = getOrgTypeMap(list);
		
		StringBuffer sb = new StringBuffer("");
		//生成树形菜单
		sb.append("var d = new dTree('d') ;") ;
		for (Fdaporg fdaporg : list) {
			if(fdaporg.getNodetype().toString().equals("2")){
				sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:;\") ;") ;
			}else{
				if(map.containsKey(fdaporg.getOid())){
					sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:select_parent_org('"+fdaporg.getOid()+"','"+fdaporg.getName()+"','"+map.get(fdaporg.getOid())+"');\") ;") ;
				}else{
					sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:select_parent_org('"+fdaporg.getOid()+"','"+fdaporg.getName()+"','3');\") ;") ;
				}
				//sb.append("d.add("+fdaporg.getOid()+","+fdaporg.getParentId()+",'"+fdaporg.getName()+"',\"javascript:select_parent_org('"+fdaporg.getOid()+"','"+fdaporg.getName()+"');\") ;") ;
			}
		}
		
		
		return	sb.toString();
	}
	
	public Map<Long,Integer> getOrgTypeMap(List<Fdaporg> list){
		Map<Long,Integer> map = new HashMap<Long, Integer>();
		for(Fdaporg fo : list){
			if(!map.containsKey(fo.getParentId())){
				map.put(fo.getParentId(), fo.getNodetype());
			}
		}
		return map;
	}
	
}
