package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccHqType;

/**
 * �ܲ����������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface HqDao {
	
	/**
	 * ���ݱ�ʶ������ȡ�ܲ�����
	 * @param id		�ܲ���ʶId
	 * @return
	 */
	public TbccHqType	get(Long id) ;
	
	
	
	/**
	 * ���ݱ�ʶId����ȡ���ܲ��¼��������ܲ���Ϣ
	 * @param parentId		�ϼ��ܲ���ʶId
	 * @return
	 */
	public List<TbccHqType> getByParentId(Long parentId) ;
	
	/**
	 * ��ȡ���е��ܲ���Ϣ
	 * @return
	 */
	public List<TbccHqType> getAll();
	
	/**
	 * 	�����ܲ��û��ı�ʶId����ȡ����ܲ�����������з�֧
	 * @param hqId		�ܲ���ʶId
	 * @return
	 */
	public List<TbccBranchType>	getBranchByHqId(Long hqId) ;
	
}
