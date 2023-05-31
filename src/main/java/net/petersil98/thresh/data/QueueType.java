package net.petersil98.thresh.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueueType {

    @JsonProperty("queueId")
    private int id;
    private String map;
    private String description;
    private String notes;

    public QueueType(int id, String map, String description, String notes) {
        this.id = id;
        this.map = map;
        this.description = description;
        this.notes = notes;
    }

    public QueueType() {}

    public int getId() {
        return id;
    }

    public String getMap() {
        return map;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
