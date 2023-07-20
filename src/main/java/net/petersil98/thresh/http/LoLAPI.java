package net.petersil98.thresh.http;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.util.settings.Settings;
import net.petersil98.stcommons.constants.LeaguePlatform;
import net.petersil98.stcommons.constants.LeagueRegion;
import net.petersil98.stcommons.http.LeagueAPI;

import java.util.HashMap;
import java.util.Map;

public class LoLAPI extends LeagueAPI {

    private static final String LOL_CHAMPION_MASTERY_V4 = "champion-mastery/v4/";
    private static final String LOL_SPECTATOR_V4 = "spectator/v4/";
    private static final String LOL_MATCH_V5 = "match/v5/";

    /**
     * Requests the LoL {@link #LOL_CHAMPION_MASTERY_V4} endpoint. If successful, the Response is mapped to the desired Class <b>T</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @return An object of class <b>T</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLChampionMasteryEndpoint(String method, String args, LeaguePlatform platform, Class<T> requiredClass) {
        return requestLoLChampionMasteryEndpoint(method, args, platform, requiredClass, new HashMap<>());
    }

    /**
     * Requests the LoL {@link #LOL_CHAMPION_MASTERY_V4} endpoint. If successful, the Response is mapped to the desired {@link TypeBase}.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * This method is intended to be used for {@link com.fasterxml.jackson.databind.type.CollectionType CollectionTypes} or
     * {@link com.fasterxml.jackson.databind.type.MapType MapTypes}.
     * @see TypeFactory
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @return An object of Type <b>{@code requiredClass}</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLChampionMasteryEndpoint(String method, String args, LeaguePlatform platform, TypeBase requiredClass) {
        return requestLoLChampionMasteryEndpoint(method, args, platform, requiredClass, new HashMap<>());
    }

    /**
     * Requests the LoL {@link #LOL_CHAMPION_MASTERY_V4} endpoint. If successful, the Response is mapped to the desired Class <b>T</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @param filter The filter that should get used for the request. <b>Note:</b> The Values in the Map need to be Strings,
     *               even if they represent an integer
     * @return An object of class <b>T</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLChampionMasteryEndpoint(String method, String args, LeaguePlatform platform, Class<T> requiredClass, Map<String, String> filter) {
        return requestLoLChampionMasteryEndpoint(method, args, platform, TypeFactory.defaultInstance().constructType(requiredClass), filter);
    }

    /**
     * Requests the LoL {@link #LOL_CHAMPION_MASTERY_V4} endpoint. If successful, the Response is mapped to the desired {@link JavaType} <b>{@code requiredClass}</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @see TypeFactory
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @param filter The filter that should get used for the request. <b>Note:</b> The Values in the Map need to be Strings,
     *               even if they represent an integer
     * @return An object of Type <b>{@code requiredClass}</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLChampionMasteryEndpoint(String method, String args, LeaguePlatform platform, JavaType requiredClass, Map<String, String> filter) {
        return handleCacheAndRateLimiter(
                constructUrl(LOL_CHAMPION_MASTERY_V4 + method + args, AppType.LOL, platform),
                LOL_CHAMPION_MASTERY_V4 + method, LeagueRegion.byPlatform(platform), requiredClass, filter);
    }

    /**
     * Requests the LoL {@link #LOL_SPECTATOR_V4} endpoint. If successful, the Response is mapped to the desired Class <b>T</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @return An object of class <b>T</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLSpectatorEndpoint(String method, String args, LeaguePlatform platform, Class<T> requiredClass) {
        return requestLoLSpectatorEndpoint(method, args, platform, requiredClass, new HashMap<>());
    }

    /**
     * Requests the LoL {@link #LOL_SPECTATOR_V4} endpoint. If successful, the Response is mapped to the desired {@link TypeBase}.
     * This method is intended to be used for {@link com.fasterxml.jackson.databind.type.CollectionType CollectionTypes} or
     * {@link com.fasterxml.jackson.databind.type.MapType MapTypes}.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @see TypeFactory
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @return An object of Type <b>{@code requiredClass}</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLSpectatorEndpoint(String method, String args, LeaguePlatform platform, TypeBase requiredClass) {
        return requestLoLSpectatorEndpoint(method, args, platform, requiredClass, new HashMap<>());
    }

    /**
     * Requests the LoL {@link #LOL_SPECTATOR_V4} endpoint. If successful, the Response is mapped to the desired Class <b>T</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @param filter The filter that should get used for the request. <b>Note:</b> The Values in the Map need to be Strings,
     *               even if they represent an integer
     * @return An object of class <b>T</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLSpectatorEndpoint(String method, String args, LeaguePlatform platform, Class<T> requiredClass, Map<String, String> filter) {
        return requestLoLSpectatorEndpoint(method, args, platform, TypeFactory.defaultInstance().constructType(requiredClass), filter);
    }

    /**
     * Requests the LoL {@link #LOL_SPECTATOR_V4} endpoint. If successful, the Response is mapped to the desired {@link JavaType} <b>{@code requiredClass}</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @see TypeFactory
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param platform Platform to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @param filter The filter that should get used for the request. <b>Note:</b> The Values in the Map need to be Strings,
     *               even if they represent an integer
     * @return An object of Type <b>{@code requiredClass}</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLSpectatorEndpoint(String method, String args, LeaguePlatform platform, JavaType requiredClass, Map<String, String> filter) {
        return handleCacheAndRateLimiter(
                constructUrl(LOL_SPECTATOR_V4 + method + args, AppType.LOL, platform),
                LOL_SPECTATOR_V4 + method, LeagueRegion.byPlatform(platform), requiredClass, filter);
    }

    /**
     * Requests the LoL {@link #LOL_MATCH_V5} endpoint. If successful, the Response is mapped to the desired Class <b>T</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param region Region to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @return An object of class <b>T</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLMatchEndpoint(String method, String args, LeagueRegion region, Class<T> requiredClass) {
        return requestLoLMatchEndpoint(method, args, region, requiredClass, new HashMap<>());
    }

    /**
     * Requests the LoL {@link #LOL_MATCH_V5} endpoint. If successful, the Response is mapped to the desired {@link TypeBase}.
     * This method is intended to be used for {@link com.fasterxml.jackson.databind.type.CollectionType CollectionTypes} or
     * {@link com.fasterxml.jackson.databind.type.MapType MapTypes}.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @see TypeFactory
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param region Region to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @return An object of Type <b>{@code requiredClass}</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLMatchEndpoint(String method, String args, LeagueRegion region, TypeBase requiredClass) {
        return requestLoLMatchEndpoint(method, args, region, requiredClass, new HashMap<>());
    }

    /**
     * Requests the LoL {@link #LOL_MATCH_V5} endpoint. If successful, the Response is mapped to the desired Class <b>T</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param region Region to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @param filter The filter that should get used for the request. <b>Note:</b> The Values in the Map need to be Strings,
     *               even if they represent an integer
     * @return An object of class <b>T</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLMatchEndpoint(String method, String args, LeagueRegion region, Class<T> requiredClass, Map<String, String> filter) {
        return requestLoLMatchEndpoint(method, args, region, TypeFactory.defaultInstance().constructType(requiredClass), filter);
    }

    /**
     * Requests the LoL {@link #LOL_MATCH_V5} endpoint. If successful, the Response is mapped to the desired {@link JavaType} <b>{@code requiredClass}</b>.
     * If caching is enabled, the cached response will be returned.
     * @see Settings#useCache(boolean)
     * @see TypeFactory
     * @param method Method in the Endpoint that should get called
     * @param args Extra data needed for the Request
     * @param region Region to make the request to
     * @param requiredClass Class to which the response should get mapped to
     * @param filter The filter that should get used for the request. <b>Note:</b> The Values in the Map need to be Strings,
     *               even if they represent an integer
     * @return An object of Type <b>{@code requiredClass}</b> if casting is successful, {@code null} otherwise
     */
    public static <T> T requestLoLMatchEndpoint(String method, String args, LeagueRegion region, JavaType requiredClass, Map<String, String> filter) {
        return handleCacheAndRateLimiter(
                constructUrl(LOL_MATCH_V5 + method + args, AppType.LOL, region),
                LOL_MATCH_V5 + method, region, requiredClass, filter);
    }
}
