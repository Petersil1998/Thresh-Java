package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.model.Deserializers;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.ChromaDeserializer.class)
public class Chroma {

    private final int id;
    private final String name;
    private final String image;
    private final List<Color> colors;

    public Chroma(int id, String name, String image, List<Color> colors) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.colors = colors;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<Color> getColors() {
        return colors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chroma chroma)) return false;
        return id == chroma.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
