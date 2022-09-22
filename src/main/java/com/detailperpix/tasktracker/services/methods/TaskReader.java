package com.detailperpix.tasktracker.services.methods;

import com.detailperpix.tasktracker.services.SQL;
import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.task.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskReader {
    public static ArrayList<Task> getTask() {
        String sql = new SQL.Builder()
                .selectClause(
                        new String[]{
                                "startTime",
                                "title",
                                "id as labelId",
                                "l.label",
                                "description",
                                "endTime"},
                        new String[]{
                                "task t",
                                "label l"}
                )
                .whereClause()
                .conditionClause("t.label = l.id")
                .build();
        return queryTask(sql);
    }

    public static ArrayList<Task> getTaskByLabel(int labelId) {
        String sql = new SQL.Builder()
                .selectClause(new String[]{
                        "startTime",
                        "title",
                        "id as labelId",
                        "l.label",
                        "description",
                        "endTime"
                },
                        new String[]{
                                "task t",
                                "label l"
                        })
                .whereClause()
                .conditionClause("t.label = l.id")
                .andClause(String.format("t.label=%d", labelId))
                .build();
        System.out.println(sql);
       return queryTask(sql);
    }

    public static ArrayList<Task> queryTask(String sql) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (ResultSet result = SQLiteDatabase.selectQuery(sql)) {
            if (result != null) {
                while (result.next()) {
                    int labelId = result.getInt("labelId");
                    String label = result.getString("label");
                    String title = result.getString("title");
                    String desc = result.getString("description");

                    long startTime = result.getLong("startTime");
                    if (result.wasNull()) {
                        startTime = -1;
                    }
                    long endTime = result.getLong("endTime");
                    if (result.wasNull()) {
                        endTime = -1;
                    }
                    tasks.add(new Task(labelId, label, title, desc, startTime, endTime));
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return tasks;
    }
}
