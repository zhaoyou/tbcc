package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CondenserBiz;
import org.tbcc.dao.CompressorSetDao;
import org.tbcc.dao.CondenserDao;
import org.tbcc.entity.cool.TbccCompressor;
import org.tbcc.entity.cool.TbccCompressorSet;
import org.tbcc.entity.cool.TbccCondenser;
import org.tbcc.entity.cool.TbccCondenserRealData;

/**
 *  冷凝器的业务无实现类
 * @author Administrator
 *
 */
public class CondenserBizImpl implements CondenserBiz {

	private CondenserDao condenserDao = null ;
	
	private CompressorSetDao compressorsetDao = null ;
	
	
	public void setCondenserDao(CondenserDao condenserDao) {
		this.condenserDao = condenserDao;
	}

	public void setCompressorsetDao(CompressorSetDao compressorsetDao) {
		this.compressorsetDao = compressorsetDao;
	}

	
	
	public List<TbccCondenserRealData> getBycsId(Integer csId) {
		TbccCompressorSet compressorSet = compressorsetDao.getById(csId);
		
		if(compressorSet.getTbccCondensers()==null || compressorSet.getTbccCondensers().size()==0)
				return null ;
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i <compressorSet.getTbccCondensers().size(); i++) {
			sb.append(((TbccCondenser)compressorSet.getTbccCondensers().toArray()[i]).getId());
			if(i!=compressorSet.getTbccCondensers().size()-1)
				sb.append(",");
		}
		sb.append(")") ;
		return condenserDao.getByCondition(sb.toString()) ;
	}

	public List<TbccCondenserRealData> getByCids(String str) {
		if(str==null || str.equals(""))
			return null ;
		StringBuffer sb = new StringBuffer("(") ;
		sb.append(str+")") ;
		return condenserDao.getByCondition(sb.toString());
	}

	
	public List<Integer> getIdsByCsid(Integer csId) {
		if(csId==null || csId.equals(""))
			return null ;
		return condenserDao.getIdsByCsId(csId);
	}

}
