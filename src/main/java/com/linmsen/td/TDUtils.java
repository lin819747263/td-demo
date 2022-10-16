package com.linmsen.td;

import com.google.common.collect.Table;
import com.linmsen.td.domain.SubTableMeta;
import com.linmsen.td.domain.SuperTableMeta;
import com.linmsen.td.domain.TagValue;
import com.taosdata.jdbc.TSDBDriver;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class TDUtils {

    public static Connection getConn(String url) throws Exception{
        Class.forName("com.taosdata.jdbc.TSDBDriver");
//        String jdbcUrl = "jdbc:TAOS://td01:6030/test?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CHARSET, "UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_LOCALE, "en_US.UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_TIME_ZONE, "UTC-8");
        connProps.setProperty("debugFlag", "135");
        connProps.setProperty("maxSQLLength", "1048576");
        return DriverManager.getConnection(url, connProps);
    }

    public static Connection getTestConnection() throws Exception{
        String jdbcUrl = "jdbc:TAOS://td01:6030/test?user=root&password=taosdata";
        return getConn(jdbcUrl);
    }



    public static void createDatabase(Connection conn, String db) throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("create database if not exists " + db);
    }

    public static void sql(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    private static void init(Connection conn, String db) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("drop database if exists " + db);
            stmt.execute("create database if not exists " + db);
            stmt.execute("use " + db);
//            for (int i = 0; i < schemaList.length; i++) {
//                stmt.execute(schemaList[i]);
//            }
        }
    }



    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:TAOS://td01:6030/test?user=root&password=taosdata";
        String res_jdbcUrl = "jdbc:TAOS-RS://td01:6041?user=root&password=taosdata";
        Connection conn = getConn(res_jdbcUrl);
        Statement stmt = conn.createStatement();
//        stmt.executeUpdate("use key_data");
//        sql(conn, "create table if not exists device_02 using key_data tags (1L,2L,3L)");
        createDatabase(conn, "db");
        // use database
        stmt.executeUpdate("use db");
//        sql(conn, "create table if not exists tb (ts timestamp, temperature int, humidity float)");
//
//        sql(conn, "insert into tb values(now, 23, 10.3) (now + 1s, 20, 9.3)");
//        sql(conn, "CREATE STABLE meters (ts timestamp, current float, voltage int, phase float) TAGS (location binary(64), groupId int)");

        sql(conn, "CREATE TABLE d1001 USING meters TAGS ('California.SanFrancisco', 2);");
//
//
//
//        // query data
//        ResultSet resultSet = stmt.executeQuery("select * from tb");
//
//        Timestamp ts = null;
//        int temperature = 0;
//        float humidity = 0;
//        while(resultSet.next()){
//
//            ts = resultSet.getTimestamp(1);
//            temperature = resultSet.getInt(2);
//            humidity = resultSet.getFloat("humidity");
//
//            System.out.printf("%s, %d, %s\n", ts, temperature, humidity);
//        }

    }


    public static SubTableMeta generate(SuperTableMeta superTableMeta, String tableName) {
        SubTableMeta subTableMeta = new SubTableMeta();
        // create table xxx.xxx using xxx tags(...)
        subTableMeta.setDatabase(superTableMeta.getDatabase());
        subTableMeta.setName(tableName);
        subTableMeta.setSupertable(superTableMeta.getName());
        subTableMeta.setFields(superTableMeta.getFields());
        List<TagValue> tagValues = TagValueGenerator.generate(superTableMeta.getTags());
        subTableMeta.setTags(tagValues);
        return subTableMeta;
    }
}
