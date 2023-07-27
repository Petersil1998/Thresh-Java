package net.petersil98.thresh.model.match.participant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.ArenaAugment;
import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.data.SummonerSpell;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.RuneStat;
import net.petersil98.thresh.data.rune.RuneStyle;
import net.petersil98.thresh.model.Deserializers;

import java.util.Objects;

@JsonDeserialize(using = Deserializers.MatchParticipantDeserializer.class)
public class MatchParticipant {
    private final int participantId;
    private final Champion champion;
    private final SummonerSpell summonerSpell1;
    private final SummonerSpell summonerSpell2;
    private final Item item0;
    private final Item item1;
    private final Item item2;
    private final Item item3;
    private final Item item4;
    private final Item item5;
    private final Item item6;
    private final int kills;
    private final int deaths;
    private final int assists;
    private final int largestKillingSpree;
    private final int largestMultiKill;
    private final int killingSprees;
    private final int longestTimeSpentLiving;
    private final int doubleKills;
    private final int tripleKills;
    private final int quadraKills;
    private final int pentaKills;
    private final int unrealKills;
    private final int totalDamageDealt;
    private final int magicDamageDealt;
    private final int physicalDamageDealt;
    private final int trueDamageDealt;
    private final int largestCriticalStrike;
    private final int totalDamageDealtToChampions;
    private final int magicDamageDealtToChampions;
    private final int physicalDamageDealtToChampions;
    private final int trueDamageDealtToChampions;
    private final int totalHeal;
    private final int totalUnitsHealed;
    private final int damageSelfMitigated;
    private final int damageDealtToObjectives;
    private final int damageDealtToTurrets;
    private final int visionScore;
    private final int timeCCingOthers;
    private final int totalDamageTaken;
    private final int magicDamageTaken;
    private final int physicalDamageTaken;
    private final int trueDamageTaken;
    private final int goldEarned;
    private final int goldSpent;
    private final int turretKills;
    private final int inhibitorKills;
    private final int totalMinionsKilled;
    private final int neutralMinionsKilled;
    private final int totalTimeCCDealt;
    private final int champLevel;
    private final int visionWardsBoughtInGame;
    private final int sightWardsBoughtInGame;
    private final int wardsPlaced;
    private final int wardsKilled;
    private final int firstBloodKill;
    private final int firstBloodAssist;
    private final int firstTowerKill;
    private final int firstTowerAssist;
    private final RuneData keyStoneData;
    private final RuneData primarySub1Data;
    private final RuneData primarySub2Data;
    private final RuneData primarySub3Data;
    private final RuneData secondarySub1Data;
    private final RuneData secondarySub2Data;
    private final RuneStyle primaryStyle;
    private final RuneStyle secondaryStyle;
    private final RuneStat statDefense;
    private final RuneStat statFlex;
    private final RuneStat statOffense;
    private final String role;
    private final String lane;
    private final int baronKills;
    private final int bountyLevel;
    private final int champExperience;
    private final int championTransform;
    private final int consumablesPurchased;
    private final int damageDealtToBuildings;
    private final int dragonKills;
    private final boolean gameEndedInEarlySurrender;
    private final boolean gameEndedInSurrender;
    private final String individualPosition;
    private final int inhibitorTakedowns;
    private final int inhibitorsLost;
    private final int itemsPurchased;
    private final int nexusKills;
    private final int nexusLost;
    private final int nexusTakedowns;
    private final int objectivesStolen;
    private final int objectivesStolenAssists;
    private final String summonerId;
    private final int spell1Casts;
    private final int spell2Casts;
    private final int spell3Casts;
    private final int spell4Casts;
    private final int summonerSpell1Casts;
    private final int summonerSpell2Casts;
    private final boolean teamEarlySurrendered;
    private final int teamId;
    private final String teamPosition;
    private final int timePlayed;
    private final int totalDamageShieldedOnTeammates;
    private final int totalHealsOnTeammates;
    private final int totalTimeSpentDead;
    private final int turretTakedowns;
    private final int turretsLost;
    private final boolean won;
    private final PingStats pingStats;
    private final int detectorWardsPlaced;
    private final boolean eligibleForProgression;
    private final ChallengeStats challenges;

    // ARENA DATA
    private final int placement;
    private final ArenaAugment playerAugment1;
    private final ArenaAugment playerAugment2;
    private final ArenaAugment playerAugment3;
    private final ArenaAugment playerAugment4;
    private final int playerSubteamId;
    private final int subteamPlacement;

    public MatchParticipant(int participantId, Champion champion, SummonerSpell summonerSpell1, SummonerSpell summonerSpell2, Item item0, Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, int kills, int deaths, int assists, int largestKillingSpree, int largestMultiKill, int killingSprees, int longestTimeSpentLiving, int doubleKills, int tripleKills, int quadraKills, int pentaKills, int unrealKills, int totalDamageDealt, int magicDamageDealt, int physicalDamageDealt, int trueDamageDealt, int largestCriticalStrike, int totalDamageDealtToChampions, int magicDamageDealtToChampions, int physicalDamageDealtToChampions, int trueDamageDealtToChampions, int totalHeal, int totalUnitsHealed, int damageSelfMitigated, int damageDealtToObjectives, int damageDealtToTurrets, int visionScore, int timeCCingOthers, int totalDamageTaken, int magicDamageTaken, int physicalDamageTaken, int trueDamageTaken, int goldEarned, int goldSpent, int turretKills, int inhibitorKills, int totalMinionsKilled, int neutralMinionsKilled, int totalTimeCCDealt, int champLevel, int visionWardsBoughtInGame, int sightWardsBoughtInGame, int wardsPlaced, int wardsKilled, int firstBloodKill, int firstBloodAssist, int firstTowerKill, int firstTowerAssist, RuneData keyStoneData, RuneData primarySub1Data, RuneData primarySub2Data, RuneData primarySub3Data, RuneData secondarySub1Data, RuneData secondarySub2Data, RuneStyle primaryStyle, RuneStyle secondaryStyle, RuneStat statDefense, RuneStat statFlex, RuneStat statOffense, String role, String lane, int baronKills, int bountyLevel, int champExperience, int championTransform, int consumablesPurchased, int damageDealtToBuildings, int dragonKills, boolean gameEndedInEarlySurrender, boolean gameEndedInSurrender, String individualPosition, int inhibitorTakedowns, int inhibitorsLost, int itemsPurchased, int nexusKills, int nexusLost, int nexusTakedowns, int objectivesStolen, int objectivesStolenAssists, String summonerId, int spell1Casts, int spell2Casts, int spell3Casts, int spell4Casts, int summonerSpell1Casts, int summonerSpell2Casts, boolean teamEarlySurrendered, int teamId, String teamPosition, int timePlayed, int totalDamageShieldedOnTeammates, int totalHealsOnTeammates, int totalTimeSpentDead, int turretTakedowns, int turretsLost, boolean won, PingStats pingStats, int detectorWardsPlaced, boolean eligibleForProgression, ChallengeStats challenges, int placement, ArenaAugment playerAugment1, ArenaAugment playerAugment2, ArenaAugment playerAugment3, ArenaAugment playerAugment4, int playerSubteamId, int subteamPlacement) {
        this.participantId = participantId;
        this.champion = champion;
        this.summonerSpell1 = summonerSpell1;
        this.summonerSpell2 = summonerSpell2;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.largestKillingSpree = largestKillingSpree;
        this.largestMultiKill = largestMultiKill;
        this.killingSprees = killingSprees;
        this.longestTimeSpentLiving = longestTimeSpentLiving;
        this.doubleKills = doubleKills;
        this.tripleKills = tripleKills;
        this.quadraKills = quadraKills;
        this.pentaKills = pentaKills;
        this.unrealKills = unrealKills;
        this.totalDamageDealt = totalDamageDealt;
        this.magicDamageDealt = magicDamageDealt;
        this.physicalDamageDealt = physicalDamageDealt;
        this.trueDamageDealt = trueDamageDealt;
        this.largestCriticalStrike = largestCriticalStrike;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.totalHeal = totalHeal;
        this.totalUnitsHealed = totalUnitsHealed;
        this.damageSelfMitigated = damageSelfMitigated;
        this.damageDealtToObjectives = damageDealtToObjectives;
        this.damageDealtToTurrets = damageDealtToTurrets;
        this.visionScore = visionScore;
        this.timeCCingOthers = timeCCingOthers;
        this.totalDamageTaken = totalDamageTaken;
        this.magicDamageTaken = magicDamageTaken;
        this.physicalDamageTaken = physicalDamageTaken;
        this.trueDamageTaken = trueDamageTaken;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.turretKills = turretKills;
        this.inhibitorKills = inhibitorKills;
        this.totalMinionsKilled = totalMinionsKilled;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.totalTimeCCDealt = totalTimeCCDealt;
        this.champLevel = champLevel;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.wardsPlaced = wardsPlaced;
        this.wardsKilled = wardsKilled;
        this.firstBloodKill = firstBloodKill;
        this.firstBloodAssist = firstBloodAssist;
        this.firstTowerKill = firstTowerKill;
        this.firstTowerAssist = firstTowerAssist;
        this.keyStoneData = keyStoneData;
        this.primarySub1Data = primarySub1Data;
        this.primarySub2Data = primarySub2Data;
        this.primarySub3Data = primarySub3Data;
        this.secondarySub1Data = secondarySub1Data;
        this.secondarySub2Data = secondarySub2Data;
        this.primaryStyle = primaryStyle;
        this.secondaryStyle = secondaryStyle;
        this.statDefense = statDefense;
        this.statFlex = statFlex;
        this.statOffense = statOffense;
        this.role = role;
        this.lane = lane;
        this.baronKills = baronKills;
        this.bountyLevel = bountyLevel;
        this.champExperience = champExperience;
        this.championTransform = championTransform;
        this.consumablesPurchased = consumablesPurchased;
        this.damageDealtToBuildings = damageDealtToBuildings;
        this.dragonKills = dragonKills;
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.individualPosition = individualPosition;
        this.inhibitorTakedowns = inhibitorTakedowns;
        this.inhibitorsLost = inhibitorsLost;
        this.itemsPurchased = itemsPurchased;
        this.nexusKills = nexusKills;
        this.nexusLost = nexusLost;
        this.nexusTakedowns = nexusTakedowns;
        this.objectivesStolen = objectivesStolen;
        this.objectivesStolenAssists = objectivesStolenAssists;
        this.summonerId = summonerId;
        this.spell1Casts = spell1Casts;
        this.spell2Casts = spell2Casts;
        this.spell3Casts = spell3Casts;
        this.spell4Casts = spell4Casts;
        this.summonerSpell1Casts = summonerSpell1Casts;
        this.summonerSpell2Casts = summonerSpell2Casts;
        this.teamEarlySurrendered = teamEarlySurrendered;
        this.teamId = teamId;
        this.teamPosition = teamPosition;
        this.timePlayed = timePlayed;
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
        this.totalHealsOnTeammates = totalHealsOnTeammates;
        this.totalTimeSpentDead = totalTimeSpentDead;
        this.turretTakedowns = turretTakedowns;
        this.turretsLost = turretsLost;
        this.won = won;
        this.pingStats = pingStats;
        this.detectorWardsPlaced = detectorWardsPlaced;
        this.eligibleForProgression = eligibleForProgression;
        this.challenges = challenges;
        this.placement = placement;
        this.playerAugment1 = playerAugment1;
        this.playerAugment2 = playerAugment2;
        this.playerAugment3 = playerAugment3;
        this.playerAugment4 = playerAugment4;
        this.playerSubteamId = playerSubteamId;
        this.subteamPlacement = subteamPlacement;
    }

    public int getParticipantId() {
        return participantId;
    }

    public Champion getChampion() {
        return champion;
    }

    public SummonerSpell getSummonerSpell1() {
        return summonerSpell1;
    }

    public SummonerSpell getSummonerSpell2() {
        return summonerSpell2;
    }

    public Item getItem0() {
        return item0;
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    public Item getItem3() {
        return item3;
    }

    public Item getItem4() {
        return item4;
    }

    public Item getItem5() {
        return item5;
    }

    public Item getItem6() {
        return item6;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public int getLargestMultiKill() {
        return largestMultiKill;
    }

    public int getKillingSprees() {
        return killingSprees;
    }

    public int getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public int getTripleKills() {
        return tripleKills;
    }

    public int getQuadraKills() {
        return quadraKills;
    }

    public int getPentaKills() {
        return pentaKills;
    }

    public int getUnrealKills() {
        return unrealKills;
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public int getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public int getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public int getTrueDamageDealt() {
        return trueDamageDealt;
    }

    public int getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    public int getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public int getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public int getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    public int getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public int getTotalHeal() {
        return totalHeal;
    }

    public int getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    public int getDamageSelfMitigated() {
        return damageSelfMitigated;
    }

    public int getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }

    public int getDamageDealtToTurrets() {
        return damageDealtToTurrets;
    }

    public int getVisionScore() {
        return visionScore;
    }

    public int getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public int getMagicDamageTaken() {
        return magicDamageTaken;
    }

    public int getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public int getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public int getTurretKills() {
        return turretKills;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    public int getTotalTimeCCDealt() {
        return totalTimeCCDealt;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public int getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public int getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public int getWardsKilled() {
        return wardsKilled;
    }

    public int getFirstBloodKill() {
        return firstBloodKill;
    }

    public int getFirstBloodAssist() {
        return firstBloodAssist;
    }

    public int getFirstTowerKill() {
        return firstTowerKill;
    }

    public int getFirstTowerAssist() {
        return firstTowerAssist;
    }

    public RuneData getKeyStoneData() {
        return keyStoneData;
    }

    public RuneData getPrimarySub1Data() {
        return primarySub1Data;
    }

    public RuneData getPrimarySub2Data() {
        return primarySub2Data;
    }

    public RuneData getPrimarySub3Data() {
        return primarySub3Data;
    }

    public RuneData getSecondarySub1Data() {
        return secondarySub1Data;
    }

    public RuneData getSecondarySub2Data() {
        return secondarySub2Data;
    }

    public RuneStyle getPrimaryStyle() {
        return primaryStyle;
    }

    public RuneStyle getSecondaryStyle() {
        return secondaryStyle;
    }

    public RuneStat getStatDefense() {
        return statDefense;
    }

    public RuneStat getStatFlex() {
        return statFlex;
    }

    public RuneStat getStatOffense() {
        return statOffense;
    }

    public String getRole() {
        return role;
    }

    public String getLane() {
        return lane;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public int getBountyLevel() {
        return bountyLevel;
    }

    public int getChampExperience() {
        return champExperience;
    }

    public int getChampionTransform() {
        return championTransform;
    }

    public int getConsumablesPurchased() {
        return consumablesPurchased;
    }

    public int getDamageDealtToBuildings() {
        return damageDealtToBuildings;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public boolean isGameEndedInEarlySurrender() {
        return gameEndedInEarlySurrender;
    }

    public boolean isGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public int getInhibitorTakedowns() {
        return inhibitorTakedowns;
    }

    public int getInhibitorsLost() {
        return inhibitorsLost;
    }

    public int getItemsPurchased() {
        return itemsPurchased;
    }

    public int getNexusKills() {
        return nexusKills;
    }

    public int getNexusLost() {
        return nexusLost;
    }

    public int getNexusTakedowns() {
        return nexusTakedowns;
    }

    public int getObjectivesStolen() {
        return objectivesStolen;
    }

    public int getObjectivesStolenAssists() {
        return objectivesStolenAssists;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public int getSpell1Casts() {
        return spell1Casts;
    }

    public int getSpell2Casts() {
        return spell2Casts;
    }

    public int getSpell3Casts() {
        return spell3Casts;
    }

    public int getSpell4Casts() {
        return spell4Casts;
    }

    public int getSummonerSpell1Casts() {
        return summonerSpell1Casts;
    }

    public int getSummonerSpell2Casts() {
        return summonerSpell2Casts;
    }

    public boolean isTeamEarlySurrendered() {
        return teamEarlySurrendered;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public int getTotalDamageShieldedOnTeammates() {
        return totalDamageShieldedOnTeammates;
    }

    public int getTotalHealsOnTeammates() {
        return totalHealsOnTeammates;
    }

    public int getTotalTimeSpentDead() {
        return totalTimeSpentDead;
    }

    public int getTurretTakedowns() {
        return turretTakedowns;
    }

    public int getTurretsLost() {
        return turretsLost;
    }

    public boolean isWon() {
        return won;
    }

    public PingStats getPingStats() {
        return pingStats;
    }

    public int getDetectorWardsPlaced() {
        return detectorWardsPlaced;
    }

    public boolean isEligibleForProgression() {
        return eligibleForProgression;
    }

    public ChallengeStats getChallenges() {
        return challenges;
    }

    public int getPlacement() {
        return placement;
    }

    public ArenaAugment getPlayerAugment1() {
        return playerAugment1;
    }

    public ArenaAugment getPlayerAugment2() {
        return playerAugment2;
    }

    public ArenaAugment getPlayerAugment3() {
        return playerAugment3;
    }

    public ArenaAugment getPlayerAugment4() {
        return playerAugment4;
    }

    public int getPlayerSubteamId() {
        return playerSubteamId;
    }

    public int getSubteamPlacement() {
        return subteamPlacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchParticipant that = (MatchParticipant) o;
        return participantId == that.participantId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantId);
    }
}
