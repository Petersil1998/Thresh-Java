package net.petersil98.thresh.constant;

public enum PlatformClean {
    EUW("euw"),
    EUNE("eune"),
    KR("kr"),
    JP("jp"),
    OCE("oce"),
    BR("br"),
    RU("ru"),
    TR("tr"),
    NA("na"),
    LAN("lan"),
    LAS("las");

    private final String name;

    PlatformClean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
