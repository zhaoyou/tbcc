package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CompressorBiz;
import org.tbcc.dao.CompressorSetDao;
import org.tbcc.dao.CompressorDao;
import org.tbcc.entity.cool.TbccCompressor;
import org.tbcc.entity.cool.TbccCompressorRealData;
import org.tbcc.entity.cool.TbccCompressorSet;

public class CompressorBizImpl implements CompressorBiz {

	private CompressorDao compressordao = null ;

	private CompressorSetDao	compressorSetdao = null ;
	
	public void setCompressorSetdao(CompressorSetDao compressorSetdao) {
		this.compressorSetdao = compressorSetdao;
	}
	
	
	public void setCompressordao(CompressorDao compressordao) {
		this.compressordao = compressordao;
	}

	
	
	public List<TbccCompressorRealData> getBycsId(Integer csId) {
		
		TbccCompressorSet compressorSet = compressorSetdao.getById(csId);
		
		if(compressorSet.getTbccCompressors()==null || compressorSet.getTbccCompressors().size()==0)
				return null ;
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i <compressorSet.getTbccCompressors().size(); i++) {
			sb.append(((TbccCompressor)compressorSet.getTbccCompressors().toArray()[i]).getId());
			if(i!=compressorSet.getTbccCompressors().size()-1)
				sb.append(",");
		}
		sb.append(")") ;
		return compressordao.getByCondition(sb.toString()) ;
		
	}


	public List<TbccCompressorRealData> getByCids(String str) {
		
		if(str==null || str.equals(""))
			return null ;
		
		StringBuffer sb = new StringBuffer("(") ;
		sb.append(str+")") ;	
		return compressordao.getByCondition(sb.toString());
	}


	public List<Integer> getIdsByCsId(Integer csId) {
		if(csId==null || csId.equals(""))
			return null ;
		return compressordao.getIdsByCsId(csId);
	}
}
