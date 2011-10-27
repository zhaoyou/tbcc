package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RealRefBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.dao.RealRefDao;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这是冷库实时数据的业务实现类
 * @author Administrator
 *
 */
public class RealRefBizImpl implements RealRefBiz {
	
	/**
	 * 由spring注入实时冷库数据访问类
	 */
	private RealRefDao realRefDao = null ;
	
	private BranchDao branchDao = null ;

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	public void setRealRefDao(RealRefDao realRefDao) {
		this.realRefDao = realRefDao;
	}
	
	
	

	public List<TbccBaseRealRef> getRealRefData(String projectId) {
		return this.realRefDao.getRealRefData(projectId);
	}


	
	public String getRefPrjId(Long branchId) {
		
		TbccBranchType branchType = this.branchDao.get(branchId);
		
		
		if(branchType!=null){			
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//把该机构下的所有移动车载加入到集合
				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
					if(tbccPrjType.getProjectType().equals(ProjectBiz.REF))
						return tbccPrjType.getProjectId();
						
				}			
			}
		}
		return null;
	}
	
	
	public TbccBranchType getById(Long branchId) {
		return this.branchDao.get(branchId);
	}

	
	
	public List<Map<String, Object>> getRefFloorInfo(List<TbccPrjType> list) {
		
		//判断是否存在仓库工程列表
		if(list==null || list.size()==0){
			return null ;
		}
		
		//定义一个变量保存仓库工程列表      
		List<String> refProjectIds = new ArrayList<String>();
		
		for (TbccPrjType tbccPrjType : list) {
			refProjectIds.add(tbccPrjType.getProjectId());
		}
		
		//获取所哟仓库工程的楼层信息
		List<Map<String, Object>> mapList = this.realRefDao.getFloorInfo(refProjectIds);
		

			
		//循环每一个仓库工程,把属于该仓库的楼层信息加入到该仓库中						
		for (Map<String, Object> map : mapList) {						
					buildFloorName(map);									//增加楼层名称												
		}
			
		return mapList;
	}
	
	
	/**
	 * 根据楼层编号和类型生成楼层名称
	 * @param map	包含楼层信息的map结构
	 */
	private void buildFloorName(Map<String, Object> map){
		String floorName = "" ;
		if(map.get("floorType").equals(RefInfoBiz.FLOORDOWN)){
			floorName = "地下室B" + map.get("floorNo") + "层" ;
		}else{
			floorName = "地面F" +map.get("floorNo")+"层";
		}
		map.put("floorName", floorName);
	}

	
	
	public List<TbccAiInfo> getAiByRef(List<TbccRefInfo> list) {
		
		//判断冷库工程
		if(list==null || list.size()==0){
			return null ;
		}
		
		List<Long> refIds = new ArrayList<Long>();
		
		for (TbccRefInfo tbccRefInfo : list) {
			refIds.add(tbccRefInfo.getId());
		}
		
		return this.realRefDao.getAiByRefId(refIds);
	}

	
	
	public List<TbccRefInfo> getRefByFloorInfo(String projectId,
			Integer floorType, Integer floorNo) {
		
		//判断参数是否合法
		if(projectId==null || projectId.equals("") || floorType==null
				|| floorType.equals("") || floorNo==null || floorNo.equals(""))
		{
			return null ;
		}
		return this.realRefDao.getRefByPidAndFloorInfo(projectId, floorType, floorNo);
	}

	
	
	public String[] getRealRefSystemData(String projectId) {
		if(projectId==null || projectId.equals(""))
		{
			return null ;
		}
		
		List<TbccBaseRealRef> list = this.realRefDao.getRealRefSysData(projectId) ;
		
		if(list==null || list.size()==0)
			return null ;
		
		//默认获取第一个设备上的实时数据
		TbccBaseRealRef realRef = list.get(0) ;
		String [] results = new String[]{realRef.getConnectStatus().toString(),
				realRef.getAlarmStatus_LostPower1().toString(),realRef.getAlarmStatus_Sound1().toString()} ;
		
		return results ;
	}


	
	
	

}
