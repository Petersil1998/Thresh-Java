package net.petersil98.thresh.util;

import net.petersil98.thresh.Thresh;
import net.petersil98.thresh.constant.Constants;
import net.petersil98.thresh.constant.RankedTier;
import net.petersil98.thresh.data.Challenge;
import net.petersil98.thresh.data.Sprite;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.AbstractRune;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Util {

    private static final Map<String, String> CHAMPION_NAMES = Map.of(
            "Wukong", "MonkeyKing",
            "Cho'Gath", "Chogath",
            "Vel'Koz", "Velkoz",
            "Kai'Sa", "Kaisa",
            "Kha'Zix", "Khazix",
            "Nunu & Willump", "Nunu");

    public static String getChampWithoutSpecials(String championName) {
        if(CHAMPION_NAMES.containsKey(championName)) {
            return CHAMPION_NAMES.get(championName);
        }
        return championName.replaceAll("[ '.]", "");
    }

    public static String getProfileIconURL(int profileIconId) {
        return String.format("%scdn/%s/img/profileicon/%s.png", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, profileIconId);
    }

    public static String getChampionIconURL(Champion champion) {
        return String.format("%scdn/%s/img/champion/%s.png", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Util.getChampWithoutSpecials(champion.getName()));
    }

    public static String getRuneIconURL(AbstractRune rune) {
        return String.format("%scdn/img/%s", Constants.DDRAGON_BASE_PATH, rune.getIconPath());
    }

    public static String getChallengeIconURL(Challenge challenge, RankedTier tier) {
        if(!challenge.getLevelToIconPath().containsKey(tier)) return null;
        return String.format("%scdn/img%s", Constants.DDRAGON_BASE_PATH, challenge.getLevelToIconPath().get(tier));
    }

    public static Image getImageFromSprite(Sprite sprite) {
        String path = String.format("%scdn/%s/img/sprite/%s", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, sprite.getSprite());
        try {
            return ImageIO.read(new URL(path)).getSubimage(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        } catch (IOException e) {
            Thresh.LOGGER.error("Couldn't load sprite from image", e);
        }
        return null;
    }

    /*public static function getBase64EncodedImageFromSprite(Sprite $sprite): string
    {
        ob_start();
        imagepng(Util::getPNGFromSprite($sprite));
        $image = ob_get_contents();
        ob_end_clean();
        return base64_encode($image);
    }*/
}
