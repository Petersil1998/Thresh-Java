package net.petersil98.thresh.model.game.match.participant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.data.SummonerSpell;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.Rune;
import net.petersil98.thresh.data.rune.RuneStat;
import net.petersil98.thresh.data.rune.RuneStyle;
import net.petersil98.thresh.model.summoner.Summoner;
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
    private final Rune keyStone;
    private final RuneExtraData keyStoneExtraData;
    private final Rune primarySub1;
    private final RuneExtraData primarySub1ExtraData;
    private final Rune primarySub2;
    private final RuneExtraData primarySub2ExtraData;
    private final Rune primarySub3;
    private final RuneExtraData primarySub3ExtraData;
    private final Rune secondarySub1;
    private final RuneExtraData secondarySub1ExtraData;
    private final Rune secondarySub2;
    private final RuneExtraData secondarySub2ExtraData;
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
    private Summoner summoner;
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

    public MatchParticipant(int participantId, Champion champion, SummonerSpell summonerSpell1, SummonerSpell summonerSpell2, Item item0, Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, int kills, int deaths, int assists, int largestKillingSpree, int largestMultiKill, int killingSprees, int longestTimeSpentLiving, int doubleKills, int tripleKills, int quadraKills, int pentaKills, int unrealKills, int totalDamageDealt, int magicDamageDealt, int physicalDamageDealt, int trueDamageDealt, int largestCriticalStrike, int totalDamageDealtToChampions, int magicDamageDealtToChampions, int physicalDamageDealtToChampions, int trueDamageDealtToChampions, int totalHeal, int totalUnitsHealed, int damageSelfMitigated, int damageDealtToObjectives, int damageDealtToTurrets, int visionScore, int timeCCingOthers, int totalDamageTaken, int magicDamageTaken, int physicalDamageTaken, int trueDamageTaken, int goldEarned, int goldSpent, int turretKills, int inhibitorKills, int totalMinionsKilled, int neutralMinionsKilled, int totalTimeCCDealt, int champLevel, int visionWardsBoughtInGame, int sightWardsBoughtInGame, int wardsPlaced, int wardsKilled, int firstBloodKill, int firstBloodAssist, int firstTowerKill, int firstTowerAssist, Rune keyStone, RuneExtraData keyStoneExtraData, Rune primarySub1, RuneExtraData primarySub1ExtraData, Rune primarySub2, RuneExtraData primarySub2ExtraData, Rune primarySub3, RuneExtraData primarySub3ExtraData, Rune secondarySub1, RuneExtraData secondarySub1ExtraData, Rune secondarySub2, RuneExtraData secondarySub2ExtraData, RuneStyle primaryStyle, RuneStyle secondaryStyle, RuneStat statDefense, RuneStat statFlex, RuneStat statOffense, String role, String lane, int baronKills, int bountyLevel, int champExperience, int championTransform, int consumablesPurchased, int damageDealtToBuildings, int dragonKills, boolean gameEndedInEarlySurrender, boolean gameEndedInSurrender, String individualPosition, int inhibitorTakedowns, int inhibitorsLost, int itemsPurchased, int nexusKills, int nexusLost, int nexusTakedowns, int objectivesStolen, int objectivesStolenAssists, String summonerId, int spell1Casts, int spell2Casts, int spell3Casts, int spell4Casts, int summonerSpell1Casts, int summonerSpell2Casts, boolean teamEarlySurrendered, int teamId, String teamPosition, int timePlayed, int totalDamageShieldedOnTeammates, int totalHealsOnTeammates, int totalTimeSpentDead, int turretTakedowns, int turretsLost, boolean won, PingStats pingStats, int detectorWardsPlaced, boolean eligibleForProgression, ChallengeStats challenges) {
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
        this.keyStone = keyStone;
        this.keyStoneExtraData = keyStoneExtraData;
        this.primarySub1 = primarySub1;
        this.primarySub1ExtraData = primarySub1ExtraData;
        this.primarySub2 = primarySub2;
        this.primarySub2ExtraData = primarySub2ExtraData;
        this.primarySub3 = primarySub3;
        this.primarySub3ExtraData = primarySub3ExtraData;
        this.secondarySub1 = secondarySub1;
        this.secondarySub1ExtraData = secondarySub1ExtraData;
        this.secondarySub2 = secondarySub2;
        this.secondarySub2ExtraData = secondarySub2ExtraData;
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
    }

    public int getParticipantId() {
        return this.participantId;
    }

    public Champion getChampion() {
        return this.champion;
    }

    public SummonerSpell getSummonerSpell1() {
        return this.summonerSpell1;
    }

    public SummonerSpell getSummonerSpell2() {
        return this.summonerSpell2;
    }

    public Item getFirstItem() {
        return this.item0;
    }

    public Item getSecondItem() {
        return this.item1;
    }

    public Item getThirdItem() {
        return this.item2;
    }

    public Item getForthItem() {
        return this.item3;
    }

    public Item getFifthItem() {
        return this.item4;
    }

    public Item getSixthItem() {
        return this.item5;
    }

    public Item getSeventhItem() {
        return this.item6;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public int getAssists() {
        return this.assists;
    }

    public int getLargestKillingSpree() {
        return this.largestKillingSpree;
    }

    public int getLargestMultiKill() {
        return this.largestMultiKill;
    }

    public int getKillingSprees() {
        return this.killingSprees;
    }

    public int getLongestTimeSpentLiving() {
        return this.longestTimeSpentLiving;
    }

    public int getDoubleKills() {
        return this.doubleKills;
    }

    public int getTripleKills() {
        return this.tripleKills;
    }

    public int getQuadraKills() {
        return this.quadraKills;
    }

    public int getPentaKills() {
        return this.pentaKills;
    }

    public int getUnrealKills() {
        return this.unrealKills;
    }

    public int getTotalDamageDealt() {
        return this.totalDamageDealt;
    }

    public int getMagicDamageDealt() {
        return this.magicDamageDealt;
    }

    public int getPhysicalDamageDealt() {
        return this.physicalDamageDealt;
    }

    public int getTrueDamageDealt() {
        return this.trueDamageDealt;
    }

    public int getLargestCriticalStrike() {
        return this.largestCriticalStrike;
    }

    public int getTotalDamageDealtToChampions() {
        return this.totalDamageDealtToChampions;
    }

    public int getMagicDamageDealtToChampions() {
        return this.magicDamageDealtToChampions;
    }

    public int getPhysicalDamageDealtToChampions() {
        return this.physicalDamageDealtToChampions;
    }

    public int getTrueDamageDealtToChampions() {
        return this.trueDamageDealtToChampions;
    }

    public int getTotalHeal() {
        return this.totalHeal;
    }

    public int getTotalUnitsHealed() {
        return this.totalUnitsHealed;
    }

    public int getDamageSelfMitigated() {
        return this.damageSelfMitigated;
    }

    public int getDamageDealtToObjectives() {
        return this.damageDealtToObjectives;
    }

    public int getDamageDealtToTurrets() {
        return this.damageDealtToTurrets;
    }

    public int getVisionScore() {
        return this.visionScore;
    }

    public int getTimeCCingOthers() {
        return this.timeCCingOthers;
    }

    public int getTotalDamageTaken() {
        return this.totalDamageTaken;
    }

    public int getMagicDamageTaken() {
        return this.magicDamageTaken;
    }

    public int getPhysicalDamageTaken() {
        return this.physicalDamageTaken;
    }

    public int getTrueDamageTaken() {
        return this.trueDamageTaken;
    }

    public int getGoldEarned() {
        return this.goldEarned;
    }

    public int getGoldSpent() {
        return this.goldSpent;
    }

    public int getTurretKills() {
        return this.turretKills;
    }

    public int getInhibitorKills() {
        return this.inhibitorKills;
    }

    public int getTotalMinionsKilled() {
        return this.totalMinionsKilled;
    }

    public int getNeutralMinionsKilled() {
        return this.neutralMinionsKilled;
    }

    public int getTotalTimeCCDealt() {
        return this.totalTimeCCDealt;
    }

    public int getChampLevel() {
        return this.champLevel;
    }

    public int getVisionWardsBoughtInGame() {
        return this.visionWardsBoughtInGame;
    }

    public int getSightWardsBoughtInGame() {
        return this.sightWardsBoughtInGame;
    }

    public int getWardsPlaced() {
        return this.wardsPlaced;
    }

    public int getWardsKilled() {
        return this.wardsKilled;
    }

    public int getFirstBloodKill() {
        return this.firstBloodKill;
    }

    public int getFirstBloodAssist() {
        return this.firstBloodAssist;
    }

    public int getFirstTowerKill() {
        return this.firstTowerKill;
    }

    public int getFirstTowerAssist() {
        return this.firstTowerAssist;
    }

    public Rune getKeyStone() {
        return this.keyStone;
    }

    public RuneExtraData getKeyStoneExtraData() {
        return this.keyStoneExtraData;
    }

    public Rune getPrimarySub1() {
        return this.primarySub1;
    }

    public RuneExtraData getPrimarySub1ExtraData() {
        return this.primarySub1ExtraData;
    }

    public Rune getPrimarySub2() {
        return this.primarySub2;
    }

    public RuneExtraData getPrimarySub2ExtraData() {
        return this.primarySub2ExtraData;
    }

    public Rune getPrimarySub3() {
        return this.primarySub3;
    }

    public RuneExtraData getPrimarySub3ExtraData() {
        return this.primarySub3ExtraData;
    }

    public Rune getSecondarySub1() {
        return this.secondarySub1;
    }

    public RuneExtraData getSecondarySub1ExtraData() {
        return this.secondarySub1ExtraData;
    }

    public Rune getSecondarySub2() {
        return this.secondarySub2;
    }

    public RuneExtraData getSecondarySub2ExtraData() {
        return this.secondarySub2ExtraData;
    }

    public RuneStyle getPrimaryStyle() {
        return this.primaryStyle;
    }

    public RuneStyle getSecondaryStyle() {
        return this.secondaryStyle;
    }

    public RuneStat getStatDefense() {
        return this.statDefense;
    }

    public RuneStat getStatFlex() {
        return this.statFlex;
    }

    public RuneStat getStatOffense() {
        return this.statOffense;
    }

    public String getRole() {
        return this.role;
    }

    public String getLane() {
        return this.lane;
    }

    public int getBaronKills() {
        return this.baronKills;
    }

    public int getBountyLevel() {
        return this.bountyLevel;
    }

    public int getChampExperience() {
        return this.champExperience;
    }

    public int getChampionTransform() {
        return this.championTransform;
    }

    public int getConsumablesPurchased() {
        return this.consumablesPurchased;
    }

    public int getDamageDealtToBuildings() {
        return this.damageDealtToBuildings;
    }

    public int getDragonKills() {
        return this.dragonKills;
    }

    public boolean isGameEndedInEarlySurrender() {
        return this.gameEndedInEarlySurrender;
    }

    public boolean isGameEndedInSurrender() {
        return this.gameEndedInSurrender;
    }

    public String getIndividualPosition() {
        return this.individualPosition;
    }

    public int getInhibitorTakedowns() {
        return this.inhibitorTakedowns;
    }

    public int getInhibitorsLost() {
        return this.inhibitorsLost;
    }

    public int getItemsPurchased() {
        return this.itemsPurchased;
    }

    public int getNexusKills() {
        return this.nexusKills;
    }

    public int getNexusLost() {
        return this.nexusLost;
    }

    public int getNexusTakedowns() {
        return this.nexusTakedowns;
    }

    public int getObjectivesStolen() {
        return this.objectivesStolen;
    }

    public int getObjectivesStolenAssists() {
        return this.objectivesStolenAssists;
    }

    public String getSummonerId() {
        return this.summonerId;
    }

    public Summoner getSummoner() {
        if (this.summoner == null) this.summoner = Summoner.getSummonerByID(this.summonerId);
        return this.summoner;
    }

    public int getSpell1Casts() {
        return this.spell1Casts;
    }

    public int getSpell2Casts() {
        return this.spell2Casts;
    }

    public int getSpell3Casts() {
        return this.spell3Casts;
    }

    public int getSpell4Casts() {
        return this.spell4Casts;
    }

    public int getSummonerSpell1Casts() {
        return this.summonerSpell1Casts;
    }

    public int getSummonerSpell2Casts() {
        return this.summonerSpell2Casts;
    }

    public boolean isTeamEarlySurrendered() {
        return this.teamEarlySurrendered;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public String getTeamPosition() {
        return this.teamPosition;
    }

    public int getTimePlayed() {
        return this.timePlayed;
    }

    public int getTotalDamageShieldedOnTeammates() {
        return this.totalDamageShieldedOnTeammates;
    }

    public int getTotalHealsOnTeammates() {
        return this.totalHealsOnTeammates;
    }

    public int getTotalTimeSpentDead() {
        return this.totalTimeSpentDead;
    }

    public int getTurretTakedowns() {
        return this.turretTakedowns;
    }

    public int getTurretsLost() {
        return this.turretsLost;
    }

    public boolean isWon() {
        return this.won;
    }

    public PingStats getPings() {
        return this.pingStats;
    }

    public int getDetectorWardsPlaced() {
        return this.detectorWardsPlaced;
    }

    public boolean isEligibleForProgression() {
        return this.eligibleForProgression;
    }

    public ChallengeStats getChallenges() {
        return this.challenges;
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
