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
                    String title = result.getString("title");
                    long startTime = result.getLong("startTime");
                    Task.Builder task = new Task.Builder()
                            .baseTask(title, startTime);

                    int labelId = result.getInt("labelId");
                    String label = result.getString("label");
                    if (labelId > 0) {
                        task.label(labelId, label);
                    }
                    String desc = result.getString("description");
                    if (desc != null) {
                        task.description(desc);
                    }

                    long endTime = result.getLong("endTime");
                    if (!result.wasNull()) {
                        task.endTime(endTime);
                    }
                    tasks.add(task.build());
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return tasks;
    }
}
