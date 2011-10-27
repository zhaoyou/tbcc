package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCcapDevType;

/**
 * �����豸ҵ��ӿ�
 * @author Administrator
 *
 */
public interface CCapDevBiz {
	
	/**
	 * ���ݹ��̱�ʶId��ȡ�����豸��¼
	 * @param projectId
	 * @return
	 */
	public List<TbccCcapDevType> getByProjectId(String projectId) ;
	
	/**
	 * ���ݶ����⹤�̻�ȡ��������豸
	 * @param str	eg: ('1001','1002')
	 * @return
	 */
	public List<TbccCcapDevType> getByProjects(String str) ;
	
}
