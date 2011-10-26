package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapauthcode;
import org.fdap.entity.Fdaporg;

/**
 * 机构配置数据接口
 * @author zhaoyou
 *
 */
public interface OrgConfigDao {
	
		/*
		 * 用mysql5.0时用到的sql语句
		 * public  String	HISALARM = 
			 "CREATE TABLE `fdaphisalarm_&&` ("+
			 " `hisalarmId` bigint(20) NOT NULL AUTO_INCREMENT,"+
			 " `aiGuid` varchar(36) NOT NULL,"+
			 " `alarmData` decimal(5,2) NOT NULL,"+
			 "   `startTime` datetime NOT NULL,"+
			 "	  `endTime` datetime  NULL,"+
			 "  `alarmLevel` int(11) NOT NULL,"+
			 "  `alarmType` int(11) NOT NULL,"+
			 "  `alarmstandard` decimal(5,2)  NULL,"+
			 "   `flag` int(11) NOT NULL DEFAULT '0',"+
			 "  PRIMARY KEY (`hisalarmId`),"+
			 "	  KEY `aiguid` (`aiGuid`),"+
			 " KEY `startTime` (`startTime`), "+
			 " KEY `endTime` (`endTime`), "+
			 "	  CONSTRAINT `fdaphisalarm_&&_ibfk_1` FOREIGN KEY (`aiGuid`) REFERENCES `fdapaiinfo` (`aiguid`) ON DELETE CASCADE ON UPDATE CASCADE"+
			 "	) ENGINE=InnoDB DEFAULT CHARSET=utf8;" ;
	
		public String DROP_HISALARM = "drop table if exists `fdaphisalarm_&&` ; " ;
		
		public String CARHIS =
		"CREATE TABLE `fdapcarhisdata_&&` ("+
		"		  `id` bigint(20) NOT NULL AUTO_INCREMENT ,"+
		"		  `t1` decimal(5,2) NOT NULL,"+
		"		  `t2` decimal(5,2) NOT NULL,"+
		"		  `t3` decimal(5,2) NOT NULL,"+
		"		  `startupId` bigint(20) NOT NULL,"+
		"		  `latitude` decimal(6,2) NOT NULL,"+
		"		  `latitude_dir` int(11) NOT NULL,"+
		"		  `longitude` decimal(7,2) NOT NULL,"+
		"		  `longitude_dir` int(11) NOT NULL,"+
		"		  `time` datetime NOT NULL,"+
		"		  `isAlarm` int(11) NOT NULL,"+
		"		  PRIMARY KEY (`id`) ,"+
		"			KEY `Time` (`Time`), "+
		"			KEY `FK_Relationship_17` (`startupid`)"+
		"		) ENGINE=InnoDB DEFAULT CHARSET=utf8;" ;
		
		public String DROP_CARHIS = "DROP TABLE IF EXISTS `fdapcarhisdata_&&`; " ;
		
		public String REFHIS = 
			"CREATE TABLE `fdaprefhisdata_&&` ("+
			 " `refhisId` bigint(20) NOT NULL AUTO_INCREMENT ,"+
			"  `aiGuid` varchar(36) NOT NULL,"+
			"  `data` decimal(5,2) NOT NULL,"+
			"  `isalarm` int(11) NOT NULL,"+
			"  `time` datetime NOT NULL,"+
			"  PRIMARY KEY (`refhisId`),"+
			"  KEY `refhisdata_aiguid` (`aiGuid`),"+
			" KEY `Time` (`Time`),"+
			"  CONSTRAINT `fdaprefhisdata_&&_ibfk_1` FOREIGN KEY (`aiGuid`) REFERENCES `fdapaiinfo` (`aiguid`) ON DELETE CASCADE ON UPDATE CASCADE"+
			") ENGINE=InnoDB DEFAULT CHARSET=utf8;"		 ;
					
		public String DROP_REFHIS = "DROP TABLE IF EXISTS `fdaprefhisdata_&&`  ; " ;
		
		public String CARSTARTUP =
			"CREATE TABLE `fdapstartup_&&` ("+
			"  `startupId` bigint(20) NOT NULL AUTO_INCREMENT ,"+
			  "`refid` bigint(20) NOT NULL,"+
			  "`startTime` datetime NOT NULL,"+
			  "`endTime` datetime NULL,"+
			  "`carrier` varchar(30)  NULL,"+
			  "`intervalValue` int(11) NOT NULL,"+
			  "PRIMARY KEY (`startupId`),"+
			  "KEY `startup_refId` (`refid`),"+
			  " KEY `startTime` (`startTime`),"+
			  "CONSTRAINT `fdapstartup_&&_ibfk_1` FOREIGN KEY (`refid`) REFERENCES `fdapref` (`refId`) ON DELETE CASCADE ON UPDATE CASCADE"+
			") ENGINE=InnoDB DEFAULT CHARSET=utf8; " ;
					
		
		public String DROP_CARSTARTUP = "DROP TABLE IF EXISTS `fdapstartup_&&`  ;  " ;*/
	
		
		
	public  String	HISALARM = 
		 "create table Fdaphisalarm_&& ("+
   "hisalarmid bigint identity,"+
   "aiguid varchar(36) null,"+
   "alarmdata decimal(5,2) not null,"+
   "startTime datetime not null,"+
   "endTime datetime null,"+
   "alarmlevel int not null,"+
   "alarmtype int not null,"+
   "alarmstandard decimal(5,2) null,"+
   "flag int not null default 0,"+
   "constraint PK_FDAPHISALARM_&& primary key nonclustered (hisalarmid)," +
   "constraint FK_FDAPHISA_RELATIONS_FDAPAIIN_&& foreign key (aiguid) references fdapaiinfo (aiguid) on update cascade on delete cascade"+
   ")" +
   " create index fdaphisalarm_&&_ibfk_1 on Fdaphisalarm_&& ("+
   "aiguid ASC"+
   ")" +
   " create index startTime on Fdaphisalarm_&& ("+
   "startTime ASC"+
   ")" +
   " create index endTime on Fdaphisalarm_&& ("+
   " endTime ASC"+
   ")";

	public String DROP_HISALARM = 
		"if exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')" +
		" where r.fkeyid = object_id('Fdaphisalarm_&&') and o.name = 'FK_FDAPHISA_RELATIONS_FDAPAIIN_&&')" +
		" alter table Fdaphisalarm_&&" +
		" drop constraint FK_FDAPHISA_RELATIONS_FDAPAIIN_&&" +
		" if exists (select 1 from  sysindexes where  id    = object_id('Fdaphisalarm_&&')" +
		" and   name  = 'endTime' and   indid > 0 and   indid < 255)" +
		" drop index Fdaphisalarm_&&.endTime" +
		" if exists (select 1 from  sysindexes where  id    = object_id('Fdaphisalarm_&&')" +
		" and   name  = 'startTime' and   indid > 0 and   indid < 255)" +
		" drop index Fdaphisalarm_&&.startTime" +
		" if exists (select 1 from  sysindexes where  id    = object_id('Fdaphisalarm_&&')" +
		" and   name  = 'Relationship_14_FK' and   indid > 0 and   indid < 255)" +
		" drop index Fdaphisalarm_&&.Relationship_14_FK" +
		" if exists (select 1 from  sysobjects where  id = object_id('Fdaphisalarm_&&')" +
		" and   type = 'U') drop table Fdaphisalarm_&&";
	
	public String CARHIS =
	"create table Fdapcarhisdata_&& (" +
	" id bigint identity," +
	" startupid bigint null," +
	" t1 decimal(5,2) not null," +
	" t2 decimal(5,2) not null," +
	" t3 decimal(5,2) not null," +
	" latitude decimal(8,4) not null," +
	" latitude_dir int not null," +
	" longitude decimal(9,4) not null," +
	" longitude_dir int not null," +
	" time datetime not null," +
	" isalarm int not null default 1," +
	" constraint PK_FDAPCARHISDATA_&& primary key nonclustered (id)," +
	"constraint FK_FDAPCARH_RELATIONS_FDAPSTAR_&& foreign key (startupid) references Fdapstartup_&& (startupid)" +
	")" +
	" create index fdapcarhisdata_&&_ibfk_1 on Fdapcarhisdata_&& (" +
	"startupid ASC" +
	")" +
	" create index time on Fdapcarhisdata_&& (" +
	"time ASC" +
	")" ;
	
	public String DROP_CARHIS = "if exists (select 1" +
			" from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')" +
			" where r.fkeyid = object_id('Fdapcarhisdata_&&') and o.name = 'FK_FDAPCARH_RELATIONS_FDAPSTAR_&&')" +
			" alter table Fdapcarhisdata_&&" +
			" drop constraint FK_FDAPCARH_RELATIONS_FDAPSTAR_&&" +
			" if exists (select 1 from  sysindexes where id = object_id('Fdapcarhisdata_&&')" +
			" and name  = 'time' and   indid > 0 and   indid < 255)" +
			" drop index Fdapcarhisdata_&&.time" +
			" if exists (select 1 from  sysindexes where id = object_id('Fdapcarhisdata_&&')" +
			" and name  = 'fdapcarhisdata_&&_ibfk_1' and   indid > 0 and   indid < 255)" +
			" drop index Fdapcarhisdata_&&.fdapcarhisdata_&&_ibfk_1" +
			" if exists (select 1 from  sysobjects where  id = object_id('Fdapcarhisdata_&&')" +
			" and   type = 'U') drop table Fdapcarhisdata_&&" ;
	
	public String REFHIS = 
		"create table Fdaprefhisdata_&& (" +
		"refhisId bigint identity," +
		"aiguid varchar(36) null," +
		"data decimal(5,2) not null," +
		"time datetime not null," +
		"isalarm int not null default 1," +
		"constraint PK_FDAPREFHISDATA_&& primary key nonclustered (refhisId)," +
		"constraint FK_FDAPREFH_RELATIONS_FDAPAIIN_&& foreign key (aiguid) references fdapaiinfo (aiguid) on update cascade on delete cascade" +
		")" +
		" create index fdaprefhisdata_&&_ibfk_1 on Fdaprefhisdata_&& (" +
		"aiguid ASC" +
		")" +
		" create index time on Fdaprefhisdata_&& (" +
		"time ASC" +
		")";
				
	public String DROP_REFHIS = 
			"if exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')" +
			" where r.fkeyid = object_id('Fdaprefhisdata_&&') and o.name = 'FK_FDAPREFH_RELATIONS_FDAPAIIN_&&')" +
			" alter table Fdaprefhisdata_&&" +
			" drop constraint FK_FDAPREFH_RELATIONS_FDAPAIIN_&&" +
			" if exists (select 1 from  sysindexes where  id    = object_id('Fdaprefhisdata_&&')" +
			" and name  = 'time' and   indid > 0 and   indid < 255)" +
			" drop index Fdaprefhisdata_&&.time" +
			" if exists (select 1 from  sysindexes where  id    = object_id('Fdaprefhisdata_&&')" +
			" and   name  = 'fdaprefhisdata_&&_ibfk_1'  and   indid > 0 and   indid < 255)" +
			" drop index Fdaprefhisdata_&&.fdaprefhisdata_&&_ibfk_1" +
			" if exists (select 1 from  sysobjects where  id = object_id('Fdaprefhisdata_&&')" +
			" and   type = 'U')" +
			" drop table Fdaprefhisdata_&&" ;
	
	public String CARSTARTUP =
		"create table Fdapstartup_&& (" +
		"startupid bigint identity," +
		"refId bigint null," +
		"startTime datetime not null," +
		"endTime datetime null," +
		"carrier varchar(30) null," +
		"intervalValue int not null default 0," +
		"constraint PK_FDAPSTARTUP_&& primary key nonclustered (startupid)," +
		"constraint FK_FDAPSTAR_RELATIONS_FDAPREF_&& foreign key (refId) references fdapref (refId) on update cascade on delete cascade" +
		")" +
		" create index fdapstartup_&&_ibfk_1 on Fdapstartup_&& (" +
		"refId ASC" +
		")" +
		" create index startTime on Fdapstartup_&& (" +
		"startTime ASC" +
		")" ;
				
	
	public String DROP_CARSTARTUP = 
		"if exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')" +
		" where r.fkeyid = object_id('Fdapstartup_&&') and o.name = 'FK_FDAPSTAR_RELATIONS_FDAPREF_&&')" +
		" alter table Fdapstartup_&&" +
		" drop constraint FK_FDAPSTAR_RELATIONS_FDAPREF_&&" +
		" if exists (select 1 from  sysindexes where  id    = object_id('Fdapstartup_&&')" +
		" and   name  = 'startTime' and   indid > 0 and   indid < 255)" +
		" drop index Fdapstartup_&&.startTime" +
		" if exists (select 1 from  sysindexes where  id    = object_id('Fdapstartup_&&')" +
		" and name  = 'fdapstartup_&&_ibfk_1' and   indid > 0 and   indid < 255)" +
		" drop index Fdapstartup_&&.fdapstartup_&&_ibfk_1" +
		" if exists (select 1 from  sysobjects where  id = object_id('Fdapstartup_&&')" +
		" and   type = 'U')" +
		" drop table Fdapstartup_&&" ;
	
		
		/**
		 * 获取系统的顶层机构
		 * @return
		 */
		public  List<Fdaporg>	queryTopOrg();
		
		/**
		 * 更新顶层机构的信息
		 * @param org	org.name ,org.account,org.email,org.telephone
		 */
		public void updateOrg(Fdaporg org);
		
		/**
		 * 删除一个机构
		 * @param oid		机构的标识oid
		 */
		public void deleteOrg(Long oid) ;
		
		/**
		 * 删除机构集合
		 * @param orgIds		机构标识集合id
		 */
		public void deleteOrgList(List<Long> orgIds);
		
		/**
		 * 添加一个机构
		 * @param org
		 */
		public void insertOrg(Fdaporg org) ;
		
		/**
		 * 根据机构的账户名获取机构列表
		 * @param account				机构名
		 * @return
		 */
		public Long queryByName(String account)  ;
		
		/**
		 * 判断是否还存在下级机构
		 * @param orgIds		机构的标识Id
		 * @return
		 */
		public Long queryExistsLowerOrg(List<Long> orgIds);
		
		/**
		 * 每次添加企业成功后，添加四个动态表
		 * @param oid		企业标识Id
		 */
		public void createTable(Long oid) ;
		
		/**
		 * 删除一个企业后，删除四个动态表
		 * @param oid
		 */
		public void dropTable(Long oid) ;

		
		/**
		 * 保存一个企业对应的授权码
		 * @param authCode
		 */
		public void insertAuthCode(Fdapauthcode authCode) ;
		
		
		/**
		 * 根据企业标识Id，删除对应的授权码
		 * @param oid
		 */
		public void delAuthCode(Long oid) ;

		
		
		/**
		 * 检测对应机构是否已经存在要添加或更改的企业名称
		 * @param name		需要验证的企业名称
		 * @param parentOid		企业标识parentOid
		 * @return
		 */
		public Long queryExistsOrg(String name,String parentOid);
		
		/**
		 * 获取所有的机构列表
		 * @return		返回所有的机构列表
		 */
		public List<Fdaporg> queryOrgList();
		
		
		/**
		 * 获取所有的授权码
		 * @return		返回所有的授权码
		 */
		public List<Object> queryAllCode();
}
