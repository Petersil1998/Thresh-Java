package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Skin {

    private int id;
    private String name;
    @JsonProperty("chromas")
    private boolean hasChromas;

    public Skin(int id, String name, boolean hasChromas) {
        this.id = id;
        this.name = name;
        this.hasChromas = hasChromas;
    }

    public Skin() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasChromas() {
        return hasChromas;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
