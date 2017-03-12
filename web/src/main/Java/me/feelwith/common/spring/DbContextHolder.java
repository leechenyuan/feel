package me.feelwith.common.spring;

public class DbContextHolder {
	private static final ThreadLocal<DbDefine> contextHolder = new ThreadLocal<DbDefine>();  

	public static void setDataSourceType(DbDefine define) {
		contextHolder.set(define);
	}

	public static DbDefine getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}