package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccHqType;
import org.tbcc.entity.TbccPrjType;

/**
 * ���ǲ����ƶ����ص�ҵ��ӿ�
 * @author Administrator
 *
 */
public interface HisCarBiz {
	/**
	 * ������γ
	 */
	public static final int SOUTH = 0 ;
	/**
	 * ����γ
	 */
	public static final int NORTH = 1 ;
	/**
	 * ������
	 */
	public static final int EAST = 0 ;
	/**
	 * ��������
	 */
	public static final int WEST =1 ;
	
	/**
	 * �����ش���Ԥ��״̬
	 */
	public static final int PREALARM = 0 ;
	
	
	/**
	 * �����ƶ����ش��ڱ���״̬
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * �����ƶ����ش�������״̬
	 */
	public static final int NORMAL =2 ;
	
	
	public List<TbccBaseHisCar> getHisCarByProperty(String proId,String startTime,String endTime,String type,String value);
	
	public List<TbccPrjType> getCarPrjList(Long branchId);
	
	public List<TbccPrjType> getCarPrjListProxy(Long branchId);
	
	public Object[] getCalcValue(List<TbccBaseHisCar> carList) ;
	
	
	/**
	 * ���ݹ��̱�ʶ��ʱ�䷶Χ����ͣ��¼Id��ʱ������ȡ������ʷ����
	 * @param proId			���̱�ʾ
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param type			�������
	 * @param value			ʱ����ֵ
	 * @param sid			��ͣ��¼�ı�ʾ
	 * @return
	 */
	public List<TbccBaseHisCar> getHisCarBySidAndTime(String proId,String startTime,String endTime ,String type,Integer value,Long sid) ;
	
	/**
	 * ������Ŀ��ţ��Լ���ʷ��ͣ��¼��id����ȡ��ʷ����
	 * @param projectId			�ƶ�������Ŀ���
	 * @param parentId			��ͣ��¼�ı��
	 * @return					���س�����ʷ����
	 */
	public List<TbccBaseHisCar> getByParentId(String projectId,Long parentId) ;
	
	/**
	 * ������Ŀ��š��Ѿ���ͣ��¼�ı�ţ���ǰ�ļ�¼��ţ���ȡ��100�����ݡ���ʱ��û��ʹ��
	 * @param projectId			�ƶ�������Ŀ���
	 * @param parentId			��ͣ��¼�ı��
	 * @param id				��ǰ���صļ�¼���
	 * @return					��ʷ��ͣ��¼
	 */
	public List<TbccBaseHisCar> getByParentIdAndId(String projectId,Long parentId,Integer id);
}
