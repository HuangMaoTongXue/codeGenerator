package jdbc;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangmao on 2017/1/11.
 */
public class OperateDB {

    private Connection conn;
    private Logger logger = Logger.getRootLogger();

    private OperateDB() {
    }//构造函数私有

    public static OperateDB getInstance() {
        return BuildOperDB.instance;
    }

    public void connet(String driver, String url, String user, String password) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Exception occurs when connect DB", e);
        }
    }

    public Map<String, String> getMetaInf(String tableName) {//注意sql注入
        try {
            if (!checkTableName(tableName))//表名不符合规范
            {
                logger.error("table name is not illegal when getMetaInf");
                return null;
            }
            Map<String, String> columnInf = new HashMap<>();
            String sql = "select * from " + tableName;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData rsMeta = resultSet.getMetaData();
            for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                String columnName = rsMeta.getColumnName(i);
                String className = rsMeta.getColumnClassName(i);
                columnInf.put(columnName, className);
            }
            return columnInf;
        } catch (SQLException e) {
            logger.error("Exception occurs when getMetaInf data", e);
            return null;
        }
    }//end of getMetaInf()

    private boolean checkTableName(String tableName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z_0-9]+$");
        Matcher matcher = pattern.matcher(tableName);
        return matcher.matches();
    }

    private static class BuildOperDB {
        private static OperateDB instance = new OperateDB();
    }
}//end of class
