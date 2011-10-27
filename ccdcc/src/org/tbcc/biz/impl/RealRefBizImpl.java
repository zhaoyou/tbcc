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
 * �������ʵʱ���ݵ�ҵ��ʵ����
 * @author Administrator
 *
 */
public class RealRefBizImpl implements RealRefBiz {
	
	/**
	 * ��springע��ʵʱ������ݷ�����
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
				//�Ѹû����µ������ƶ����ؼ��뵽����
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
		
		//�ж��Ƿ���ڲֿ⹤���б�
		if(list==null || list.size()==0){
			return null ;
		}
		
		//����һ����������ֿ⹤���б�      
		List<String> refProjectIds = new ArrayList<String>();
		
		for (TbccPrjType tbccPrjType : list) {
			refProjectIds.add(tbccPrjType.getProjectId());
		}
		
		//��ȡ��Ӵ�ֿ⹤�̵�¥����Ϣ
		List<Map<String, Object>> mapList = this.realRefDao.getFloorInfo(refProjectIds);
		

			
		//ѭ��ÿһ���ֿ⹤��,�����ڸòֿ��¥����Ϣ���뵽�òֿ���						
		for (Map<String, Object> map : mapList) {						
					buildFloorName(map);									//����¥������												
		}
			
		return mapList;
	}
	
	
	/**
	 * ����¥���ź���������¥������
	 * @param map	����¥����Ϣ��map�ṹ
	 */
	private void buildFloorName(Map<String, Object> map){
		String floorName = "" ;
		if(map.get("floorType").equals(RefInfoBiz.FLOORDOWN)){
			floorName = "������B" + map.get("floorNo") + "��" ;
		}else{
			floorName = "����F" +map.get("floorNo")+"��";
		}
		map.put("floorName", floorName);
	}

	
	
	public List<TbccAiInfo> getAiByRef(List<TbccRefInfo> list) {
		
		//�ж���⹤��
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
		
		//�жϲ����Ƿ�Ϸ�
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
		
		//Ĭ�ϻ�ȡ��һ���豸�ϵ�ʵʱ����
		TbccBaseRealRef realRef = list.get(0) ;
		String [] results = new String[]{realRef.getConnectStatus().toString(),
				realRef.getAlarmStatus_LostPower1().toString(),realRef.getAlarmStatus_Sound1().toString()} ;
		
		return results ;
	}


	
	
	

}
