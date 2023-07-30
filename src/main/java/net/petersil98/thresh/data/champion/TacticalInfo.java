package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TacticalInfo {

    private int style;
    private int difficulty;
    private DamageType damageType;

    public int getStyle() {
        return style;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public enum DamageType {

        @JsonProperty("kPhysical")
        PHYSICAL,
        @JsonProperty("kMagic")
        MAGIC,
        @JsonProperty("kMixed")
        MIXED
    }
}
