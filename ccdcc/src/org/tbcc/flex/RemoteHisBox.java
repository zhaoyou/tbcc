package org.tbcc.flex;

import java.util.List;

import org.tbcc.biz.HisBoxBiz;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

/**
 * �����Ϊ��flex ���� spring�����е�bean���� hisboxBizs
 */
public class RemoteHisBox {
	
	private HisBoxBiz hisboxBiz = null ;

	
	public RemoteHisBox(){
		hisboxBiz = (HisBoxBiz)MySpringFactory.getInstance().getBean("hisboxBiz");
	}
	
	/**
	 * ��ȡ�÷�֧�µ�����С������Ŀ
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getBoxPrjList(Long branchId){
		return hisboxBiz.getBoxPrjProxy(branchId) ;
	}
	
	/**
	 *  ����������ȡС�������ʷ����
	 * @param proId
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	public List<TbccBaseHisBox> getHisBoxData(String proId,String startTime,String endTime,String type,String value){
		return hisboxBiz.getByProperty(proId, startTime, endTime, type, value) ;
	}
	
}
