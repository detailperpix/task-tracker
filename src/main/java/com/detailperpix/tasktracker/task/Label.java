package com.detailperpix.tasktracker.task;

public class Label {


    private final int labelId;
    private final String label;

    public Label(int labelId, String label) {
        this.labelId = labelId;
        this.label = label;
    }

    public Label(Label label) {
        this.labelId = label.getLabelId();
        this.label = label.getLabelDescription();
    }

    public int getLabelId() {
        return this.labelId;
    }

    public String getLabelDescription() {
        return this.label;
    }
}
