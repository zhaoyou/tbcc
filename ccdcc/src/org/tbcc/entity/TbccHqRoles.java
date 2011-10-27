package org.tbcc.entity;

import java.io.Serializable;

/**
 * 总部角色实体类
 * @author Administrator
 *
 */
public class TbccHqRoles implements Serializable {
	
		private Long id ;
		private Long	hqId ;
		private String 	name ;
		
		public TbccHqRoles(){
			super() ;
		}
		
		public TbccHqRoles(Long id, Long hqId, String name) {
			super();
			this.id = id;
			this.hqId = hqId;
			this.name = name;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getHqId() {
			return hqId;
		}
		public void setHqId(Long hqId) {
			this.hqId = hqId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
