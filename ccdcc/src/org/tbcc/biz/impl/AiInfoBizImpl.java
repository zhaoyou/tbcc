package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.AiInfoBiz;
import org.tbcc.dao.AiInfoDao;
import org.tbcc.entity.TbccAiInfo;

/**
 * ���ǲ���̽ͷ��ҵ��ʵ����
 * @author Administrator
 *
 */
public class AiInfoBizImpl implements AiInfoBiz {

	
	private AiInfoDao aiinfoDao = null ;
	
	public void setAiinfoDao(AiInfoDao aiinfoDao) {
		this.aiinfoDao = aiinfoDao;
	}

	
	public List<TbccAiInfo> getListByProId(String proId) {
		return this.aiinfoDao.getAiListByproId(proId);
	}
	
	
	public List<TbccAiInfo> getRefAiList(String proId, String netId,
			String refId) {
		return this.aiinfoDao.getRefAiList(proId, netId, refId);
	}


	public List<TbccAiInfo> getListByRid(Long rid) {
		
		//��֤����
		if(rid==null || rid.equals("")){
			return null ;
		}
		return this.aiinfoDao.getAiListByRid(rid);
	}

}
