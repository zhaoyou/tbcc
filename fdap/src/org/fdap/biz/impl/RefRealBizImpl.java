package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.fdap.biz.ProjectBiz;
import org.fdap.biz.RefRealBiz;
import org.fdap.dao.AiInfoDao;
import org.fdap.dao.ProjectDao;
import org.fdap.dao.RefDao;
import org.fdap.dao.RefRealDataDao;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdaprefrealdata;

/**
 * 冷库实时数据业务实现接口
 * @author zhaoyou
 *
 */
public class RefRealBizImpl implements RefRealBiz {

	private RefRealDataDao refRealDao = null ;
	
	private RefDao refDao = null ;
	
	private AiInfoDao aiInfoDao = null ;
	
	private ProjectDao projectDao = null ;
	
	
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public AiInfoDao getAiInfoDao() {
		return aiInfoDao;
	}

	public void setAiInfoDao(AiInfoDao aiInfoDao) {
		this.aiInfoDao = aiInfoDao;
	}

	public RefDao getRefDao() {
		return refDao;
	}

	public void setRefDao(RefDao refDao) {
		this.refDao = refDao;
	}

	public RefRealDataDao getRefRealDao() {
		return refRealDao;
	}

	public void setRefRealDao(RefRealDataDao refRealDao) {
		this.refRealDao = refRealDao;
	}



	public List<Fdapref> getRefByProjectIds(String projectIds) {
		
		//验证参数
		if(projectIds==null || projectIds.equals("")){
			return null ;
		}
		
		//把string工程集合转化为Long集合	
		return this.refDao.queryByProjectIds(buildList(projectIds));
		
	}



	public List<Fdapaiinfo> getAiByProjectIds(String projectIds) {
		
		//验证参数
		if(projectIds==null || projectIds.equals("")){
			return null ;
		}
		
		return this.aiInfoDao.queryRefAiByProjectIds(buildList(projectIds));
	}



	public List<Fdaprefrealdata> getRealByProjectIds(String projectIds) {
		
		//验证参数
		if(projectIds==null|| projectIds.equals("")){
			return null ;
		}
		
		return this.refRealDao.queryByProjectIds(buildList(projectIds));
	}
	
	/**
	 * 构造工程集合
	 * @param projectIds		工程集合字符串
	 * @return
	 */
	private List<Long> buildList(String projectIds){

		List<Long> list = new ArrayList<Long>();
			
		if(projectIds==null || projectIds.equals(""))
			return list ;
		
		String[] ids = projectIds.split(",");
		
		
		for (String string : ids) {
			list.add(Long.parseLong(string));
		}
			return list ;
	}



	public List<Fdapproject> getRefProjectByOid(Long oid) {
		
		//验证参数
		if(oid==null || oid.equals("")){
			return null ;
		}
		
		return this.projectDao.queryByOidAndType(new Long(oid), ProjectBiz.REF_TYPE);
	}

}
