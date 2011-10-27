package org.tbcc.entity;

import java.io.Serializable;

/**
 * 这是用来描述机构与子机构的关系
 * @author Administrator
 *
 */
public class TbccCrm implements Serializable {
		
		private Integer id  ;
		
		private String hid ;
		
		private String cid ;

		public TbccCrm(){
			
		}
		
	

		
		public boolean equals(Object obj) {
			
			if(obj==this)
				return true ;
			if(obj==null)
				return false ;
			if(!(obj instanceof TbccCrm))
				return false ;
			
			TbccCrm crm = (TbccCrm) obj ;
			if(this.getId().equals(crm.getId()))
				return true; 
			
			return false ;
	
		}

		
		public int hashCode() {
			return this.getId().hashCode() ;
		}




		public Integer getId() {
			return id;
		}




		public void setId(Integer id) {
			this.id = id;
		}




		public String getHid() {
			return hid;
		}




		public void setHid(String hid) {
			this.hid = hid;
		}




		public String getCid() {
			return cid;
		}




		public void setCid(String cid) {
			this.cid = cid;
		}
}
