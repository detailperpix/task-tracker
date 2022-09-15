package com.detailperpix.tasktracker.task;

import java.util.Date;

public class Task {
    String[] labels;
    String title;
    String desc;
    Date startTime;
    Date endTime;

    public Task(String[] labels, String title, String desc) {
        this.labels = labels;
        this.title = title;
        this.desc = desc;
        this.startTime = new Date();
    }

    public void finishTask() {
        // set endTime and write to db
        this.endTime = new Date();
    }

}
