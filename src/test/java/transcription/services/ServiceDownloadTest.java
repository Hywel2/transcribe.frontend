package transcription.services;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.providers.ProviderDownload;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ServiceDownloadTest {

    @Test
    @DisplayName("Testing ")
    public void executePostTest () {
        ServiceDownload serviceDownload = new ServiceDownload();
        File file = new File("Main.java");
        String filePath = file.getAbsolutePath();

        new MockUp<ProviderDownload>() {
            @Mock
            public String sendDownloadHttp(String jobName) {

                return "DATA";
            }
        };

        while (filePath.charAt(filePath.length()-1)!='/'){
            filePath = filePath.substring(0, filePath.length()-2);
        }

        assertEquals(true,serviceDownload.sendDownloadHttp("DATA",filePath));
    }
}
