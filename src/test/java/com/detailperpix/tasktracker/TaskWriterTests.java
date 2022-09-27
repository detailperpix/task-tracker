package com.detailperpix.tasktracker;

import com.detailperpix.tasktracker.services.methods.LabelWriter;
import com.detailperpix.tasktracker.services.methods.TaskReader;
import com.detailperpix.tasktracker.services.methods.TaskWriter;
import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.task.Task;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
@RunWith(JUnit4.class)
public class TaskWriterTests {

    public TaskWriterTests() {

    }

    @BeforeClass
    public static void init() {


        /*
         * Initialise table for testing
         */
        try {
            String dir = "./build/dbtest.db";
            SQLiteDatabase.createAndUseDatabase(dir);
            assert TaskWriter.initTable();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addLabel() {
        assert LabelWriter.addLabel(1, "label");
        assert LabelWriter.addLabel(2, "label0");
    }

    @Test
    public void addValidTask() {
        Task aTask1 = new Task.Builder()
                .baseTask("title", new Date().getTime())
                .label(1, "label")
                .description("desc")
                .build();
        assert TaskWriter.addTask(aTask1);
        Task aTask2 = new Task.Builder()
                .baseTask("title2", new Date().getTime())
                .label(2, "label0")
                .description("desc")
                .endTime(new Date().getTime() + 100)
                .build();

        assert TaskWriter.addTask(aTask2);
        Task aTask3 = new Task.Builder()
                .baseTask("title3", new Date().getTime())
                .build();
        assert TaskWriter.addTask(aTask3);
        Task aTask4 = new Task.Builder()
                .baseTask("title4", new Date().getTime())
                .label(1, "label")
                .build();
        assert TaskWriter.addTask(aTask4);
    }

    @Test
    public void finishTask() {
        Task aTask = new Task.Builder()
                .baseTask("title5", new Date().getTime())
                .label(1, "label")
                .description("desc")
                .build();

        TaskWriter.addTask(aTask);
        aTask.finishTask();
        assert TaskWriter.updateFinishedTask(aTask);
        ArrayList<Task> tasks = TaskReader.getTaskByLabel(1);
        Task aTask2 = tasks.get(tasks.size() - 1);
        assert aTask.getLabel().equals(aTask2.getLabel());
        assert aTask.getTitle().equals(aTask2.getTitle());
        assert aTask.getDesc().equals(aTask2.getDesc());
        assert aTask.getStartTimeInEpochMilliseconds() == aTask2.getStartTimeInEpochMilliseconds();
        assert aTask.getEndTimeInEpochMilliseconds() == aTask2.getEndTimeInEpochMilliseconds();

    }

}
