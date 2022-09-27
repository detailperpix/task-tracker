package com.detailperpix.tasktracker.task;

import java.util.Date;

public class Task {
    String title;
    String desc;
    Date startTime;
    Date endTime;
    Label label;
    private Task(String title, long startTime) {
        setTitle(title);
        setStartTime(startTime);
    }

    public Task(Task task) {
        setLabel(task.getLabelId(), task.getLabel());
        setTitle(task.getTitle());
        setDescription(task.getDesc());
        setStartTime(task.getStartTimeInEpochMilliseconds());
        setEndTime(task.getEndTimeInEpochMilliseconds());
    }

    public Task(int labelId, String label, String title, String desc, long startTime, long endTime) {
        this.label = new Label(labelId, label);
        setTitle(title);
        setDescription(desc);
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
        return this.label.getLabelDescription();
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getLabelId() {
        return this.label.getLabelId();
    }

    public void finishTask() {
        // set endTime and write to db
        this.endTime = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setDescription(String desc) {
        this.desc = desc;
    }


    public void setLabel(int labelId, String label) {
        this.label = new Label(labelId, label);
    }

    public void setStartTime(long time) {
        this.startTime = new Date(time);
    }

    public void setEndTime(long time) {
        this.endTime = new Date(time);
    }

    public static class Builder {
        private Task task;
        public Builder() {

        }

        public Builder baseTask(String title, long startTime) {
            task = new Task(title, startTime);
            return this;
        }

        public Builder description(String desc) {
            task.setDescription(desc);
            return this;
        }

        public Builder endTime(long endTime) {
            task.setEndTime(endTime);
            return this;
        }

        public Builder label(int labelId, String label) {
            task.setLabel(labelId, label);
            return this;
        }

        public Task build() {
            return new Task(this.task);
        }
    }
}
