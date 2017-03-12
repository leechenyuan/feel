package test.me.feelwith.business.service.util;

import org.junit.*;
import org.slf4j.helpers.MessageFormatter;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Administrator on 2016/11/17.
 */
public class TableGenerator {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/gossip";

   //  Database credentials
   static final String USER = "practice";
   static final String PASS = "fishing";

    @org.junit.Test
    public void createTable() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        //STEP 3: Open a connection
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String createTableSql =
                "CREATE TABLE `{}` ("+
                        "`Id` INT(11) NOT NULL AUTO_INCREMENT,"+
                        "`user_id` VARCHAR(50) NOT NULL,"+
                        "`gossip_id` VARCHAR(50) NOT NULL,"+
                        "`reply_id` VARCHAR(50) NULL DEFAULT NULL,"+
                        "`content` VARCHAR(200) NOT NULL,"+
                        "`img` VARCHAR(200) NULL DEFAULT NULL,"+
                        "`create_time` VARCHAR(50) NOT NULL,"+
                        "PRIMARY KEY (`Id`),"+
                        "INDEX `user_id` (`user_id`)"+
                        ")"+
                        "COLLATE='utf8_general_ci'"+
                        "ENGINE=InnoDB"
                ;
        createPartitionTable(conn,"gossip",createTableSql,100);
    }

    private static  void createPartitionTable(Connection dbConnection,String tableName,String createTableSQL,int nums) throws SQLException {
		Statement statement = null;
		try {
            statement = dbConnection.createStatement();
            for(int i = 0;i<nums;i++){
                String sql = MessageFormatter.format(createTableSQL,tableName+"_"+(i+1)).getMessage();
                statement.addBatch(sql);
            }
            statement.executeBatch();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
}
