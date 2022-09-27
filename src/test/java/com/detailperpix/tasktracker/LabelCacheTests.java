package com.detailperpix.tasktracker;

import com.detailperpix.tasktracker.services.SQLiteDatabase;
import com.detailperpix.tasktracker.services.methods.LabelCache;
import com.detailperpix.tasktracker.services.methods.TaskWriter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
@RunWith(JUnit4.class)
public class LabelCacheTests {

    private static LabelCache cache;
    @BeforeClass
    public static void init() {
        try {
            String dir = "./build/dbtest.db";
            SQLiteDatabase.createAndUseDatabase(dir);
            assert TaskWriter.initTable();
            cache = new LabelCache();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addLabel() {
        if (!cache.addLabel("Label 1")) throw new AssertionError();
        if (!cache.addLabel("Label 2")) throw new AssertionError();
        if (cache.addLabel("Label 1")) throw new AssertionError();
        if (cache.addLabel("Label 2")) throw new AssertionError();
        assert cache.getLabelTableSize() == 2;
    }

    @Test
    public void getLabel() {
        assert cache.getLabel(1).equals("Label 1");
        assert cache.getLabel(2).equals("Label 2");
        assert cache.getLabel(3) == null;
        assert cache.getLabel(4) == null;
    }

}
