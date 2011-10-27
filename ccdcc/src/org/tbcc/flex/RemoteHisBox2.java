package org.tbcc.flex;

import java.util.List;

import org.tbcc.biz.HisBoxBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

import sun.nio.cs.HistoricallyNamedCharset;

/**
 * �����Ϊ�˷��� spring ������ҵ�����,��������ͣ��¼�Ĳ�ѯ����
 * @author Administrator
 *
 */
public class RemoteHisBox2 {
		
	private ProjectBiz projectBiz = null ;
	
	private HisStartUpBiz hisstartBiz = null ;
	
	private HisBoxBiz hisboxBiz = null ;
	
	public RemoteHisBox2(){
		projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
		hisstartBiz = (HisStartUpBiz)MySpringFactory.getInstance().getBean("startUpBiz");
		hisboxBiz = (HisBoxBiz)MySpringFactory.getInstance().getBean("hisboxBiz");
	}
	
	/**
	 * ������Ŀ����Id����ȡ��Ŀ��Ϣ
	 * @param proId
	 * @return
	 */
	public TbccPrjType getProjectById(String proId){
		return this.projectBiz.getByproIdProxy(proId);
	}
	
	/**
	 * ������ͣ��¼��Id ����ĿId����ȡ������ͣ��¼����Ϣ
	 */
	
	public TbccBaseHisStartUp getStartById(String proId,String sid){
		return this.hisstartBiz.getStartUp(proId, Long.parseLong(sid));
	}
	
	/**
	 * ����������ȡС�������ʷ����
	 */
	public List<TbccBaseHisBox> getHisBoxData(String proId,String startTime,String endTime,String interval,String value){
		return this.hisboxBiz.getByProperty(proId, startTime, endTime, interval, value);
	}
	
}
