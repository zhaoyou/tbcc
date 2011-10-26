package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.EngineerBiz;
import org.fdap.dao.EngineerDao;
import org.fdap.entity.Fdapuser;

public class EngineerBizImpl implements EngineerBiz {
	
	private EngineerDao engineerdao;
		
	public EngineerDao getEngineerdao() {
		return engineerdao;
	}

	public void setEngineerdao(EngineerDao engineerdao) {
		this.engineerdao = engineerdao;
	}

	public Fdapuser getEngineer() {
		List<Fdapuser> engineerlist=engineerdao.getEngineer();
		
		return (engineerlist!=null&&engineerlist.size()!=0)?engineerlist.get(0):null;
	}

	public boolean updateEngineer(Fdapuser engineer) {
		
		return engineerdao.upUser(engineer);
	}
	
}
