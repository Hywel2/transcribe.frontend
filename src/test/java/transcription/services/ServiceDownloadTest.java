package transcription.services;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.providers.ProviderDownload;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ServiceDownloadTest {

    @Test
    @DisplayName("Testing ")
    public void executePostTest() {
        ServiceDownload serviceDownload = new ServiceDownload();
        File file = new File("Main.java");
        String filePath = file.getAbsolutePath();

        new MockUp<ProviderDownload>() {
            @Mock
            public String executeDownloadHttp(String jobName) {
                return "DATA";
            }
        };

        while (filePath.charAt(filePath.length() - 1) != '/') {
            filePath = filePath.substring(0, filePath.length() - 2);
        }

        assertEquals("DATA", serviceDownload.sendDownloadHttp("DATA", filePath));
    }

    @Test
    @DisplayName("testing catch block for sendDownloadHttp")
    public void testCatchBlock() {
        ServiceDownload serviceDownload = new ServiceDownload();
        File file = new File("Main.java");
        String filePath = file.getAbsolutePath();

        new MockUp<ProviderDownload>() {
            @Mock
            public String executeDownloadHttp(String jobName) throws IOException {
                throw new IOException("Forced Exception");
            }
        };

        assertNull(serviceDownload.sendDownloadHttp("JOBNAME", "file"));
    }
}
