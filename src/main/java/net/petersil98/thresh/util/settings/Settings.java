package net.petersil98.thresh.util.settings;

import java.util.function.Function;
import java.util.function.Supplier;

public class Settings {

    private static ServerConfig currentConfig = ServerConfig.EUW_CONFIG;
    private static Language language = Language.EN_US;
    private static Supplier<String> encryptedKey;
    private static Function<String, String> decryptor;

    public static ServerConfig getCurrentConfig() {
        return Settings.currentConfig;
    }

    public static void setServerConfig(ServerConfig newConfig) {
        if (newConfig != null) Settings.currentConfig = newConfig;
    }

    public static Language getLanguage() {
        return Settings.language;
    }

    public static void setLanguage(Language newLanguage) {
        if (newLanguage != null) Settings.language = newLanguage;
    }

    public static String getAPIKey() {
        return Settings.decryptor.apply(Settings.encryptedKey.get());
    }

    public static void setAPIKey(Supplier<String> encryptedKey) {
        if (encryptedKey != null) Settings.encryptedKey = encryptedKey;
    }

    public static void setDecryptor(Function<String, String> decryptor) {
        if (decryptor != null) Settings.decryptor = decryptor;
    }
}
