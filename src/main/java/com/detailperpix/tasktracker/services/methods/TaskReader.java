package com.detailperpix.tasktracker.services.methods;

import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.task.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskReader {
    public static ArrayList<Task> getTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (ResultSet result = SQLiteDatabase.selectQuery("SELECT * FROM task")) {
            if (result != null) {
                do {
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
                } while (result.next());
            }
        } catch (SQLException e) {
            return null;
        }

        return tasks;
    }
}
