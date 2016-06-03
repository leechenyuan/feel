package me.feelwith.business.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by lideda on 2016/5/11.
 */
public abstract class BasicDao extends JdbcDaoSupport{
    @Autowired
    private DataSource dataSource;
    @PostConstruct
    public void init() {
        setDataSource(this.dataSource);
    }
}
