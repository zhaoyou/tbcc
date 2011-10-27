package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccAiInfo;

/**
 *  ���ǲ���̽ͷ��ҵ��ӿ�
 * @author Administrator
 *
 */
public interface AiInfoBiz {
	
		/**
		 * �����¶�
		 */
		public final Integer T =  1;
		
		/**
		 *  ����ʪ��
		 */
		public final Integer RH = 2 ;
		
		/**
		 *  ����ѹ��
		 */
		public final Integer P = 3 ;
		
		/**
		 * ������⹤�̻�ȡ�ù����µ�����̽ͷ��Ϣ
		 * @param proId			���̱�ʶId
		 * @return
		 */
		public List<TbccAiInfo> getListByProId(String proId);
		
		/**
		 * ��ȡ���������µ�̽ͷ
		 * @param proId		��⹤�̱�ʶId
		 * @param netId		�����豸Id
		 * @param refId		�����
		 * @return
		 */
		public List<TbccAiInfo> getRefAiList(String proId,String netId,String refId);
		
		/**
		 * ͨ������Ż�ȡ����������̽ͷ
		 * @param rid		�����
		 * @return
		 */
		public List<TbccAiInfo> getListByRid(Long rid);
}
