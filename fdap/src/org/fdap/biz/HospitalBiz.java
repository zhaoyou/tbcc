package org.fdap.biz;

import java.util.List;
import org.fdap.entity.FdapHospital;

public interface HospitalBiz {
	public void add(FdapHospital obj);
	
	public void delete(FdapHospital deleteObj);
	
	public void update(FdapHospital updateObj);
	
	public List<FdapHospital> getAll();
	
	public FdapHospital get(Integer id);
}
