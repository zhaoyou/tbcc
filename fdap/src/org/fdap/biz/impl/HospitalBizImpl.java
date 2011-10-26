package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.HospitalBiz;
import org.fdap.dao.HospitalDao;
import org.fdap.entity.FdapHospital;

public class HospitalBizImpl implements HospitalBiz {

	private HospitalDao hospitalDao = null;

	public void setHospitalDao(HospitalDao hospitalDao) {
		this.hospitalDao = hospitalDao;
	}

	@Override
	public void add(FdapHospital obj) {
		if (obj != null) {
			hospitalDao.addHospital(obj);
		}
	}

	@Override
	public void delete(FdapHospital deleteObj) {
		if (deleteObj != null) {
			hospitalDao.deleteHospital(deleteObj);
		}
	}

	@Override
	public void update(FdapHospital updateObj) {
		if (updateObj != null) {
			hospitalDao.updateHospital(updateObj);
		}
	}

	@Override
	public List<FdapHospital> getAll() {
		return this.hospitalDao.getAll();
	}

	@Override
	public FdapHospital get(Integer id) {
		return this.hospitalDao.get(id);
	}

}
