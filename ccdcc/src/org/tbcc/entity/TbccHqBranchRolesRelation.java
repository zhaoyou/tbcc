package org.tbcc.entity;

import java.io.Serializable;

/**
 * 总部角色与分支角色的关联实体
 * @author Administrator
 *
 */
public class TbccHqBranchRolesRelation implements Serializable {
		
			private Long id ;
			private Integer	branchRoleId ;
			private Long hqRoleId ;
			
			
			public TbccHqBranchRolesRelation(){}
			
			public TbccHqBranchRolesRelation(Long id, Integer branchRoleId,
					Long hqRoleId) {
				super();
				this.id = id;
				this.branchRoleId = branchRoleId;
				this.hqRoleId = hqRoleId;
			}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public Integer getBranchRoleId() {
				return branchRoleId;
			}
			public void setBranchRoleId(Integer branchRoleId) {
				this.branchRoleId = branchRoleId;
			}
			public Long getHqRoleId() {
				return hqRoleId;
			}
			public void setHqRoleId(Long hqRoleId) {
				this.hqRoleId = hqRoleId;
			}
			
}
