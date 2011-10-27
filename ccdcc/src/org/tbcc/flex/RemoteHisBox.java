package org.tbcc.flex;

import java.util.List;

import org.tbcc.biz.HisBoxBiz;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.MySpringFactory;

/**
 * 这个是为了flex 访问 spring容器中的bean对象 hisboxBizs
 */
public class RemoteHisBox {
	
	private HisBoxBiz hisboxBiz = null ;

	
	public RemoteHisBox(){
		hisboxBiz = (HisBoxBiz)MySpringFactory.getInstance().getBean("hisboxBiz");
	}
	
	/**
	 * 获取该分支下的所有小批零项目
	 * @param branchId
	 * @return
	 */
	public List<TbccPrjType> getBoxPrjList(Long branchId){
		return hisboxBiz.getBoxPrjProxy(branchId) ;
	}
	
	/**
	 *  根据条件获取小批零的历史数据
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
