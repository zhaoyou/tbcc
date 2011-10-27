package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CrmBiz;
import org.tbcc.dao.CrmDao;
import org.tbcc.entity.TbccCrm;

/**
 * �ͻ���ϵҵ��ʵ����
 * @author Administrator
 *
 */
public class CrmBizImpl implements CrmBiz {

	private CrmDao crmDao = null ;
	
	/**
	 * ��spring ע��
	 * @param crmDao
	 */
	
	public void setCrmDao(CrmDao crmDao) {
		this.crmDao = crmDao;
	}


	/**
	 * ���ݿͻ��Ļ���Id ��ȡ�ͻ���ϵʵ��
	 */
	public List<TbccCrm> getByHid(String hid) {
		return  this.crmDao.getByHid(hid) ;
	}

}
