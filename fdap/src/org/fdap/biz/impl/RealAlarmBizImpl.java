package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fdap.biz.ProjectBiz;
import org.fdap.biz.RealAlarmBiz;
import org.fdap.dao.OrgDao;
import org.fdap.dao.ProjectDao;
import org.fdap.dao.RealAlarmDao;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdaprealalarm;
import org.fdap.util.AlarmTip;

/**
 * 实时报警业务实现接口
 * @author zhaoyou
 *
 */
public class RealAlarmBizImpl implements RealAlarmBiz {
	
		private RealAlarmDao realAlarmDao = null ;
		
		private ProjectDao	projectDao = null ;
		
		private OrgDao orgDao = null  ;
		
		

		public OrgDao getOrgDao() {
			return orgDao;
		}

		public void setOrgDao(OrgDao orgDao) {
			this.orgDao = orgDao;
		}

		public RealAlarmDao getRealAlarmDao() {
			return realAlarmDao;
		}

		public void setRealAlarmDao(RealAlarmDao realAlarmDao) {
			this.realAlarmDao = realAlarmDao;
		}

		public ProjectDao getProjectDao() {
			return projectDao;
		}

		public void setProjectDao(ProjectDao projectDao) {
			this.projectDao = projectDao;
		}

		public List<Fdaprealalarm> getAlarmByProjectIds(String projectIds) {
			
			//验证参数
			if(projectIds==null || projectIds.equals("")){
				return null ;
			}
			
			//获取报警数据，为了解决显示报警冷库名称问题(ajax问题)
			List<Fdaprealalarm> list =  this.realAlarmDao.queryAlarmByProjectIds(buildList(projectIds)) ;
			//为了让AI对象和REF对象懒加载而提高效率，在这里直接给属性aiName和refName赋值
			for (Fdaprealalarm fdaprealalarm : list) {
				fdaprealalarm.setAiName(fdaprealalarm.getFdapaiinfo().getName());
				fdaprealalarm.setRefName(fdaprealalarm.getFdapaiinfo().getFdapref().getName());
			}
			return list ;
		}

		
		
		public List<Fdapproject> getCarProjectByOid(Long oid) {
			
			//验证参数
			if(oid==null || oid.equals("")){
				return null ;
			}
			return this.projectDao.queryByOidAndType(oid, ProjectBiz.CAR_TYPE);
		}

		
		public List<Fdapproject> getRefProjectByOid(Long oid) {
			
			//验证参数
			if(oid==null || oid.equals("")){
				return null ;
			}
			return this.getProjectDao().queryByOidAndType(oid, ProjectBiz.REF_TYPE);
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

		public List<AlarmTip> getAlarmTip(String oid) {
			
			//验证参数
			if(oid==null || oid.equals("")){
				return null;
			}
			
			//获取所有的机构
			List<Fdaporg> orgList = this.orgDao.queryAll();
			
			
			// 获取所有的报警企业标识Id,保存至map结构中
			List<Long > alarmOidList = this.realAlarmDao.queryAlarmOid() ;
			
			if(alarmOidList==null || alarmOidList.size()==0){
				return null ;
			}
			
			Map<Long,Long> alarmOidMap = new HashMap<Long, Long>();
			for (Long long1 : alarmOidList) {
				alarmOidMap.put(long1, long1);
			}
			
			
			//定义变量保存某个机构下，对应企业的map结构
			Map<Long,List<Fdaporg>> map = new HashMap<Long, List<Fdaporg>>();
			
			for (Fdaporg fdaporg : orgList) {
				if(fdaporg.getParentId().toString().equals(oid)){
					//实际上，在低版本中，不会传递下级机构就是企业的编号过来。		
					if(fdaporg.getNodetype().toString().equals("2")){
						map.put(fdaporg.getOid(), Arrays.asList(fdaporg));			
					}else{				
						map.put(fdaporg.getOid(), isEntry(orgList,fdaporg.getOid())) ;
					}
				}
			}
			
			//循环遍历每个机构下的企业
			for (List<Fdaporg> tempList : map.values()) {
				
				for (Iterator<Fdaporg> iterator = tempList.iterator(); iterator
						.hasNext();) {
					Fdaporg obj =  iterator.next();
					//如果某个区域下的企业不在报警企业内，则去掉该企业
					if(!alarmOidMap.containsKey(obj.getOid())){
						iterator.remove();			
					}			
				}
			}
			
			//定义集合保存报警的机构下的企业信息
			List<AlarmTip> tipList = new ArrayList<AlarmTip>();
			
			for (Long key:map.keySet()) {
					List<Fdaporg > s = map.get(key);
					//如果当前区域下还有报警企业了,怎添加报警
					if(s.size()!=0){
						AlarmTip tip = new AlarmTip();
						StringBuffer sb = new StringBuffer();
						for (Fdaporg fdaporg3 : s) {
							sb.append(fdaporg3.getName()+"<br/>");
						}
						tip.setMsg(sb.toString());
						tip.setOid(key);
						tip.setOrgName(getOrgName(orgList, key));
						tipList.add(tip);
					}
					
			}
			return tipList;
		}
		
		/**
		 * 根据机构标识Id，获取该机构的名称
		 * @param list		机构集合
		 * @param oid		机构标识Id
		 * @return
		 */
		private String getOrgName(List<Fdaporg> list ,Long oid){
			
			if(list==null)
				return "" ;
			
			for (Fdaporg fdaporg : list) {
				if(fdaporg.getOid().equals(oid))
					return fdaporg.getName() ;
			}
			return "" ;
		}
		
		
		/**
		 * 递归算出某个机构下的所有企业
		 * @param orgList
		 * @param parentId
		 * @return
		 */
		private List<Fdaporg> isEntry(List<Fdaporg > orgList,Long parentId){
				
			//获取满足要求的下级机构
				List<Fdaporg> sourceList = new ArrayList<Fdaporg>();
				
				for (Fdaporg fdaporg : orgList) {
					if(fdaporg.getParentId().equals(parentId))
						sourceList.add(fdaporg);
				}
				
				//定义符合条件的企业集合，用于返回值
				List<Fdaporg> resultList = new ArrayList<Fdaporg>();
				
				for (Fdaporg fdaporg2 : sourceList) {
					//循环遍历有效集合，如果是企业，则添加到结果集合
					if(fdaporg2.getNodetype().toString().equals("2")){
						resultList.add(fdaporg2);
					}else{				
						resultList.addAll(isEntry(orgList, fdaporg2.getOid()));
					}
				
				}
				return resultList ;
				
		}

		
		
		public List<AlarmTip> getAlarmEntryTip(String parentId) {
			
			//验证参数
			if(parentId==null || parentId.equals("")){
				return null ;
			}
			
			//获取机构下的所有企业
			List<Fdaporg> list = this.orgDao.queryByParentId(new Long(parentId));
			
			//获取报警的企业标识Id集合
			List<Long> oidAlarmList = this.realAlarmDao.queryAlarmOid();
			Map<Long, Long> map = new HashMap<Long, Long>();
			for (Long long1 : oidAlarmList) {
				map.put(long1, long1);
			}
			
			
			//获取报警提示信息
			List<AlarmTip > tipList = new ArrayList<AlarmTip>();
			
			for (Fdaporg fdaporg : list) {
				AlarmTip tip = new AlarmTip();
				tip.setOid(fdaporg.getOid());
				
				//如果当前企业报警了
				if(map.containsKey(fdaporg.getOid())){
					tip.setMsg("1") ;
				}else{
					tip.setMsg("0") ;
				}
					tipList.add(tip) ;
			}

			return tipList;
		}

		public String getAlarmOrgTip(String oid) {
			//验证参数
			if(oid==null || oid.equals("")){
				return null ;
			}
			String isAlarm = "1";
			//获取报警的企业标识Id集合
			List<Long> oidAlarmList = this.realAlarmDao.queryAlarmOid();
			Map<Long, Long> map = new HashMap<Long, Long>();
			for (Long long1 : oidAlarmList) {
				map.put(long1, long1);
			}
			//如果当前企业报警了
			if(map.containsKey(Long.parseLong(oid))){
				isAlarm = "0";
			}
			return isAlarm;
		}
		
}
