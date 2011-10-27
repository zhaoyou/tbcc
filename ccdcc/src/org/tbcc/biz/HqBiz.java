package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccHqType;

/**
 * �ܲ���ҵ������ӿ�
 * @author Administrator
 *
 */
public interface HqBiz {
	
		/**
		 * ��ʾ�ܲ�Logo 0
		 */
		public final Integer LogoEnable = 0 ;
		
		/**
		 * ����ʾ�ܲ�Logo(����ʾĬ��Logo) 1
		 */
		public final Integer LogoDisable = 1 ;
	
		/**
		 * �����ܲ��ı�ʶId����ȡ�ܲ���Ϣ
		 * @param id		�ܲ���ʶId
		 * @return
		 */
		public TbccHqType	getById(Long id) ;
		
		/**
		 * �����ܲ���ʶId����ȡ���ܲ���һ�����ܲ���Ϣ
		 * @param parentId		�ܲ��ı�ʶId
		 * @return
		 */
		public List<TbccHqType> getByParentId(Long parentId) ;
		
		/**
		 * ��ȡ���е��ܲ���Ϣ
		 * @return
		 */
		public List<TbccHqType> getAllHqType() ;
		
		/**
		 * �����ܲ��ı�ʶId����ȡ�ܲ���֧��ϵ��tree
		 * @param id		�ܲ��ı�ʶId
		 * @return
		 */
		public String getHqBranchTree(Long id) ;
}
