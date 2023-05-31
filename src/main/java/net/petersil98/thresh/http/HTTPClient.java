package net.petersil98.thresh.http;

import net.petersil98.thresh.Thresh;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HTTPClient {

    private final HttpClient client;
    private static final HTTPClient INSTANCE = new HTTPClient();
    private static final Marker MARKER = MarkerManager.getMarker(HTTPClient.class.getSimpleName());

    public static HTTPClient getInstance() {
        return INSTANCE;
    }

    private HTTPClient() {
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> get(String url, Map<String, String> params) {
        try {
            URI uri = URI.create(url+"?"+buildParameters(params));
            return request(HttpRequest.newBuilder(uri).GET().build());
        } catch (Exception e) {
            Thresh.LOGGER.error(MARKER, "Failed to perform GET request", e);
        }
        return null;
    }

    private HttpResponse<String> request(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String buildParameters(Map<String, String> params) {
        List<BasicNameValuePair> list = new ArrayList<>();
        params.forEach((k, v) -> list.add(new BasicNameValuePair(k, v)));
        return URLEncodedUtils.format(list, StandardCharsets.UTF_8);
    }
}
