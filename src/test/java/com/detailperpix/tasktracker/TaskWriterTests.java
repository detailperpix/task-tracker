package com.detailperpix.tasktracker;

import com.detailperpix.tasktracker.services.methods.TaskWriter;
import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.task.Task;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
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
        System.out.println("Done");
    }

    @Test
    public void addLabel() {
        assert TaskWriter.addLabel(1, "label");
        assert TaskWriter.addLabel(2, "label0");
    }

    @Test
    public void addValidTask() {
        Task aTask = new Task(1, "label", "title", "desc", new Date().getTime(), 0);
        assert TaskWriter.addTask(aTask);
        Task aTask2 = new Task(2, "title0", "title2", "desc", new Date().getTime(), new Date().getTime() + 100);
        assert TaskWriter.addTask(aTask2);
    }

    @Test
    public void finishTask() {
        Task aTask = new Task(1, "label", "title", "desc", new Date().getTime(), 0);
        TaskWriter.addTask(aTask);
        aTask.finishTask();
        assert TaskWriter.updateFinishedTask(aTask);
//       TaskWriter writer = new TaskWriter(statement);

    }

}
