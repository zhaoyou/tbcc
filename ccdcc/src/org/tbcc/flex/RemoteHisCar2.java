package org.tbcc.flex;

import java.util.List;

import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

/**
 * �������Ϊ�˽��flex��spring�������õ��Ķ�����
 * ������ֱ�ӷ���spring������Ϊ�˷��� ҵ���Ķ���
 * ������������ͣ��¼��ѯ
 * @author Administrator
 *
 */
public class RemoteHisCar2 {
	private HisCarBiz hiscarBiz = null ;
	private ProjectBiz projectBiz = null ;
	private HisStartUpBiz startupBiz = null ;
	
	/**
	 * ��ȡspring���������bean����
	 */
	public RemoteHisCar2(){
		hiscarBiz = (HisCarBiz)MySpringFactory.getInstance().getBean("hiscarBiz");
		projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
		startupBiz = (HisStartUpBiz)MySpringFactory.getInstance().getBean("startUpBiz");
	}
	
	/**
	 * ������ĿId����ȡ����Ŀ�ĵ���ϸ��Ϣ
	 * @param proId
	 * @return
	 */
	public TbccPrjType getProById(String proId){
		return this.projectBiz.getByproIdProxy(proId);
	}
	
	/**
	 * ������ͣ��¼Id����ȡ��ͣ��¼
	 */
	
	public TbccBaseHisStartUp getStartUp(String proId,long id){
		
		//return this.startupBiz.getStartUp(proId, id);	//�����˻�ȡ��ͣ��¼��ʵ�ַ�ʽ
		return this.startupBiz.getStartUpById(proId, id);
	}
	
	/**
	 * ��ȡ�ƶ����ص���ʷ����
	 */
	
	public List<TbccBaseHisCar> getHisCarByProperty(String proId,String startTime,String endTime,String interval,String value,String sid){
		//return this.hiscarBiz.getHisCarByProperty(proId, startTime, endTime, interval, value) ;
		return this.hiscarBiz.getHisCarBySidAndTime(proId, startTime, endTime, interval, Integer.parseInt(value),Long.parseLong(sid)) ;	
	}
	
 }
