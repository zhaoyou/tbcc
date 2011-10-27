package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBranchType;

/**
 * ���Ƿ�֧����ҵ��ӿ�
 * @author Administrator
 *
 */
public interface BranchBiz {
	
	/**
	 * ��ʾ��֧��Logo 0
	 */
	public final Integer LogoEnable = 0 ;
	
	/**
	 * ����ʾ��֧��Logo(��ʾĬ�ϵ�Logo) 1 
	 */
	public final Integer LogoDisable = 1 ;
	
	
	/**
	 * ����ϵͳ
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * ����ϵͳ
	 */
	public static final int COLD = 2 ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ��֧����
	 * @param branchId		��֧��ʶId
	 * @return				��֧���� 
	 */
	public TbccBranchType getById(Long branchId);             //����branchId��ȡ����
	
	/**
	 * ���ݷ�֧�б��ϣ���ȡ��֧���󼯺� ���ǿ��Ǽ���ͻ���ϵʱ���ӵĶ�����
	 * @param ids		��֧�б��ʶ����
	 * @return
	 */
	public List<TbccBranchType> getByIds(List<String> ids) ;

}
