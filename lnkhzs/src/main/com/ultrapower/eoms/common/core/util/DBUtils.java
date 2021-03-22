package com.ultrapower.eoms.common.core.util;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

    public static Connection getConn() {
        // 创建连接
        Properties prop = new Properties();
        InputStreamReader isr;
        Connection conn = null;
        try {
            isr = new InputStreamReader(
                    DBUtils.class.getClassLoader().getResourceAsStream("security.properties"),
                    "UTF-8");
            prop.load(isr);
            // 数据库连接
            final String URL = prop.getProperty("jdbc.url");
            final String NAME = prop.getProperty("jdbc.username");
            final String PASS = prop.getProperty("jdbc.password");
            final String DRIVER = prop.getProperty("jdbc.driverClassName");
            // 查要生成实体类的表

            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            conn = DriverManager.getConnection(URL, NAME, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建连接
        return conn;
    }

    public static Connection getDacpConn() {
        // 创建连接
        Properties prop = new Properties();
        InputStreamReader isr;
        Connection conn = null;
        try {
            isr = new InputStreamReader(
                    DBUtils.class.getClassLoader().getResourceAsStream("security.properties"),
                    "UTF-8");
            prop.load(isr);
            // 数据库连�?
            final String URL = prop.getProperty("dacp.jdbc.url");
            final String NAME = prop.getProperty("dacp.jdbc.username");
            final String PASS = prop.getProperty("dacp.jdbc.password");
            final String DRIVER = prop.getProperty("dacp.jdbc.driverClassName");
            // 查要生成实体类的�?

            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            conn = DriverManager.getConnection(URL, NAME, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建连接
        return conn;
    }

    public static Connection getSmsDbConn() {
        // 创建连接
        Properties prop = new Properties();
        InputStreamReader isr;
        Connection conn = null;
        try {
            isr = new InputStreamReader(
                    DBUtils.class.getClassLoader().getResourceAsStream("security.properties"),
                    "UTF-8");
            prop.load(isr);
            // 数据库连 ?
            final String URL = prop.getProperty("smsdb.jdbc.url");
            final String NAME = prop.getProperty("smsdb.jdbc.username");
            final String PASS = prop.getProperty("smsdb.jdbc.password");
            final String DRIVER = prop.getProperty("smsdb.jdbc.driverClassName");
            // 查要生成实体类的 ?

            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            conn = DriverManager.getConnection(URL, NAME, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建连接
        return conn;
    }
    
    public static void close(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(PreparedStatement stsm) {
        if (stsm != null) {
            try {
                stsm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public static void close(Statement stsm) {
        if (stsm != null) {
            try {
                stsm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}
