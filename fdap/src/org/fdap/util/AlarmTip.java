package org.fdap.util;

/**
 * 自定义报警提示的实体类
 * @author zhaoyou
 *
 */
public class AlarmTip {
	
		private Long  oid  ;
		
		private String orgName ;
	
		private String  msg  ;
		
		public AlarmTip(){	
		}
		
		public AlarmTip(Long oid, String msg) {
			super();
			this.oid = oid;
			this.msg = msg;
		}
		
		
		public Long getOid() {
			return oid;
		}
		
		public void setOid(Long oid) {
			this.oid = oid;
		}
		
		public String getMsg() {
			return msg;
		}
		
		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		
		

}
