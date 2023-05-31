package net.petersil98.thresh.data.rune;

import java.util.Objects;

public class RuneStyle extends AbstractRune {
    private final String key;

    public RuneStyle(int id, String name, String iconPath, String key) {
        super(id, name, iconPath);
        this.key = key;
    }

    public String getKey() {
        return this.key;
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
