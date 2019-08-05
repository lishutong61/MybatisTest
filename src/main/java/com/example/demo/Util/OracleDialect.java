package com.example.demo.Util;
/**
* @author lishutong
* @version 创建时间:2019年8月2日 上午11:03:40
* 类说明
*/
public class OracleDialect extends ADialect {
	@Override
	public String getLimitSQL(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pageSelect = new StringBuffer(sql.length() + 100);
		pageSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pageSelect.append(sql);
		pageSelect.append(" ) row_ ) where rownum_ > ").append(offset).append(
				" and rownum_ <= ").append(offset + limit);
 
		return pageSelect.toString();
	}

}
