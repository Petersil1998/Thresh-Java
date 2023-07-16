package net.petersil98.thresh.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.stcommons.data.Sprite;
import net.petersil98.thresh.model.Deserializers;

@JsonDeserialize(using = Deserializers.MapDeserializer.class)
public record Map(int id, String name, String fullImage, Sprite sprite) {

    @Override
    public String toString() {
        return this.name;
    }
}
