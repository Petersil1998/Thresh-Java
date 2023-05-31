package net.petersil98.thresh;

import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.constant.RankedTier;
import net.petersil98.thresh.model.game.match.MatchDetails;
import net.petersil98.thresh.model.game.match.timeline.Timeline;
import net.petersil98.thresh.model.game.spectator.ActiveGame;
import net.petersil98.thresh.model.summoner.Summoner;
import net.petersil98.thresh.util.EncryptionUtil;
import net.petersil98.thresh.util.Loader;
import net.petersil98.thresh.util.Util;
import net.petersil98.thresh.util.settings.Language;
import net.petersil98.thresh.util.settings.ServerConfig;
import net.petersil98.thresh.util.settings.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Thresh {

    public static final Logger LOGGER = LogManager.getLogger("THRESH");

    public static void main(String[] args) {
        Settings.setAPIKey(() -> EncryptionUtil.encrypt("<API_KEY>"));
        Settings.setDecryptor(EncryptionUtil::decrypt);
        Settings.setServerConfig(ServerConfig.EUW_CONFIG);
        Settings.setLanguage(Language.EN_US);
        Loader.init();
        Summoner me = Summoner.getSummonerByName("faker");
        List<MatchDetails> matchHistory = MatchDetails.getMatchHistory(me, Map.of());
        Timeline timeline = matchHistory.get(1).getTimeline();
        matchHistory.stream().map(MatchDetails::getQueueType).forEach(System.out::println);
        System.out.println(me.getPuuid());
        System.out.println(me.getTotalMasteryPoints());
        System.out.println(me.getTotalMasteryPointsCombined());
        System.out.println(me.getChampionMasteries());
        System.out.println(me.getRankFlex5v5());
        System.out.println(me.getAccount());
        System.out.println(me);
        MatchDetails details = MatchDetails.getMatchDetails("EUW1_987654321");
        System.out.println(Util.getChallengeIconURL(Challenges.getChallenges().stream().filter(challenge -> !challenge.getLevelToIconPath().isEmpty()).findAny().get(), RankedTier.BRONZE));
        ActiveGame game = ActiveGame.ofSummoner(Summoner.getSummonerByName("madlife"));
        System.out.println(game.getSpectatorCommandWindows("C:\\"));
        System.out.println(Util.getProfileIconURL(me.getProfileIcon()));
        System.out.println(Util.getChampionIconURL(Champions.getChampionByName("Thresh")));
        System.out.println(Util.getRuneIconURL(RuneStyles.getRuneStyles().get(2)));
        System.out.println(Util.getRuneIconURL(RuneStats.getRuneStats().get(1)));
        System.out.println(RuneStyles.getRuneStyles());
        System.out.println(Runes.getRunes());
        System.out.println(RuneStats.getRuneStats());
        Items.getItem(1001).getInto().forEach(System.out::println);
        System.out.println(SummonerSpells.getSummonerSpell(14));
    }
}
