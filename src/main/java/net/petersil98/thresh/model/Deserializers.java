package net.petersil98.thresh.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.constant.Platform;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.BaseRune;
import net.petersil98.thresh.data.rune.Rune;
import net.petersil98.thresh.model.game.match.MatchDetails;
import net.petersil98.thresh.model.game.match.Objective;
import net.petersil98.thresh.model.game.match.Team;
import net.petersil98.thresh.model.game.match.participant.ChallengeStats;
import net.petersil98.thresh.model.game.match.participant.MatchParticipant;
import net.petersil98.thresh.model.game.match.participant.PingStats;
import net.petersil98.thresh.model.game.match.participant.RuneExtraData;
import net.petersil98.thresh.model.game.match.timeline.*;
import net.petersil98.thresh.model.game.match.timeline.event.*;
import net.petersil98.thresh.model.game.spectator.ActiveGame;
import net.petersil98.thresh.model.game.spectator.Ban;
import net.petersil98.thresh.model.game.spectator.Participant;
import net.petersil98.thresh.model.summoner.ChampionMastery;
import net.petersil98.thresh.model.summoner.Summoner;

import java.io.IOException;
import java.util.*;
import java.util.stream.StreamSupport;

public class Deserializers {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static class ChampionMasteryDeserializer extends JsonDeserializer<ChampionMastery> {

        @Override
        public ChampionMastery deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            return new ChampionMastery(root.get("chestGranted").asBoolean(), root.get("championLevel").asInt(), root.get("championPoints").asInt(),
                    Champions.getChampion(root.get("championId").asInt()), root.get("championPointsUntilNextLevel").asInt(),
                    root.get("lastPlayTime").asInt(), root.get("tokensEarned").asInt(), root.get("championPointsSinceLastLevel").asInt());
        }
    }

    public static class ParticipantDeserializer extends JsonDeserializer<Participant> {

        @Override
        public Participant deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            JsonNode perks = root.get("perks");
            List<BaseRune> runes = new ArrayList<>(9);
            for (JsonNode node : perks.get("perkIds")) {
                BaseRune rune = Runes.getRune(node.asInt());
                if (rune == null) rune = RuneStats.getRuneStat(node.asInt());
                runes.add(rune);
            }
            return new Participant(Summoner.getSummonerByID(root.get("summonerId").asText()), root.get("bot").asBoolean(),
                    Champions.getChampion(root.get("championId").asInt()), root.get("teamId").asInt(),
                    SummonerSpells.getSummonerSpell(root.get("spell1Id").asInt()), SummonerSpells.getSummonerSpell(root.get("spell2Id").asInt()),
                    runes, RuneStyles.getRuneStyle(perks.get("perkStyle").asInt()), RuneStyles.getRuneStyle(perks.get("perkSubStyle").asInt()));
        }
    }

    public static class BanDeserializer extends JsonDeserializer<Ban> {

        @Override
        public Ban deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            return new Ban(Champions.getChampion(root.get("championId").asInt()), root.get("teamId").asInt(), root.get("pickTurn").asInt());
        }
    }

    public static class ActiveGameDeserializer extends JsonDeserializer<ActiveGame> {

        @Override
        public ActiveGame deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            List<Participant> participants = MAPPER.reader(TypeFactory.defaultInstance().constructCollectionType(List.class, Participant.class)).readValue(root.get("participants"));
            List<Ban> bans = MAPPER.reader(TypeFactory.defaultInstance().constructCollectionType(List.class, Ban.class)).readValue(root.get("bannedChampions"));
            return new ActiveGame(root.get("gameId").asLong(), Maps.getMap(root.get("mapId").asInt()), root.get("gameMode").asText(),
                    root.get("gameType").asText(), QueueTypes.getQueueType(root.get("gameQueueConfigId").asInt()),
                    participants.stream().filter(participant -> participant.getTeamId() == 100).toList(),
                    participants.stream().filter(participant -> participant.getTeamId() == 200).toList(),
                    participants.size() / 2, bans, root.get("observers").get("encryptionKey").asText(), root.get("platformId").asText(),
                    root.get("gameStartTime").asInt(), root.get("gameLength").asInt());
        }
    }

    public static class MatchParticipantDeserializer extends JsonDeserializer<MatchParticipant> {

        @Override
        public MatchParticipant deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            Champion champion = Champions.getChampion(root.get("championId").asInt());
            if (champion == null) champion = Champions.getChampionByName(root.get("championName").asText());

            JsonNode perks = root.get("perks");
            JsonNode primaryPerks = perks.get("styles").get(0);
            JsonNode secondaryPerks = perks.get("styles").get(1);
            JsonNode runeStats = perks.get("statPerks");

            List<Map.Entry<Rune, RuneExtraData>> primaryRunes = this.getExtraData(primaryPerks);
            List<Map.Entry<Rune, RuneExtraData>> secondaryRunes = this.getExtraData(secondaryPerks);

            PingStats pingStats = new PingStats(root.get("allInPings").asInt(), root.get("assistMePings").asInt(),
                    root.get("baitPings").asInt(), root.get("basicPings").asInt(), root.get("commandPings").asInt(),
                    root.get("dangerPings").asInt(), root.get("enemyMissingPings").asInt(), root.get("enemyVisionPings").asInt(),
                    root.get("getBackPings").asInt(), root.get("holdPings").asInt(), root.get("needVisionPings").asInt(),
                    root.get("onMyWayPings").asInt(), root.get("pushPings").asInt(), root.get("visionClearedPings").asInt());

            JsonNode challenges = root.get("challenges");
            ChallengeStats challengeStats = new ChallengeStats(
                    intField(challenges, "12AssistStreakCount"),
                    intField(challenges, "abilityUses"),
                    intField(challenges, "acesBefore15Minutes"),
                    intField(challenges, "alliedJungleMonsterKills"),
                    intField(challenges, "baronBuffGoldAdvantageOverThreshold"),
                    intField(challenges, "baronTakedowns"),
                    intField(challenges, "blastConeOppositeOpponentCount"),
                    intField(challenges, "bountyGold"),
                    intField(challenges, "buffsStolen"),
                    intField(challenges, "completeSupportQuestInTime"),
                    doubleField(challenges, "controlWardTimeCoverageInRiverOrEnemyHalf"),
                    intField(challenges, "controlWardsPlaced"),
                    doubleField(challenges, "damagePerMinute"),
                    doubleField(challenges, "damageTakenOnTeamPercentage"),
                    intField(challenges, "dancedWithRiftHerald"),
                    intField(challenges, "deathsByEnemyChamps"),
                    intField(challenges, "dodgeSkillShotsSmallWindow"),
                    intField(challenges, "doubleAces"),
                    intField(challenges, "dragonTakedowns"),
                    doubleField(challenges, "earliestBaron"),
                    doubleField(challenges, "earliestDragonTakedown"),
                    doubleField(challenges, "earliestElderDragon"),
                    intField(challenges, "earlyLaningPhaseGoldExpAdvantage"),
                    doubleField(challenges, "effectiveHealAndShielding"),
                    intField(challenges, "elderDragonKillsWithOpposingSoul"),
                    intField(challenges, "elderDragonMultikills"),
                    intField(challenges, "enemyChampionImmobilizations"),
                    intField(challenges, "enemyJungleMonsterKills"),
                    intField(challenges, "epicMonsterKillsNearEnemyJungler"),
                    intField(challenges, "epicMonsterKillsWithin30SecondsOfSpawn"),
                    intField(challenges, "epicMonsterSteals"),
                    intField(challenges, "epicMonsterStolenWithoutSmite"),
                    doubleField(challenges, "fasterSupportQuestCompletion"),
                    doubleField(challenges, "fastestLegendary"),
                    doubleField(challenges, "firstTurretKilled"),
                    doubleField(challenges, "firstTurretKilledTime"),
                    intField(challenges, "flawlessAces"),
                    intField(challenges, "fullTeamTakedown"),
                    doubleField(challenges, "gameLength"),
                    intField(challenges, "getTakedownsInAllLanesEarlyJungleAsLaner"),
                    doubleField(challenges, "goldPerMinute"),
                    intField(challenges, "hadAfkTeammate") == 1,
                    intField(challenges, "hadOpenNexus") == 1,
                    intField(challenges, "highestChampionDamage"),
                    intField(challenges, "highestCrowdControlScore"),
                    intField(challenges, "highestWardKills"),
                    intField(challenges, "immobilizeAndKillWithAlly"),
                    intField(challenges, "initialBuffCount"),
                    intField(challenges, "initialCrabCount"),
                    doubleField(challenges, "jungleCsBefore10Minutes"),
                    intField(challenges, "junglerKillsEarlyJungle"),
                    intField(challenges, "junglerTakedownsNearDamagedEpicMonster"),
                    doubleField(challenges, "kda"),
                    intField(challenges, "killAfterHiddenWithAlly"),
                    intField(challenges, "killedChampTookFullTeamDamageSurvived"),
                    intField(challenges, "killingSprees"),
                    doubleField(challenges, "killParticipation"),
                    intField(challenges, "killsNearEnemyTurret"),
                    intField(challenges, "killsOnLanersEarlyJungleAsJungler"),
                    intField(challenges, "killsOnOtherLanesEarlyJungleAsLaner"),
                    intField(challenges, "killsOnRecentlyHealedByAramPack"),
                    intField(challenges, "killsUnderOwnTurret"),
                    intField(challenges, "killsWithHelpFromEpicMonster"),
                    intField(challenges, "knockEnemyIntoTeamAndKill"),
                    intField(challenges, "kTurretsDestroyedBeforePlatesFall"),
                    intField(challenges, "landSkillShotsEarlyGame"),
                    intField(challenges, "laneMinionsFirst10Minutes"),
                    intField(challenges, "laningPhaseGoldExpAdvantage"),
                    intField(challenges, "legendaryCount"),
                    intField(challenges, "lostAnInhibitor") == 1,
                    intField(challenges, "maxCsAdvantageOnLaneOpponent"),
                    intField(challenges, "maxKillDeficit"),
                    intField(challenges, "maxLevelLeadLaneOpponent"),
                    intField(challenges, "mejaisFullStackInTime") == 1,
                    doubleField(challenges, "moreEnemyJungleThanOpponent"),
                    intField(challenges, "mostWardsDestroyedOneSweeper"),
                    intField(challenges, "multiKillOneSpell"),
                    intField(challenges, "multikills"),
                    intField(challenges, "multikillsAfterAggressiveFlash"),
                    intField(challenges, "multiTurretRiftHeraldCount"),
                    Items.getItem(intField(challenges, "mythicItemUsed")),
                    intField(challenges, "outerTurretExecutesBefore10Minutes"),
                    intField(challenges, "outnumberedKills"),
                    intField(challenges, "outnumberedNexusKill"),
                    intField(challenges, "perfectDragonSoulsTaken"),
                    intField(challenges, "perfectGame"),
                    intField(challenges, "pickKillWithAlly"),
                    intField(challenges, "playedChampSelectPosition"),
                    intField(challenges, "poroExplosions"),
                    intField(challenges, "quickCleanse"),
                    intField(challenges, "quickFirstTurret"),
                    intField(challenges, "quickSoloKills"),
                    intField(challenges, "riftHeraldTakedowns"),
                    intField(challenges, "saveAllyFromDeath"),
                    intField(challenges, "scuttleCrabKills"),
                    doubleField(challenges, "shortestTimeToAceFromFirstTakedown"),
                    intField(challenges, "skillshotsDodged"),
                    intField(challenges, "skillshotsHit"),
                    intField(challenges, "snowballsHit"),
                    intField(challenges, "soloBaronKills"),
                    intField(challenges, "soloKills"),
                    intField(challenges, "soloTurretsLategame"),
                    intField(challenges, "stealthWardsPlaced"),
                    intField(challenges, "survivedSingleDigitHpCount"),
                    intField(challenges, "survivedThreeImmobilizesInFight"),
                    intField(challenges, "takedownOnFirstTurret"),
                    intField(challenges, "takedowns"),
                    intField(challenges, "takedownsAfterGainingLevelAdvantage"),
                    intField(challenges, "takedownsBeforeJungleMinionSpawn"),
                    intField(challenges, "takedownsFirst25Minutes"),
                    intField(challenges, "takedownsFirstXMinutes"),
                    intField(challenges, "takedownsInAlcove"),
                    intField(challenges, "takedownsInEnemyFountain"),
                    intField(challenges, "teamBaronKills"),
                    doubleField(challenges, "teamDamagePercentage"),
                    intField(challenges, "teamElderDragonKills"),
                    intField(challenges, "teamRiftHeraldKills"),
                    intField(challenges, "teleportTakedowns"),
                    doubleField(challenges, "thirdInhibitorDestroyedTime"),
                    intField(challenges, "threeWardsOneSweeperCount"),
                    intField(challenges, "tookLargeDamageSurvived"),
                    intField(challenges, "turretPlatesTaken"),
                    intField(challenges, "turretsTakenWithRiftHerald"),
                    intField(challenges, "turretTakedowns"),
                    intField(challenges, "twentyMinionsIn3SecondsCount"),
                    intField(challenges, "unseenRecalls"),
                    doubleField(challenges, "visionScoreAdvantageLaneOpponent"),
                    doubleField(challenges, "visionScorePerMinute"),
                    intField(challenges, "wardsGuarded"),
                    intField(challenges, "wardTakedowns"),
                    intField(challenges, "wardTakedownsBefore20M")
            );


            return new MatchParticipant(root.get("participantId").asInt(), champion, SummonerSpells.getSummonerSpell(root.get("summoner1Id").asInt()),
                    SummonerSpells.getSummonerSpell(root.get("summoner2Id").asInt()), Items.getItem(root.get("item0").asInt()),
                    Items.getItem(root.get("item1").asInt()), Items.getItem(root.get("item2").asInt()), Items.getItem(root.get("item3").asInt()),
                    Items.getItem(root.get("item4").asInt()), Items.getItem(root.get("item5").asInt()), Items.getItem(root.get("item6").asInt()),
                    root.get("kills").asInt(), root.get("deaths").asInt(), root.get("assists").asInt(), root.get("largestKillingSpree").asInt(),
                    root.get("largestMultiKill").asInt(), root.get("killingSprees").asInt(), root.get("longestTimeSpentLiving").asInt(),
                    root.get("doubleKills").asInt(), root.get("tripleKills").asInt(), root.get("quadraKills").asInt(), root.get("pentaKills").asInt(),
                    root.get("unrealKills").asInt(), root.get("totalDamageDealt").asInt(), root.get("magicDamageDealt").asInt(),
                    root.get("physicalDamageDealt").asInt(), root.get("trueDamageDealt").asInt(), root.get("largestCriticalStrike").asInt(),
                    root.get("totalDamageDealtToChampions").asInt(), root.get("magicDamageDealtToChampions").asInt(),
                    root.get("physicalDamageDealtToChampions").asInt(), root.get("trueDamageDealtToChampions").asInt(),
                    root.get("totalHeal").asInt(), root.get("totalUnitsHealed").asInt(), root.get("damageSelfMitigated").asInt(),
                    root.get("damageDealtToObjectives").asInt(), root.get("damageDealtToTurrets").asInt(),
                    root.get("visionScore").asInt(), root.get("timeCCingOthers").asInt(), root.get("totalDamageTaken").asInt(),
                    root.get("magicDamageTaken").asInt(), root.get("physicalDamageTaken").asInt(), root.get("trueDamageTaken").asInt(),
                    root.get("goldEarned").asInt(), root.get("goldSpent").asInt(), root.get("turretKills").asInt(),
                    root.get("inhibitorKills").asInt(), root.get("totalMinionsKilled").asInt(), root.get("neutralMinionsKilled").asInt(),
                    root.get("totalTimeCCDealt").asInt(), root.get("champLevel").asInt(), root.get("visionWardsBoughtInGame").asInt(),
                    root.get("sightWardsBoughtInGame").asInt(), root.get("wardsPlaced").asInt(), root.get("wardsKilled").asInt(),
                    root.get("firstBloodKill").asInt(), root.get("firstBloodAssist").asInt(), root.get("firstTowerKill").asInt(),
                    root.get("firstTowerAssist").asInt(), primaryRunes.get(0).getKey(), primaryRunes.get(0).getValue(),
                    primaryRunes.get(1).getKey(), primaryRunes.get(1).getValue(), primaryRunes.get(2).getKey(), primaryRunes.get(2).getValue(),
                    primaryRunes.get(3).getKey(), primaryRunes.get(3).getValue(), secondaryRunes.get(0).getKey(), secondaryRunes.get(0).getValue(),
                    secondaryRunes.get(1).getKey(), secondaryRunes.get(1).getValue(), RuneStyles.getRuneStyle(primaryPerks.get("style").asInt()),
                    RuneStyles.getRuneStyle(secondaryPerks.get("style").asInt()), RuneStats.getRuneStat(runeStats.get("defense").asInt()),
                    RuneStats.getRuneStat(runeStats.get("flex").asInt()), RuneStats.getRuneStat(runeStats.get("offense").asInt()),
                    root.get("role").asText(), root.get("lane").asText(), root.get("baronKills").asInt(), root.get("bountyLevel").asInt(),
                    root.get("champExperience").asInt(), root.get("championTransform").asInt(), root.get("consumablesPurchased").asInt(),
                    root.get("damageDealtToBuildings").asInt(), root.get("dragonKills").asInt(), root.get("gameEndedInEarlySurrender").asBoolean(),
                    root.get("gameEndedInSurrender").asBoolean(), root.get("individualPosition").asText(), root.get("inhibitorTakedowns").asInt(),
                    root.get("inhibitorsLost").asInt(), root.get("itemsPurchased").asInt(), root.get("nexusKills").asInt(),
                    root.get("nexusLost").asInt(), root.get("nexusTakedowns").asInt(), root.get("objectivesStolen").asInt(),
                    root.get("objectivesStolenAssists").asInt(), root.get("summonerId").asText(),
                    root.get("spell1Casts").asInt(), root.get("spell2Casts").asInt(), root.get("spell3Casts").asInt(),
                    root.get("spell4Casts").asInt(), root.get("summoner1Casts").asInt(), root.get("summoner2Casts").asInt(),
                    root.get("teamEarlySurrendered").asBoolean(), root.get("teamId").asInt(), root.get("teamPosition").asText(),
                    root.get("timePlayed").asInt(), root.get("totalDamageShieldedOnTeammates").asInt(), root.get("totalHealsOnTeammates").asInt(),
                    root.get("totalTimeSpentDead").asInt(), root.get("turretTakedowns").asInt(), root.get("turretsLost").asInt(),
                    root.get("win").asBoolean(), pingStats, root.get("detectorWardsPlaced").asInt(),
                    root.get("eligibleForProgression").asBoolean(false), challengeStats);
        }

        private List<Map.Entry<Rune, RuneExtraData>> getExtraData(JsonNode style) {
            List<Map.Entry<Rune, RuneExtraData>> runes = new ArrayList<>(4);
            for (JsonNode node : style.get("selections")) {
                runes.add(new AbstractMap.SimpleEntry<>(
                        Runes.getRune(node.get("perk").asInt()),
                        new RuneExtraData(node.get("var1").asInt(), node.get("var2").asInt(), node.get("var3").asInt())));
            }
            return runes;
        }

        private static int intField(JsonNode node, String fieldName) {
            return node != null && node.has(fieldName) ? node.get(fieldName).asInt() : -1;
        }

        private static double doubleField(JsonNode node, String fieldName) {
            return node != null && node.has(fieldName) ? node.get(fieldName).asDouble() : -1;
        }
    }

    public static class MatchDetailsDeserializer extends JsonDeserializer<MatchDetails> {

        @Override
        public MatchDetails deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            JsonNode details = root.get("info");
            List<MatchParticipant> participants = MAPPER.reader(TypeFactory.defaultInstance().constructCollectionType(List.class, MatchParticipant.class)).readValue(details.get("participants"));
            List<Team> teams = MAPPER.reader(TypeFactory.defaultInstance().constructCollectionType(List.class, Team.class)).readValue(details.get("teams"));

            return new MatchDetails(
                    details.get("gameCreation").asLong(),
                    details.get("gameDuration").asInt(),
                    details.get("gameEndTimestamp").asLong(),
                    details.get("gameId").asLong(),
                    details.get("gameMode").asText(),
                    details.get("gameName").asText(),
                    details.get("gameStartTimestamp").asLong(),
                    details.get("gameType").asText(),
                    details.get("gameVersion").asText(),
                    Maps.getMap(details.get("mapId").asInt()),
                    participants,
                    Platform.getPlatform(details.get("platformId").asText()),
                    QueueTypes.getQueueType(details.get("queueId").asInt()),
                    teams,
                    details.get("tournamentCode").asText()
            );
        }
    }

    public static class TeamDeserializer extends JsonDeserializer<Team> {

        @Override
        public Team deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            ObjectReader reader = MAPPER.reader(Objective.class);
            JsonNode objectives = root.get("objectives");

            Map<Integer, Champion> bans = new HashMap<>();
            for(JsonNode ban: root.get("bans")) {
                bans.put(ban.get("pickTurn").asInt(), Champions.getChampion(ban.get("championId").asInt()));
            }
            return new Team(
                    root.get("teamId").asInt(),
                    root.get("win").asBoolean(),
                    reader.readValue(objectives.get("baron")),
                    reader.readValue(objectives.get("champion")),
                    reader.readValue(objectives.get("dragon")),
                    reader.readValue(objectives.get("inhibitor")),
                    reader.readValue(objectives.get("riftHerald")),
                    reader.readValue(objectives.get("tower")),
                    bans
            );
        }
    }

    public static class TimelineDeserializer extends JsonDeserializer<Timeline> {
        @Override
        public Timeline deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            JsonNode info = root.get("info");

            List<TimelineParticipant> participants = MAPPER.reader(TypeFactory.defaultInstance().constructCollectionType(List.class, TimelineParticipant.class)).readValue(info.get("participants"));
            List<TimelineFrame> frames = new ArrayList<>();
            for(JsonNode frameNode: info.get("frames")) {
                List<TimelineEvent> events = new ArrayList<>();
                for(JsonNode eventNode: frameNode.get("events")) {
                    events.add(deserializeEvent(eventNode, participants));
                }
                Map<Integer, ParticipantFrameData> participantFrames = new HashMap<>();
                for (Iterator<String> it = frameNode.get("participantFrames").fieldNames(); it.hasNext();) {
                    String fieldName = it.next();
                    participantFrames.put(Integer.parseInt(fieldName), MAPPER.reader(ParticipantFrameData.class).readValue(frameNode.get("participantFrames").get(fieldName)));
                }
                frames.add(new TimelineFrame(frameNode.get("timestamp").asInt(), events, participantFrames));
            }

            return new Timeline(info.get("frameInterval").asInt(), frames, participants);
        }

        private TimelineEvent deserializeEvent(JsonNode event, List<TimelineParticipant> participants) throws IOException {
            EventType type = EventType.valueOf(event.get("type").asText());
            switch (type) {
                case ASCENDED_EVENT, CAPTURE_POINT, PORO_KING_SUMMON -> {
                    return new TimelineEvent(event.get("timestamp").asLong(), type) {};
                }
                case BUILDING_KILL -> {
                    return new BuildingKill(event.get("timestamp").asLong(), type, getAssistingParticipants(event, participants),
                            event.get("bounty").asInt(), BuildingKill.BuildingType.valueOf(event.get("buildingType").asText()),
                            getParticipantFromField(event, "killerId", participants),
                            LaneType.valueOf(event.get("laneType").asText()),
                            MAPPER.reader(Point.class).readValue(event.get("position")), event.get("teamId").asInt(),
                            event.has("towerType") ? BuildingKill.TowerType.valueOf(event.get("towerType").asText()) : null);
                }
                case CHAMPION_KILL -> {
                    List<ChampionKill.DamageData> damageDealt = new ArrayList<>();
                    List<ChampionKill.DamageData> damageReceived = new ArrayList<>();
                    if(event.has("victimDamageDealt")) {
                        damageDealt.addAll(createDamageData(event.get("victimDamageDealt"), participants));
                    }
                    if(event.has("victimDamageReceived")) {
                        damageReceived.addAll(createDamageData(event.get("victimDamageReceived"), participants));
                    }
                    return new ChampionKill(event.get("timestamp").asLong(), type, getAssistingParticipants(event, participants),
                            event.get("bounty").asInt(), event.get("killStreakLength").asInt(),
                            getParticipantFromField(event, "killerId", participants),
                            MAPPER.reader(Point.class).readValue(event.get("position")), event.get("shutdownBounty").asInt(),
                            damageDealt, damageReceived,
                            getParticipantFromField(event, "victimId", participants));
                }
                case CHAMPION_SPECIAL_KILL -> {
                    return new ChampionSpecialKill(event.get("timestamp").asLong(), type,
                            ChampionSpecialKill.KillType.valueOf(event.get("killType").asText()),
                            getParticipantFromField(event, "killerId", participants),
                            event.has("multiKillLength") ? event.get("multiKillLength").asInt() : 1,
                            MAPPER.reader(Point.class).readValue(event.get("position")));
                }
                case CHAMPION_TRANSFORM -> {
                    return new ChampionTransform(event.get("timestamp").asLong(), type,
                            getParticipantFromField(event, "participantId", participants),
                            ChampionTransform.TransformType.valueOf(event.get("transformType").asText()));
                }
                case DRAGON_SOUL_GIVEN -> {
                    return new DragonSoulGiven(event.get("timestamp").asLong(), type,
                            DragonSoulGiven.DragonType.valueOf(event.get("name").asText().toUpperCase()),
                            event.get("teamId").asInt());
                }
                case ELITE_MONSTER_KILL -> {
                    return new EliteMonsterKill(event.get("timestamp").asLong(), type,
                            event.get("bounty").asInt(), getParticipantFromField(event, "killerId", participants),
                            event.get("killerTeamId").asInt(), EliteMonsterKill.MonsterType.valueOf(event.get("monsterType").asText()),
                            event.has("monsterSubType") ? EliteMonsterKill.MonsterSubType.valueOf(event.get("monsterSubType").asText()) : null,
                            MAPPER.reader(Point.class).readValue(event.get("position")));
                }
                case GAME_END -> {
                    return new GameEnd(event.get("timestamp").asLong(), type, event.get("gameId").asLong(),
                            event.get("realTimestamp").asLong(), event.get("winningTeam").asInt());
                }
                case ITEM_DESTROYED -> {
                    return new ItemDestroyed(event.get("timestamp").asLong(), type, Items.getItem(event.get("itemId").asInt()),
                            getParticipantFromField(event, "participantId", participants));
                }
                case ITEM_PURCHASED -> {
                    return new ItemPurchased(event.get("timestamp").asLong(), type, Items.getItem(event.get("itemId").asInt()),
                            getParticipantFromField(event, "participantId", participants));
                }
                case ITEM_SOLD -> {
                    return new ItemSold(event.get("timestamp").asLong(), type, Items.getItem(event.get("itemId").asInt()),
                            getParticipantFromField(event, "participantId", participants));
                }
                case ITEM_UNDO -> {
                    return new ItemUndo(event.get("timestamp").asLong(), type, Items.getItem(event.get("afterId").asInt()),
                            Items.getItem(event.get("beforeId").asInt()), event.get("goldGain").asInt(),
                            getParticipantFromField(event, "participantId", participants));
                }
                case LEVEL_UP -> {
                    return new LevelUp(event.get("timestamp").asLong(), type, event.get("level").asInt(),
                            getParticipantFromField(event, "participantId", participants));
                }
                case OBJECTIVE_BOUNTY_PRESTART -> {
                    return new ObjectiveBountyPrestart(event.get("timestamp").asLong(), type,
                            event.get("actualStartTime").asLong(), event.get("teamId").asInt());
                }
                case OBJECTIVE_BOUNTY_FINISH -> {
                    return new ObjectiveBountyFinish(event.get("timestamp").asLong(), type, event.get("teamId").asInt());
                }
                case PAUSE_END -> {
                    return new PauseEnd(event.get("timestamp").asLong(), type, event.get("realTimestamp").asLong());
                }
                case SKILL_LEVEL_UP -> {
                    return new SkillLevelUp(event.get("timestamp").asLong(), type, event.get("levelUpType").asText(),
                            getParticipantFromField(event, "participantId", participants), event.get("skillSlot").asInt());
                }
                case TURRET_PLATE_DESTROYED -> {
                    return new TurretPlateDestroyed(event.get("timestamp").asLong(), type,
                            getParticipantFromField(event, "killerId", participants),
                            LaneType.valueOf(event.get("laneType").asText()),
                            MAPPER.reader(Point.class).readValue(event.get("position")), event.get("teamId").asInt());
                }
                case WARD_KILL -> {
                    return new WardKill(event.get("timestamp").asLong(), type,
                            getParticipantFromField(event, "killerId", participants),
                            WardKill.WardType.valueOf(event.get("wardType").asText()));
                }
                case WARD_PLACED -> {
                    return new WardPlaced(event.get("timestamp").asLong(), type,
                            getParticipantFromField(event, "creatorId", participants),
                            WardKill.WardType.valueOf(event.get("wardType").asText()));
                }
                default -> throw new IllegalArgumentException("Unknown event type \"" + type + "\" in match timeline");
            }
        }

        private static List<TimelineParticipant> getAssistingParticipants(JsonNode root, List<TimelineParticipant> participants) {
            if(root.has("assistingParticipantIds")) {
                List<Integer> ids = StreamSupport.stream(root.get("assistingParticipantIds").spliterator(), false)
                        .map(JsonNode::asInt).toList();
                return participants.stream().filter(p -> ids.contains(p.getParticipantId())).toList();
            }
            return new ArrayList<>();
        }

        private static List<ChampionKill.DamageData> createDamageData(JsonNode arrayNode, List<TimelineParticipant> participants) {
            return StreamSupport.stream(arrayNode.spliterator(), false).map(node ->
                    new ChampionKill.DamageData(node.get("basic").asBoolean(), node.get("magicDamage").asInt(),
                    Champions.getChampionByName(node.get("name").asText()),
                    getParticipantFromField(node, "participantId", participants),
                    node.get("physicalDamage").asInt(), node.get("spellName").asText(), node.get("spellSlot").asInt(),
                    node.get("trueDamage").asInt(), node.get("type").asText())).toList();
        }

        private static TimelineParticipant getParticipantFromField(JsonNode node, String fieldName, List<TimelineParticipant> participants) {
            return participants.stream().filter(p -> p.getParticipantId() == node.get(fieldName).asInt()).findAny().orElse(null);
        }
    }
}
