package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccCrm;

/**
 * ����һ���ͻ���ϵ��Ϊ��֮ǰ����ܲ��鿴��֧��������Ϣ�������õġ�
 * @author Administrator
 *
 */
public interface CrmDao {
	
	/**
	 * ���ݿͻ���branchId����ȡ�û����Ŀͻ���branchId
	 * @param hid		�ͻ�branchId
	 * @return			�ͻ���ϵʵ��
	 */
	List<TbccCrm> getByHid(String hid) ;
	
}
