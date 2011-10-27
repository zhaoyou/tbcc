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
			System.out.println("���Ӷ���ʧ��: "+e.getMessage());
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
		 * OptStatus ��3 ����ʧ��
		 * OptFailReason	��������ֶ���չ����Ϣ
		 * 1��δ֪ʧ��ԭ��
		 * 2: δ����ԭ��
		 * ���״̬Ϊ3����ʾ����ʧ�ܡ���鿴����ʧ��״̬��Ϣ
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
			System.out.println("���¶�������ʧ�� �� "+e.getMessage());
			return 0 ;
		}
		
	}

}
