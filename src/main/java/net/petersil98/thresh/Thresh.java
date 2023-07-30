package net.petersil98.thresh;

import net.petersil98.core.util.EncryptionUtil;
import net.petersil98.core.util.Loader;
import net.petersil98.core.util.settings.Language;
import net.petersil98.core.util.settings.Settings;
import net.petersil98.stcommons.constants.*;
import net.petersil98.stcommons.model.Summoner;
import net.petersil98.stcommons.model.league.League;
import net.petersil98.stcommons.model.league.RankEntry;
import net.petersil98.thresh.model.ChampionMasteries;
import net.petersil98.thresh.model.LoLRanked;
import net.petersil98.thresh.model.PlayerRanks;
import net.petersil98.thresh.model.match.MatchDetails;
import net.petersil98.thresh.model.spectator.ActiveGame;
import net.petersil98.thresh.util.LoLLoader;
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
        Summoner me = Summoner.getSummonerByName("hiiamcool34", LeaguePlatform.EUW);
        List<MatchDetails> matchHistory = MatchDetails.getMatchHistory(me.getPuuid(), LeagueRegion.EUROPE, Map.of());
        matchHistory.stream().map(MatchDetails::getQueueType).forEach(System.out::println);
        ChampionMasteries masteries = ChampionMasteries.getChampionMasteriesOfSummoner(me.getId(), LeaguePlatform.EUW);
        System.out.println(masteries.getTotalMasteryPoints());
        System.out.println(masteries.getTotalMasteryPointsCombined());
        System.out.println(masteries.getChampionMasteries());
        PlayerRanks ranked = LoLRanked.getLoLRanksOfSummoner(me.getId(), LeaguePlatform.EUW);
        System.out.println(ranked.getRankFlex5v5());
        System.out.println(ranked.getRankSoloDuo());
        League league = LoLRanked.getLeagueById(ranked.getRankSoloDuo().getLeagueId(), LeaguePlatform.EUW);
        League challengers = LoLRanked.getChallengerLeague(RankedQueue.SOLO_DUO, LeaguePlatform.EUW);
        List<RankEntry> leagueEntries = LoLRanked.getRankEntries(RankedDivision.I, RankedTier.BRONZE, RankedQueue.FLEX, LeaguePlatform.EUW);
        MatchDetails details = MatchDetails.getMatchDetails("EUW1_987654321", LeagueRegion.EUROPE);
        ActiveGame game = ActiveGame.ofSummoner(Summoner.getSummonerByName("prinzessin", LeaguePlatform.EUW).getId(), LeaguePlatform.EUW);
        System.out.println(game.getSpectatorCommandWindows("C:\\"));
    }
}
