package net.petersil98.thresh.data.rune;

import java.util.Objects;

public abstract class BaseRune {

    protected int id;
    protected String name;
    protected String iconPath;
    protected final String shortDesc;
    protected final String longDesc;

    protected BaseRune(int id, String name, String iconPath, String shortDesc, String longDesc) {
        this.id = id;
        this.name = name;
        this.iconPath = iconPath;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
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

    public String getShortDesc() {
        return this.shortDesc;
    }

    public String getLongDesc() {
        return this.longDesc;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BaseRune) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
