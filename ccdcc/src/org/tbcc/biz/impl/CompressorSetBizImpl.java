package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CCapDevBiz;
import org.tbcc.biz.CompressorSetBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.dao.CompressorSetDao;
import org.tbcc.dao.ProjectDao;
import org.tbcc.dao.RealMulCompressorDao;
import org.tbcc.dao.RealSingleCompressorDao;
import org.tbcc.entity.cool.TbccCcapDevType;
import org.tbcc.entity.cool.TbccCompressorSet;
import org.tbcc.entity.cool.TbccMultiCompressorRealData;
import org.tbcc.entity.cool.TbccSingleCompressorRealData;

public class CompressorSetBizImpl implements CompressorSetBiz {

	private CCapDevBiz ccapDevBiz = null ;

	private CompressorSetDao compressorSetDao = null ;
	
	private RealMulCompressorDao realmulcompressordao = null ;
	
	private RealSingleCompressorDao realsinglecompressordao = null ;
	
	private ProjectDao	projectdao = null ;
	
	public void setProjectdao(ProjectDao projectdao) {
		this.projectdao = projectdao;
	}
	public void setRealmulcompressordao(RealMulCompressorDao realmulcompressordao) {
		this.realmulcompressordao = realmulcompressordao;
	}
	public void setCompressorSetDao(CompressorSetDao compressorSetDao) {
		this.compressorSetDao = compressorSetDao;
	}
	public void setCcapDevBiz(CCapDevBiz ccapDevBiz) {
		this.ccapDevBiz = ccapDevBiz;
	}
	
	public void setRealsinglecompressordao(
			RealSingleCompressorDao realsinglecompressordao) {
		this.realsinglecompressordao = realsinglecompressordao;
	}

	
	
	
	
	public List<TbccCompressorSet> getByProjectId(String projectId) {
		if(projectId==null || projectId.equals(""))
			return null ;
		
		List<TbccCcapDevType> devTypeList = ccapDevBiz.getByProjectId(projectId);
		
		if(devTypeList==null || devTypeList.size()==0)
			return null ;
		
		StringBuffer sb  = new StringBuffer("(") ;
		
		for (int i = 0; i < devTypeList.size(); i++) {
			TbccCcapDevType type = devTypeList.get(i);
			sb.append(type.getId());
			if(i!=devTypeList.size()-1)
				sb.append(",");
		}
	  	sb.append(")") ;
		return compressorSetDao.getByCondition(sb.toString());
	}
	
	
	
	public TbccCompressorSet getById(Integer id) {
		if(id==null || id.equals(""))
			return null ;
		return compressorSetDao.getById(id);
	}
	
	
	
	public TbccMultiCompressorRealData getMultiRealById(Integer id) {
		if(id==null || id.equals(""))
			return null ;
		
		List<TbccMultiCompressorRealData> list = realmulcompressordao.getByCsId(id);
		if(list==null || list.size()==0 )
			return null ;
		return list.get(0) ;
	}
	
	
	
	
	public TbccSingleCompressorRealData getSingleRealById(Integer id) {
		if(id==null || id.equals(""))
			return null ;
		
		List<TbccSingleCompressorRealData> list = this.realsinglecompressordao.get(id);
		
		if(list==null || list.size()==0)
			return null ;
		
		return list.get(0);
	}
	
	
	
	
	public List<TbccCompressorSet> getByBranchId(Long branchId) {
		
		if(branchId==null || branchId.equals(""))
				return null ;
		
		//获取所有的工程标识Id ,原先是直接获取所有的冷库工程、现在是该为获取冷库工程、和制冷工程的总和
		//List<String> list  = projectdao.getRefProjectIds(branchId);
	
		List<String> list  = projectdao.getCoolerProjectIds(branchId);
		
		if(list==null || list.size()==0)
				return null ;
		
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < list.size(); i++) {
			sb.append("'"+list.get(i)+"'");
			if(i!=list.size()-1)
				sb.append(",");
		}
		sb.append(")");
		
		
		//获取所有的制冷设备集合
		List<TbccCcapDevType>  devList  =  ccapDevBiz.getByProjects(sb.toString());
		
		if(devList==null || devList.size()==0)
				return null ;
		
		
		StringBuffer sb2  = new StringBuffer("(") ;
		
		for (int i = 0; i < devList.size(); i++) {
			sb2.append(devList.get(i).getId());
			if(i!=devList.size()-1)
				sb2.append(",");
		}
		sb2.append(")") ;
		
		return compressorSetDao.getByCondition(sb2.toString());
		
	}
	
	
	

}
