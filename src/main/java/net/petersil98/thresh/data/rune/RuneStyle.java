package net.petersil98.thresh.data.rune;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.model.Deserializers;

import java.util.Objects;

@JsonDeserialize(using = Deserializers.RuneStyleDeserializer.class)
public class RuneStyle {

    protected int id;
    protected String name;
    protected String iconPath;
    private final String key;

    public RuneStyle(int id, String name, String iconPath, String key) {
        this.id = id;
        this.name = name;
        this.iconPath = iconPath;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RuneStyle) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
