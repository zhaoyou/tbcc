package org.fdap.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fdap.biz.RefHisBiz;
import org.fdap.dao.ProjectDao;
import org.fdap.dao.RefDao;
import org.fdap.dao.RefHisDao;
import org.fdap.entity.FdapRefHisData;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;
//import org.fdap.entity.Fdapref;
import org.fdap.util.GetMaxMinAvg;


/**
 * 仓库冷库历史数据业务实现接口
 * @author zhoukuanwei
 *
 */
public class RefHisBizImpl implements RefHisBiz {

	private RefHisDao refhisdao;
	private ProjectDao projectdao;
	private RefDao refdao;
	private GetMaxMinAvg getmaxminavg;
	
	public GetMaxMinAvg getGetmaxminavg() {
		return getmaxminavg;
	}

	public void setGetmaxminavg(GetMaxMinAvg getmaxminavg) {
		this.getmaxminavg = getmaxminavg;
	}

	public RefHisDao getRefhisdao() {
		return refhisdao;
	}

	public void setRefhisdao(RefHisDao refhisdao) {
		this.refhisdao = refhisdao;
	}
	
	public ProjectDao getProjectdao() {
		return projectdao;
	}

	public void setProjectdao(ProjectDao projectdao) {
		this.projectdao = projectdao;
	}
	
	public RefDao getRefdao() {
		return refdao;
	}

	public void setRefdao(RefDao refdao) {
		this.refdao = refdao;
	}

	public List<Object> getRefHisData(String tableName, String startTime,
			String endTime,Integer startrow,Integer pagesize,List<Fdapaiinfo> AiList,String refId) {
		//获取 pagesize 个从小到大的时刻
		List<Date> timeList=refhisdao.queryTimeScope(tableName, startTime, endTime, startrow, pagesize,refId);
//		System.out.println();
		List<FdapRefHisData> list=null;
		//将第一个时间刻和最后一个时间刻的类型由Date类型转换成String类型
		if(timeList!=null&&timeList.size()>=1){
			String time1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeList.get(0));
			String time2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeList.get(timeList.size()-1));
			list= refhisdao.queryRefHisData(tableName, time1,time2,refId);
		}
		return getmaxminavg.getrefhis(list, timeList, AiList);
	}

	public List<Fdapaiinfo> getAiByRefId(String refId) {
		
		return refhisdao.queryAiByRefId(refId);
	}

	public Integer getRefHisCount(String tableName, String startTime,
			String endTime,String refId) {
		return refhisdao.queryRefHisCount(tableName, startTime, endTime,refId).size();
	}

	public List<Object> getRefHisMMA(String tableName, String startTime,
			String endTime,List<Fdapaiinfo> AiList,String refId) {
		List<FdapRefHisData> list=refhisdao.queryRefHisAll(tableName, startTime, endTime,refId);
		if(list!=null&&list.size()!=0){
			return getmaxminavg.getRefMaxMinAvg(list, AiList);
		}
		return null;
	}

	public List<Fdapproject> getProjectByOid(Long oid) {
		return projectdao.queryByOidAndType(oid, REF_TYPE);
	}

	public List<Fdapref> getRefByProjectId(Long projectId) {
		return refdao.queryByProjectId(projectId);
	}

	public List<Map> getRefHisData_flex(String oid, String refId, String startTime,
			String endTime) {
		List<Fdapaiinfo> AiList = refhisdao.queryAiByRefId(refId);
		String tableName ="fdaprefhisdata_"+oid;
		//获取 pagesize 个从小到大的时刻
		List<Date> timeList=refhisdao.queryTimeScope_All(tableName, startTime, endTime,refId);
//		System.out.println();
		List<FdapRefHisData> list=null;
		//将第一个时间刻和最后一个时间刻的类型由Date类型转换成String类型
		if(timeList!=null&&timeList.size()>=1){
			String time1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeList.get(0));
			String time2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeList.get(timeList.size()-1));
			list= refhisdao.queryRefHisData(tableName, time1,time2,refId);
		}
		return dealrefhisData(list, timeList, AiList);
	}
	
	
	private List<Map> dealrefhisData(List<FdapRefHisData> list,List<Date> timeList,List<Fdapaiinfo> ailist){
		if(ailist!=null&&ailist.size()!=0&&list!=null&&list.size()!=0&&timeList!=null&&timeList.size()!=0){
			Map<String,Object> data = null ;
			List<Map> mlist=new ArrayList<Map>();
			for(Date time : timeList){
				data=new HashMap<String, Object>();
				//保存历史数据的时间
				data.put("updateTime", time);
				//每个探头数据默认赋值为null
				for(int i=0;i<ailist.size();i++){
					data.put(ailist.get(i).getReid().toString(), null);
				}
				for(FdapRefHisData frh : list){
					if(frh.getTime().compareTo(time)==0){
						for(int i=0;i<ailist.size();i++){
							if(ailist.get(i).getAiguid().equals(frh.getAiGuid())){
								data.put(ailist.get(i).getReid().toString(), frh.getData());
								break;
							}
						}
					}
				}
				mlist.add(data);
			}
			return mlist;
		}
		return null;
	}
	
}
