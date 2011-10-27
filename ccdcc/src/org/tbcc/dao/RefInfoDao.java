package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccRefInfo;

/**
 * ���������Ϣ�����ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface RefInfoDao {
	/**
	 * ���ݹ��̱�ʶId����ȡ�ù����µ���������б�
	 * @param projectId		���̱�ʶId
	 * @return
	 */
	public List<TbccRefInfo> getRefByProId(String projectId);
	
	/**
	 * ���ݹ��̱�ʶId�������豸Id���Լ�����Ż�ȡĳ�������Ϣ
	 * @param proId		���̱�ʶId
	 * @param netId		�����豸Id
	 * @param refId		�����
	 * @return
	 */
	public List<TbccRefInfo> getRef(String proId,String netId,String refId);
	
	/**
	 * ������������������
	 * @param id	���ı�ʶId
	 * @return
	 */
	public TbccRefInfo get(Long id);
}
