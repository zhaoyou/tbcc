package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.ConfigInfoBiz;
import org.fdap.dao.ConfigInfoDao;
import org.fdap.entity.FdapConfigInfo;
import org.fdap.entity.Fdaporg;

public class ConfigInfoBizImpl implements ConfigInfoBiz {
	private ConfigInfoDao configinfodao;
	
	public ConfigInfoDao getConfiginfodao() {
		return configinfodao;
	}

	public void setConfiginfodao(ConfigInfoDao configinfodao) {
		this.configinfodao = configinfodao;
	}

	@Override
	public List<FdapConfigInfo> getConfigInfo(Long oid, Integer type) {
		return configinfodao.getConfigInfo(oid, type);
	}

	@Override
	public List<Fdaporg> getAllOrg() {
		List<Fdaporg> list = configinfodao.queryAllOrg();
		if(list==null||list.size()==0){
			return null;
		}
		return list;
	}
	
}
