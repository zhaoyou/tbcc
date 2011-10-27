package org.tbcc.flex;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

/**
 * 这个类是为了解决flex与spring集成所用到的东西，
 * 在这里直接访问spring容器，为了访问 业务层的东西
 * @author Administrator
 *
 */
public class RemoteHisCar {
	
	
	private HisCarBiz hiscarBiz = null ;
	
	public RemoteHisCar(){	
		 hiscarBiz = (HisCarBiz)MySpringFactory.getInstance().getBean("hiscarBiz");
	}
	
	/**
	 * 获取该分支下的所有的车载项目
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getCarPrjList(Long branchId){
		return hiscarBiz.getCarPrjListProxy(branchId);
	}
	
	/**
	 * 根据条件获取车载的历史数据
	 */
	
	public List<TbccBaseHisCar> getHisCarData(String proId,String startTime,String endTime,String type,String value){
		return hiscarBiz.getHisCarByProperty(proId, startTime, endTime, type, value) ;
	}
	
	
}
