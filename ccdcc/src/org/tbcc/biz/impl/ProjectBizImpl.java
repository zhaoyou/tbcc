package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.ProjectBiz;
import org.tbcc.dao.ProjectDao;
import org.tbcc.entity.TbccPrjType;

/**
 * 这是项目操作业务实现类
 * @author Administrator
 *
 */
public class ProjectBizImpl implements ProjectBiz {
	
	private ProjectDao proDao = null ;

	public void setProDao(ProjectDao proDao) {
		this.proDao = proDao;
	}

	
	public List<TbccPrjType> getByBranchId(Long branchId) {
		return proDao.getByBranchId(branchId);
	}

	
	public TbccPrjType getByProId(String projectId) {
		return this.proDao.get(projectId);
	}

	
	public TbccPrjType getByproIdProxy(String projectId) {
		TbccPrjType typeS = this.proDao.get(projectId) ;
		TbccPrjType typeT = null ;
		if(typeS!=null){
			typeT = new TbccPrjType();
			typeT.setProjectId(typeS.getProjectId()) ;
			typeT.setProjectName(typeS.getProjectName());
			return typeT ;
		}
	
		return null;
	}
	
	
	public List<TbccPrjType> getRefProjListBybId(Long branchId) {
		
		if(branchId==null || branchId.equals("")){
			return null ;
		}		
		return this.proDao.getRefProjList(branchId)  ;
	}


	public List<TbccPrjType> getRefProjListProxyByBId(Long branchId) {
		
		if(branchId==null || branchId.equals("")){
			return null ;
		}
		List<TbccPrjType> list = this.proDao.getRefProjList(branchId);
		for (TbccPrjType tbccPrjType : list) {
			tbccPrjType.setBranchTypes(null) ;
		}
		return list;
	}


	public List<String> getRefProjects(Long branchId) {
		return proDao.getRefProjectIds(branchId);
	}


	public List<TbccPrjType> getHasImagesProjListBybId(Long branchId) {
		if(branchId==null || branchId.equals("")){
			return null ;
		}	
		return proDao.getHasImagesProjList(branchId);
	}


	public List<TbccPrjType> getBoxProjListBybId(Long branchId) {
		if(branchId == null || branchId.equals(""))
			return null ;
		return proDao.getBoxProjList(branchId);
	}


	public List<TbccPrjType> getCarProjListBybId(Long branchId) {
		if(branchId==null || branchId.equals(""))
			return null ;
		return proDao.getCarProjList(branchId);
	}
	
	
	public List<String> getCarProjectIds(Long branchId) {
		if(branchId==null || branchId.equals(""))
			return null ;
		return proDao.getCarProjectIds(branchId) ;
	}


	public List<TbccPrjType> getCoolerProjListBybid(Long branchId) {	
		if(branchId==null || branchId.equals(""))
			return null ;	
		return proDao.getCoolerProjList(branchId);
	}


	public List<String> getCoolerProjects(Long branchId) {
		if(branchId==null || branchId.equals(""))
			return null ;
		return this.proDao.getCoolerProjectIds(branchId);
	}

}
