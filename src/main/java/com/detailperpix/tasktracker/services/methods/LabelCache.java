package com.detailperpix.tasktracker.services.methods;


import java.util.HashMap;

public class LabelCache {
    private final HashMap<Integer, String> labels;
    private int labelTableSize;

    public LabelCache() {
        this.labels = new HashMap<>();
        this.labelTableSize = refreshLabelTableSize();
    }

    public String getLabel(int labelId) {
        String cacheLabel = labels.get(labelId);
        if (cacheLabel == null) {
            String label = LabelReader.getLabel(labelId);
            if (label != null) {
                labels.put(labelId, label);
            } else {
                return null;
            }
        }
        return labels.get(labelId);
    }

    public boolean addLabel(String label) {
        boolean success = LabelWriter.addLabel(labelTableSize + 1, label);
        if (success) {
            labelTableSize++;
        }
        return success;
    }

    private int refreshLabelTableSize() {
        return LabelReader.getTableSize();
    }

    public int getLabelTableSize() {
        return this.labelTableSize;
    }
}
