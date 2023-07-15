package net.petersil98.thresh.util;

import net.petersil98.core.util.InvalidFilterException;
import net.petersil98.stcommons.constants.RankedTier;
import net.petersil98.stcommons.constants.STConstants;
import net.petersil98.thresh.collection.QueueTypes;
import net.petersil98.thresh.data.Challenge;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.BaseRune;
import net.petersil98.thresh.data.rune.RuneStyle;

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
        return String.format("%scdn/%s/img/profileicon/%s.png", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, profileIconId);
    }

    public static String getChampionIconURL(Champion champion) {
        return String.format("%scdn/%s/img/champion/%s.png", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Util.getChampWithoutSpecials(champion.getName()));
    }

    public static String getRuneIconURL(BaseRune rune) {
        return String.format("%scdn/img/%s", STConstants.DDRAGON_BASE_PATH, rune.getIconPath());
    }

    public static String getRuneIconURL(RuneStyle rune) {
        return String.format("%scdn/img/%s", STConstants.DDRAGON_BASE_PATH, rune.getIconPath());
    }

    public static String getChallengeIconURL(Challenge challenge, RankedTier tier) {
        if(!challenge.getLevelToIconPath().containsKey(tier)) return null;
        return String.format("%scdn/img%s", STConstants.DDRAGON_BASE_PATH, challenge.getLevelToIconPath().get(tier));
    }

    public static void validateFilter(java.util.Map<String, String> filter) {
        filter.forEach((filterName, arg) -> {
            switch (filterName) {
                case "endTime", "start", "startTime" -> {
                    try {
                        long time = Long.parseLong(arg);
                        if (time < 0) throw new InvalidFilterException(arg + " cannot be negative");
                    } catch (NumberFormatException e) {
                        throw new InvalidFilterException("Filter \"" + arg + "\" isn't a number", e);
                    }
                }
                case "queue" -> {
                    try {
                        int queueId = Integer.parseInt(arg);
                        if (QueueTypes.getQueueTypes().stream().noneMatch(queueType -> queueType.getId() == queueId)) {
                            throw new InvalidFilterException("No queue type found with ID \"" + arg + "\"");
                        }
                    } catch (NumberFormatException e) {
                        throw new InvalidFilterException("Filter \"" + arg + "\" isn't a number", e);
                    }
                }
                case "type" -> {}
                case "count" -> {
                    try {
                        int count = Integer.parseInt(arg);
                        if (count < 0 || count > 100)
                            throw new InvalidFilterException("count must be between 0 and 100");
                    } catch (NumberFormatException e) {
                        throw new InvalidFilterException("Filter \"" + arg + "\" isn't a number", e);
                    }
                }
                default -> throw new InvalidFilterException("Unknown filter \"" + filterName + "\" for match history");
            }
        });
    }
}
