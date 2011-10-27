package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.BranchUserAlarmLogsBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.dao.BranchUserAlarmLogsDao;
import org.tbcc.dao.RealRefDao;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccBranchUserAlarmLogs;
import org.tbcc.entity.TbccPrjType;

public class BranchUserAlarmBizImpl implements BranchUserAlarmLogsBiz {

	private BranchUserAlarmLogsDao branchUserAlarmLogsDao = null ;
	
	private RealRefDao realRefDao = null ;
	
	private BranchDao branchDao = null ;
	
	
	public BranchDao getBranchDao() {
		return branchDao;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	public RealRefDao getRealRefDao() {
		return realRefDao;
	}

	public void setRealRefDao(RealRefDao realRefDao) {
		this.realRefDao = realRefDao;
	}

	public BranchUserAlarmLogsDao getBranchUserAlarmLogsDao() {
		return branchUserAlarmLogsDao;
	}

	public void setBranchUserAlarmLogsDao(
			BranchUserAlarmLogsDao branchUserAlarmLogsDao) {
		this.branchUserAlarmLogsDao = branchUserAlarmLogsDao;
	}

	public void addLogs(TbccBranchUserAlarmLogs logs) {
		//验证参数
		if(logs==null){
			return  ;
		}
		
		this.branchUserAlarmLogsDao.insertLogs(logs) ;

	}

	public void updateLogs(TbccBranchUserAlarmLogs logs) {
		//验证参数
		if(logs==null){
			return ;
		}
		
		this.branchUserAlarmLogsDao.updateLogs(logs) ;

	}

	public Integer getStoreSysAlarmState(Long branchId) {
		
		//验证参数 分支标识Id
		if(branchId==null || branchId.toString().equals("")){
			return 3 ;		//状态未知
		}
		
		TbccBranchType branchType = this.branchDao.get(branchId) ;
		
		String projectId = null ;
		
		//循环遍历所有的冷库工程
		for (TbccPrjType prj : branchType.getPrjTypes()) {
			if(prj.getProjectType().equals(ProjectBiz.REF) ){
				projectId = prj.getProjectId() ;
				break ;
			}
		}
		
		//如果没有独立的仓库，则检测仓库与制冷的混合系统
		if(projectId==null){
			for (TbccPrjType prj : branchType.getPrjTypes()) {
				if( prj.getProjectType().equals(ProjectBiz.REF_COOLER)||
						prj.getProjectType().equals(ProjectBiz.REF_COOLER_BASE)){
					projectId = prj.getProjectId() ;
					break ;
				}
			}
		}
		
		//验证参数 工程标识Id
		if(projectId==null)
			return 3 ;		//状态未知
		
		List<TbccBaseRealRef> list = this.realRefDao.getRealRefSysData(projectId) ;
		if(list==null || list.size()==0){
			return 3 ;
		}
		
		TbccBaseRealRef realRef = list.get(0) ;
		
		
		//验证是否断开连接
		if(realRef.getConnectStatus().toString().equals("2")){
			
			//判断报警状态
			Integer alarm_state = realRef.getAlarmStatus_Sound1() ;
			
			if(alarm_state.toString().equals("-1")){
				return 3 ;		
			}else if (alarm_state.toString().equals("0") || alarm_state.toString().equals("1") ){
				return 2 ;
			}else{
				return 0 ;
			}
		}else{
			return 1 ;
		}
		

	}

	public Integer getAllLogsNumber(String clientName, String userName) {
		//验证参数
		if(clientName==null || userName==null || clientName.equals("")|| userName.equals("")){
			return 0 ;
		}
		return this.branchUserAlarmLogsDao.getAllNumber(clientName, userName);
	}

	public List<TbccBranchUserAlarmLogs> getLogs(String clientName,
			String userName, Integer index, Integer number) {
		//验证参数
		if(clientName==null || userName==null || clientName.equals("")|| userName.equals("")||
				index==null || index.equals("")||number==null || number.equals("")){
			return null ;
		}
		return this.branchUserAlarmLogsDao.getLogs(clientName, userName, index, number);
	}

}
