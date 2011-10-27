package org.tbcc.flex;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

/**
 * �������Ϊ�˽��flex��spring�������õ��Ķ�����
 * ������ֱ�ӷ���spring������Ϊ�˷��� ҵ���Ķ���
 * @author Administrator
 *
 */
public class RemoteHisCar {
	
	
	private HisCarBiz hiscarBiz = null ;
	
	public RemoteHisCar(){	
		 hiscarBiz = (HisCarBiz)MySpringFactory.getInstance().getBean("hiscarBiz");
	}
	
	/**
	 * ��ȡ�÷�֧�µ����еĳ�����Ŀ
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getCarPrjList(Long branchId){
		return hiscarBiz.getCarPrjListProxy(branchId);
	}
	
	/**
	 * ����������ȡ���ص���ʷ����
	 */
	
	public List<TbccBaseHisCar> getHisCarData(String proId,String startTime,String endTime,String type,String value){
		return hiscarBiz.getHisCarByProperty(proId, startTime, endTime, type, value) ;
	}
	
	
}
