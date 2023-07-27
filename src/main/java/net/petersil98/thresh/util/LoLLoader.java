package net.petersil98.thresh.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.Core;
import net.petersil98.core.util.Loader;
import net.petersil98.core.util.settings.Settings;
import net.petersil98.stcommons.constants.STConstants;
import net.petersil98.thresh.Thresh;
import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.data.*;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.Rune;
import net.petersil98.thresh.data.rune.RuneStat;
import net.petersil98.thresh.data.rune.RuneStyle;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.petersil98.thresh.model.Deserializers.MAPPER;

//TODO: Check image locations
public class LoLLoader extends Loader {

    private static String latestDDragonVersion;

    public static java.util.Map<Integer, List<Integer>> ITEMS_FROM = new HashMap<>();
    public static java.util.Map<Integer, List<Integer>> ITEMS_INTO = new HashMap<>();
    public static java.util.Map<Integer, Integer> ITEMS_SPECIAL_RECIPE = new HashMap<>();

    @Override
    protected void load() {
        if(latestDDragonVersion == null) loadLatestVersions();
        loadRuneStyles();
        loadRunes();
        loadRuneStats();
        loadMaps();
        loadChampions();
        loadQueueTypes();
        loadItems();
        loadSummonerSpells();
        loadChallenges();
        loadArenaAugments();
    }

    @Override
    protected boolean shouldReloadData() {
        String versionsUrl = STConstants.DDRAGON_BASE_PATH + "api/versions.json";
        try(InputStream lolVersion = URI.create(versionsUrl).toURL().openConnection().getInputStream()) {
            String[] versions = Core.MAPPER.readValue(lolVersion, TypeFactory.defaultInstance().constructArrayType(String.class));
            STConstants.DDRAGON_VERSION = versions[0];
            if(!latestDDragonVersion.equals(STConstants.DDRAGON_VERSION)) {
                latestDDragonVersion = STConstants.DDRAGON_VERSION;
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static void loadLatestVersions() {
        String versionsUrl = STConstants.DDRAGON_BASE_PATH + "api/versions.json";
        try(InputStream lolVersion = URI.create(versionsUrl).toURL().openConnection().getInputStream()) {
            String[] versions = Core.MAPPER.readValue(lolVersion, TypeFactory.defaultInstance().constructArrayType(String.class));
            STConstants.DDRAGON_VERSION = versions[0];
            latestDDragonVersion = versions[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRuneStyles() {
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/runesReforged.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            List<RuneStyle> runeStyles = MAPPER.readerForListOf(RuneStyle.class).readValue(MAPPER.readTree(in));
            setFieldInCollection(RuneStyles.class, runeStyles.stream().collect(Collectors.toMap(RuneStyle::getId, runeStyle -> runeStyle)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadRunes() {
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/runesReforged.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            Map<Integer, Rune> runes = new HashMap<>();
            for(JsonNode runeStyle: MAPPER.readTree(in)) {
                for (JsonNode slot : runeStyle.get("slots")) {
                    for (JsonNode rune: slot.get("runes")) {
                        int id = rune.get("id").asInt();
                        runes.put(id, new Rune(id,
                                rune.get("name").asText(),
                                rune.get("icon").asText(),
                                rune.get("key").asText(),
                                rune.get("shortDesc").asText(),
                                rune.get("longDesc").asText(),
                                RuneStyles.getRuneStyle(runeStyle.get("id").asInt()))
                        );
                    }
                }
            }
            setFieldInCollection(Runes.class, runes);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadRuneStats() {
        try (InputStream in = new URI("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perks.json").toURL().openStream()) {
            Iterator<JsonNode> iterator = MAPPER.readTree(in).iterator();
            Map<Integer, RuneStat> runeStats = new HashMap<>();
            while (iterator.hasNext()) {
                JsonNode runeStyle = iterator.next();
                int id = runeStyle.get("id").asInt();
                if(id >= 5000 && id <= 5999) {
                    RuneStat stat = MAPPER.readerFor(RuneStat.class).readValue(runeStyle);
                    runeStats.put(stat.getId(), stat);
                }
            }
            setFieldInCollection(RuneStats.class, runeStats);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadMaps() {
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/map.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            Map<Integer, net.petersil98.thresh.data.Map> maps = new HashMap<>();
            for(JsonNode node: MAPPER.readTree(in).get("data")) {
                net.petersil98.thresh.data.Map map = MAPPER.readerFor(net.petersil98.thresh.data.Map.class).readValue(node);
                maps.put(map.id(), map);
            }
            setFieldInCollection(Maps.class, maps);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadChampions() {
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/championFull.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openConnection().getInputStream()) {
            Map<Integer, Champion> champions = new HashMap<>();
            for(JsonNode node: MAPPER.readTree(in).get("data")) {
                Champion champion = MAPPER.readerFor(Champion.class).readValue(node);
                champions.put(champion.getId(), champion);
            }
            setFieldInCollection(Champions.class, champions);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadQueueTypes() {
        try(InputStream in = new URI("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/queues.json").toURL().openStream()) {
            JsonNode root = MAPPER.readTree(in);
            Map<Integer, QueueType> queueTypes = new HashMap<>();
            root.properties().forEach(entry -> ((ObjectNode)entry.getValue()).put("id", entry.getKey()));
            for(JsonNode node: root) {
                QueueType queueType = MAPPER.readerFor(QueueType.class).readValue(node);
                queueTypes.put(queueType.getId(), queueType);
            }
            setFieldInCollection(QueueTypes.class, queueTypes);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadItems() {
        try (InputStream in = new URI(String.format("%scdn/%s/data/%s/item.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()){
            ITEMS_FROM.clear();
            ITEMS_INTO.clear();
            ITEMS_SPECIAL_RECIPE.clear();
            JsonNode data = MAPPER.readTree(in).get("data");
            Map<Integer, Item> items = new HashMap<>();
            data.properties().forEach(entry -> ((ObjectNode)entry.getValue()).put("id", entry.getKey()));
            for(JsonNode node: data) {
                Item item = MAPPER.readerFor(Item.class).readValue(node);
                items.put(item.getId(), item);
                if(node.has("from")) {
                    ITEMS_FROM.put(item.getId(), MAPPER.readerForListOf(Integer.class).readValue(node.get("from")));
                }
                if(node.has("into")) {
                    ITEMS_INTO.put(item.getId(), MAPPER.readerForListOf(Integer.class).readValue(node.get("into")));
                }
                if(node.has("specialRecipe")) {
                    ITEMS_SPECIAL_RECIPE.put(item.getId(), node.get("specialRecipe").asInt());
                }
            }
            setFieldInCollection(Items.class, items);
            Items.getItems().forEach(Item::postInit);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadSummonerSpells() {
        try (InputStream in = new URI(String.format("%scdn/%s/data/%s/summoner.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            Map<Integer, SummonerSpell> summonerSpells = new HashMap<>();
            for(JsonNode node: MAPPER.readTree(in).get("data")) {
                SummonerSpell summonerSpell = MAPPER.readerFor(SummonerSpell.class).readValue(node);
                summonerSpells.put(summonerSpell.getId(), summonerSpell);
            }
            setFieldInCollection(SummonerSpells.class, summonerSpells);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadChallenges() {
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/challenges.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            List<Challenge> challenges = MAPPER.readerForListOf(Challenge.class).readValue(in);
            setFieldInCollection(Challenges.class, challenges.stream().collect(Collectors.toMap(Challenge::getId, challenge -> challenge)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadArenaAugments() {
        try(InputStream in = new URI(String.format("https://raw.communitydragon.org/latest/cdragon/arena/%s.json", Settings.getLanguage().toString().toLowerCase())).toURL().openStream()) {
            List<ArenaAugment> arenaAugments = MAPPER.readerForListOf(ArenaAugment.class).readValue(MAPPER.readTree(in).get("augments"));
            setFieldInCollection(ArenaAugments.class, arenaAugments.stream().collect(Collectors.toMap(ArenaAugment::getId, augment -> augment)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void setFieldInCollection(Class<?> collectionClass, Map<?, ?> elements) {
        try {
            char[] fieldName = collectionClass.getSimpleName().toCharArray();
            fieldName[0] += 32;
            Field field = collectionClass.getDeclaredField(new String(fieldName));
            field.setAccessible(true);
            field.set(null, elements);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Thresh.LOGGER.error("Couldn't set collection Type of class " + collectionClass.getSimpleName(), e);
        }
    }
}