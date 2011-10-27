package org.tbcc.dwr;

import org.tbcc.biz.ParamActionBiz;
import org.tbcc.biz.ParamCarVehicleBiz;
import org.tbcc.log.entity.TbccLog;
import org.tbcc.util.MySpringFactory;

/**
 * 车载参数配置，通过页面动态调用而设计的
 * @author zhaoyou
 *
 */
public class CarParamOperate {
	private ParamActionBiz paramActionBiz = null ;
	
	private ParamCarVehicleBiz	paramCarVehicleBiz = null ;
	
	public CarParamOperate(){
		paramActionBiz = (ParamActionBiz)MySpringFactory.getInstance().getBean("paramActionBiz") ;
		paramCarVehicleBiz = (ParamCarVehicleBiz)MySpringFactory.getInstance().getBean("paramCarVehicleBiz") ;
	}
	
	
	public Long insertOperate(String projectId,Byte funcType,Byte cmdType,Byte optStatus){
		return paramActionBiz.addOperate(projectId, funcType, cmdType, optStatus);
	}
	
	
	public Byte QueryOptStatus(Long id){
		return paramActionBiz.getOperateStatus(id);
	}
	
	public Integer	UpdateOptStatus(Long id ,Byte optStatus){
		return paramActionBiz.updateOperateStatus(id, optStatus);
	}
	
	public String QueryCarParam(Long parentId){
		return paramCarVehicleBiz.getCarVehicleByParentId(parentId);
	}
	
	public Integer UpdateCarParam(String projectId ,String source,TbccLog log){
		return paramCarVehicleBiz.updateCarVehicle(projectId, source,log) ;
	}
	
	public Integer addUpdateResultLog(TbccLog log){
		return paramCarVehicleBiz.addUpdateResultLog(log);
	}
	
	
	
	
	
}
