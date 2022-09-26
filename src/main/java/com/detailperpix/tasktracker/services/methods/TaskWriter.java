package com.detailperpix.tasktracker.services.methods;

import com.detailperpix.tasktracker.services.SQL;
import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.task.Task;

import java.sql.SQLException;

public class TaskWriter {
    public static boolean addTask(Task task) {
        // build column-value pairs first
        String sql = new SQL.Builder()
                .insertClause("task")
                .tableColumn(new String[]{
                        "title",
                        "description",
                        "startTime",
                        "endTime",
                        "label"
                })
                .valuesClause(new String[]{
                        String.format("'%s'", task.getTitle()),
                        String.format("'%s'", task.getDesc()),
                        String.valueOf(task.getStartTimeInEpochMilliseconds()),
                        String.valueOf(task.getEndTimeInEpochMilliseconds()),
                        String.valueOf(task.getLabelId())})
                .build();
        try {
            System.out.println(sql);
            SQLiteDatabase.execQuery(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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

    public static boolean updateFinishedTask(Task task) {
        String sql = String.format("UPDATE task SET endTime=%d " +
                        "WHERE startTime=%d AND title='%s'",
                task.getEndTimeInEpochMilliseconds(),
                task.getStartTimeInEpochMilliseconds(),
                task.getTitle());
        try {
            SQLiteDatabase.execQuery(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean initTable() {
        String dropLabelSql = new SQL.Builder()
                .dropTableIfExists("label")
                .build();
        String dropTaskSql = new SQL.Builder()
                .dropTableIfExists("task")
                .build();
        String taskSql = new SQL.Builder()
                .createTable("task")
                .tableColumn(new String[]{
                        "startTime INTEGER NOT NULL",
                        "title TEXT NOT NULL",
                        "label INTEGER",
                        "description TEXT",
                        "endTime INTEGER",
                        "PRIMARY KEY(startTime, title)"
                })
                .build();
        String labelSql = new SQL.Builder()
                .createTable("label")
                .tableColumn(new String[]{
                        "id INTEGER PRIMARY KEY UNIQUE NOT NULL",
                        "label TEXT NOT NULL"
                })
                .build();
        boolean initSuccess = true;
        try {
            SQLiteDatabase.execQuery(dropLabelSql);
            SQLiteDatabase.execQuery(dropTaskSql);
            SQLiteDatabase.execQuery(taskSql);
            SQLiteDatabase.execQuery(labelSql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            initSuccess = false;
        }
        return initSuccess;

    }

}
