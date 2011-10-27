package org.tbcc.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

/**
 * ����Ϊ�˽����SQLQuery APIʱ���׳����쳣�����ݿ������demiacl�ֶ����Ͳ�����java��bigDemical�ɹ�ӳ��
 * ������չSQLServerDialect
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
