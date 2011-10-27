package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.AirCoolerBiz;
import org.tbcc.dao.CompressorSetDao;
import org.tbcc.dao.AirCoolerDao;
import org.tbcc.entity.cool.TbccAirCooler;
import org.tbcc.entity.cool.TbccAirCoolerRealData;
import org.tbcc.entity.cool.TbccCompressorSet;


public class AirCoolerBizImpl implements AirCoolerBiz {

	private CompressorSetDao	compressorsetdao = null ;
	
	private AirCoolerDao	aircoolerdao = null ;
	
	public void setCompressorsetdao(CompressorSetDao compressorsetdao) {
		this.compressorsetdao = compressorsetdao;
	}

	public void setAircoolerdao(AirCoolerDao aircoolerdao) {
		this.aircoolerdao = aircoolerdao;
	}

	
	public List<TbccAirCoolerRealData> getBycsId(Integer csId) {	
		
		
		TbccCompressorSet compressorSet = compressorsetdao.getById(csId);
		
		if(compressorSet.getTbccAirCoolers()==null || compressorSet.getTbccAirCoolers().size()==0)
				return null ;
		
		StringBuffer sb = new StringBuffer("(");
		
		for (int i = 0; i <compressorSet.getTbccAirCoolers().size(); i++) {
			sb.append(((TbccAirCooler)compressorSet.getTbccAirCoolers().toArray()[i]).getId());
			if(i!=compressorSet.getTbccAirCoolers().size()-1)
				sb.append(",");
		}
		sb.append(")") ;
		return aircoolerdao.getByCondition(sb.toString()) ;
	}

	public List<TbccAirCoolerRealData> getByAids(String ids) {
		if(ids==null || ids.equals(""))
			return null ;
		StringBuffer sb =new StringBuffer("(");
		sb.append(ids+")") ;
		return aircoolerdao.getByCondition(sb.toString());
	}

	
	public List<Integer> getIdsBycsId(Integer csId) {
		if(csId==null || csId.equals(""))
			return null ;
		return aircoolerdao.getIdsByCsId(csId);		
	}

}
