package com.detailperpix.tasktracker.services.methods;

import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.task.Task;

import java.sql.SQLException;

public class TaskWriter {
    public static boolean addTask(Task task) {
        String sql = String.format("INSERT INTO task(title, desc, startTime)" +
                " VALUES('%s', '%s', '%s');",
                task.getTitle(),
                task.getDesc(),
                task.getStartTimeInEpochMilliseconds());
        try {
            return SQLiteDatabase.execQuery(sql);
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean addLabel(int labelId, String label) {
        String sql = String.format("INSERT INTO label(id, label) " +
                        "VALUES (%d, '%s');",
                labelId, label);
        try {
            return SQLiteDatabase.execQuery(sql);
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateFinishedTask(Task task) {
        String sql = String.format("UPDATE task SET endTime=%d " +
                "WHERE startTime=%d AND title='%s'",
                task.getEndTimeInEpochMilliseconds(),
                task.getStartTimeInEpochMilliseconds(),
                task.getTitle());
        try {
            return SQLiteDatabase.execQuery(sql);
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean initTable() {
        String dropLabelSql = "DROP TABLE IF EXISTS label";
        String dropTaskSql = "DROP TABLE IF EXISTS task";
        String taskSql = "CREATE TABLE task ("
                + " startTime INTEGER PRIMARY KEY NOT NULL,"
                + " title TEXT PRIMARY KEY NOT NULL,"
                + " label INTEGER,"
                + " description TEXT,"
                + " endTime INTEGER)";
        String labelSql = "CREATE TABLE label (id INTEGER PRIMARY KEY NOT NULL";
        boolean initSuccess = true;
        try {
            SQLiteDatabase.execQuery(dropLabelSql);
            SQLiteDatabase.execQuery(dropTaskSql);
            SQLiteDatabase.execQuery(taskSql);
            SQLiteDatabase.execQuery(labelSql);
        } catch (SQLException e) {
            initSuccess = false;
        }
        return initSuccess;

    }

}
