package org.tbcc.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

/**
 * 这是为了解决用SQLQuery API时，抛出的异常，数据库里面的demiacl字段类型不能与java的bigDemical成功映射
 * 所以扩展SQLServerDialect
 * @author Administrator
 *
 */
public class MySQLServerDialect extends SQLServerDialect {
	public MySQLServerDialect(){
		super();
		registerHibernateType(Types.DECIMAL	, Hibernate.DOUBLE.getName());
		registerHibernateType(Types.BIGINT, Hibernate.LONG.getName());
	}
}
