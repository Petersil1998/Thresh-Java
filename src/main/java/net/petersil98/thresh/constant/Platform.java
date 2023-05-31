package net.petersil98.thresh.constant;

public enum Platform {
    EUW("euw1"),
    EUNE("eun1"),
    KR("kr"),
    JP("jp1"),
    OCE("oc1"),
    BR("br1"),
    RU("ru"),
    TR("tr1"),
    NA("na1"),
    LAN("la1"),
    LAS("la2");

    private final String name;

    Platform(String name) {
        this.name = name;
    }

    public static Platform getPlatform(String platformName) {
        for (Platform platform: Platform.values()) {
            if (platform.name.equalsIgnoreCase(platformName)) return platform;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
