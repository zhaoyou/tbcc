package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccPrjType;

/**
 * �������Ŀ�����ӿ�
 * @author Administrator
 *
 */
public interface ProjectDao {
	/**
	 * ���ݷ�֧Id����ȡ������Ŀ����
	 * @param branchId		��֧��ʶid
	 * @return					���̼���
	 */
	public List<TbccPrjType> getByBranchId(Long branchId);
	
	/**
	 * ���ݹ��̱�ʶId����ȡ���̶���
	 * @param projectId		���̱�ʶId
	 * @return				���̶���
	 */
	public TbccPrjType get(String projectId	) ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ���е���⹤��
	 * @param branchId		��֧��ʶId
	 * @return				���е���⹤���б�
	 */
	public List<TbccPrjType> getRefProjList(Long branchId) ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ���е��ƶ����ع���
	 * @param branchId		��֧��ʶId
	 * @return				���е��ƶ����ع����б�
	 */
	public List<TbccPrjType> getCarProjList(Long branchId) ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ���еĳ��ع��̱�ż���
	 * @param branchId		��֧��ʶId
	 * @return				���еĳ��ع��̱�ʶId�б�
	 */
	public List<String>		getCarProjectIds(Long branchId) ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ���е�С���㹤��
	 * @param branchId		��֧��ʶId
	 * @return				���е�С���㹤���б�
	 */
	public List<TbccPrjType> getBoxProjList(Long branchId) ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ�÷�֧��Ӧ����⹤�̱�ż���
	 * @param branchId		��֧��ʶId
	 * @return
	 */
	public List<String> getRefProjectIds(Long branchId) ;
	
	/**
	 * ���ݷ�֧�ı�ʶId����ȡ�÷�֧�����乤���б�
	 * (������⹤��(��⹤���п��ܾ�����⹤��Ҳ�����乤��)�ʹ�������乤��)
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getCoolerProjList(Long branchId);
	
	/**
	 * ���ݷ�֧��ʶId����ȡ�÷�֧��Ӧ���乤�̱��(��⹤�̺ʹ�������乤��)����
	 * ���������������������Ļ�Ϲ������
	 * @param branchId		��֧��ʶId
	 * @return
	 */
	public List<String>	getCoolerProjectIds(Long branchId);
	
	/**
	 * ���ݷ�֧��ʶ����ȡ����ͼ�㹤���б�(����ʵ��)
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getHasImagesProjList( Long branchId) ;
	
	
	
	
}
