package Resources;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class Http {
    private static final Logger LOGGER = Logger.getLogger(Http.class);
    HttpClient client = HttpClientBuilder.create().build();

    public Http() {
        // no args needed
    }

    /**
     * Sends a post request to the ApiGateway, set in the HttpPost object. It attaches the authorization token as a
     * header and prints a response according to the response sent back by the lambda.
     */
    public String sendPost(String json) {
        try {
            HttpPost request = new HttpPost("https://ea8s51420g.execute-api.eu-west-2.amazonaws.com/prod/TranscribeAuthorizer");
            StringEntity params;

            if (json.equalsIgnoreCase("")) {
                params = new StringEntity("{}");
            } else {
                params = new StringEntity(json);
            }

            request.addHeader("Authorization", "transcribeMp3");
            request.setEntity(params);

            HttpResponse httpResponse = client.execute(request);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            LOGGER.info("Something isn't right! Error: " + e);
        }
        return null;
    }
}

//    /Users/hywelgriffiths/Documents/personal/pictures/Test.mp4
