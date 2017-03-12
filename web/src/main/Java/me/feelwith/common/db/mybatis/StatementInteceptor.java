package me.feelwith.common.db.mybatis;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import me.feelwith.business.data.entity.Gossip;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.*;

/**
 * Created by L on 2016/11/21.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class , Integer.class }) })
public class StatementInteceptor implements Interceptor{
  /**
     * 线程安全类，初始化常量，避免重复创建
     * **/
    private final static ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    /**
     * 线程安全类，初始化常量，避免重复创建
     * **/
    private final static ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY =
        new DefaultObjectWrapperFactory();

    private final static ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private final static String BOUNDSQL_SQL_NAME = "delegate.boundSql.sql";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        Field field = ReflectionUtils.findField(statementHandler.getClass(), "delegate");
                    ReflectionUtils.makeAccessible(field);
                    Object delegate = ReflectionUtils.getField(field, invocation.getTarget());
                    Field mappedStatementField = ReflectionUtils.findField(delegate.getClass(), "mappedStatement");
                    ReflectionUtils.makeAccessible(mappedStatementField);
                    MappedStatement mappedStatement = (MappedStatement)ReflectionUtils.getField(mappedStatementField, delegate);
        MetaObject metaObject = MetaObject.forObject(statementHandler , DEFAULT_OBJECT_FACTORY , DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);

//        Map<String,Object> params = parseParams(statementHandler.getBoundSql().getParameterObject());
        Object parameterObject = statementHandler.getBoundSql().getParameterObject();
        String tableName = getTableNameByEntity(mappedStatement);
        //TODO 替换表名
        BoundSql boundSql = statementHandler.getBoundSql();
        String origalSql = boundSql.getSql();
//        String shardTableSql = StringUtils.replaceOnce(origalSql,tableName,shardTableName(tableName,parameterObject));
        String shardTable = shardTableName(tableName,parameterObject);
        String shardTableSql = StringUtils.replacePattern(origalSql,"\\b"+tableName+"\\b",shardTable);
        Field sqlField = ReflectionUtils.findField(BoundSql.class,"sql");
        ReflectionUtils.makeAccessible(sqlField);
        ReflectionUtils.setField(sqlField,boundSql,shardTableSql);
        return invocation.proceed();
    }

    //TODO 改写成配置文件
    private static String shardTableName(String table ,Object paramObject){
        if(paramObject instanceof  Gossip ){
            Gossip mGossip = (Gossip)paramObject;
            HashFunction hf = Hashing.md5();
            HashCode hc = hf.newHasher()
            .putLong(mGossip.getUserId())
            .hash();
            int partionNum = hc.asInt()%100;
            return StringUtils.join(table,"_",partionNum);
        }else{
            return table;
        }
    }

    private String getTableNameByEntity(MappedStatement mappedStatement){
        String entityName = mappedStatement.getParameterMap().getType().getSimpleName();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<entityName.length();i++){
            char ch = entityName.charAt(i);
            if(Character.isUpperCase(ch)){
                if(i != 0){
                    builder.append("_");
                }
                ch = Character.toLowerCase(ch);
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    @Override
    public Object plugin(Object target) {
         return (target instanceof StatementHandler)? Plugin.wrap(target, this):target;
    }

    @Override
    public void setProperties(Properties properties) {
    }



}
