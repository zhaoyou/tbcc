package org.tbcc.entity.cool;

/**
 *  这是制冷系统实时数据的实体类
 * @author Administrator
 *
 */
public class TbccCcapSystemRealData implements java.io.Serializable {
		
	
		
	
		private Integer id ;
		private String 	projectId ;
		private Integer dynamoState ;
		private Integer dynamoAlarm ;
		private Integer sysoutageState ;
		private Integer sysoutageAlarm ;
		
		
		
		public TbccCcapSystemRealData(){
		}
		
		
		public TbccCcapSystemRealData(Integer id, String projectId,
				Integer dynamoState, Integer dynamoAlarm,
				Integer sysoutageState, Integer sysoutageAlarm) {
			super();
			this.id = id;
			this.projectId = projectId;
			this.dynamoState = dynamoState;
			this.dynamoAlarm = dynamoAlarm;
			this.sysoutageState = sysoutageState;
			this.sysoutageAlarm = sysoutageAlarm;
		}
		
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		public Integer getDynamoState() {
			return dynamoState;
		}
		public void setDynamoState(Integer dynamoState) {
			this.dynamoState = dynamoState;
		}
		public Integer getDynamoAlarm() {
			return dynamoAlarm;
		}
		public void setDynamoAlarm(Integer dynamoAlarm) {
			this.dynamoAlarm = dynamoAlarm;
		}
		public Integer getSysoutageState() {
			return sysoutageState;
		}
		public void setSysoutageState(Integer sysoutageState) {
			this.sysoutageState = sysoutageState;
		}
		public Integer getSysoutageAlarm() {
			return sysoutageAlarm;
		}
		public void setSysoutageAlarm(Integer sysoutageAlarm) {
			this.sysoutageAlarm = sysoutageAlarm;
		}
		
		
		
}
