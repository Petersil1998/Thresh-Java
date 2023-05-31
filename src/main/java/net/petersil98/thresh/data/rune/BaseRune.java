package net.petersil98.thresh.data.rune;

public abstract class BaseRune extends AbstractRune {

    protected final String shortDesc;
    protected final String longDesc;


    protected BaseRune(int id, String name, String iconPath, String shortDesc, String longDesc) {
        super(id, name, iconPath);
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
    }

    public String getShortDesc() {
        return this.shortDesc;
    }

    public String getLongDesc() {
        return this.longDesc;
    }
}
