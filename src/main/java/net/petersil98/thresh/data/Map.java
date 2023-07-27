package net.petersil98.thresh.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.stcommons.data.Sprite;
import net.petersil98.thresh.model.Deserializers;

import java.util.Objects;

@JsonDeserialize(using = Deserializers.MapDeserializer.class)
public record Map(int id, String name, String fullImage, Sprite sprite) {

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
