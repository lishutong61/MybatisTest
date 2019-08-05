package com.example.demo.Util;


import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
* @author lishutong
* @version 创建时间:2019年8月5日 上午10:01:36
* 类说明
*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class})})
public class TestInterceptor implements Interceptor {
	private static Logger logger =LoggerFactory.getLogger(TestInterceptor.class);
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
		MetaObject metaObject=MetaObject.forObject(statementHandler,SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		MappedStatement mappedStatement=(MappedStatement) metaObject.getValue("delegate.mappedStatement");
		logger.info("getId："+mappedStatement.getId());
		logger.info("getDatabaseId:"+mappedStatement.getDatabaseId());
		logger.info("getSqlCommandType:"+mappedStatement.getSqlCommandType());
		logger.info("getResource:"+mappedStatement.getResource());
		BoundSql boundsql=statementHandler.getBoundSql();
		
		logger.info("boundsql:"+boundsql.getSql());
		logger.info("拦截成功");
		StringBuffer newsql=new StringBuffer(boundsql.getSql().length()+100);
		newsql.append(boundsql.getSql()).append(" limit 0,1");
		logger.info("newsql:"+newsql);
		metaObject.setValue("delegate.boundSql.sql", newsql.toString());
		
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Interceptor.super.plugin(target);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		Interceptor.super.setProperties(properties);
	}
	

}
