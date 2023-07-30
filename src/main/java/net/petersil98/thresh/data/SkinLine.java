package net.petersil98.thresh.data;

import java.util.Objects;

public class SkinLine {

    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkinLine skinLine)) return false;
        return id == skinLine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
