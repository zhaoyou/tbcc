package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.StartUpBiz;
import org.fdap.dao.StartUpDao;
import org.fdap.entity.FdapStartUp;

/**
 * 车载启停记录业务实现接口
 * @author zhoukuanwei
 *
 */
public class StartUpBizImpl implements StartUpBiz {

	private StartUpDao startupdao;
	
	public StartUpDao getStartupdao() {
		return startupdao;
	}
	public void setStartupdao(StartUpDao startupdao) {
		this.startupdao = startupdao;
	}

	public List<FdapStartUp> getStartup(String tableName, String refid, String startTime,
			String endTime,Integer startRow,Integer pagesize) {
		return startupdao.queryStartUp(tableName, refid, startTime, endTime,startRow,pagesize);
	}
	
	public Integer getStartupCounts(String tableName, String refid,
			String startTime, String endTime) {
		return startupdao.getStartupCounts(tableName, refid, startTime, endTime);
	}
	
	public FdapStartUp getByOidAndSid(String oid, long sid) {
		if(oid==null||oid.equals("")||sid<0){
			return null;
		}else{
			String tableName="Fdapstartup_"+oid;
			return startupdao.queryBySid(tableName, sid);
		}
	}
	
}
