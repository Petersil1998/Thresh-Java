package net.petersil98.thresh.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.Core;
import net.petersil98.core.util.Loader;
import net.petersil98.core.util.settings.Settings;
import net.petersil98.stcommons.constants.STConstants;
import net.petersil98.stcommons.data.Sprite;
import net.petersil98.thresh.Thresh;
import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.constants.LoLConstants;
import net.petersil98.thresh.data.Challenge;
import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.data.QueueType;
import net.petersil98.thresh.data.SummonerSpell;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.champion.Info;
import net.petersil98.thresh.data.champion.Skin;
import net.petersil98.thresh.data.champion.Stats;
import net.petersil98.thresh.data.rune.Rune;
import net.petersil98.thresh.data.rune.RuneStat;
import net.petersil98.thresh.data.rune.RuneStyle;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    }

    @Override
    protected boolean shouldReloadData() {
        String versionsUrl = STConstants.DDRAGON_BASE_PATH + "api/versions.json";
        try(InputStream lolVersion = URI.create(versionsUrl).toURL().openConnection().getInputStream()) {
            String[] versions = Core.MAPPER.readValue(IOUtils.toString(new InputStreamReader(lolVersion)), TypeFactory.defaultInstance().constructArrayType(String.class));
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
            String[] versions = Core.MAPPER.readValue(IOUtils.toString(new InputStreamReader(lolVersion)), TypeFactory.defaultInstance().constructArrayType(String.class));
            STConstants.DDRAGON_VERSION = versions[0];
            latestDDragonVersion = versions[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRuneStyles() {
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/runesReforged.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            Map<Integer, RuneStyle> runeStyles = new HashMap<>();
            for(JsonNode runeStyle: MAPPER.readTree(in)) {
                int id = runeStyle.get("id").asInt();
                runeStyles.put(id, new RuneStyle(id,
                        runeStyle.get("name").asText(),
                        runeStyle.get("icon").asText(),
                        runeStyle.get("key").asText())
                );
            }
            setFieldInCollection(RuneStyles.class, runeStyles);
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
                    runeStats.put(id, new RuneStat(id,
                            runeStyle.get("name").asText(),
                            runeStyle.get("iconPath").asText(),
                            runeStyle.get("shortDesc").asText(),
                            runeStyle.get("longDesc").asText())
                    );
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
            for(JsonNode map: MAPPER.readTree(in).get("data")) {
                Sprite sprite = new Sprite(map.get("image").get("sprite").asText(),
                        map.get("image").get("group").asText(),
                        map.get("image").get("x").asInt(),
                        map.get("image").get("y").asInt(),
                        map.get("image").get("w").asInt(),
                        map.get("image").get("h").asInt());
                int id = map.get("MapId").asInt();
                maps.put(id, new net.petersil98.thresh.data.Map(id,
                        map.get("MapName").asText(),
                        map.get("image").get("full").asText(),
                        sprite)
                );
            }
            setFieldInCollection(Maps.class, maps);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadChampions() {
        //TODO: Add Spells and Passive to Champion
        try(InputStream in = new URI(String.format("%scdn/%s/data/%s/championFull.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openConnection().getInputStream()) {
            Map<Integer, Champion> champions = new HashMap<>();
            for(JsonNode champion: MAPPER.readTree(in).get("data")) {
                JsonNode spriteNode = champion.get("image");
                Sprite sprite = new Sprite(spriteNode.get("sprite").asText(), spriteNode.get("group").asText(),
                        spriteNode.get("x").asInt(), spriteNode.get("y").asInt(), spriteNode.get("w").asInt(), spriteNode.get("h").asInt());
                List<Skin> skins = StreamSupport.stream(champion.get("skins").spliterator(), false)
                                .map(node -> new Skin(node.get("id").asInt(), node.get("num").asInt(),
                                        node.get("name").asText(), node.get("chromas").asBoolean())).toList();
                JsonNode infoNode = champion.get("info");
                Info info = new Info(infoNode.get("attack").asInt(), infoNode.get("defense").asInt(),
                        infoNode.get("magic").asInt(),infoNode.get("difficulty").asInt());
                JsonNode statsNode = champion.get("stats");
                Stats stats = new Stats((float) statsNode.get("hp").asDouble(), (float) statsNode.get("hpperlevel").asDouble(),
                        (float) statsNode.get("mp").asDouble(), (float) statsNode.get("mpperlevel").asDouble(), statsNode.get("movespeed").asInt(),
                        (float) statsNode.get("armor").asDouble(), (float) statsNode.get("armorperlevel").asDouble(),
                        (float) statsNode.get("spellblock").asDouble(), (float) statsNode.get("spellblockperlevel").asDouble(),
                        statsNode.get("attackrange").asInt(), (float) statsNode.get("hpregen").asDouble(),
                        (float) statsNode.get("hpregenperlevel").asDouble(), (float) statsNode.get("mpregen").asDouble(),
                        (float) statsNode.get("mpregenperlevel").asDouble(), (float) statsNode.get("crit").asDouble(),
                        (float) statsNode.get("critperlevel").asDouble(), (float) statsNode.get("attackdamage").asDouble(),
                        (float) statsNode.get("attackdamageperlevel").asDouble(), (float) statsNode.get("attackspeed").asDouble(),
                        (float) statsNode.get("attackspeedperlevel").asDouble());
                int id = champion.get("key").asInt();
                champions.put(id, new Champion(id, champion.get("name").asText(),
                        champion.get("title").asText(), spriteNode.get("full").asText(), sprite, skins, champion.get("lore").asText(),
                        MAPPER.readValue(champion.get("allytips").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class)),
                        MAPPER.readValue(champion.get("enemytips").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class)),
                        MAPPER.readValue(champion.get("tags").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class)),
                        champion.get("partype").asText(), info, stats));
            }
            setFieldInCollection(Champions.class, champions);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadQueueTypes() {
        try(InputStream in = new URI(String.format("%squeues.json", LoLConstants.STATIC_DATA_BASE_PATH)).toURL().openStream()) {
            List<QueueType> queueTypes = MAPPER.readerForListOf(QueueType.class).readValue(in);
            setFieldInCollection(QueueTypes.class, queueTypes.stream().collect(Collectors.toMap(QueueType::getId, queueType -> queueType)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadItems() {
        try (InputStream in = new URI(String.format("%scdn/%s/data/%s/item.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()){
            ITEMS_FROM.clear();
            ITEMS_INTO.clear();
            ITEMS_SPECIAL_RECIPE.clear();
            Map<Integer, Item> items = new HashMap<>();
            Map<Integer, JsonNode> itemNodes = MAPPER.readValue(MAPPER.readTree(in).get("data").toString(), TypeFactory.defaultInstance().constructMapType(Map.class, Integer.class, JsonNode.class));
            for(Map.Entry<Integer, JsonNode> itemNode: itemNodes.entrySet()) {
                JsonNode item = itemNode.getValue();
                java.util.Map<Integer, Boolean> maps = MAPPER.readValue(item.get("maps").toString(), TypeFactory.defaultInstance().constructMapType(Map.class, Integer.class, Boolean.class));
                items.put(itemNode.getKey(), new Item(itemNode.getKey(),
                        item.get("name").asText(),
                        item.get("gold").get("base").asInt(),
                        item.get("gold").get("total").asInt(),
                        item.get("gold").get("sell").asInt(),
                        item.get("gold").get("purchasable").asBoolean(),
                        item.get("description").asText(),
                        item.get("plaintext").asText(),
                        item.has("consumed") && item.get("consumed").asBoolean(),
                        item.has("stacks") ? item.get("stacks").asInt() : 1,
                        item.has("depth") ? item.get("depth").asInt() : 1,
                        item.has("consumeOnFull") && item.get("consumeOnFull").asBoolean(),
                        item.has("inStore") && item.get("inStore").asBoolean(),
                        item.has("hideFromAll") && item.get("hideFromAll").asBoolean(),
                        item.has("requiredChampion") ? Champions.getChampionByName(item.get("requiredChampion").asText()) : null,
                        item.has("requiredAlly") ? Champions.getChampionByName(item.get("requiredAlly").asText()) : null,
                                MAPPER.readValue(item.get("stats").toString(), TypeFactory.defaultInstance().constructMapType(Map.class, String.class, Float.class)),
                                MAPPER.readValue(item.get("tags").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class)),
                                maps.entrySet().stream().filter(java.util.Map.Entry::getValue).map(mapEntry -> Maps.getMap(mapEntry.getKey())).collect(Collectors.toList()),
                                new Sprite(
                                        item.get("image").get("sprite").asText(),
                                        item.get("image").get("sprite").asText(),
                                        item.get("image").get("x").asInt(),
                                        item.get("image").get("y").asInt(),
                                        item.get("image").get("w").asInt(),
                                        item.get("image").get("h").asInt()),
                        item.get("image").get("full").asText(),
                        item.has("effect") ? MAPPER.readValue(item.get("effect").toString(), TypeFactory.defaultInstance().constructMapType(Map.class, String.class, String.class)) : java.util.Map.of()
                ));
                if(item.has("from")) {
                    ITEMS_FROM.put(itemNode.getKey(), MAPPER.readValue(item.get("from").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, Integer.class)));
                }
                if(item.has("into")) {
                    ITEMS_INTO.put(itemNode.getKey(), MAPPER.readValue(item.get("into").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, Integer.class)));
                }
                if(item.has("specialRecipe")) {
                    ITEMS_SPECIAL_RECIPE.put(itemNode.getKey(), item.get("specialRecipe").asInt());
                }
            }
            setFieldInCollection(Items.class, items);
            Items.getItems().forEach(Item::postInit);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadSummonerSpells() {
        //TODO: Check modes
        try (InputStream in = new URI(String.format("%scdn/%s/data/%s/summoner.json", STConstants.DDRAGON_BASE_PATH, STConstants.DDRAGON_VERSION, Settings.getLanguage().toString())).toURL().openStream()) {
            Map<Integer, SummonerSpell> summonerSpells = new HashMap<>();
            for(JsonNode summonerSpell: MAPPER.readTree(in).get("data")) {
                int id = summonerSpell.get("key").asInt();
                summonerSpells.put(id, new SummonerSpell(id,
                        summonerSpell.get("name").asText(),
                        summonerSpell.get("description").asText(),
                        summonerSpell.get("cooldown").get(0).asInt(),
                        summonerSpell.get("summonerLevel").asInt(),
                        summonerSpell.get("range").get(0).asInt(),
                        MAPPER.readValue(summonerSpell.get("modes").toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class)),
                        new Sprite(
                                summonerSpell.get("image").get("sprite").asText(),
                                summonerSpell.get("image").get("sprite").asText(),
                                summonerSpell.get("image").get("x").asInt(),
                                summonerSpell.get("image").get("y").asInt(),
                                summonerSpell.get("image").get("w").asInt(),
                                summonerSpell.get("image").get("h").asInt()),
                        summonerSpell.get("image").get("full").asText()
                ));
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