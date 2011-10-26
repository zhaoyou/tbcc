package org.fdap.dao;

import java.util.List;
import org.fdap.entity.FdapHospital;

public interface HospitalDao {
	
	public void addHospital(FdapHospital obj);
	
	public List<FdapHospital> getAll();
	
	public void updateHospital(FdapHospital updateObj);
	
	public void deleteHospital(FdapHospital deleteObj);
	
	public FdapHospital get(Integer id);
}
