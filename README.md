# Thresh

Thresh is an Object-Oriented Java Library, which takes over the Communication with the League of Legends API. It supports In-Memory caching and uses a (blocking) Rate Limiter. It makes retrieving Summoner Data, Match History,
etc. much easier.

Other Projects:
- [Spatula](https://github.com/Petersil1998/Spatula) for Teamfight Tactics
- [Scuttlegeist](https://github.com/Petersil1998/Scuttlegeist) for Legends of Runeterra
- [Fade](https://github.com/Petersil1998/Fade) for Valorant

## Usage

Thresh can be included like this using **Gradle**:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    ...
    implementation 'com.github.Petersil1998:Core:v1.4'
    implementation 'com.github.Petersil1998:STCommons:v1.4'
    implementation 'com.github.Petersil1998:Thresh-Java:v1.4'
}
```

or using **Maven**:

```XML
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Petersil1998</groupId>
    <artifactId>Core</artifactId>
    <version>v1.4</version>
</dependency>
<dependency>
    <groupId>com.github.Petersil1998</groupId>
    <artifactId>STCommons</artifactId>
    <version>v1.4</version>
</dependency>
<dependency>
    <groupId>com.github.Petersil1998</groupId>
    <artifactId>Thresh-Java</artifactId>
    <version>v1.4</version>
</dependency>
```

## Setup

In Order for Thresh to work properly there are a few things you need to set up
at the beginning of your application.

```JAVA
public class Example {
    public static void main(String[] args) {
        // First we need to provide our Riot API Key. Ideally the API Key is encrypted
        Settings.setAPIKey(() -> EncryptionUtil.encrypt(System.getenv("API_KEY")));
        // If the provided API Key is encrypted, we need to provide a function to decrypt the API Key
        Settings.setDecryptor(EncryptionUtil::decrypt);
        // We also need to provide a language. The language is used to static Data like Champions, Item, etc.
        Settings.setLanguage(Language.EN_US);
        // If we want to use caching we can enable it in the Settings. Caching is disabled by default
        Settings.useCache(true);
        // We also need to add the Loader for the static LoL Data
        Loader.addLoader(new LoLLoader());
        // Lastly we need to initialize the static Data
        Loader.init();
    }
}
```

Now Thresh is ready and set up!

## Examples

- **Summoner and Account Data**

    ```JAVA
    public class Example {
        public static void main(String[] args) {
            // Setup code...
            
            Summoner faker = Summoner.getSummonerByName("Faker", LeaguePlatform.KR);
            int summonerLevel = faker.getSummonerLevel();
            // Get the URL for the profile Icon
            String profileIconURL = Util.getProfileIconURL(faker.getProfileIcon());
            // Get Account
            Account account = Account.getAccountByPuuid(faker.getPuuid(), LeagueRegion.ASIA);
            // Get the Tag (e.g. Faker#KR1)
            String tag = account.toString();
        }
    } 
    ```

- **Champion Mastery**

    ```JAVA
    public class Example { 
        public static void main(String[] args) {
            // Setup code...
           
            Summoner faker = Summoner.getSummonerByName("Faker", LeaguePlatform.KR);
            ChampionMasteries masteries = ChampionMasteries.getChampionMasteriesOfSummoner(faker.getId(), LeaguePlatform.KR);
            // Get Mastery Score
            int masteryScore = masteries.getTotalMasteryPoints();
            // Get Mastery Points on all Champions Combined
            int totalMasteryPoints = masteries.getTotalMasteryPointsCombined();
            // Get a List of all Champion Masteries
            List<ChampionMasteries.Mastery> championMasteries = masteries.getChampionMasteries();
            for(ChampionMasteries.Mastery mastery: championMasteries) {
                // Get the Champion
                Champion champion = mastery.getChampion();
                // Get the Mastery Level on that Champion
                int masteryLevel = mastery.getChampionLevel();
                // Get the Mastery Points on that Champion
                int masteryPoints = mastery.getChampionPoints();
            }
        }
    } 
    ```

- **Rank and Leagues**

    ```JAVA
    public class Example {
        public static void main(String[] args) {
            // Setup code...
            
            Summoner faker = Summoner.getSummonerByName("Faker", LeaguePlatform.KR);
            // Get Solo/Duo and Flex Rank
            PlayerRanks ranked = LoLRanked.getLoLRanksOfSummoner(faker.getId(), LeaguePlatform.KR);
            RankEntry soloDuo = ranked.getRankSoloDuo();
            int lp = soloDuo.getLeaguePoints();
            RankEntry flex = ranked.getRankFlex5v5();
  
            // Get Challenger Solo Queue Players
            League challengers = LoLRanked.getChallengerLeague(RankedQueue.SOLO_DUO, LeaguePlatform.EUW);
            for(LeagueEntry leagueEntry: challengers.getEntries()) {
                // Get all players and their LP
                Summoner player = Summoner.getSummonerByID(leagueEntry.getSummonerId(), LeaguePlatform.EUW);
                int playerLp = leagueEntry.getLeaguePoints();
            }
  
            // Get a list of Gold 1 Flex Players
            List<RankEntry> firstPage = LoLRanked.getRankEntries(RankedDivision.I, RankedTier.GOLD, RankedQueue.FLEX, LeaguePlatform.NA);
            List<RankEntry> secondPage = LoLRanked.getRankEntries(RankedDivision.I, RankedTier.GOLD, RankedQueue.FLEX, LeaguePlatform.NA, 2);
        }
    } 
    ```

- **Active Games**

    ```JAVA
    public class Example {
        public static void main(String[] args) {
            // Setup code...
            
            Summoner faker = Summoner.getSummonerByName("Faker", LeaguePlatform.NA);
            // Get Active Game of a given Summoner
            ActiveGame game = ActiveGame.ofSummoner(faker.getId(), LeaguePlatform.NA);
            Map map = game.getMap();
            String gameMode = game.getGameMode();
            int duration = game.getDuration();
            // Get all Players of the blue Side Team
            List<Participant> blueTeam = game.getBlueSideTeam();
            // Get a String, that can be entered in the Windows Commandline to spectate the Game
            String cmd = game.getSpectatorCommandWindows("C:\\");
        } 
    } 
    ```

- **Match History**

    ```JAVA
    public class Example {
        public static void main(String[] args) {
            // Setup code...
            
            Summoner faker = Summoner.getSummonerByName("Faker", LeaguePlatform.NA);
            // Get the Player's Match History. The Seconds Parameter is a Filter.
            List<MatchDetails> matchHistory = MatchDetails.getMatchHistory(faker.getId(), LeagueRegion.ASIA, Map.of());
            MatchDetails latestGame = matchHistory.get(0);
            int duration = latestGame.getGameDuration();
            // Get Team-based Info like bans, barons killed, turrets killed, etc.
            List<Team> teams = latestGame.getTeams();
            // Get all the Players. Match Participants have A LOT of data like Items bought, Champion played,
            // Summoner Spells used, Damage Dealt, Pings used, etc.
            List<MatchParticipant> participants = latestGame.getParticipants();
        } 
    } 
    ```
  The Match History Filter can have the following values:

  | Key       | Description                                                                                                                                                                                                      | Type   |
  |-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
  | startTime | Unix Timestamp in seconds. Only start Times after June 16th 2021 will have an effect. [More](https://developer.riotgames.com/apis#match-v5/GET_getMatchIdsByPUUID)                                               | long   | 
  | endTime   | Unix Timestamp in seconds.                                                                                                                                                                                       | long   |
  | queue     | Queue ID. Works mutually inclusive with **type**. [QueueTypes](https://github.com/Petersil1998/Thresh-Java/blob/master/src/main/java/net/petersil98/thresh/collection/QueueTypes.java) contains all valid queues | int    |
  | type      | Type of a Match. Works mutually inclusive with **queue**                                                                                                                                                         | String |
  | start     | The offset of the first Match entry                                                                                                                                                                              | int    |
  | count     | The Amount of Matches entries to return. Has to be between 0 and 100                                                                                                                                             | int    |

  **Note**: *All values need to be passed as **Strings** in the filter*


- **Collections**

    The package [collection](https://github.com/Petersil1998/Thresh-Java/blob/main/src/main/java/net/petersil98/thresh/collection/) contains a bunch of Collections for static Data including:
  
    - Challenges
    - Champions
    - Items
    - Maps
    - QueueTypes
    - Runes
    - RuneStats (The 3 stats you can choose in a Rune Page like adaptive Force, Armor, MR, etc.)
    - RuneStyles (Precision, Domination, Sorcery, Resolve, Inspiration)
    - Summoner Spells

### Feel free to give Feedback and add suggestions on how this library can be improved. <br>Thank you for using Thresh, you're awesome!
