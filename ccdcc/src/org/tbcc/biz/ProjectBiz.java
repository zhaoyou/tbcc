package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccPrjType;

/**
 * ������Ŀ����ҵ��ӿ�
 * @author Administrator
 *
 */
public interface ProjectBiz {
	/**
	 * �������ĿΪ���
	 */
	public static final int REF = 1 ;
	/**
	 * �������ĿΪ�ƶ�����
	 */
	public static final int CAR = 2 ;
	
	/**
	 * �������ĿΪС����
	 */
	public static final int BOX = 3 ;
	
	/**
	 * �������ĿΪ8DI������������Ŀ
	 */
	public static final int COOLER =  4 ;
	
	
	/**
	 * ������ĿΪ8DI��������Ļ��
	 */
	
	public static final int REF_COOLER = 5 ;
	
	/**
	 * �������ĿΪ24DI�Ķ���������Ŀ
	 */
	public static final int COOLER_BASE = 6 ;
	
	/**
	 * �������ĿΪ24DI�����������Ļ��
	 */
	public static final int REF_COOLER_BASE = 7 ;
	
	/**
	 * ���ݷ�֧�����ı�ʶid����ȡ�÷�֧�µ����й���
	 * @param branchId		��֧��ʶ
	 * @return				���̼���
	 */
	public List<TbccPrjType> getByBranchId(Long branchId) ;
	
	/**
	 *  ���ݹ��̱�ʶId����ȡ���̶���
	 * @param projectId		���̱�ʶId
	 * @return				���̶���
	 */
	public TbccPrjType getByProId(String projectId);
	
	/**
	 * ���ݹ��̱�ʶId����ȡ���̶���Ϊ�˽�����ӣ��ӳټ��ص�����(flex����ͼ)��
	 * @param projectId	���̱�ʶID
	 * @return			���̶���
	 */
	public TbccPrjType getByproIdProxy(String projectId);
	
	/**
	 * ���ݻ�����֧��ʶ����ȡ�û����µ�������⹤��
	 * @param branchId		������֧��ʶ
	 * @return				��⹤�̼���
	 */
	public List<TbccPrjType> getRefProjListBybId(Long branchId) ;
	
	/**
	 * ���ݷ�֧�ı�ʶId����ȡ��֧�µ����г��ع���
	 * @param branchId		��֧��ʶId
	 * @return				���ع��̼���
	 */
	public List<TbccPrjType> getCarProjListBybId(Long branchId) ;
	
	/**
	 * ���ݷ�֧��ʶId����ȡ�÷�֧�µ����г��ع��̱���б�
	 * @param branchId		��֧��ʶId
	 * @return
	 */
	public List<String> getCarProjectIds(Long branchId) ;
	
	/**
	 * ���ݷ�֧�ı�ʶId����ȡ��֧�µ�����С���㹤��
	 * @param branchId		��֧��ʶId
	 * @return				С���㹤�̼���
	 */
	public List<TbccPrjType> getBoxProjListBybId(Long branchId) ;
	
	
	/**
	 *  ���ݻ�����֧��ʶ����ȡ�û����µ�������⹤�̱�ʶ,Ϊ�˽��flex���ã��ӳټ�������
	 * @param branchId		������֧��ʶ
	 * @return				��⹤�̼���
	 */
	public List<TbccPrjType> getRefProjListProxyByBId(Long branchId);
	
	/**
	 * ���ݷ�֧������ʶId����ȡ���е���⹤�̱�ʶId
	 * @param branchId		��֧��ʶId
	 * @return
	 */
	public List<String> getRefProjects(Long branchId);
	
	/**
	 * ���ݻ����ı�ʶId����ȡ�û����µ����乤��(������������乤�̺���⹤��)
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getCoolerProjListBybid(Long branchId) ;
	
	/**
	 * ���ݷ�֧������ʶId����ȡ���е����乤�̼���⹤�̵ı�ʶId
	 * @param branchId	��֧��ʶid
	 * @return
	 */
	public List<String>	getCoolerProjects(Long  branchId);
	
	
	public List<TbccPrjType> getHasImagesProjListBybId(Long branchId) ;
	
	

}
