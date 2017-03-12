package me.feelwith.common.spring;

import java.lang.annotation.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 *
 */
@Aspect    // for aop
@Component // for auto scan
@Order(0)  // execute before @Transactional
public class DataSourceAspect {
	public void switchDataSource(JoinPoint point){
		Object target = point.getTarget();
        String method = point.getSignature().getName();
        Annotation[] methodAnos = ((MethodSignature) point.getSignature()).getMethod().getDeclaredAnnotations();
        DbDefine dbDefine = getDbDefine(methodAnos);
        if(dbDefine == null){
        	Annotation[] classAnos = target.getClass().getAnnotations();
        	dbDefine = getDbDefine(classAnos);
        }
        if(dbDefine == null){
        	throw new IllegalAccessError("can't find datasource define !");
        }
        DbContextHolder.setDataSourceType(dbDefine);
        
	}
	
	private static DbDefine getDbDefine(Annotation[] anos){
		DbDefine dbDefine = null;
		for(Annotation an : anos){
    		if(an instanceof DataSourceS){
    			dbDefine = new DbDefine();
    			dbDefine.setDbName(((DataSourceS) an).dataSource());
    			dbDefine.setReadOnly(((DataSourceS) an).readOnly());
    		}
    	}
		return dbDefine;
	}
}