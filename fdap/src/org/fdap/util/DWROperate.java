package org.fdap.util;

import java.util.List;

import org.fdap.biz.CarRealBiz;
import org.fdap.biz.OrgConfigBiz;
import org.fdap.biz.ProjectConfigBiz;
import org.fdap.biz.RealAlarmBiz;
import org.fdap.biz.RefConfigBiz;
import org.fdap.biz.RefHisBiz;
import org.fdap.biz.UserConfigBiz;
import org.fdap.entity.Fdapcarrealdata;
import org.fdap.entity.Fdaprealalarm;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdapuser;


/**
 * dwr调用的中间类,为了解决spring容器与dwr集成而采用的一种折中的实现
 * @author zhaoyou
 *
 */
public class DWROperate {
	
		private CarRealBiz carRealBiz = (CarRealBiz)SpringFactoryProxy.getInstance().getBeanObject("carRealBiz");

	
		private RefHisBiz refhisbiz =(RefHisBiz)SpringFactoryProxy.getInstance().getBeanObject("refhisBiz");

		
		private OrgConfigBiz orgConfigBiz = (OrgConfigBiz)SpringFactoryProxy.getInstance().getBeanObject("orgConfigBiz") ;
		

		private UserConfigBiz userConfigBiz=(UserConfigBiz)SpringFactoryProxy.getInstance().getBeanObject("userConfigBiz");

		
		private ProjectConfigBiz projectconfigbiz = (ProjectConfigBiz)SpringFactoryProxy.getInstance().getBeanObject("projectConfigBiz");
		
		
		private RefConfigBiz refconfigbiz = (RefConfigBiz)SpringFactoryProxy.getInstance().getBeanObject("refConfigBiz");
		
		private RealAlarmBiz realAlarmBiz = (RealAlarmBiz) SpringFactoryProxy.getInstance().getBeanObject("realAlarmBiz");
		
		/**
		 * 通过车载工程标示Id集合，获取车载实时数据 
		 * @param projectIds		车载工程集合
		 * @return
		 */
		public List<Fdapcarrealdata> getRealCarData(String projectIds){
			return carRealBiz.getByProjectids(projectIds);
		}
		
		
		/**
		 *  通过工程标识Ｉｄ集合，获取该工程下的所有报警集合
		 * @param projectIds		工程标识集合
		 * @return
		 */
		public List<Fdaprealalarm> getRealAlarm(String projectIds){
			return realAlarmBiz.getAlarmByProjectIds(projectIds);
		}
		
		/**
		 * 通过机构的标识Id，获取该机构下的企业报警信息
		 * @param oid
		 * @return
		 */
		public List<AlarmTip>  getAlarmTip(String oid){
			return realAlarmBiz.getAlarmTip(oid);
		}
		
		/**
		 * 通过机构的标识id，获取该机构下的企业报警信息列表
		 * @param parentId		机构标识Id
		 * @return
		 */
		public List<AlarmTip> getAlarmEntryTip(String parentId){
			return realAlarmBiz.getAlarmEntryTip(parentId);
		}
		
		/**
		 * 通过企业的标识id，获取该企业的报警信息
		 * @param oid		企业标识Id
		 * @return
		 */
		public String getAlarmOrgTip(String oid){
			return realAlarmBiz.getAlarmOrgTip(oid);
		}
		
		/**
		 * 通过工程标识Id，获取该工程下的所有冷库集合
		 * @param projectId		工程标识Id
		 * @return
		 */
		public List<Fdapref> getRefByProjectId(Long projectId) {
			return refhisbiz.getRefByProjectId(projectId);
		}
		

		/**
		 * 判断账户名是否同名
		 * @param account		需要验证的账户名
		 * @return
		 */
		public boolean getExistsAccount(String account){
			return orgConfigBiz.getExistsAccount(account);
		}
		
		/**
		 * 判断工程名是否同名
		 * @param name		需要验证的工程名
		 * @param oid		企业标识oid
		 * @return
		 */
		public boolean getExistsProject(String name,String oid){
			return projectconfigbiz.getExistsProject(name,oid);
		}

		/**
		 * 通过企业标识Id，获取该企业下的所有非系统管理员和非工程人员的用户信息列表
		 * @param oid		企业标识Id
		 * @return
		 */
		public List<Fdapuser> getUserByOid(String oid){
			return userConfigBiz.getOrguser(oid);
		}
		
		
		/**
		 * 判断冷库名是否同名
		 * @param name		需要验证的冷库名
		 * @param projectid		工程标识projectid
		 * @return
		 */
		public boolean getExistsRef(String name,String projectid){
			return refconfigbiz.getExistsRef(name, projectid);
		}
		
		/**
		 * 检测对应机构是否已经存在要添加或更改的企业名称
		 * @param name		需要验证的企业名称
		 * @param parentOid		企业标识parentOid
		 * @return
		 */
		public boolean getExistsOrg(String name,String parentOid){
			return orgConfigBiz.getExistsOrg(name, parentOid);
		}
		
		/**
		 * 判断工程编号是否存在
		 * @param projectNO		需要验证的工程编号
		 * @return
		 */
		public boolean getExistsProjectNO(String projectNO){
			return projectconfigbiz.getExistsProjectNO(projectNO);
		}
		
}
