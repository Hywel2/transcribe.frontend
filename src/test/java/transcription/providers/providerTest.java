package transcription.providers;

import mockit.Mock;
import mockit.MockUp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.Assert.assertNull;

public class providerTest {

    @Test
    @DisplayName("Testing the executePostTest and sendDownloadHttp for returning response form Http class")
    public void executePostTest () {
        ProviderUpload providerUpload = new ProviderUpload();
        ProviderDownload providerDownload = new ProviderDownload();
        String mp3Base64 = "DATA";
        String jobName = "DATA";
        String tag = "DATA";
        String email = "DATA";

        new MockUp<HttpClient>() {
            @Mock
            HttpResponse execute(HttpUriRequest var1) throws IOException, ClientProtocolException {
                HttpResponse httpResponse = null;

                return httpResponse;
            }
        };

        new MockUp<HttpResponse>() {
            @Mock
            HttpEntity getEntity() {
                HttpEntity httpEntity = null;

                return httpEntity;
                }
        };

        assertNull(providerUpload.executeUploadHttp(mp3Base64, jobName, tag, email));
        assertNull(providerDownload.executeDownloadHttp(jobName));
    }
}
