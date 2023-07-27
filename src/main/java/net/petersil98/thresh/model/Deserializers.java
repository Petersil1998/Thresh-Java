package net.petersil98.thresh.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import net.petersil98.stcommons.constants.LeaguePlatform;
import net.petersil98.stcommons.data.Sprite;
import net.petersil98.thresh.Thresh;
import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.data.SummonerSpell;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.champion.Info;
import net.petersil98.thresh.data.champion.Skin;
import net.petersil98.thresh.data.champion.Stats;
import net.petersil98.thresh.data.rune.BaseRune;
import net.petersil98.thresh.data.rune.RuneStat;
import net.petersil98.thresh.data.rune.RuneStyle;
import net.petersil98.thresh.model.match.MatchDetails;
import net.petersil98.thresh.model.match.Objective;
import net.petersil98.thresh.model.match.Team;
import net.petersil98.thresh.model.match.participant.ChallengeStats;
import net.petersil98.thresh.model.match.participant.MatchParticipant;
import net.petersil98.thresh.model.match.participant.PingStats;
import net.petersil98.thresh.model.match.participant.RuneData;
import net.petersil98.thresh.model.match.timeline.*;
import net.petersil98.thresh.model.match.timeline.event.*;
import net.petersil98.thresh.model.spectator.ActiveGame;
import net.petersil98.thresh.model.spectator.Ban;
import net.petersil98.thresh.model.spectator.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Deserializers {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static class ChampionMasteryDeserializer extends JsonDeserializer<ChampionMasteries.Mastery> {

        @Override
        public ChampionMasteries.Mastery deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            return new ChampionMasteries.Mastery(root.get("chestGranted").asBoolean(), root.get("championLevel").asInt(), root.get("championPoints").asInt(),
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
            return new Participant(root.get("summonerId").asText(), root.get("bot").asBoolean(),
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

            List<Participant> participants = MAPPER.readerForListOf(Participant.class).readValue(root.get("participants"));
            List<Ban> bans = MAPPER.readerForListOf(Ban.class).readValue(root.get("bannedChampions"));
            return new ActiveGame(root.get("gameId").asLong(), Maps.getMap(root.get("mapId").asInt()), root.get("gameMode").asText(),
                    root.get("gameType").asText(), QueueTypes.getQueueType(root.get("gameQueueConfigId").asInt()),
                    participants.stream().filter(participant -> participant.getTeamId() == 100).toList(),
                    participants.stream().filter(participant -> participant.getTeamId() == 200).toList(),
                    participants.size() / 2, bans, root.get("observers").get("encryptionKey").asText(),
                    LeaguePlatform.getPlatform(root.get("platformId").asText()), root.get("gameStartTime").asInt(),
                    root.get("gameLength").asInt());
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

            List<RuneData> primaryRunes = MAPPER.readerForListOf(RuneData.class).readValue(primaryPerks.get("selections"));
            List<RuneData> secondaryRunes = MAPPER.readerForListOf(RuneData.class).readValue(secondaryPerks.get("selections"));

            PingStats pingStats = MAPPER.readerFor(PingStats.class).readValue(root);
            ChallengeStats challengeStats = null;
            if (root.has("challenges"))  challengeStats = MAPPER.readerFor(ChallengeStats.class).readValue(root.get("challenges"));

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
                    root.get("firstTowerAssist").asInt(), primaryRunes.get(0), primaryRunes.get(1), primaryRunes.get(2),
                    primaryRunes.get(3), secondaryRunes.get(0), secondaryRunes.get(1), RuneStyles.getRuneStyle(primaryPerks.get("style").asInt()),
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
                    root.get("eligibleForProgression").asBoolean(false), challengeStats,
                    root.has("placement") ? root.get("placement").asInt() : -1,
                    root.has("playerAugment1") ? ArenaAugments.getArenaAugment(root.get("playerAugment1").asInt()) : null,
                    root.has("playerAugment2") ? ArenaAugments.getArenaAugment(root.get("playerAugment2").asInt()) : null,
                    root.has("playerAugment3") ? ArenaAugments.getArenaAugment(root.get("playerAugment3").asInt()) : null,
                    root.has("playerAugment4") ? ArenaAugments.getArenaAugment(root.get("playerAugment4").asInt()) : null,
                    root.has("playerSubteamId") ? root.get("playerSubteamId").asInt() : -1,
                    root.has("subteamPlacement") ? root.get("subteamPlacement").asInt() : -1);
        }
    }

    public static class RuneDataDeserializer extends JsonDeserializer<RuneData> {

        @Override
        public RuneData deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new RuneData(Runes.getRune(root.get("perk").asInt()), root.get("var1").asInt(), root.get("var2").asInt(), root.get("var3").asInt());
        }
    }

    public static class PingStatsDeserializer extends JsonDeserializer<PingStats> {

        @Override
        public PingStats deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new PingStats(root.get("allInPings").asInt(), root.get("assistMePings").asInt(),
                    root.get("baitPings").asInt(), root.get("basicPings").asInt(), root.get("commandPings").asInt(),
                    root.get("dangerPings").asInt(), root.get("enemyMissingPings").asInt(), root.get("enemyVisionPings").asInt(),
                    root.get("getBackPings").asInt(), root.get("holdPings").asInt(), root.get("needVisionPings").asInt(),
                    root.get("onMyWayPings").asInt(), root.get("pushPings").asInt(), root.get("visionClearedPings").asInt());
        }
    }

    public static class ChallengeStatsDeserializer extends JsonDeserializer<ChallengeStats> {

        @Override
        public ChallengeStats deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);


            return new ChallengeStats(
                    intField(root, "12AssistStreakCount"),
                    intField(root, "abilityUses"),
                    intField(root, "acesBefore15Minutes"),
                    intField(root, "alliedJungleMonsterKills"),
                    intField(root, "baronBuffGoldAdvantageOverThreshold"),
                    intField(root, "baronTakedowns"),
                    intField(root, "blastConeOppositeOpponentCount"),
                    intField(root, "bountyGold"),
                    intField(root, "buffsStolen"),
                    intField(root, "completeSupportQuestInTime"),
                    doubleField(root, "controlWardTimeCoverageInRiverOrEnemyHalf"),
                    intField(root, "controlWardsPlaced"),
                    doubleField(root, "damagePerMinute"),
                    doubleField(root, "damageTakenOnTeamPercentage"),
                    intField(root, "dancedWithRiftHerald"),
                    intField(root, "deathsByEnemyChamps"),
                    intField(root, "dodgeSkillShotsSmallWindow"),
                    intField(root, "doubleAces"),
                    intField(root, "dragonTakedowns"),
                    doubleField(root, "earliestBaron"),
                    doubleField(root, "earliestDragonTakedown"),
                    doubleField(root, "earliestElderDragon"),
                    intField(root, "earlyLaningPhaseGoldExpAdvantage"),
                    doubleField(root, "effectiveHealAndShielding"),
                    intField(root, "elderDragonKillsWithOpposingSoul"),
                    intField(root, "elderDragonMultikills"),
                    intField(root, "enemyChampionImmobilizations"),
                    intField(root, "enemyJungleMonsterKills"),
                    intField(root, "epicMonsterKillsNearEnemyJungler"),
                    intField(root, "epicMonsterKillsWithin30SecondsOfSpawn"),
                    intField(root, "epicMonsterSteals"),
                    intField(root, "epicMonsterStolenWithoutSmite"),
                    doubleField(root, "fasterSupportQuestCompletion"),
                    doubleField(root, "fastestLegendary"),
                    doubleField(root, "firstTurretKilled"),
                    doubleField(root, "firstTurretKilledTime"),
                    intField(root, "flawlessAces"),
                    intField(root, "fullTeamTakedown"),
                    doubleField(root, "gameLength"),
                    intField(root, "getTakedownsInAllLanesEarlyJungleAsLaner"),
                    doubleField(root, "goldPerMinute"),
                    intField(root, "hadAfkTeammate") == 1,
                    intField(root, "hadOpenNexus") == 1,
                    intField(root, "highestChampionDamage"),
                    intField(root, "highestCrowdControlScore"),
                    intField(root, "highestWardKills"),
                    intField(root, "immobilizeAndKillWithAlly"),
                    intField(root, "initialBuffCount"),
                    intField(root, "initialCrabCount"),
                    doubleField(root, "jungleCsBefore10Minutes"),
                    intField(root, "junglerKillsEarlyJungle"),
                    intField(root, "junglerTakedownsNearDamagedEpicMonster"),
                    doubleField(root, "kda"),
                    intField(root, "killAfterHiddenWithAlly"),
                    intField(root, "killedChampTookFullTeamDamageSurvived"),
                    intField(root, "killingSprees"),
                    doubleField(root, "killParticipation"),
                    intField(root, "killsNearEnemyTurret"),
                    intField(root, "killsOnLanersEarlyJungleAsJungler"),
                    intField(root, "killsOnOtherLanesEarlyJungleAsLaner"),
                    intField(root, "killsOnRecentlyHealedByAramPack"),
                    intField(root, "killsUnderOwnTurret"),
                    intField(root, "killsWithHelpFromEpicMonster"),
                    intField(root, "knockEnemyIntoTeamAndKill"),
                    intField(root, "kTurretsDestroyedBeforePlatesFall"),
                    intField(root, "landSkillShotsEarlyGame"),
                    intField(root, "laneMinionsFirst10Minutes"),
                    intField(root, "laningPhaseGoldExpAdvantage"),
                    intField(root, "legendaryCount"),
                    intField(root, "lostAnInhibitor") == 1,
                    intField(root, "maxCsAdvantageOnLaneOpponent"),
                    intField(root, "maxKillDeficit"),
                    intField(root, "maxLevelLeadLaneOpponent"),
                    intField(root, "mejaisFullStackInTime") == 1,
                    doubleField(root, "moreEnemyJungleThanOpponent"),
                    intField(root, "mostWardsDestroyedOneSweeper"),
                    intField(root, "multiKillOneSpell"),
                    intField(root, "multikills"),
                    intField(root, "multikillsAfterAggressiveFlash"),
                    intField(root, "multiTurretRiftHeraldCount"),
                    Items.getItem(intField(root, "mythicItemUsed")),
                    intField(root, "outerTurretExecutesBefore10Minutes"),
                    intField(root, "outnumberedKills"),
                    intField(root, "outnumberedNexusKill"),
                    intField(root, "perfectDragonSoulsTaken"),
                    intField(root, "perfectGame"),
                    intField(root, "pickKillWithAlly"),
                    intField(root, "playedChampSelectPosition"),
                    intField(root, "poroExplosions"),
                    intField(root, "quickCleanse"),
                    intField(root, "quickFirstTurret"),
                    intField(root, "quickSoloKills"),
                    intField(root, "riftHeraldTakedowns"),
                    intField(root, "saveAllyFromDeath"),
                    intField(root, "scuttleCrabKills"),
                    doubleField(root, "shortestTimeToAceFromFirstTakedown"),
                    intField(root, "skillshotsDodged"),
                    intField(root, "skillshotsHit"),
                    intField(root, "snowballsHit"),
                    intField(root, "soloBaronKills"),
                    intField(root, "soloKills"),
                    intField(root, "soloTurretsLategame"),
                    intField(root, "stealthWardsPlaced"),
                    intField(root, "survivedSingleDigitHpCount"),
                    intField(root, "survivedThreeImmobilizesInFight"),
                    intField(root, "takedownOnFirstTurret"),
                    intField(root, "takedowns"),
                    intField(root, "takedownsAfterGainingLevelAdvantage"),
                    intField(root, "takedownsBeforeJungleMinionSpawn"),
                    intField(root, "takedownsFirst25Minutes"),
                    intField(root, "takedownsFirstXMinutes"),
                    intField(root, "takedownsInAlcove"),
                    intField(root, "takedownsInEnemyFountain"),
                    intField(root, "teamBaronKills"),
                    doubleField(root, "teamDamagePercentage"),
                    intField(root, "teamElderDragonKills"),
                    intField(root, "teamRiftHeraldKills"),
                    intField(root, "teleportTakedowns"),
                    doubleField(root, "thirdInhibitorDestroyedTime"),
                    intField(root, "threeWardsOneSweeperCount"),
                    intField(root, "tookLargeDamageSurvived"),
                    intField(root, "turretPlatesTaken"),
                    intField(root, "turretsTakenWithRiftHerald"),
                    intField(root, "turretTakedowns"),
                    intField(root, "twentyMinionsIn3SecondsCount"),
                    intField(root, "unseenRecalls"),
                    doubleField(root, "visionScoreAdvantageLaneOpponent"),
                    doubleField(root, "visionScorePerMinute"),
                    intField(root, "wardsGuarded"),
                    intField(root, "wardTakedowns"),
                    intField(root, "wardTakedownsBefore20M"));
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

            List<MatchParticipant> participants = MAPPER.readerForListOf(MatchParticipant.class).readValue(details.get("participants"));
            List<Team> teams = MAPPER.readerForListOf(Team.class).readValue(details.get("teams"));

            return new MatchDetails(details.get("gameCreation").asLong(), details.get("gameDuration").asInt(),
                    details.get("gameEndTimestamp").asLong(), details.get("gameId").asLong(), details.get("gameMode").asText(),
                    details.get("gameName").asText(), details.get("gameStartTimestamp").asLong(), details.get("gameType").asText(),
                    details.get("gameVersion").asText(), Maps.getMap(details.get("mapId").asInt()), participants,
                    LeaguePlatform.getPlatform(details.get("platformId").asText()), QueueTypes.getQueueType(details.get("queueId").asInt()),
                    teams, details.get("tournamentCode").asText());
        }
    }

    public static class TeamDeserializer extends JsonDeserializer<Team> {

        @Override
        public Team deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            ObjectReader reader = MAPPER.readerFor(Objective.class);
            JsonNode objectives = root.get("objectives");

            Map<Integer, Champion> bans = StreamSupport.stream(root.get("bans").spliterator(), false)
                    .collect(HashMap::new, (map, node) -> map.put(node.get("pickTurn").asInt(), Champions.getChampion(node.get("championId").asInt())), HashMap::putAll);
            return new Team(root.get("teamId").asInt(), root.get("win").asBoolean(), reader.readValue(objectives.get("baron")),
                    reader.readValue(objectives.get("champion")), reader.readValue(objectives.get("dragon")),
                    reader.readValue(objectives.get("inhibitor")), reader.readValue(objectives.get("riftHerald")),
                    reader.readValue(objectives.get("tower")), bans);
        }
    }

    public static class TimelineDeserializer extends JsonDeserializer<Timeline> {
        @Override
        public Timeline deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);
            JsonNode info = root.get("info");

            List<TimelineParticipant> participants = MAPPER.readerForListOf(TimelineParticipant.class).readValue(info.get("participants"));
            List<TimelineFrame> frames = new ArrayList<>();
            for(JsonNode frameNode: info.get("frames")) {
                List<TimelineEvent> events = StreamSupport.stream(frameNode.get("events").spliterator(), false)
                        .map(node -> deserializeEvent(node, participants)).toList();
                Map<Integer, ParticipantFrameData> participantFrames = new HashMap<>();
                for (Map.Entry<String, JsonNode> entry: frameNode.get("participantFrames").properties()) {
                    participantFrames.put(Integer.parseInt(entry.getKey()), MAPPER.readerFor(ParticipantFrameData.class).readValue(entry.getValue()));
                }
                frames.add(new TimelineFrame(frameNode.get("timestamp").asInt(), events, participantFrames));
            }
            return new Timeline(info.get("frameInterval").asInt(), frames, participants);
        }

        private TimelineEvent deserializeEvent(JsonNode event, List<TimelineParticipant> participants) {
            EventType type = EventType.valueOf(event.get("type").asText());
            try {
                switch (type) {
                    case ASCENDED_EVENT, CAPTURE_POINT, PORO_KING_SUMMON -> {
                        return new TimelineEvent(event.get("timestamp").asLong(), type) {
                        };
                    }
                    case BUILDING_KILL -> {
                        return new BuildingKill(event.get("timestamp").asLong(), type, getAssistingParticipants(event, participants),
                                event.get("bounty").asInt(), BuildingKill.BuildingType.valueOf(event.get("buildingType").asText()),
                                getParticipantFromField(event, "killerId", participants),
                                LaneType.valueOf(event.get("laneType").asText()),
                                MAPPER.readerFor(Point.class).readValue(event.get("position")), event.get("teamId").asInt(),
                                event.has("towerType") ? BuildingKill.TowerType.valueOf(event.get("towerType").asText()) : null);
                    }
                    case CHAMPION_KILL -> {
                        List<ChampionKill.DamageData> damageDealt = new ArrayList<>();
                        List<ChampionKill.DamageData> damageReceived = new ArrayList<>();
                        if (event.has("victimDamageDealt")) {
                            damageDealt.addAll(createDamageData(event.get("victimDamageDealt"), participants));
                        }
                        if (event.has("victimDamageReceived")) {
                            damageReceived.addAll(createDamageData(event.get("victimDamageReceived"), participants));
                        }
                        return new ChampionKill(event.get("timestamp").asLong(), type, getAssistingParticipants(event, participants),
                                event.get("bounty").asInt(), event.get("killStreakLength").asInt(),
                                getParticipantFromField(event, "killerId", participants),
                                MAPPER.readerFor(Point.class).readValue(event.get("position")), event.get("shutdownBounty").asInt(),
                                damageDealt, damageReceived,
                                getParticipantFromField(event, "victimId", participants));
                    }
                    case CHAMPION_SPECIAL_KILL -> {
                        return new ChampionSpecialKill(event.get("timestamp").asLong(), type,
                                ChampionSpecialKill.KillType.valueOf(event.get("killType").asText()),
                                getParticipantFromField(event, "killerId", participants),
                                event.has("multiKillLength") ? event.get("multiKillLength").asInt() : 1,
                                MAPPER.readerFor(Point.class).readValue(event.get("position")));
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
                                MAPPER.readerFor(Point.class).readValue(event.get("position")));
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
                                MAPPER.readerFor(Point.class).readValue(event.get("position")), event.get("teamId").asInt());
                    }
                    case WARD_KILL -> {
                        return new WardKill(event.get("timestamp").asLong(), type,
                                getParticipantFromField(event, "killerId", participants),
                                WardType.valueOf(event.get("wardType").asText()));
                    }
                    case WARD_PLACED -> {
                        return new WardPlaced(event.get("timestamp").asLong(), type,
                                getParticipantFromField(event, "creatorId", participants),
                                WardType.valueOf(event.get("wardType").asText()));
                    }
                    default -> throw new IllegalArgumentException("Unknown event type \"" + type + "\" in match timeline");
                }
            } catch (IOException e) {
                Thresh.LOGGER.error("Error when Deserializing Timeline Event! ",e);
            }
            return null;
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

    public static class RuneStyleDeserializer extends JsonDeserializer<RuneStyle> {

        @Override
        public RuneStyle deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new RuneStyle(root.get("id").asInt(), root.get("name").asText(),
                    root.get("icon").asText(), root.get("key").asText());
        }
    }

    public static class RuneStatDeserializer extends JsonDeserializer<RuneStat> {

        @Override
        public RuneStat deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new RuneStat(root.get("id").asInt(), root.get("name").asText(), root.get("iconPath").asText(),
                    root.get("shortDesc").asText(), root.get("longDesc").asText());
        }
    }

    public static class MapDeserializer extends JsonDeserializer<net.petersil98.thresh.data.Map> {

        @Override
        public net.petersil98.thresh.data.Map deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            Sprite sprite = deserializeSprite(root.get("image"));
            return new net.petersil98.thresh.data.Map(root.get("MapId").asInt(), root.get("MapName").asText(),
                    root.get("image").get("full").asText(), sprite);
        }
    }

    //TODO: Add Spells and Passive to Champions
    public static class ChampionDeserializer extends JsonDeserializer<Champion> {

        @Override
        public Champion deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            Sprite sprite = deserializeSprite(root.get("image"));
            List<Skin> skins = MAPPER.readerForListOf(Skin.class).readValue(root.get("skins"));
            Info info = MAPPER.readerFor(Info.class).readValue(root.get("info"));
            Stats stats = MAPPER.readerFor(Stats.class).readValue(root.get("stats"));
            return new Champion(root.get("key").asInt(), root.get("name").asText(),
                    root.get("title").asText(), root.get("image").get("full").asText(), sprite, skins, root.get("lore").asText(),
                    MAPPER.readerForListOf(String.class).readValue(root.get("allytips")),
                    MAPPER.readerForListOf(String.class).readValue(root.get("enemytips")),
                    MAPPER.readerForListOf(String.class).readValue(root.get("tags")),
                    root.get("partype").asText(), info, stats);
        }
    }

    public static class StatsDeserializer extends JsonDeserializer<Stats> {

        @Override
        public Stats deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new Stats((float) root.get("hp").asDouble(), (float) root.get("hpperlevel").asDouble(),
                    (float) root.get("mp").asDouble(), (float) root.get("mpperlevel").asDouble(), root.get("movespeed").asInt(),
                    (float) root.get("armor").asDouble(), (float) root.get("armorperlevel").asDouble(),
                    (float) root.get("spellblock").asDouble(), (float) root.get("spellblockperlevel").asDouble(),
                    root.get("attackrange").asInt(), (float) root.get("hpregen").asDouble(),
                    (float) root.get("hpregenperlevel").asDouble(), (float) root.get("mpregen").asDouble(),
                    (float) root.get("mpregenperlevel").asDouble(), (float) root.get("crit").asDouble(),
                    (float) root.get("critperlevel").asDouble(), (float) root.get("attackdamage").asDouble(),
                    (float) root.get("attackdamageperlevel").asDouble(), (float) root.get("attackspeed").asDouble(),
                    (float) root.get("attackspeedperlevel").asDouble());
        }
    }

    public static class InfoDeserializer extends JsonDeserializer<Info> {

        @Override
        public Info deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new Info(root.get("attack").asInt(), root.get("defense").asInt(),
                    root.get("magic").asInt(), root.get("difficulty").asInt());
        }
    }

    public static class SkinDeserializer extends JsonDeserializer<Skin> {

        @Override
        public Skin deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new Skin(root.get("id").asInt(), root.get("num").asInt(),
                    root.get("name").asText(), root.get("chromas").asBoolean());
        }
    }

    public static class ItemDeserializer extends JsonDeserializer<Item> {

        @Override
        public Item deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            java.util.Map<net.petersil98.thresh.data.Map, Boolean> maps = root.get("maps").properties().stream().collect(Collectors
                    .toMap(entry -> Maps.getMap(Integer.parseInt(entry.getKey())), entry -> entry.getValue().asBoolean()));
            return new Item(root.get("id").asInt(),
                    root.get("name").asText(), root.get("gold").get("base").asInt(), root.get("gold").get("total").asInt(),
                    root.get("gold").get("sell").asInt(), root.get("gold").get("purchasable").asBoolean(),
                    root.get("description").asText(), root.get("plaintext").asText(),
                    root.has("consumed") && root.get("consumed").asBoolean(),
                    root.has("stacks") ? root.get("stacks").asInt() : 1,
                    root.has("depth") ? root.get("depth").asInt() : 1,
                    root.has("consumeOnFull") && root.get("consumeOnFull").asBoolean(),
                    root.has("inStore") && root.get("inStore").asBoolean(),
                    root.has("hideFromAll") && root.get("hideFromAll").asBoolean(),
                    root.has("requiredChampion") ? Champions.getChampionByName(root.get("requiredChampion").asText()) : null,
                    root.has("requiredAlly") ? Champions.getChampionByName(root.get("requiredAlly").asText()) : null,
                    MAPPER.readerForMapOf(Float.class).readValue(root.get("stats")),
                    MAPPER.readerForListOf(String.class).readValue(root.get("tags")),
                    maps.entrySet().stream().filter(java.util.Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList()),
                    deserializeSprite(root.get("image")), root.get("image").get("full").asText(),
                    root.has("effect") ? MAPPER.readerForMapOf(String.class).readValue(root.get("effect")) : java.util.Map.of());
        }
    }

    //TODO: Check modes
    public static class SummonerSpellDeserializer extends JsonDeserializer<SummonerSpell> {

        @Override
        public SummonerSpell deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = jp.getCodec().readTree(jp);

            return new SummonerSpell(root.get("key").asInt(), root.get("name").asText(), root.get("description").asText(),
                    root.get("cooldown").get(0).asInt(), root.get("summonerLevel").asInt(), root.get("range").get(0).asInt(),
                    MAPPER.readerForListOf(String.class).readValue(root.get("modes")), deserializeSprite(root.get("image")),
                    root.get("image").get("full").asText());
        }
    }

    private static Sprite deserializeSprite(JsonNode node) {
        return new Sprite(node.get("sprite").asText(), node.get("group").asText(), node.get("x").asInt(),
                node.get("y").asInt(), node.get("w").asInt(), node.get("h").asInt());
    }
}
