package net.petersil98.thresh.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.stcommons.data.Sprite;
import net.petersil98.thresh.model.Deserializers;

import java.util.Objects;

@JsonDeserialize(using = Deserializers.MapDeserializer.class)
public class Map {

    private final int id;
    private final String name;
    private final String fullImage;
    private final Sprite sprite;

    public Map(int id, String name, String fullImage, Sprite sprite) {
        this.id = id;
        this.name = name;
        this.fullImage = fullImage;
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullImage() {
        return fullImage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return id == map.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
