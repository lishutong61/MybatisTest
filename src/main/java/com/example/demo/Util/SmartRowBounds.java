package com.example.demo.Util;

import org.apache.ibatis.session.RowBounds;

/**
* @author lishutong
* @version 创建时间:2019年8月2日 上午11:14:55
* 类说明
*/
public class SmartRowBounds extends RowBounds {
	private int queryOffset=-1;
	private int queryLimit=-1;
	private int totalCount;
	private boolean isDbSupport=false;
	private boolean preventDefaultRowBounds = false;
	public SmartRowBounds() {
        //无分页参数，不分页
		super(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
	}
	public SmartRowBounds(int queryOffset,int queryLimit) {
		super(queryOffset,queryLimit);
		this.queryOffset = queryOffset;
		this.queryLimit = queryLimit;
	}
	public SmartRowBounds(int queryOffset, int queryLimit,
			boolean isDbSupport) {
		this(queryOffset, queryLimit);
		this.isDbSupport = isDbSupport;
	}

	public String getPageSql(DBType dbType, String rawSql) {
		IDialect dialet = RoutingDialect.getDialect(dbType);
                //只有使用数据库分页的情况下才会对sql进行数据库相关的转换
		if (isDbSupport && queryOffset >= 0 && queryLimit >= 0) {
			rawSql = dialet.getLimitSQL(rawSql, queryOffset, queryLimit);
			//如果使用了数据库分页，就必须阻止mybatis默认分页行为。
			//设置一个阻止默认分页的标识preventDefaultRowBounds=tue
			preventDefaultRowBounds = true;
		}
		return rawSql;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalSQL(DBType dbType, String rawSql) {
		 return RoutingDialect.getDialect(dbType).getTotalSQL(rawSql);
	}
	@Override
	public int getOffset() {

        //mysql会调用RowBounds的getOffset方法计算查询偏移，
		if (!preventDefaultRowBounds) {
			return queryOffset;
		}
		//如果默认使用了数据库分页，那么mybatis内部就不能再使用ResultSet滚动分页了
		//因此返回NO_ROW_OFFSET
		return super.NO_ROW_OFFSET;


	}
	@Override
	public int getLimit() {

        //mysql会调用RowBounds的getLimit方法计算查询记录条数，
        //如果未使用数据库分页，你需要告诉mybatis查询的记录长度queryLimit
		if (!preventDefaultRowBounds) {
			return queryLimit;
		}
		//如果你使用了数据库分页，那么mybatis内部就不能再使用ResultSet滚动分页了
		//因此需要返回super.NO_ROW_LIMIT，告知mybatis不进行分页相关的结果集指针跳转
		return super.NO_ROW_LIMIT;

	}

	
	
}
