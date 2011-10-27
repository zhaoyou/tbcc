package org.tbcc.biz.impl;

import org.tbcc.biz.ParamActionBiz;
import org.tbcc.dao.ParamActionDao;
import org.tbcc.entity.config.TbccParamAction;

public class ParamActionBizImpl implements ParamActionBiz {

	private ParamActionDao	paramActionDao = null ;
	
	public void setParamActionDao(ParamActionDao paramActionDao) {
		this.paramActionDao = paramActionDao;
	}

	
	public Long addOperate(String projectId, Byte funcType, Byte cmdType,
			Byte optStatus) {
		if(projectId==null || projectId.equals("") || funcType==null || funcType.equals("")||
				cmdType==null || cmdType.equals("") || optStatus==null || optStatus.equals("")){
			return new Long(0) ;
		}
		
		try {	
			return paramActionDao.addAction(projectId, funcType, cmdType, optStatus) ;
		} catch (Exception e) {
			System.out.println("增加动作失败: "+e.getMessage());
			return	new Long(0) ;
		}
		
	}

	public Byte getOperateStatus(Long id) {
		if(id==null || id.equals(""))
			return	0 ;
		TbccParamAction paramobj =  paramActionDao.getAction(id);
		
		if(paramobj==null)
			return 0 ;
		
		/**
		 * OptStatus ：3 代表失败
		 * OptFailReason	代表错误字段扩展误信息
		 * 1：未知失败原因
		 * 2: 未连接原因
		 * 如果状态为3，表示操作失败。则查看操作失败状态信息
		 */
		if(paramobj.getOptStatus().toString().equals("3")){
			return (byte)(paramobj.getOptFailReason()+12) ;
		}
		return paramobj.getOptStatus() ;
	}

	public Integer updateOperateStatus(Long id, Byte optStatus) {
		if(id==null || id.equals("") || optStatus==null || optStatus.equals(""))
				return 0 ;
		try {
			return paramActionDao.updateActionStatus(id, optStatus);
		} catch (Exception e) {
			System.out.println("更新动作参数失败 ： "+e.getMessage());
			return 0 ;
		}
		
	}

}
