package com.detailperpix.tasktracker.task;

import java.util.Date;

public class Task {
    int labelId;
    String label;
    String title;
    String desc;
    Date startTime;
    Date endTime;

    public Task(int labelId, String label, String title, String desc, long startTime, long endTime) {
        this.labelId = labelId;
        this.label = label;
        this.title = title;
        this.desc = desc;
        this.startTime = new Date(startTime);
        this.endTime = new Date(endTime);
    }

    public long getStartTimeInEpochMilliseconds() {
        return this.startTime.getTime();
    }

    public long getEndTimeInEpochMilliseconds() {
        return this.endTime != null ? this.endTime.getTime() : -1;
    }

    public String getLabel() {
        return this.label;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getLabelId() {
        return labelId;
    }

    public void finishTask() {
        // set endTime and write to db
        this.endTime = new Date();
    }

    @Override
    public String toString() {
        return String.format("Task: \n" +
                "Title: %s \n" +
                "Label: %s Id: %d\n" +
                "Description: %s\n" +
                "Start Time: %s\n" +
                "End Time: %s",
                getTitle(),
                getLabel(),
                getLabelId(),
                getDesc(),
                this.startTime,
                this.endTime != null ? this.endTime : "NOT DONE" );
    }
}
