package net.petersil98.thresh.constant;

public enum Region {
    AMERICA("america"),
    EUROPE("europe"),
    ASIA("asia");

    private final String name;

    Region(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
