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
 * 这个是为了访问 spring 容器的业务对象,增加了启停记录的查询问题
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
	 * 根据项目工程Id，获取项目信息
	 * @param proId
	 * @return
	 */
	public TbccPrjType getProjectById(String proId){
		return this.projectBiz.getByproIdProxy(proId);
	}
	
	/**
	 * 根据启停记录的Id 和项目Id，获取整个启停记录的信息
	 */
	
	public TbccBaseHisStartUp getStartById(String proId,String sid){
		return this.hisstartBiz.getStartUp(proId, Long.parseLong(sid));
	}
	
	/**
	 * 根据条件获取小批零的历史数据
	 */
	public List<TbccBaseHisBox> getHisBoxData(String proId,String startTime,String endTime,String interval,String value){
		return this.hisboxBiz.getByProperty(proId, startTime, endTime, interval, value);
	}
	
}
