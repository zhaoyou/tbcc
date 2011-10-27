package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCcapDevType;

/**
 * �����豸���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface CcapDevDao {
	/**
	 * ����projectId ����ȡ�豸����
	 * @param projectId	����Id
	 * @return
	 */
	List<TbccCcapDevType> getByProjectId(String projectId) ;
	
	/**
	 * ������⹤�̼��ϣ���ȡ��Ӧ�������豸����(�������⹤�̵ķ���)
	 * @param str	eg: ('1002','1004')
	 * @return
	 */
	List<TbccCcapDevType> getByCondition(String str) ;
}
