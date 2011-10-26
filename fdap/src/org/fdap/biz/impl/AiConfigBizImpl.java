package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.AiConfigBiz;
import org.fdap.dao.AiConfigDao;
import org.fdap.entity.Fdapaiinfo;


/**
 * 探头配置业务实现类
 * @author zhaoyou
 *
 */
public class AiConfigBizImpl implements AiConfigBiz {
	private AiConfigDao aiconfigdao;
	
	public AiConfigDao getAiconfigdao() {
		return aiconfigdao;
	}

	public void setAiconfigdao(AiConfigDao aiconfigdao) {
		this.aiconfigdao = aiconfigdao;
	}

	@Override
	public List<Fdapaiinfo> getByref(Long refId) {
		if(refId==null||refId.equals("")){
			return null;
		}
		return aiconfigdao.queryByRefId(refId);
	}

	public Integer getMaxReid(Long oid ,Integer preid) {
		// TODO Auto-generated method stub
		return aiconfigdao.queryMaxReid(oid,preid);
	}

	@Override
	public boolean addAi(Fdapaiinfo aiinfo) {
		// TODO Auto-generated method stub
		return aiconfigdao.insertAi(aiinfo);
	}

	@Override
	public boolean DelAi(String[] delAiguid) {
		try{
			for(int i=0;i<delAiguid.length;i++){
				aiconfigdao.deleteAi(delAiguid[i]);
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fdapaiinfo getByAiguid(String aiGuid) {
		if(aiGuid==null||aiGuid.equals("")){
			return null;
		}
		return aiconfigdao.queryByAiguid(aiGuid);
	}

	@Override
	public boolean updateAi(Fdapaiinfo aiinfo) {
		try{
			/*if(aiinfo.getSelftype()==0){
				aiconfigdao.updateTempAi(aiinfo);
			}else{
				aiconfigdao.updateHumAi(aiinfo);
			}*/
			aiconfigdao.updateAi(aiinfo);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Fdapaiinfo> getAiByOid(Long oid) {
		if(oid==0||oid==null){
			return null;
		}
		return aiconfigdao.queryAiByOid(oid);
	}
	
}
