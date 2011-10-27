package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealCar;

/**
 * ����һ�������ƶ�����ʵʱ���ݵ����ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface RealCarDao {
	
	/**
	 * ���ݲ�ͬ��projectid����ȡ����ʵʱ����
	 * @param condition	��ͬ��projectId eg: ��3000,3001,3002��
	 * @return	�������������г�վʵʱ���ݼ���
	 */
	public List<TbccBaseRealCar> getRealCars(String condition) ;
	
	/**
	 * ������Ŀ���̱�ţ���ȡ����ʵʱ����
	 * @param projectId		���̱��
	 * @return				����һ������ʵʱ���ݵļ���
	 */
	public List<TbccBaseRealCar>	getRealCar(String projectId) ;
	
}
