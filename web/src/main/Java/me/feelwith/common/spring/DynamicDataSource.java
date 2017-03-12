package me.feelwith.common.spring;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDataSourceType();
	}
	
	@Override
	protected DataSource determineTargetDataSource() {
		DbDefine dbDefine = (DbDefine)determineCurrentLookupKey();
		DataSource dataSource ;
        dataSource = DatasourceFactory.getDataSource(dbDefine.getDbName(),dbDefine.isReadOnly());
		return dataSource;
	}
}