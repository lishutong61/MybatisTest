package com.example.demo.Util;

import java.util.List;

/**
* @author lishutong
* @version 创建时间:2019年8月2日 上午10:21:40
* 类说明
*/
public abstract class ADialect implements IDialect {

	@Override
	public String getSortSQL(String sql, List<String> sortFields, List<String> sortOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLimitSQL(String sql, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTotalSQL(String sql) {
		// TODO Auto-generated method stub
		StringBuffer totalSql = new StringBuffer(sql.length() + 100);
		totalSql.append("select count(0) from ( ").append(sql).append(
				" ) as _tmp_count");
		return totalSql.toString();

	}

}
