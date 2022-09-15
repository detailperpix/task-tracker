package com.detailperpix.tasktracker.services;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDatabase {

    public static void createDatabase(String filename) {
        String url = "jdbc:sqlite:./build/db/" + filename;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver name " + meta.getDriverName());
                System.out.println("New DB file has been created");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
