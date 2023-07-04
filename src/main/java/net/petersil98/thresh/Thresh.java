package net.petersil98.thresh;

import net.petersil98.core.constant.*;
import net.petersil98.core.util.EncryptionUtil;
import net.petersil98.core.util.Loader;
import net.petersil98.core.util.settings.Language;
import net.petersil98.core.util.settings.Settings;
import net.petersil98.stcommons.model.Summoner;
import net.petersil98.stcommons.model.league.League;
import net.petersil98.stcommons.model.league.RankEntry;
import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.model.ChampionMasteries;
import net.petersil98.thresh.model.LoLRanked;
import net.petersil98.thresh.model.match.MatchDetails;
import net.petersil98.thresh.model.spectator.ActiveGame;
import net.petersil98.thresh.util.LoLLoader;
import net.petersil98.thresh.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Thresh {

    public static final Logger LOGGER = LogManager.getLogger("THRESH");

    public static void main(String[] args) {
        Settings.setAPIKey(() -> EncryptionUtil.encrypt(System.getenv("API_KEY")));
        Settings.setDecryptor(EncryptionUtil::decrypt);
        Settings.setLanguage(Language.EN_US);
        Settings.useCache(true);
        Loader.addLoader(new LoLLoader());
        Loader.init();
        Summoner me = Summoner.getSummonerByName("Petersil98", Platform.EUW);
        List<MatchDetails> matchHistory = MatchDetails.getMatchHistory(me.getPuuid(), Region.EUROPE, Map.of());
        matchHistory.stream().map(MatchDetails::getQueueType).forEach(System.out::println);
        ChampionMasteries masteries = ChampionMasteries.getChampionMasteriesOfSummoner(me.getId(), Platform.EUW);
        System.out.println(masteries.getTotalMasteryPoints());
        System.out.println(masteries.getTotalMasteryPointsCombined());
        System.out.println(masteries.getChampionMasteries());
        LoLRanked ranked = LoLRanked.getLoLRanksOfSummoner(me.getId(), Platform.EUW);
        System.out.println(ranked.getRankFlex5v5());
        System.out.println(ranked.getRankSoloDuo());
        League league = LoLRanked.getLeagueById(ranked.getRankSoloDuo().getLeagueId(), Platform.EUW);
        League challengers = LoLRanked.getChallengerLeague(RankedQueue.SOLO_DUO, Platform.EUW);
        List<RankEntry> leagueEntries = LoLRanked.getRankEntries(RankedDivision.I, RankedTier.BRONZE, RankedQueue.FLEX, Platform.EUW);
        MatchDetails details = MatchDetails.getMatchDetails("EUW1_987654321", Region.EUROPE);
        System.out.println(Util.getChallengeIconURL(Challenges.getChallenges().stream().filter(challenge -> !challenge.getLevelToIconPath().isEmpty()).findAny().get(), RankedTier.BRONZE));
        ActiveGame game = ActiveGame.ofSummoner(Summoner.getSummonerByName("katikaze", Platform.EUW).getId(), Platform.EUW);
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
