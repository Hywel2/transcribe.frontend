package transcription.providers;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.resources.Http;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProviderDownloadTest {

    @Test
    @DisplayName("Testing executeDownloadHttp returns correct string")
    void testExecuteDownloadHttp(){
        new MockUp<Http>(){
            @Mock
            public String sendPost(String json){
                return "Test";
            }
        };
        ProviderDownload providerDownload = new ProviderDownload();

        assertEquals("Test", providerDownload.executeDownloadHttp("testJobName"));
    }
}
