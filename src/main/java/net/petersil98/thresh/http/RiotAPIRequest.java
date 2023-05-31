package net.petersil98.thresh.http;

import com.fasterxml.jackson.databind.type.CollectionType;
import net.petersil98.thresh.Thresh;
import net.petersil98.thresh.constant.Constants;
import net.petersil98.thresh.model.Deserializers;
import net.petersil98.thresh.util.settings.Settings;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class RiotAPIRequest {
    private static final Marker MARKER = MarkerManager.getMarker(RiotAPIRequest.class.getSimpleName());

    private static HttpResponse<String> request(String endPoint, AppType app, RoutingType type, Map<String, String> params) {
        String url = Constants.API_BASE_PATH + app + "/";
        switch (type) {
            case PLATFORM -> url = url.replaceAll("#", Settings.getCurrentConfig().getPlatform().toString());
            case REGION -> url = url.replaceAll("#", Settings.getCurrentConfig().getRegion().toString());
        }
        HashMap<String, String> mutableMap = new HashMap<>(params);
        mutableMap.put("api_key", Settings.getAPIKey());
        return HTTPClient.getInstance().get(url + endPoint, mutableMap);
    }

    public static <T> T requestLoLSummonerEndpoint(String endPoint, Class<T> requiredClass) {
        return requestLoLSummonerEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLSummonerEndpoint(String endPoint, CollectionType requiredClass) {
        return requestLoLSummonerEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLSummonerEndpoint(String endPoint, Class<T> requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("summoner/v4/"+endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLSummonerEndpoint(String endPoint, CollectionType requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("summoner/v4/"+endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLChampionMasteryEndpoint(String endPoint, Class<T> requiredClass) {
        return requestLoLChampionMasteryEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLChampionMasteryEndpoint(String endPoint, CollectionType requiredClass) {
        return requestLoLChampionMasteryEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLChampionMasteryEndpoint(String endPoint, Class<T> requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("champion-mastery/v4/" + endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLChampionMasteryEndpoint(String endPoint, CollectionType requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("champion-mastery/v4/" + endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLLeagueEndpoint(String endPoint, Class<T> requiredClass) {
        return requestLoLLeagueEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLLeagueEndpoint(String endPoint, CollectionType requiredClass) {
        return requestLoLLeagueEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLLeagueEndpoint(String endPoint, Class<T> requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("league/v4/" + endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLLeagueEndpoint(String endPoint, CollectionType requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("league/v4/" + endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLSpectatorEndpoint(String endPoint, Class<T> requiredClass) {
        return requestLoLSpectatorEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLSpectatorEndpoint(String endPoint, CollectionType requiredClass) {
        return requestLoLSpectatorEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLSpectatorEndpoint(String endPoint, Class<T> requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("spectator/v4/" + endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLSpectatorEndpoint(String endPoint, CollectionType requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("spectator/v4/" + endPoint, AppType.LOL, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLMatchEndpoint(String endPoint, Class<T> requiredClass) {
        return requestLoLMatchEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLMatchEndpoint(String endPoint, CollectionType requiredClass) {
        return requestLoLMatchEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestLoLMatchEndpoint(String endPoint, Class<T> requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("match/v5/" + endPoint, AppType.LOL, RoutingType.REGION, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestLoLMatchEndpoint(String endPoint, CollectionType requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("match/v5/" + endPoint, AppType.LOL, RoutingType.REGION, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestRiotAccountEndpoint(String endPoint, Class<T> requiredClass) {
        return requestRiotAccountEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestRiotAccountEndpoint(String endPoint, CollectionType requiredClass) {
        return requestRiotAccountEndpoint(endPoint, requiredClass, new HashMap<>());
    }

    public static <T> T requestRiotAccountEndpoint(String endPoint, Class<T> requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("account/v1/" + endPoint, AppType.RIOT, RoutingType.REGION, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    public static <T> T requestRiotAccountEndpoint(String endPoint, CollectionType requiredClass, Map<String, String> filter) {
        HttpResponse<String> response = request("account/v1/" + endPoint, AppType.RIOT, RoutingType.PLATFORM, filter);
        return handleAndCastResponse(response, requiredClass);
    }

    private static <T> T handleAndCastResponse(HttpResponse<String> response, Class<T> clazz) {
        try {
            if(response.statusCode() == HttpStatus.SC_OK) {
                return Deserializers.MAPPER.readValue(response.body(), clazz);
            }
            Thresh.LOGGER.error(MARKER, String.format("Got bad status code %d. Body: %s", response.statusCode(), response.body()));
        } catch (IOException e) {
            Thresh.LOGGER.error(MARKER, String.format("Failed to parse JSON to %s object", clazz.getSimpleName()), e);
        }
        return null;
    }

    private static <T> T handleAndCastResponse(HttpResponse<String> response, CollectionType collectionType) {
        try {
            if(response.statusCode() == HttpStatus.SC_OK) {
                return Deserializers.MAPPER.readValue(response.body(), collectionType);
            }
            Thresh.LOGGER.error(MARKER, String.format("Got bad status code %d. Body: %s", response.statusCode(), response.body()));
        } catch (IOException e) {
            Thresh.LOGGER.error(MARKER, String.format("Failed to parse JSON to %s object", collectionType.getContentType().getRawClass().getSimpleName()), e);
        }
        return null;
    }

    private enum AppType {
        RIOT("riot"),
        LOL("lol"),
        TFT("riot");

        private final String name;

        AppType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private enum RoutingType {
        PLATFORM,
        REGION;
    }
}
