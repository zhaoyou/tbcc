package org.fdap.flex;

import java.util.List;

import org.fdap.biz.HisAlarmBiz;
import org.fdap.util.AlarmStatisticsTip;
import org.fdap.util.SpringFactoryProxy;

public class HisAlarmCount {
	
	private HisAlarmBiz hisalarmbiz = null;
	
	
	public HisAlarmCount(){
		hisalarmbiz = (HisAlarmBiz)SpringFactoryProxy.getInstance().getBeanObject("hisalarmBiz");
	}
	
	/**
	 * 获取某机构的历史报警统计
	 * @param oid         机构(或企业)Id
	 * @return
	 */
	public List<AlarmStatisticsTip> getAlarmCounts(String oid){
//		System.out.println("==================================");
		return hisalarmbiz.getAlarmCounts(oid);
	}
	
}
