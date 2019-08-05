package com.example.demo.Util;


import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

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
		logger.info("拦截成功");
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
