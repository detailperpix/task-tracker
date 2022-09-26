package com.detailperpix.tasktracker.services;

import java.sql.*;

public final class SQLiteDatabase {

    private static Statement statement = null;

    private SQLiteDatabase() {

    }

    public static void createAndUseDatabase(String dir) throws SQLException {
        String url = "jdbc:sqlite:" + dir;
        Connection conn = DriverManager.getConnection(url);
        if (conn != null) {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("Driver name " + meta.getDriverName());
            System.out.println("New DB file has been created");
            statement = conn.createStatement();
            statement.setQueryTimeout(30);
        }

    }

    public static void execQuery(String sql) throws SQLException {
        statement.execute(sql);
    }

    public static ResultSet selectQuery(String sql) throws SQLException {
        if (statement.execute(sql)) {
            return statement.getResultSet();
        }
        return null;

    }

}
