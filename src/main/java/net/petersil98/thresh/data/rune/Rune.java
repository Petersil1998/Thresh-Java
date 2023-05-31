package net.petersil98.thresh.data.rune;

public class Rune extends BaseRune {
    private final String key;
    private final RuneStyle runeStyle;

    public Rune(int id, String name, String iconPath, String key, String shortDesc, String longDesc, RuneStyle runeStyle) {
        super(id, name, iconPath, shortDesc, longDesc);
        this.key = key;
        this.runeStyle = runeStyle;
    }

    public String getKey() {
        return this.key;
    }

    public RuneStyle getRuneStyle() {
        return this.runeStyle;
    }
}
