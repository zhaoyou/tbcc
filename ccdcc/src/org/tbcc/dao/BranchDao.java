package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBranchType;

/**
 * ����һ�����������Ľӿ�
 * @author Administrator
 *
 */
public interface BranchDao {
	
	/**
	 * ���ݷ�֧��ʶId�����ظ÷�֧����
	 * @param branchId		��֧��ʶid
	 * @return
	 */
	public TbccBranchType get(Long branchId);
	
	/**
	 * ���ݷ�֧��ʶId���� ��ȡ��֧�����ļ���
	 * @param condition	eg: (12,13,13)
	 * @return
	 */
	public List<TbccBranchType> getByIds(String condition) ;
	
}
