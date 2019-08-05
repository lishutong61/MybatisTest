package com.example.demo.Util;
/**
* @author lishutong
* @version 创建时间:2019年8月2日 上午10:49:58
* 类说明
*/
public class MySqlDialect extends ADialect {

	@Override
	public String getLimitSQL(String sql, int offset, int limit) {
		// TODO Auto-generated method stub
		sql=sql.trim();
		StringBuffer newSql=new StringBuffer(sql.length()+100);
		newSql.append("select * from (").append(sql).append(") as _tmp_query limit ").append(offset).append(",").append(limit);
		return newSql.toString();
	}

	
	
}
