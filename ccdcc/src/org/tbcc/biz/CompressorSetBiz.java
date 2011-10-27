package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorSet;
import org.tbcc.entity.cool.TbccMultiCompressorRealData;
import org.tbcc.entity.cool.TbccSingleCompressorRealData;

/**
 * ѹ�������ҵ��ʵ�ֽӿ�
 * @author Administrator
 *
 */
public interface CompressorSetBiz {
	
	/**
	 * �������� 1 
	 */
	public static final Integer MULTICOMPRESSOR = 1 ;
	
	/**
	 * �������� 0 
	 */
	public static final Integer SINGLECOMPRESSOR = 0 ;
	
	/**
	 * ������Ŀ��ʶprojectId��ȡ���е�ѹ������
	 * @param projectId		��⹤�̱�ʶId
	 * @return
	 */
	public List<TbccCompressorSet> getByProjectId(String projectId);
	
	/**
	 * ���ݻ�����֧��ʶId����ȡ���л��鼯��
	 * @param projects	���̱�ʶ����
	 * @return
	 */
	public List<TbccCompressorSet> getByBranchId(Long branchId) ;
	
	/**
	 * ���ݻ���ı�ʶId����ȡ�û������Ϣ
	 * @param id		����ı�ʶId
	 * @return
	 */
	public TbccCompressorSet 	getById(Integer id) ;
	
	
	/**
	 * ���ݻ���ı�ʶId����ȡ���������ʵʱ����
	 * @param id		����ı�ʶId
	 * @return
	 */
	 public TbccMultiCompressorRealData getMultiRealById(Integer id) ;
	 
	 /**
	  * ���ݻ���ı�ʶId����ȡ���������ʵʱ����
	  * @param id		����ı�ʶId
	  * @return
	  */
	 public TbccSingleCompressorRealData getSingleRealById(Integer id);
	 
	 
}
