package org.tbcc.entity;

import java.io.Serializable;

/**
 * �ܲ��û�ʵ����
 * @author Administrator
 *
 */
public class TbccHqUser extends TbccBaseUser implements Serializable {
	

		private TbccHqRoles	hqRoles = new TbccHqRoles();  ;
		
		
		public TbccHqRoles getHqRoles() {
			return hqRoles;
		}

		public void setHqRoles(TbccHqRoles hqRoles) {
			this.hqRoles = hqRoles;
		}

		public TbccHqUser(){		
		}
		
		public Integer getRoleId() {
			return this.hqRoles.getId().intValue() ;
		}
			
		public void setRoleId(Integer roleId) {
			this.hqRoles.setId(roleId.longValue());
		}
}
