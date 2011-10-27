package org.tbcc.flex;

import java.util.List;

import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

/**
 * 这个类是为了解决flex与spring集成所用到的东西，
 * 在这里直接访问spring容器，为了访问 业务层的东西
 * 这里是用了启停记录查询
 * @author Administrator
 *
 */
public class RemoteHisCar2 {
	private HisCarBiz hiscarBiz = null ;
	private ProjectBiz projectBiz = null ;
	private HisStartUpBiz startupBiz = null ;
	
	/**
	 * 获取spring容器里面的bean对象
	 */
	public RemoteHisCar2(){
		hiscarBiz = (HisCarBiz)MySpringFactory.getInstance().getBean("hiscarBiz");
		projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
		startupBiz = (HisStartUpBiz)MySpringFactory.getInstance().getBean("startUpBiz");
	}
	
	/**
	 * 根据项目Id，获取该项目的的详细信息
	 * @param proId
	 * @return
	 */
	public TbccPrjType getProById(String proId){
		return this.projectBiz.getByproIdProxy(proId);
	}
	
	/**
	 * 根据启停记录Id，获取启停记录
	 */
	
	public TbccBaseHisStartUp getStartUp(String proId,long id){
		
		//return this.startupBiz.getStartUp(proId, id);	//改善了获取起停记录的实现方式
		return this.startupBiz.getStartUpById(proId, id);
	}
	
	/**
	 * 获取移动车载的历史数据
	 */
	
	public List<TbccBaseHisCar> getHisCarByProperty(String proId,String startTime,String endTime,String interval,String value,String sid){
		//return this.hiscarBiz.getHisCarByProperty(proId, startTime, endTime, interval, value) ;
		return this.hiscarBiz.getHisCarBySidAndTime(proId, startTime, endTime, interval, Integer.parseInt(value),Long.parseLong(sid)) ;	
	}
	
 }
