package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.fdap.biz.HisAlarmBiz;
import org.fdap.dao.HisAlarmDao;
import org.fdap.dao.OrgDao;
import org.fdap.entity.FdapHisAlarm;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapref;
import org.fdap.util.AlarmStatisticsTip;

/**
 * 历史报警业务实现接口
 * @author zhoukuanwei
 *
 */
public class HisAlarmBizImpl implements HisAlarmBiz {

	private HisAlarmDao hisalarmdao;
	private OrgDao orgdao;
	public HisAlarmDao getHisalarmdao() {
		return hisalarmdao;
	}

	public void setHisalarmdao(HisAlarmDao hisalarmdao) {
		this.hisalarmdao = hisalarmdao;
	}

	public OrgDao getOrgdao() {
		return orgdao;
	}

	public void setOrgdao(OrgDao orgdao) {
		this.orgdao = orgdao;
	}

	public List<FdapHisAlarm> getHisAlarm(String tableName, String startTime,String endTime,
			Integer startrow,Integer pagesize,String oid,String aiType,String alarmLevel,String projectType) {
		List<Fdapaiinfo> ailist=null;
		if(!projectType.equals("4")){
			ailist=hisalarmdao.queryAiinfoByOidAndPType(oid, projectType);
		}
		else{
			ailist=hisalarmdao.queryAiinfoByOid(oid);
		}
		
		List<FdapHisAlarm> alarmlist=null;
		//4代表全部
		if(ailist!=null&&ailist.size()!=0){
			String[] aiIds=new String[ailist.size()];
			for(int i=0;i<ailist.size();i++){
				aiIds[i]=(ailist.get(i)).getAiguid();
			}
			
			if(!aiType.equals("4")&&!alarmLevel.equals("4")){
				alarmlist=hisalarmdao.queryHisAlarmByAiTypeAndLevel(tableName, startTime, endTime, startrow, pagesize, aiType, alarmLevel, aiIds);
			}
			else if(aiType.equals("4")&&!alarmLevel.equals("4")){
				alarmlist=hisalarmdao.queryHisAlarmByAlarmLevel(tableName, startTime, endTime, startrow, pagesize, alarmLevel, aiIds);
			}
			else if(!aiType.equals("4")&&alarmLevel.equals("4")){
				alarmlist=hisalarmdao.queryHisAlarmByAiType(tableName, startTime, endTime, startrow, pagesize, aiType, aiIds);
			}
			else{
				alarmlist=hisalarmdao.queryHisAlarm(tableName, startTime, endTime,startrow,pagesize, aiIds);
			}
		}
		
		
		//为了解决在页面显示按照冷库排序
		List<Fdapref> reflist = new ArrayList<Fdapref>();
		reflist = hisalarmdao.queryRefByOid(oid);
		List<FdapHisAlarm> list = new ArrayList<FdapHisAlarm>();
		
		for(Fdapref ref : reflist){
			for(FdapHisAlarm hisalarm : alarmlist){
				if(ref.getRefId().longValue()==hisalarm.getFdapaiinfo().getFdapref().getRefId().longValue()){
					list.add(hisalarm);
				}
			}
		}
		
		/*//给每条历史报警对象的冷库名称赋值
		if(alarmlist!=null&&alarmlist.size()!=0){
				for(FdapHisAlarm fha : alarmlist){
					fha.setRefName(fha.getFdapaiinfo().getFdapref().getName());
				}
		}*/
		return list;
	}


	public Integer getHisAlarmCount(String tableName, String startTime,
			String endTime,String aiType,String alarmLevel,String oid,String projectType) {
		List<Fdapaiinfo> ailist=null;
		if(!projectType.equals("4")){
			ailist=hisalarmdao.queryAiinfoByOidAndPType(oid, projectType);
		}
		else{
			ailist=hisalarmdao.queryAiinfoByOid(oid);
		}
		
		//4代表全部
		if(ailist!=null&&ailist.size()!=0){
			String[] aiIds=new String[ailist.size()];
			for(int i=0;i<ailist.size();i++){
				aiIds[i]=(ailist.get(i)).getAiguid();
			}
			if(!aiType.equals("4")&&!alarmLevel.equals("4")){
				return hisalarmdao.queryHisAlarmCountByAiTypeAndLevel(tableName, startTime, endTime, aiType, alarmLevel,aiIds);
			}
			else if(aiType.equals("4")&&!alarmLevel.equals("4")){
				return hisalarmdao.queryHisAlarmCountByAlarmLevel(tableName, startTime, endTime, alarmLevel,aiIds);
			}
			else if(!aiType.equals("4")&&alarmLevel.equals("4")){
				return hisalarmdao.queryHisAlarmCountByAiType(tableName, startTime, endTime, aiType,aiIds);
			}
			return hisalarmdao.queryHisAlarmCount(tableName, startTime, endTime,aiIds);
		}
		else{
			return null;
		}
	}
	
	public List<AlarmStatisticsTip> getAlarmCounts(String oid) {
		if(oid==null||oid.equals("")){
			return null;
		}
		List<Fdaporg> orglist = orgdao.queryByParentId(Long.parseLong(oid));
		List<AlarmStatisticsTip> atlist = new ArrayList<AlarmStatisticsTip>();
		for(Fdaporg org : orglist){
			AlarmStatisticsTip at = new AlarmStatisticsTip();
			at.setOid(org.getOid());
			at.setOrgName(org.getName());
			//所有的报警数
			at.setMsg(this.hiscount(new Long(0), org, 0).toString());
			//严重报警数量
			at.setSerious(this.hiscount(new Long(0), org, 1).toString());
			//普通报警数量
			at.setCommon(this.hiscount(new Long(0), org, 2).toString());
			//轻微报警数量
			at.setLight(this.hiscount(new Long(0), org, 3).toString());
			atlist.add(at);
		}
		Integer count = 0;
		for(AlarmStatisticsTip at : atlist){
			count += Integer.parseInt(at.getMsg());
		}
		for(AlarmStatisticsTip at : atlist){
			at.setPercent(String.valueOf(Math.round((Float.parseFloat(at.getMsg())/count)*10000)/100f)+"％");
		}
		
		return atlist;
	}

	public Long hiscount(Long count,Fdaporg org,Integer alarmLevel){
		if(org.getNodetype()==2){
			String tableName = "fdaphisalarm_"+org.getOid();
			if(alarmLevel==0)
				count=count+hisalarmdao.queryAlarmCounts(tableName);
			else
				count=count+hisalarmdao.queryAlarmCountsByLevel(tableName,alarmLevel);
		}else{
			List<Fdaporg> orglist = orgdao.queryByParentId(org.getOid());
			for(Fdaporg forg : orglist){
				count = hiscount(count,forg,alarmLevel);
			}
		}
		return count;
	}
}
