package com.detailperpix.tasktracker.services.methods;

import com.detailperpix.tasktracker.services.SQLiteDatabase;

import java.sql.SQLException;

public class LabelWriter {
    public static boolean addLabel(int labelId, String label) {
        String sql = String.format("INSERT INTO label(id, label) " +
                        "VALUES (%d, '%s');",
                labelId, label);
        try {
            SQLiteDatabase.execQuery(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
