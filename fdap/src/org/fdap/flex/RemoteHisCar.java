package org.fdap.flex;

import java.util.List;

import org.fdap.biz.CarHisBiz;
import org.fdap.biz.StartUpBiz;
import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.FdapStartUp;
import org.fdap.entity.Fdapref;
import org.fdap.util.FdaprefData;
import org.fdap.util.SpringFactoryProxy;


/**
 * 这个类是为了解决flex与spring集成所用到的东西，
 * 在这里直接访问spring容器，为了访问 业务层的东西
 * 这里是用了启停记录查询
 * @author zhoukuanwei
 *
 */
public class RemoteHisCar {
	private CarHisBiz hiscarBiz = null ;

	private StartUpBiz startupBiz = null ;
	
	/**
	 * 获取spring容器里面的bean对象
	 */
	public RemoteHisCar(){
		hiscarBiz = (CarHisBiz)SpringFactoryProxy.getInstance().getBeanObject("carhisBiz");
		startupBiz = (StartUpBiz)SpringFactoryProxy.getInstance().getBeanObject("startupBiz");
	}
	
	/**
	 * 根据车载冷库的Id，获取车载冷库详细信息
	 * @param refId			车载冷库id
	 * @return
	 */
	public FdaprefData getRefById(String refId){
		Fdapref ref = this.hiscarBiz.getCarRefById(refId);
		
		FdaprefData refData = new FdaprefData();
		refData.setName(ref.getName());
		refData.setRefId(ref.getRefId());
		refData.setFloorNo(ref.getFloorNo());
		refData.setFloorType(ref.getFloorType());
		refData.setIsactive(ref.getIsactive());
		refData.setRemark(ref.getRemark());
		
//		System.out.println("来获取车载冷库信息refname="+refData.getName());
		return refData;
	}
	
	/**
	 * 根据启停记录Id，获取启停记录
	 */
	
	public FdapStartUp getStartUp(String oid,long sid){
		return this.startupBiz.getByOidAndSid(oid, sid);
	}
	
	/**
	 * 获取移动车载的历史数据
	 */
	
	public List<FdapCarHisData> getHisCar(String oid,String sid){
		return this.hiscarBiz.getCarHis(oid, sid);
	}
	
 }
