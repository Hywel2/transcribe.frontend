import mockit.Mock;
import mockit.MockUp;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.resources.Http;

import java.io.*;

import static org.junit.Assert.assertNull;

public class HttpTest {

    @DisplayName("Check sendPost sends the http")
    @Test
    void testCheckSendPostSendsTheHttp() throws IOException {
        Http http = new Http();

        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
                return null;
            }
        };

        new MockUp<BasicHttpResponse>() {
            @Mock
            public HttpEntity getEntity() {
                return null;
            }
        };

        InputStream inputStream = new FileInputStream("/Users/hywelgriffiths/Documents/IntellijProjects/sipho/transcriptionSoftware/audio.transcribe.front/src/test/java/resources/base64.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String json = bufferedReader.readLine();

        assertNull(http.sendPost(json));

    }
}
