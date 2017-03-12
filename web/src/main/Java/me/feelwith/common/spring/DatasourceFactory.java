package me.feelwith.common.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by L on 2016/11/26.
 */
public class DatasourceFactory {
    private static final String user = "practice";
    private static final String password = "fishing";
    private static final Map<String, DataSource> mapping = loadMapping();

    //FIXME 加载所有的dataSource
    public static DataSource getDataSource(String dbName, boolean isReadonly) {
        DataSource ds = mapping.get(dbName);
        return ds;
    }

    private static Map<String, DataSource> loadMapping() {
        Map<String, DataSource> tmp = new HashMap<>();
        String [] dbs = new String []{"gossip","user","auth"};
        for(String db : dbs){
            HikariConfig config = new HikariConfig();
            String uri = String.format("jdbc:mysql://localhost:3306/%s",db);
            config.setJdbcUrl(uri);
            config.setUsername(user);
            config.setPassword(password);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            HikariDataSource dataSource = new HikariDataSource(config);
            tmp.put(db, dataSource);
        }
        return tmp;
    }
}
