package net.petersil98.thresh.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.constant.Constants;
import net.petersil98.core.data.Sprite;
import net.petersil98.core.util.Loader;
import net.petersil98.core.util.settings.Settings;
import net.petersil98.thresh.collection.*;
import net.petersil98.thresh.data.Challenge;
import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.data.QueueType;
import net.petersil98.thresh.data.SummonerSpell;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.Rune;
import net.petersil98.thresh.data.rune.RuneStat;
import net.petersil98.thresh.data.rune.RuneStyle;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static net.petersil98.thresh.model.Deserializers.MAPPER;

public class LoLLoader extends Loader {

    private static final String LOL_BASE_PATH = BASE_PATH + "lol" + File.separator;
    private static final String RUNE_STATS_FILE_PATH = LOL_BASE_PATH + "runeStats.json";
    private static final String RUNES_AND_RUNE_STYLES_FILE_PATH = LOL_BASE_PATH + "runes.json";
    private static final String MAPS_FILE_PATH = LOL_BASE_PATH + "maps.json";
    private static final String CHAMPIONS_FILE_PATH = LOL_BASE_PATH + "champions.json";
    private static final String QUEUE_TYPES_FILE_PATH = LOL_BASE_PATH + "queues.json";
    private static final String ITEMS_FILE_PATH = LOL_BASE_PATH + "items.json";
    private static final String SUMMONER_SPELLS_FILE_PATH = LOL_BASE_PATH + "summonerSpells.json";
    private static final String CHALLENGES_FILE_PATH = LOL_BASE_PATH + "challenges.json";

    public static java.util.Map<Integer, List<Integer>> ITEMS_FROM = new HashMap<>();
    public static java.util.Map<Integer, List<Integer>> ITEMS_INTO = new HashMap<>();
    public static java.util.Map<Integer, Integer> ITEMS_SPECIAL_RECIPE = new HashMap<>();

    @Override
    protected void load(boolean shouldUpdate) {
        createFilesIfNotExistent();
        if(shouldUpdate) {
            updateRunesAndRuneStylesFile();
            updateRuneStatsFile();
            updateMapsFile();
            updateChampionsFile();
            updateQueueTypesFile();
            updateItemsFile();
            updateSummonerSpellsFile();
            updateChallengesFile();
        }
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

    private void createFilesIfNotExistent() {
        try {
            new File(LOL_BASE_PATH).mkdirs();
            new File(RUNE_STATS_FILE_PATH).createNewFile();
            new File(RUNES_AND_RUNE_STYLES_FILE_PATH).createNewFile();
            new File(MAPS_FILE_PATH).createNewFile();
            new File(CHAMPIONS_FILE_PATH).createNewFile();
            new File(QUEUE_TYPES_FILE_PATH).createNewFile();
            new File(ITEMS_FILE_PATH).createNewFile();
            new File(SUMMONER_SPELLS_FILE_PATH).createNewFile();
            new File(CHALLENGES_FILE_PATH).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRunesAndRuneStylesFile(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(String.format("%scdn/%s/data/%s/runesReforged.json", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Settings.getLanguage().toString())).openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(RUNES_AND_RUNE_STYLES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRuneStyles(){
        try {
            String content = Files.readString(Paths.get(RUNES_AND_RUNE_STYLES_FILE_PATH));
            JsonNode node = MAPPER.readTree(content);
            List<RuneStyle> runeStyles = new ArrayList<>();
            for(JsonNode runeStyle: node) {
                runeStyles.add(new RuneStyle(runeStyle.get("id").asInt(),
                        runeStyle.get("name").asText(),
                        runeStyle.get("icon").asText(),
                        runeStyle.get("key").asText())
                );
            }
            RuneStyles.setRuneStyles(runeStyles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRunes(){
        try {
            String content = Files.readString(Paths.get(RUNES_AND_RUNE_STYLES_FILE_PATH));
            JsonNode json = MAPPER.readTree(content);
            List<Rune> runes = new ArrayList<>();
            for(JsonNode runeStyle: json) {
                for (JsonNode slot : runeStyle.get("slots")) {
                    for (JsonNode rune: slot.get("runes")) {
                        runes.add(new Rune(rune.get("id").asInt(),
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
            Runes.setRunes(runes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRuneStatsFile(){
        try (Scanner sc = new Scanner(new URL("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perks.json").openStream())) {
            sc.useDelimiter("\n");
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.next());
            }
            JsonNode json = MAPPER.readTree(sb.toString());
            Iterator<JsonNode> iterator = json.iterator();
            while (iterator.hasNext()) {
                int id = iterator.next().get("id").asInt();
                if(id < 5000 || id > 5999) {
                    iterator.remove();
                }
            }
            Files.writeString(Paths.get(RUNE_STATS_FILE_PATH), json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRuneStats(){
        try {
            String content = Files.readString(Paths.get(RUNE_STATS_FILE_PATH));
            JsonNode node = MAPPER.readTree(content);
            List<RuneStat> runeStats = new ArrayList<>();
            for(JsonNode runeStyle: node) {
                String iconPath = runeStyle.get("iconPath").asText();
                runeStats.add(new RuneStat(runeStyle.get("id").asInt(),
                        runeStyle.get("name").asText(),
                        iconPath.substring(iconPath.indexOf("perk-images/")),
                        runeStyle.get("shortDesc").asText(),
                        runeStyle.get("longDesc").asText())
                );
            }
            RuneStats.setRuneStats(runeStats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateMapsFile(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(String.format("%scdn/%s/data/%s/map.json", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Settings.getLanguage().toString())).openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(MAPS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMaps(){
        try {
            String content = Files.readString(Paths.get(MAPS_FILE_PATH));
            JsonNode node = MAPPER.readTree(content);
            List<net.petersil98.thresh.data.Map> maps = new ArrayList<>();
            for(JsonNode map: node.get("data")) {
                Sprite sprite = new Sprite(map.get("image").get("sprite").asText(),
                        map.get("image").get("group").asText(),
                        map.get("image").get("x").asInt(),
                        map.get("image").get("y").asInt(),
                        map.get("image").get("w").asInt(),
                        map.get("image").get("h").asInt());
                maps.add(new net.petersil98.thresh.data.Map(map.get("MapId").asInt(),
                        map.get("MapName").asText(),
                        map.get("image").get("full").asText(),
                        sprite)
                );
            }
            Maps.setMaps(maps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateChampionsFile(){
        try {
            String json = createChampionsJSON();
            Files.writeString(Paths.get(CHAMPIONS_FILE_PATH), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChampions(){
        try {
            String content = Files.readString(Paths.get(CHAMPIONS_FILE_PATH));
            Champions.setChampions(MAPPER.readValue(content, TypeFactory.defaultInstance().constructCollectionType(List.class, Champion.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateQueueTypesFile(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(String.format("%squeues.json", Constants.STATIC_DATA_BASE_PATH)).openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(QUEUE_TYPES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQueueTypes(){
        try {
            String content = Files.readString(Paths.get(QUEUE_TYPES_FILE_PATH));
            QueueTypes.setQueueTypes(MAPPER.readValue(content, TypeFactory.defaultInstance().constructCollectionType(List.class, QueueType.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateItemsFile(){
        try (Scanner sc = new Scanner(new URL(String.format("%scdn/%s/data/%s/item.json", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Settings.getLanguage().toString())).openStream())){
            sc.useDelimiter("\n");
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.next());
            }
            JsonNode json = MAPPER.readTree(sb.toString());
            for(JsonNode item: json.get("data")) {
                ((ObjectNode) item).remove("colloq");
            }
            Files.writeString(Paths.get(ITEMS_FILE_PATH), json.get("data").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadItems(){
        try {
            ITEMS_FROM.clear();
            ITEMS_INTO.clear();
            ITEMS_SPECIAL_RECIPE.clear();
            String content = Files.readString(Paths.get(ITEMS_FILE_PATH));
            java.util.Map<String, JsonNode> nodes = MAPPER.readValue(content, new TypeReference<Map<String, JsonNode>>(){});
            List<Item> items = new ArrayList<>();
            for(java.util.Map.Entry<String, JsonNode> entry: nodes.entrySet()) {
                JsonNode node = MAPPER.readTree(entry.getValue().toString());
                java.util.Map<Integer, Boolean> maps = MAPPER.readValue(node.get("maps").toString(), new TypeReference<java.util.Map<Integer, Boolean>>() {});
                items.add(new Item(Integer.parseInt(entry.getKey()),
                                node.get("name").asText(),
                                node.get("gold").get("base").asInt(),
                                node.get("gold").get("total").asInt(),
                                node.get("gold").get("sell").asInt(),
                                node.get("gold").get("purchasable").asBoolean(),
                                node.get("description").asText(),
                                node.get("plaintext").asText(),
                                node.has("consumed") && node.get("consumed").asBoolean(),
                                node.has("stacks") ? node.get("stacks").asInt() : 1,
                                node.has("depth") ? node.get("depth").asInt() : 1,
                                node.has("consumeOnFull") && node.get("consumeOnFull").asBoolean(),
                                node.has("inStore") && node.get("inStore").asBoolean(),
                                node.has("hideFromAll") && node.get("hideFromAll").asBoolean(),
                                node.has("requiredChampion") ? Champions.getChampionByName(node.get("requiredChampion").asText()) : null,
                                node.has("requiredAlly") ? Champions.getChampionByName(node.get("requiredAlly").asText()) : null,
                                MAPPER.readValue(node.get("stats").toString(), new TypeReference<java.util.Map<String, Float>>() {}),
                                MAPPER.readValue(node.get("tags").toString(), new TypeReference<List<String>>() {}),
                                maps.entrySet().stream().filter(java.util.Map.Entry::getValue).map(mapEntry -> Maps.getMap(mapEntry.getKey())).collect(Collectors.toList()),
                                new Sprite(
                                        node.get("image").get("sprite").asText(),
                                        node.get("image").get("sprite").asText(),
                                        node.get("image").get("x").asInt(),
                                        node.get("image").get("y").asInt(),
                                        node.get("image").get("w").asInt(),
                                        node.get("image").get("h").asInt()),
                                node.get("image").get("full").asText(),
                                node.has("effect") ? MAPPER.readValue(node.get("effect").toString(), new TypeReference<java.util.Map<String, String>>() {}) : java.util.Map.of()
                        )
                );
                if(node.has("from")) {
                    ITEMS_FROM.put(Integer.parseInt(entry.getKey()), MAPPER.readValue(node.get("from").toString(), new TypeReference<List<Integer>>() {}));
                }
                if(node.has("into")) {
                    ITEMS_INTO.put(Integer.parseInt(entry.getKey()), MAPPER.readValue(node.get("into").toString(), new TypeReference<List<Integer>>() {}));
                }
                if(node.has("specialRecipe")) {
                    ITEMS_SPECIAL_RECIPE.put(Integer.parseInt(entry.getKey()), node.get("specialRecipe").asInt());
                }
            }
            Items.setItems(items);
            Items.getItems().forEach(Item::postInit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSummonerSpellsFile(){
        try (Scanner sc = new Scanner(new URL(String.format("%scdn/%s/data/%s/summoner.json", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Settings.getLanguage().toString())).openStream())) {
            sc.useDelimiter("\n");
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.next());
            }
            JsonNode json = MAPPER.readTree(sb.toString());
            for(JsonNode summonerSpell: json.get("data")) {
                ((ObjectNode) summonerSpell).remove("tooltip");
                ((ObjectNode) summonerSpell).remove("cooldownBurn");
                ((ObjectNode) summonerSpell).remove("cost");
                ((ObjectNode) summonerSpell).remove("costBurn");
                ((ObjectNode) summonerSpell).remove("datavalues");
                ((ObjectNode) summonerSpell).remove("effect");
                ((ObjectNode) summonerSpell).remove("effectBurn");
                ((ObjectNode) summonerSpell).remove("vars");
                ((ObjectNode) summonerSpell).remove("costType");
                ((ObjectNode) summonerSpell).remove("maxammo");
                ((ObjectNode) summonerSpell).remove("rangeBurn");
                ((ObjectNode) summonerSpell).remove("resource");
                ((ObjectNode) summonerSpell).set("cooldown", summonerSpell.get("cooldown").get(0));
                ((ObjectNode) summonerSpell).set("range", summonerSpell.get("range").get(0));
            }
            Files.writeString(Paths.get(SUMMONER_SPELLS_FILE_PATH), json.get("data").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSummonerSpells(){
        try {
            String content = Files.readString(Paths.get(SUMMONER_SPELLS_FILE_PATH));
            JsonNode summonerSpells = MAPPER.readTree(content);
            List<SummonerSpell> list = new ArrayList<>();
            for(JsonNode summonerSpell: summonerSpells) {
                list.add(new SummonerSpell(summonerSpell.get("key").asInt(),
                                summonerSpell.get("name").asText(),
                                summonerSpell.get("description").asText(),
                                summonerSpell.get("cooldown").asInt(),
                                summonerSpell.get("summonerLevel").asInt(),
                                summonerSpell.get("range").asInt(),
                                MAPPER.readValue(summonerSpell.get("modes").toString(), new TypeReference<List<String>>() {}),
                                new Sprite(
                                        summonerSpell.get("image").get("sprite").asText(),
                                        summonerSpell.get("image").get("sprite").asText(),
                                        summonerSpell.get("image").get("x").asInt(),
                                        summonerSpell.get("image").get("y").asInt(),
                                        summonerSpell.get("image").get("w").asInt(),
                                        summonerSpell.get("image").get("h").asInt()),
                                summonerSpell.get("image").get("full").asText()
                        )
                );
            }
            SummonerSpells.setSummonerSpells(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateChallengesFile() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(String.format("%scdn/%s/data/%s/challenges.json", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Settings.getLanguage().toString())).openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(CHALLENGES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChallenges(){
        try {
            String content = Files.readString(Paths.get(CHALLENGES_FILE_PATH));
            Challenges.setChallenges(MAPPER.readValue(content, TypeFactory.defaultInstance().constructCollectionType(List.class, Challenge.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createChampionsJSON() {
        try {
            InputStream in = new URL(String.format("%scdn/%s/data/%s/championFull.json", Constants.DDRAGON_BASE_PATH, Constants.DDRAGON_VERSION, Settings.getLanguage().toString())).openConnection().getInputStream();
            JsonNode json = MAPPER.readTree(IOUtils.toString(new InputStreamReader(in)));
            ArrayNode node = MAPPER.createArrayNode();
            for(JsonNode champion: json.get("data")) {
                renameFieldInNode((ObjectNode) champion, "key", "id");
                ((ObjectNode) champion).set("fullImage", champion.get("image").get("full"));
                ObjectNode sprite = MAPPER.createObjectNode();
                sprite.set("sprite", champion.get("image").get("sprite"));
                sprite.set("group", champion.get("image").get("group"));
                sprite.set("x", champion.get("image").get("x"));
                sprite.set("y", champion.get("image").get("y"));
                sprite.set("width", champion.get("image").get("w"));
                sprite.set("height", champion.get("image").get("h"));
                ((ObjectNode) champion).set("sprite", sprite);
                ((ObjectNode) champion).remove("image");
                for(JsonNode skin: champion.get("skins")) {
                    renameFieldInNode((ObjectNode) skin, "num", "id");
                }
                ((ObjectNode) champion).remove("blurb");
                renameFieldInNode((ObjectNode) champion, "allytips", "allyTips");
                renameFieldInNode((ObjectNode) champion, "enemytips", "enemyTips");
                renameFieldInNode((ObjectNode) champion, "partype", "resourceType");
                ((ObjectNode) champion).remove("info");
                ObjectNode stats = (ObjectNode) champion.get("stats");
                renameFieldInNode(stats, "hp", "health");
                renameFieldInNode(stats, "hpperlevel", "healthPerLevel");
                renameFieldInNode(stats, "mp", "resource");
                renameFieldInNode(stats, "mpperlevel", "resourcePerLevel");
                renameFieldInNode(stats, "movespeed", "movementSpeed");
                renameFieldInNode(stats, "armorperlevel", "armorPerLevel");
                renameFieldInNode(stats, "spellblock", "magicResist");
                renameFieldInNode(stats, "spellblockperlevel", "magicResistPerLevel");
                renameFieldInNode(stats, "attackrange", "attackRange");
                renameFieldInNode(stats, "hpregen", "healthRegeneration");
                renameFieldInNode(stats, "hpregenperlevel", "healthRegenerationPerLevel");
                renameFieldInNode(stats, "mpregen", "resourceRegeneration");
                renameFieldInNode(stats, "mpregenperlevel", "resourceRegenerationPerLevel");
                renameFieldInNode(stats, "crit", "critChance");
                renameFieldInNode(stats, "critperlevel", "critChancePerLevel");
                renameFieldInNode(stats, "attackdamage", "attackDamage");
                renameFieldInNode(stats, "attackdamageperlevel", "attackDamagePerLevel");
                renameFieldInNode(stats, "attackspeed", "attackSpeed");
                renameFieldInNode(stats, "attackspeedperlevel", "attackSpeedPerLevel");
                renameFieldInNode(stats, "attackdamage", "attackDamage");
                ((ObjectNode) champion).remove("spells");
                ((ObjectNode) champion).remove("passive");
                ((ObjectNode) champion).remove("recommended");
                node.add(champion);
            }
            return node.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void renameFieldInNode(ObjectNode node, String fieldName, String newFieldName) {
        node.set(newFieldName, node.get(fieldName));
        node.remove(fieldName);
    }
}