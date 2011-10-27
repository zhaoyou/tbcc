package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CoolerSystemBiz;
import org.tbcc.dao.CoolerSystemDao;
import org.tbcc.entity.cool.TbccCcapSystemRealData;

/**
 * 制冷系统业务实现类
 * @author Administrator
 *
 */
public class CoolerSystemBizImpl implements CoolerSystemBiz {

	private CoolerSystemDao coolerSystemDao  = null ;
	
	public void setCoolerSystemDao(CoolerSystemDao coolerSystemDao) {
		this.coolerSystemDao = coolerSystemDao;
	}

	public TbccCcapSystemRealData getByProjectId(String[] str ) {
		
		if(str==null || str.length==0)
			return null ;
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		
		for (int i = 0; i < str.length; i++) {
			sb.append("'"+str[i]+"'");
			if(i!=str.length-1)
				sb.append(",");
		}
		
		sb.append(")");
		
		List<TbccCcapSystemRealData> list = coolerSystemDao.getByProjectIds(sb.toString());
		
		if(list==null || list.size()==0)
			return null ;

		return list.get(0);
	}

}
