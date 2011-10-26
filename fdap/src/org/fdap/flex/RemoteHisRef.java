package org.fdap.flex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fdap.biz.RefHisBiz;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.util.FdaprefAi;
import org.fdap.util.SpringFactoryProxy;


/**
 * 这是为了解决flex 与spring集成。而解决的方案
 * 通过spring容器访问业务层方法 hisrefBiz
 * @author Administrator
 *
 */
public class RemoteHisRef {
		
	private RefHisBiz hisrefBiz = null ;
	
	public RemoteHisRef(){
		hisrefBiz = (RefHisBiz)SpringFactoryProxy.getInstance().getBeanObject("refhisBiz");
	}
	
	
	/**
	 * 根据冷库标识refId,获取该冷库的探头集合
	 * @param refId
	 * @return
	 */
	public List<FdaprefAi> getAiList(String refId){
		List<Fdapaiinfo> aiList = hisrefBiz.getAiByRefId(refId);
		List<FdaprefAi> refAiList = new ArrayList<FdaprefAi>();
		for(Fdapaiinfo ai : aiList){
			FdaprefAi refAi = new FdaprefAi();
			refAi.setAiguid(ai.getAiguid());
			refAi.setName(ai.getName());
			refAi.setReid(ai.getReid());
			refAi.setSelftype(ai.getSelftype());
			refAi.setRefId(ai.getFdapref().getRefId());
			refAiList.add(refAi);
		}
		return refAiList;
	}
	
	/**
	 * 通过企业标识oid、仓库冷库标识refId、开始时间以及结束时间查询仓库冷库历史数据
	 * @param oid
	 * @param refId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Map> getRefHisData(String oid,String refId ,String startTime,String endTime){
		return this.hisrefBiz.getRefHisData_flex(oid, refId, startTime, endTime);
	}
}
