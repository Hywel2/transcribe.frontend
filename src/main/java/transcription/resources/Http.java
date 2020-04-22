package transcription.resources;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import transcription.encryption.Encryption;

import java.util.logging.Logger;

public class Http {
    private static final Logger LOGGER = Logger.getLogger(Http.class.getName());
    HttpClient client = HttpClientBuilder.create().build();

    /**
     * Sends a post request to the ApiGateway, set in the HttpPost object. It attaches the authorization token as a
     * header and prints a response according to the response sent back by the lambda.
     */
    public String sendPost(String json) {
        Encryption encryption = new Encryption();
        try {
            HttpPost request = new HttpPost(System.getenv("url"));
            StringEntity params;

            if (json.equalsIgnoreCase("")) {
                params = new StringEntity("{}");
            } else {
                params = new StringEntity(json);
            }

            request.addHeader(encryption.decrypt("Btugpqjybsjno"), encryption.decrypt("uqbmtbshcdNo4"));
            request.setEntity(params);

            HttpResponse httpResponse = client.execute(request);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }
}