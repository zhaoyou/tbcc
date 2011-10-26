package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.fdap.biz.RefConfigBiz;
import org.fdap.biz.StoreConfigBiz;
import org.fdap.dao.AiConfigDao;
import org.fdap.dao.RefConfigDao;
import org.fdap.dao.StoreConfigDao;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdapstoretype;

/**
 * 冷库配置业务 
 * @author zhaoyou
 *
 */
public class RefConfigBizImpl implements RefConfigBiz {

	private RefConfigDao 	refConfigDao = null ;
	
	private AiConfigDao 		aiConfigDao = null ;
	
	private StoreConfigDao	storeConfigDao = null;

	public AiConfigDao getAiConfigDao() {
		return aiConfigDao;
	}

	public void setAiConfigDao(AiConfigDao aiConfigDao) {
		this.aiConfigDao = aiConfigDao;
	}

	public RefConfigDao getRefConfigDao() {
		return refConfigDao;
	}

	public void setRefConfigDao(RefConfigDao refConfigDao) {
		this.refConfigDao = refConfigDao;
	}

	public StoreConfigDao getStoreConfigDao() {
		return storeConfigDao;
	}

	public void setStoreConfigDao(StoreConfigDao storeConfigDao) {
		this.storeConfigDao = storeConfigDao;
	}

	@Override
	public boolean addRef(Fdapref ref) {
		//验证参数
		if(ref==null ){
			return false ;
		}
		try {
			this.refConfigDao.insertRef(ref);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}

	@Override
	public boolean delRef(String[] refIds) {
		//验证参数
		if(refIds==null || refIds.length==0){
			return false ;
		}
		List<Long> list = new ArrayList<Long>();
		try {
			for (String string : refIds) {
				list.add(new Long(string));
				//删除探头信息
				this.getAiConfigDao().deleteAiByRefId(new Long(string));
			}
			
			//删除冷库
			this.getRefConfigDao().deleteRefByIds(list);
		} catch (Exception e) {
			return false ; 
		}
		return true;
	}

	@Override
	public Fdapref getById(Long refId) {
		//验证参数
		if(refId==null || refId.equals("")){
			return null ;
		}
		return this.getRefConfigDao().queryByRefId(refId);
	}

	@Override
	public List<Fdapref> getByProjectId(Long projectId) {
		//验证参数
		if(projectId==null || projectId.equals("")){
			return null ;
		}
		return this.getRefConfigDao().queryByProjectId(projectId);
	}

	@Override
	public boolean updateRef(Fdapref ref) {
		// 验证参数
		if(ref==null){
			return false ;
		}
		try {
				this.getRefConfigDao().updateRef(ref);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}

	@Override
	public boolean getExistsRef(String name, String projectid) {
		if(name==null||name==""||projectid==null||projectid.equals("")){
			return true;
		}
		
		Long exists = refConfigDao.queryExistsRef(name, projectid);
		if(exists==null||exists>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateRefAi(Long refId, Long storeid) {
		Fdapstoretype store = this.getStoreConfigDao().queryById(storeid.intValue());
		
		List<Fdapaiinfo> ailist = null;
		try {
			ailist = aiConfigDao.queryByRefId(refId);
			if(ailist!=null&&ailist.size()!=0){
				for(Fdapaiinfo ai : ailist){
					if(ai.getSelftype()==0){
						ai.setHighdelay(store.getTemphighdelay());
						ai.setHighlimit(store.getTemphighlimit());
						ai.setLowerdelay(store.getTemplowerdelay());
						ai.setLowerlimit(store.getTemplowerlimit());
						//暂时没用到
						/*ai.setMaxhighdelay(store.getTempmaxhighdelay());
						ai.setMaxhighlimit(store.getTempmaxhighlimit());
						ai.setMinlowerdelay(store.getTempminlowerdelay());
						ai.setMinlowerlimit(store.getTempminlowerlimit());*/
						
					}else{
						ai.setHighdelay(store.getHumhighdelay());
						ai.setHighlimit(store.getHumhighlimit());
						ai.setLowerdelay(store.getHumlowerdelay());
						ai.setLowerlimit(store.getHumlowerlimit());
					}
					aiConfigDao.updateAi(ai);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
