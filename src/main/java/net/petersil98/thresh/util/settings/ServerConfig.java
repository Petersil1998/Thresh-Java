package net.petersil98.thresh.util.settings;

import net.petersil98.thresh.constant.Platform;
import net.petersil98.thresh.constant.Region;

public class ServerConfig {

    public static final ServerConfig EUW_CONFIG = new ServerConfig(Region.EUROPE, Platform.EUW);
    public static final ServerConfig EUNE_CONFIG = new ServerConfig(Region.EUROPE, Platform.EUNE);
    public static final ServerConfig KR_CONFIG = new ServerConfig(Region.ASIA, Platform.KR);
    public static final ServerConfig JP_CONFIG = new ServerConfig(Region.ASIA, Platform.JP);
    public static final ServerConfig OCE_CONFIG = new ServerConfig(Region.AMERICA, Platform.OCE);
    public static final ServerConfig BR_CONFIG = new ServerConfig(Region.AMERICA, Platform.BR);
    public static final ServerConfig RU_CONFIG = new ServerConfig(Region.EUROPE, Platform.RU);
    public static final ServerConfig TR_CONFIG = new ServerConfig(Region.EUROPE, Platform.TR);
    public static final ServerConfig NA_CONFIG = new ServerConfig(Region.AMERICA, Platform.NA);
    public static final ServerConfig LAN_CONFIG = new ServerConfig(Region.AMERICA, Platform.LAN);
    public static final ServerConfig LAS_CONFIG = new ServerConfig(Region.AMERICA, Platform.LAS);

    private Region region;
    private Platform platform;

    public ServerConfig(Region region, Platform platform) {
        this.region = region;
        this.platform = platform;
    }

    public Region getRegion() {
        return region;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
