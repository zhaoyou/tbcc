package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccAiInfo;

/**
 * ���ǲ���̽ͷ�����ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface AiInfoDao {
	
	/**
	 * ������⹤����ĿId����ȡ�������Ŀ������̽ͷ
	 * @param proId		��⹤�̱�ʶ
	 * @return
	 */
	public List<TbccAiInfo> getAiListByproId(String proId);
	
	/**
	 * ���������ĿId������netId������ʶId��ȡĳһ����������̽ͷ 
	 * @param proId			��⹤�̱�ʶId
	 * @param netId			�����豸Id
	 * @param refId			��⹤�̱�ʶId
	 * @return
	 */
	public List<TbccAiInfo> getRefAiList(String proId,String netId,String refId);
	
	/**
	 * ͨ������ʶid����ȡ����������̽ͷ
	 * @param refId		����ʶId
	 * @return	
	 */
	public List<TbccAiInfo> getAiListByRid(Long rid);
}
