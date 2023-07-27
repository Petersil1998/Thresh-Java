package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.model.Deserializers;

import java.util.Objects;

@JsonDeserialize(using = Deserializers.SkinDeserializer.class)
public class Skin {

    private final int id;
    private final int skinNum;
    private final String name;
    private final boolean hasChromas;

    public Skin(int id, int skinNum, String name, boolean hasChromas) {
        this.id = id;
        this.skinNum = skinNum;
        this.name = name;
        this.hasChromas = hasChromas;
    }

    public int getId() {
        return id;
    }

    public int getSkinNum() {
        return skinNum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skin skin = (Skin) o;
        return id == skin.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
