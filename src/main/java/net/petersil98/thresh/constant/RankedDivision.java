package net.petersil98.thresh.constant;

public enum RankedDivision {
    I("I"),
    II("II"),
    III("III"),
    IV("IV");

    private final String name;

    RankedDivision(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
