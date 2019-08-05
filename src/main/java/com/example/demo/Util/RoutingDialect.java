package com.example.demo.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author lishutong
* @version 创建时间:2019年8月2日 上午11:00:57
* 类说明
*/
public class RoutingDialect implements IDialect {

	private IDialect delegate;
	
	private static Map<DBType, IDialect> dialectMap = new HashMap<DBType, IDialect>();
	
	private RoutingDialect(DBType dbType) {
		switch (dbType) {
		case MYSQL:
			delegate = new MySqlDialect();
			break;
		case ORACLE:
			delegate = new OracleDialect();
			break;
		default:
			delegate = new MySqlDialect();
		}
	}

	public static IDialect getDialect(DBType dbType) {
		IDialect dialect = null;
		dialect = dialectMap.get(dbType);
		if (dialect == null) {
			synchronized (dialectMap) {
				dialect = dialectMap.get(dbType);
				if (dialect == null) {
					dialect = new RoutingDialect(dbType);
					dialectMap.put(dbType, dialect);
				}
			}
		}
		return dialect;
	}

	
	
	
	
	@Override
	public String getSortSQL(String sql, List<String> sortFields, List<String> sortOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLimitSQL(String sql, int offset, int limit) {
		// TODO Auto-generated method stub
		return delegate.getLimitSQL(sql, offset, limit);
	}

	@Override
	public String getTotalSQL(String sql) {
		// TODO Auto-generated method stub
		return delegate.getTotalSQL(sql);
	}

}
