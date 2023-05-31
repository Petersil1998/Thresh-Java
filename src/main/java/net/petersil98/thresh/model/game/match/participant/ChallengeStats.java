package net.petersil98.thresh.model.game.match.participant;

import net.petersil98.thresh.data.Item;

public class ChallengeStats {

    private final int twelveAssistStreakCount;
    private final int abilityUses;
    private final int acesBefore15Minutes;
    private final int alliedJungleMonsterKills;
    private final int baronBuffGoldAdvantageOverThreshold;
    private final int baronTakedowns;
    private final int blastConeOppositeOpponentCount;
    private final int bountyGold;
    private final int buffsStolen;
    private final int completeSupportQuestInTime;
    private final double controlWardTimeCoverageInRiverOrEnemyHalf;
    private final int controlWardsPlaced;
    private final double damagePerMinute;
    private final double damageTakenOnTeamPercentage;
    private final int dancedWithRiftHerald;
    private final int deathsByEnemyChamps;
    private final int dodgeSkillShotsSmallWindow;
    private final int doubleAces;
    private final int dragonTakedowns;
    private final double earliestBaron;
    private final double earliestDragonTakedown;
    private final double earliestElderDragon;
    private final int earlyLaningPhaseGoldExpAdvantage;
    private final double effectiveHealAndShielding;
    private final int elderDragonKillsWithOpposingSoul;
    private final int elderDragonMultikills;
    private final int enemyChampionImmobilizations;
    private final int enemyJungleMonsterKills;
    private final int epicMonsterKillsNearEnemyJungler;
    private final int epicMonsterKillsWithin30SecondsOfSpawn;
    private final int epicMonsterSteals;
    private final int epicMonsterStolenWithoutSmite;
    private final double fasterSupportQuestCompletion;
    private final double fastestLegendary;
    private final double firstTurretKilled;
    private final double firstTurretKilledTime;
    private final int flawlessAces;
    private final int fullTeamTakedown;
    private final double gameLength;
    private final int getTakedownsInAllLanesEarlyJungleAsLaner;
    private final double goldPerMinute;
    private final boolean hadAfkTeammate;
    private final boolean hadOpenNexus;
    private final int highestChampionDamage;
    private final int highestCrowdControlScore;
    private final int highestWardKills;
    private final int immobilizeAndKillWithAlly;
    private final int initialBuffCount;
    private final int initialCrabCount;
    private final double jungleCsBefore10Minutes;
    private final int junglerKillsEarlyJungle;
    private final int junglerTakedownsNearDamagedEpicMonster;
    private final double kda;
    private final int killAfterHiddenWithAlly;
    private final int killedChampTookFullTeamDamageSurvived;
    private final int killingSprees;
    private final double killParticipation;
    private final int killsNearEnemyTurret;
    private final int killsOnLanersEarlyJungleAsJungler;
    private final int killsOnOtherLanesEarlyJungleAsLaner;
    private final int killsOnRecentlyHealedByAramPack;
    private final int killsUnderOwnTurret;
    private final int killsWithHelpFromEpicMonster;
    private final int knockEnemyIntoTeamAndKill;
    private final int kTurretsDestroyedBeforePlatesFall;
    private final int landSkillShotsEarlyGame;
    private final int laneMinionsFirst10Minutes;
    private final int laningPhaseGoldExpAdvantage;
    private final int legendaryCount;
    private final boolean lostAnInhibitor;
    private final int maxCsAdvantageOnLaneOpponent;
    private final int maxKillDeficit;
    private final int maxLevelLeadLaneOpponent;
    private final boolean mejaisFullStackInTime;
    private final double moreEnemyJungleThanOpponent;
    private final int mostWardsDestroyedOneSweeper;
    private final int multiKillOneSpell;
    private final int multikills;
    private final int multikillsAfterAggressiveFlash;
    private final int multiTurretRiftHeraldCount;
    private final Item mythicItemUsed;
    private final int outerTurretExecutesBefore10Minutes;
    private final int outnumberedKills;
    private final int outnumberedNexusKill;
    private final int perfectDragonSoulsTaken;
    private final int perfectGame;
    private final int pickKillWithAlly;
    private final int playedChampSelectPosition;
    private final int poroExplosions;
    private final int quickCleanse;
    private final int quickFirstTurret;
    private final int quickSoloKills;
    private final int riftHeraldTakedowns;
    private final int saveAllyFromDeath;
    private final int scuttleCrabKills;
    private final double shortestTimeToAceFromFirstTakedown;
    private final int skillshotsDodged;
    private final int skillshotsHit;
    private final int snowballsHit;
    private final int soloBaronKills;
    private final int soloKills;
    private final int soloTurretsLategame;
    private final int stealthWardsPlaced;
    private final int survivedSingleDigitHpCount;
    private final int survivedThreeImmobilizesInFight;
    private final int takedownOnFirstTurret;
    private final int takedowns;
    private final int takedownsAfterGainingLevelAdvantage;
    private final int takedownsBeforeJungleMinionSpawn;
    private final int takedownsFirst25Minutes;
    private final int takedownsFirstXMinutes;
    private final int takedownsInAlcove;
    private final int takedownsInEnemyFountain;
    private final int teamBaronKills;
    private final double teamDamagePercentage;
    private final int teamElderDragonKills;
    private final int teamRiftHeraldKills;
    private final int teleportTakedowns;
    private final double thirdInhibitorDestroyedTime;
    private final int threeWardsOneSweeperCount;
    private final int tookLargeDamageSurvived;
    private final int turretPlatesTaken;
    private final int turretsTakenWithRiftHerald;
    private final int turretTakedowns;
    private final int twentyMinionsIn3SecondsCount;
    private final int unseenRecalls;
    private final double visionScoreAdvantageLaneOpponent;
    private final double visionScorePerMinute;
    private final int wardsGuarded;
    private final int wardTakedowns;
    private final int wardTakedownsBefore20M;

    public ChallengeStats(int twelveAssistStreakCount, int abilityUses, int acesBefore15Minutes, int alliedJungleMonsterKills, int baronBuffGoldAdvantageOverThreshold, int baronTakedowns, int blastConeOppositeOpponentCount, int bountyGold, int buffsStolen, int completeSupportQuestInTime, double controlWardTimeCoverageInRiverOrEnemyHalf, int controlWardsPlaced, double damagePerMinute, double damageTakenOnTeamPercentage, int dancedWithRiftHerald, int deathsByEnemyChamps, int dodgeSkillShotsSmallWindow, int doubleAces, int dragonTakedowns, double earliestBaron, double earliestDragonTakedown, double earliestElderDragon, int earlyLaningPhaseGoldExpAdvantage, double effectiveHealAndShielding, int elderDragonKillsWithOpposingSoul, int elderDragonMultikills, int enemyChampionImmobilizations, int enemyJungleMonsterKills, int epicMonsterKillsNearEnemyJungler, int epicMonsterKillsWithin30SecondsOfSpawn, int epicMonsterSteals, int epicMonsterStolenWithoutSmite, double fasterSupportQuestCompletion, double fastestLegendary, double firstTurretKilled, double firstTurretKilledTime, int flawlessAces, int fullTeamTakedown, double gameLength, int getTakedownsInAllLanesEarlyJungleAsLaner, double goldPerMinute, boolean hadAfkTeammate, boolean hadOpenNexus, int highestChampionDamage, int highestCrowdControlScore, int highestWardKills, int immobilizeAndKillWithAlly, int initialBuffCount, int initialCrabCount, double jungleCsBefore10Minutes, int junglerKillsEarlyJungle, int junglerTakedownsNearDamagedEpicMonster, double kda, int killAfterHiddenWithAlly, int killedChampTookFullTeamDamageSurvived, int killingSprees, double killParticipation, int killsNearEnemyTurret, int killsOnLanersEarlyJungleAsJungler, int killsOnOtherLanesEarlyJungleAsLaner, int killsOnRecentlyHealedByAramPack, int killsUnderOwnTurret, int killsWithHelpFromEpicMonster, int knockEnemyIntoTeamAndKill, int kTurretsDestroyedBeforePlatesFall, int landSkillShotsEarlyGame, int laneMinionsFirst10Minutes, int laningPhaseGoldExpAdvantage, int legendaryCount, boolean lostAnInhibitor, int maxCsAdvantageOnLaneOpponent, int maxKillDeficit, int maxLevelLeadLaneOpponent, boolean mejaisFullStackInTime, double moreEnemyJungleThanOpponent, int mostWardsDestroyedOneSweeper, int multiKillOneSpell, int multikills, int multikillsAfterAggressiveFlash, int multiTurretRiftHeraldCount, Item mythicItemUsed, int outerTurretExecutesBefore10Minutes, int outnumberedKills, int outnumberedNexusKill, int perfectDragonSoulsTaken, int perfectGame, int pickKillWithAlly, int playedChampSelectPosition, int poroExplosions, int quickCleanse, int quickFirstTurret, int quickSoloKills, int riftHeraldTakedowns, int saveAllyFromDeath, int scuttleCrabKills, double shortestTimeToAceFromFirstTakedown, int skillshotsDodged, int skillshotsHit, int snowballsHit, int soloBaronKills, int soloKills, int soloTurretsLategame, int stealthWardsPlaced, int survivedSingleDigitHpCount, int survivedThreeImmobilizesInFight, int takedownOnFirstTurret, int takedowns, int takedownsAfterGainingLevelAdvantage, int takedownsBeforeJungleMinionSpawn, int takedownsFirst25Minutes, int takedownsFirstXMinutes, int takedownsInAlcove, int takedownsInEnemyFountain, int teamBaronKills, double teamDamagePercentage, int teamElderDragonKills, int teamRiftHeraldKills, int teleportTakedowns, double thirdInhibitorDestroyedTime, int threeWardsOneSweeperCount, int tookLargeDamageSurvived, int turretPlatesTaken, int turretsTakenWithRiftHerald, int turretTakedowns, int twentyMinionsIn3SecondsCount, int unseenRecalls, double visionScoreAdvantageLaneOpponent, double visionScorePerMinute, int wardsGuarded, int wardTakedowns, int wardTakedownsBefore20M) {
        this.twelveAssistStreakCount = twelveAssistStreakCount;
        this.abilityUses = abilityUses;
        this.acesBefore15Minutes = acesBefore15Minutes;
        this.alliedJungleMonsterKills = alliedJungleMonsterKills;
        this.baronBuffGoldAdvantageOverThreshold = baronBuffGoldAdvantageOverThreshold;
        this.baronTakedowns = baronTakedowns;
        this.blastConeOppositeOpponentCount = blastConeOppositeOpponentCount;
        this.bountyGold = bountyGold;
        this.buffsStolen = buffsStolen;
        this.completeSupportQuestInTime = completeSupportQuestInTime;
        this.controlWardTimeCoverageInRiverOrEnemyHalf = controlWardTimeCoverageInRiverOrEnemyHalf;
        this.controlWardsPlaced = controlWardsPlaced;
        this.damagePerMinute = damagePerMinute;
        this.damageTakenOnTeamPercentage = damageTakenOnTeamPercentage;
        this.dancedWithRiftHerald = dancedWithRiftHerald;
        this.deathsByEnemyChamps = deathsByEnemyChamps;
        this.dodgeSkillShotsSmallWindow = dodgeSkillShotsSmallWindow;
        this.doubleAces = doubleAces;
        this.dragonTakedowns = dragonTakedowns;
        this.earliestBaron = earliestBaron;
        this.earliestDragonTakedown = earliestDragonTakedown;
        this.earliestElderDragon = earliestElderDragon;
        this.earlyLaningPhaseGoldExpAdvantage = earlyLaningPhaseGoldExpAdvantage;
        this.effectiveHealAndShielding = effectiveHealAndShielding;
        this.elderDragonKillsWithOpposingSoul = elderDragonKillsWithOpposingSoul;
        this.elderDragonMultikills = elderDragonMultikills;
        this.enemyChampionImmobilizations = enemyChampionImmobilizations;
        this.enemyJungleMonsterKills = enemyJungleMonsterKills;
        this.epicMonsterKillsNearEnemyJungler = epicMonsterKillsNearEnemyJungler;
        this.epicMonsterKillsWithin30SecondsOfSpawn = epicMonsterKillsWithin30SecondsOfSpawn;
        this.epicMonsterSteals = epicMonsterSteals;
        this.epicMonsterStolenWithoutSmite = epicMonsterStolenWithoutSmite;
        this.fasterSupportQuestCompletion = fasterSupportQuestCompletion;
        this.fastestLegendary = fastestLegendary;
        this.firstTurretKilled = firstTurretKilled;
        this.firstTurretKilledTime = firstTurretKilledTime;
        this.flawlessAces = flawlessAces;
        this.fullTeamTakedown = fullTeamTakedown;
        this.gameLength = gameLength;
        this.getTakedownsInAllLanesEarlyJungleAsLaner = getTakedownsInAllLanesEarlyJungleAsLaner;
        this.goldPerMinute = goldPerMinute;
        this.hadAfkTeammate = hadAfkTeammate;
        this.hadOpenNexus = hadOpenNexus;
        this.highestChampionDamage = highestChampionDamage;
        this.highestCrowdControlScore = highestCrowdControlScore;
        this.highestWardKills = highestWardKills;
        this.immobilizeAndKillWithAlly = immobilizeAndKillWithAlly;
        this.initialBuffCount = initialBuffCount;
        this.initialCrabCount = initialCrabCount;
        this.jungleCsBefore10Minutes = jungleCsBefore10Minutes;
        this.junglerKillsEarlyJungle = junglerKillsEarlyJungle;
        this.junglerTakedownsNearDamagedEpicMonster = junglerTakedownsNearDamagedEpicMonster;
        this.kda = kda;
        this.killAfterHiddenWithAlly = killAfterHiddenWithAlly;
        this.killedChampTookFullTeamDamageSurvived = killedChampTookFullTeamDamageSurvived;
        this.killingSprees = killingSprees;
        this.killParticipation = killParticipation;
        this.killsNearEnemyTurret = killsNearEnemyTurret;
        this.killsOnLanersEarlyJungleAsJungler = killsOnLanersEarlyJungleAsJungler;
        this.killsOnOtherLanesEarlyJungleAsLaner = killsOnOtherLanesEarlyJungleAsLaner;
        this.killsOnRecentlyHealedByAramPack = killsOnRecentlyHealedByAramPack;
        this.killsUnderOwnTurret = killsUnderOwnTurret;
        this.killsWithHelpFromEpicMonster = killsWithHelpFromEpicMonster;
        this.knockEnemyIntoTeamAndKill = knockEnemyIntoTeamAndKill;
        this.kTurretsDestroyedBeforePlatesFall = kTurretsDestroyedBeforePlatesFall;
        this.landSkillShotsEarlyGame = landSkillShotsEarlyGame;
        this.laneMinionsFirst10Minutes = laneMinionsFirst10Minutes;
        this.laningPhaseGoldExpAdvantage = laningPhaseGoldExpAdvantage;
        this.legendaryCount = legendaryCount;
        this.lostAnInhibitor = lostAnInhibitor;
        this.maxCsAdvantageOnLaneOpponent = maxCsAdvantageOnLaneOpponent;
        this.maxKillDeficit = maxKillDeficit;
        this.maxLevelLeadLaneOpponent = maxLevelLeadLaneOpponent;
        this.mejaisFullStackInTime = mejaisFullStackInTime;
        this.moreEnemyJungleThanOpponent = moreEnemyJungleThanOpponent;
        this.mostWardsDestroyedOneSweeper = mostWardsDestroyedOneSweeper;
        this.multiKillOneSpell = multiKillOneSpell;
        this.multikills = multikills;
        this.multikillsAfterAggressiveFlash = multikillsAfterAggressiveFlash;
        this.multiTurretRiftHeraldCount = multiTurretRiftHeraldCount;
        this.mythicItemUsed = mythicItemUsed;
        this.outerTurretExecutesBefore10Minutes = outerTurretExecutesBefore10Minutes;
        this.outnumberedKills = outnumberedKills;
        this.outnumberedNexusKill = outnumberedNexusKill;
        this.perfectDragonSoulsTaken = perfectDragonSoulsTaken;
        this.perfectGame = perfectGame;
        this.pickKillWithAlly = pickKillWithAlly;
        this.playedChampSelectPosition = playedChampSelectPosition;
        this.poroExplosions = poroExplosions;
        this.quickCleanse = quickCleanse;
        this.quickFirstTurret = quickFirstTurret;
        this.quickSoloKills = quickSoloKills;
        this.riftHeraldTakedowns = riftHeraldTakedowns;
        this.saveAllyFromDeath = saveAllyFromDeath;
        this.scuttleCrabKills = scuttleCrabKills;
        this.shortestTimeToAceFromFirstTakedown = shortestTimeToAceFromFirstTakedown;
        this.skillshotsDodged = skillshotsDodged;
        this.skillshotsHit = skillshotsHit;
        this.snowballsHit = snowballsHit;
        this.soloBaronKills = soloBaronKills;
        this.soloKills = soloKills;
        this.soloTurretsLategame = soloTurretsLategame;
        this.stealthWardsPlaced = stealthWardsPlaced;
        this.survivedSingleDigitHpCount = survivedSingleDigitHpCount;
        this.survivedThreeImmobilizesInFight = survivedThreeImmobilizesInFight;
        this.takedownOnFirstTurret = takedownOnFirstTurret;
        this.takedowns = takedowns;
        this.takedownsAfterGainingLevelAdvantage = takedownsAfterGainingLevelAdvantage;
        this.takedownsBeforeJungleMinionSpawn = takedownsBeforeJungleMinionSpawn;
        this.takedownsFirst25Minutes = takedownsFirst25Minutes;
        this.takedownsFirstXMinutes = takedownsFirstXMinutes;
        this.takedownsInAlcove = takedownsInAlcove;
        this.takedownsInEnemyFountain = takedownsInEnemyFountain;
        this.teamBaronKills = teamBaronKills;
        this.teamDamagePercentage = teamDamagePercentage;
        this.teamElderDragonKills = teamElderDragonKills;
        this.teamRiftHeraldKills = teamRiftHeraldKills;
        this.teleportTakedowns = teleportTakedowns;
        this.thirdInhibitorDestroyedTime = thirdInhibitorDestroyedTime;
        this.threeWardsOneSweeperCount = threeWardsOneSweeperCount;
        this.tookLargeDamageSurvived = tookLargeDamageSurvived;
        this.turretPlatesTaken = turretPlatesTaken;
        this.turretsTakenWithRiftHerald = turretsTakenWithRiftHerald;
        this.turretTakedowns = turretTakedowns;
        this.twentyMinionsIn3SecondsCount = twentyMinionsIn3SecondsCount;
        this.unseenRecalls = unseenRecalls;
        this.visionScoreAdvantageLaneOpponent = visionScoreAdvantageLaneOpponent;
        this.visionScorePerMinute = visionScorePerMinute;
        this.wardsGuarded = wardsGuarded;
        this.wardTakedowns = wardTakedowns;
        this.wardTakedownsBefore20M = wardTakedownsBefore20M;
    }

    public int getTwelveAssistStreakCount() {
        return twelveAssistStreakCount;
    }

    public int getAbilityUses() {
        return abilityUses;
    }

    public int getAcesBefore15Minutes() {
        return acesBefore15Minutes;
    }

    public int getAlliedJungleMonsterKills() {
        return alliedJungleMonsterKills;
    }

    public int getBaronBuffGoldAdvantageOverThreshold() {
        return baronBuffGoldAdvantageOverThreshold;
    }

    public int getBaronTakedowns() {
        return baronTakedowns;
    }

    public int getBlastConeOppositeOpponentCount() {
        return blastConeOppositeOpponentCount;
    }

    public int getBountyGold() {
        return bountyGold;
    }

    public int getBuffsStolen() {
        return buffsStolen;
    }

    public int getCompleteSupportQuestInTime() {
        return completeSupportQuestInTime;
    }

    public double getControlWardTimeCoverageInRiverOrEnemyHalf() {
        return controlWardTimeCoverageInRiverOrEnemyHalf;
    }

    public int getControlWardsPlaced() {
        return controlWardsPlaced;
    }

    public double getDamagePerMinute() {
        return damagePerMinute;
    }

    public double getDamageTakenOnTeamPercentage() {
        return damageTakenOnTeamPercentage;
    }

    public int getDancedWithRiftHerald() {
        return dancedWithRiftHerald;
    }

    public int getDeathsByEnemyChamps() {
        return deathsByEnemyChamps;
    }

    public int getDodgeSkillShotsSmallWindow() {
        return dodgeSkillShotsSmallWindow;
    }

    public int getDoubleAces() {
        return doubleAces;
    }

    public int getDragonTakedowns() {
        return dragonTakedowns;
    }

    public double getEarliestBaron() {
        return earliestBaron;
    }

    public double getEarliestDragonTakedown() {
        return earliestDragonTakedown;
    }

    public double getEarliestElderDragon() {
        return earliestElderDragon;
    }

    public int getEarlyLaningPhaseGoldExpAdvantage() {
        return earlyLaningPhaseGoldExpAdvantage;
    }

    public double getEffectiveHealAndShielding() {
        return effectiveHealAndShielding;
    }

    public int getElderDragonKillsWithOpposingSoul() {
        return elderDragonKillsWithOpposingSoul;
    }

    public int getElderDragonMultikills() {
        return elderDragonMultikills;
    }

    public int getEnemyChampionImmobilizations() {
        return enemyChampionImmobilizations;
    }

    public int getEnemyJungleMonsterKills() {
        return enemyJungleMonsterKills;
    }

    public int getEpicMonsterKillsNearEnemyJungler() {
        return epicMonsterKillsNearEnemyJungler;
    }

    public int getEpicMonsterKillsWithin30SecondsOfSpawn() {
        return epicMonsterKillsWithin30SecondsOfSpawn;
    }

    public int getEpicMonsterSteals() {
        return epicMonsterSteals;
    }

    public int getEpicMonsterStolenWithoutSmite() {
        return epicMonsterStolenWithoutSmite;
    }

    public double getFasterSupportQuestCompletion() {
        return fasterSupportQuestCompletion;
    }

    public double getFastestLegendary() {
        return fastestLegendary;
    }

    public double getFirstTurretKilled() {
        return firstTurretKilled;
    }

    public double getFirstTurretKilledTime() {
        return firstTurretKilledTime;
    }

    public int getFlawlessAces() {
        return flawlessAces;
    }

    public int getFullTeamTakedown() {
        return fullTeamTakedown;
    }

    public double getGameLength() {
        return gameLength;
    }

    public int getGetTakedownsInAllLanesEarlyJungleAsLaner() {
        return getTakedownsInAllLanesEarlyJungleAsLaner;
    }

    public double getGoldPerMinute() {
        return goldPerMinute;
    }

    public boolean isHadAfkTeammate() {
        return hadAfkTeammate;
    }

    public boolean isHadOpenNexus() {
        return hadOpenNexus;
    }

    public int getHighestChampionDamage() {
        return highestChampionDamage;
    }

    public int getHighestCrowdControlScore() {
        return highestCrowdControlScore;
    }

    public int getHighestWardKills() {
        return highestWardKills;
    }

    public int getImmobilizeAndKillWithAlly() {
        return immobilizeAndKillWithAlly;
    }

    public int getInitialBuffCount() {
        return initialBuffCount;
    }

    public int getInitialCrabCount() {
        return initialCrabCount;
    }

    public double getJungleCsBefore10Minutes() {
        return jungleCsBefore10Minutes;
    }

    public int getJunglerKillsEarlyJungle() {
        return junglerKillsEarlyJungle;
    }

    public int getJunglerTakedownsNearDamagedEpicMonster() {
        return junglerTakedownsNearDamagedEpicMonster;
    }

    public double getKda() {
        return kda;
    }

    public int getKillAfterHiddenWithAlly() {
        return killAfterHiddenWithAlly;
    }

    public int getKilledChampTookFullTeamDamageSurvived() {
        return killedChampTookFullTeamDamageSurvived;
    }

    public int getKillingSprees() {
        return killingSprees;
    }

    public double getKillParticipation() {
        return killParticipation;
    }

    public int getKillsNearEnemyTurret() {
        return killsNearEnemyTurret;
    }

    public int getKillsOnLanersEarlyJungleAsJungler() {
        return killsOnLanersEarlyJungleAsJungler;
    }

    public int getKillsOnOtherLanesEarlyJungleAsLaner() {
        return killsOnOtherLanesEarlyJungleAsLaner;
    }

    public int getKillsOnRecentlyHealedByAramPack() {
        return killsOnRecentlyHealedByAramPack;
    }

    public int getKillsUnderOwnTurret() {
        return killsUnderOwnTurret;
    }

    public int getKillsWithHelpFromEpicMonster() {
        return killsWithHelpFromEpicMonster;
    }

    public int getKnockEnemyIntoTeamAndKill() {
        return knockEnemyIntoTeamAndKill;
    }

    public int getkTurretsDestroyedBeforePlatesFall() {
        return kTurretsDestroyedBeforePlatesFall;
    }

    public int getLandSkillShotsEarlyGame() {
        return landSkillShotsEarlyGame;
    }

    public int getLaneMinionsFirst10Minutes() {
        return laneMinionsFirst10Minutes;
    }

    public int getLaningPhaseGoldExpAdvantage() {
        return laningPhaseGoldExpAdvantage;
    }

    public int getLegendaryCount() {
        return legendaryCount;
    }

    public boolean isLostAnInhibitor() {
        return lostAnInhibitor;
    }

    public int getMaxCsAdvantageOnLaneOpponent() {
        return maxCsAdvantageOnLaneOpponent;
    }

    public int getMaxKillDeficit() {
        return maxKillDeficit;
    }

    public int getMaxLevelLeadLaneOpponent() {
        return maxLevelLeadLaneOpponent;
    }

    public boolean isMejaisFullStackInTime() {
        return mejaisFullStackInTime;
    }

    public double getMoreEnemyJungleThanOpponent() {
        return moreEnemyJungleThanOpponent;
    }

    public int getMostWardsDestroyedOneSweeper() {
        return mostWardsDestroyedOneSweeper;
    }

    public int getMultiKillOneSpell() {
        return multiKillOneSpell;
    }

    public int getMultikills() {
        return multikills;
    }

    public int getMultikillsAfterAggressiveFlash() {
        return multikillsAfterAggressiveFlash;
    }

    public int getMultiTurretRiftHeraldCount() {
        return multiTurretRiftHeraldCount;
    }

    public Item getMythicItemUsed() {
        return mythicItemUsed;
    }

    public int getOuterTurretExecutesBefore10Minutes() {
        return outerTurretExecutesBefore10Minutes;
    }

    public int getOutnumberedKills() {
        return outnumberedKills;
    }

    public int getOutnumberedNexusKill() {
        return outnumberedNexusKill;
    }

    public int getPerfectDragonSoulsTaken() {
        return perfectDragonSoulsTaken;
    }

    public int getPerfectGame() {
        return perfectGame;
    }

    public int getPickKillWithAlly() {
        return pickKillWithAlly;
    }

    public int getPlayedChampSelectPosition() {
        return playedChampSelectPosition;
    }

    public int getPoroExplosions() {
        return poroExplosions;
    }

    public int getQuickCleanse() {
        return quickCleanse;
    }

    public int getQuickFirstTurret() {
        return quickFirstTurret;
    }

    public int getQuickSoloKills() {
        return quickSoloKills;
    }

    public int getRiftHeraldTakedowns() {
        return riftHeraldTakedowns;
    }

    public int getSaveAllyFromDeath() {
        return saveAllyFromDeath;
    }

    public int getScuttleCrabKills() {
        return scuttleCrabKills;
    }

    public double getShortestTimeToAceFromFirstTakedown() {
        return shortestTimeToAceFromFirstTakedown;
    }

    public int getSkillshotsDodged() {
        return skillshotsDodged;
    }

    public int getSkillshotsHit() {
        return skillshotsHit;
    }

    public int getSnowballsHit() {
        return snowballsHit;
    }

    public int getSoloBaronKills() {
        return soloBaronKills;
    }

    public int getSoloKills() {
        return soloKills;
    }

    public int getSoloTurretsLategame() {
        return soloTurretsLategame;
    }

    public int getStealthWardsPlaced() {
        return stealthWardsPlaced;
    }

    public int getSurvivedSingleDigitHpCount() {
        return survivedSingleDigitHpCount;
    }

    public int getSurvivedThreeImmobilizesInFight() {
        return survivedThreeImmobilizesInFight;
    }

    public int getTakedownOnFirstTurret() {
        return takedownOnFirstTurret;
    }

    public int getTakedowns() {
        return takedowns;
    }

    public int getTakedownsAfterGainingLevelAdvantage() {
        return takedownsAfterGainingLevelAdvantage;
    }

    public int getTakedownsBeforeJungleMinionSpawn() {
        return takedownsBeforeJungleMinionSpawn;
    }

    public int getTakedownsFirst25Minutes() {
        return takedownsFirst25Minutes;
    }

    public int getTakedownsFirstXMinutes() {
        return takedownsFirstXMinutes;
    }

    public int getTakedownsInAlcove() {
        return takedownsInAlcove;
    }

    public int getTakedownsInEnemyFountain() {
        return takedownsInEnemyFountain;
    }

    public int getTeamBaronKills() {
        return teamBaronKills;
    }

    public double getTeamDamagePercentage() {
        return teamDamagePercentage;
    }

    public int getTeamElderDragonKills() {
        return teamElderDragonKills;
    }

    public int getTeamRiftHeraldKills() {
        return teamRiftHeraldKills;
    }

    public int getTeleportTakedowns() {
        return teleportTakedowns;
    }

    public double getThirdInhibitorDestroyedTime() {
        return thirdInhibitorDestroyedTime;
    }

    public int getThreeWardsOneSweeperCount() {
        return threeWardsOneSweeperCount;
    }

    public int getTookLargeDamageSurvived() {
        return tookLargeDamageSurvived;
    }

    public int getTurretPlatesTaken() {
        return turretPlatesTaken;
    }

    public int getTurretsTakenWithRiftHerald() {
        return turretsTakenWithRiftHerald;
    }

    public int getTurretTakedowns() {
        return turretTakedowns;
    }

    public int getTwentyMinionsIn3SecondsCount() {
        return twentyMinionsIn3SecondsCount;
    }

    public int getUnseenRecalls() {
        return unseenRecalls;
    }

    public double getVisionScoreAdvantageLaneOpponent() {
        return visionScoreAdvantageLaneOpponent;
    }

    public double getVisionScorePerMinute() {
        return visionScorePerMinute;
    }

    public int getWardsGuarded() {
        return wardsGuarded;
    }

    public int getWardTakedowns() {
        return wardTakedowns;
    }

    public int getWardTakedownsBefore20M() {
        return wardTakedownsBefore20M;
    }
}
