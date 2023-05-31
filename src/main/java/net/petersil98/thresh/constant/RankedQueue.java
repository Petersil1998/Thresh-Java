package net.petersil98.thresh.constant;

public enum RankedQueue {
    SOLO_DUO("RANKED_SOLO_5x5"),
    FLEX("RANKED_FLEX_SR");

    private final String name;

    RankedQueue(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
