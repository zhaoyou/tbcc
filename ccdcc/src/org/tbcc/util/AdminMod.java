package org.tbcc.util;

import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccUser;

public class AdminMod {
	private TbccUser user;
	private TbccBranchType branchType;
	public TbccUser getUser() {
		return user;
	}
	public void setUser(TbccUser user) {
		this.user = user;
	}
	public TbccBranchType getBranchType() {
		return branchType;
	}
	public void setBranchType(TbccBranchType branchType) {
		this.branchType = branchType;
	}
}
