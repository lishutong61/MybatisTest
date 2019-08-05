package com.example.demo.Util;

import java.util.List;

/**
* @author lishutong
* @version 创建时间:2019年8月2日 上午10:18:05
* 类说明
*/
public interface IDialect {
    //支持根据字段进行排序查询，留给读者实现吧
		String getSortSQL(String sql, List<String> sortFields, List<String> sortOrders);

    //根据原始查询sql生成与数据库相关的查询sql

		String getLimitSQL(String sql, int offset, int limit);

    //根据原始的sql生成查询总记录数的sql
		String getTotalSQL(String sql);
}
