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
    @DisplayName("Test serviceDownload when file is not transcribed.")
    public void testFileNotTranscribed() {
        ServiceDownload serviceDownload = new ServiceDownload();
        File file = new File("Main.java");
        String filePath = file.getAbsolutePath();

        new MockUp<ProviderDownload>() {
            @Mock
            public String executeDownloadHttp(String jobName) throws IOException {
                return "\"java.nio.file.NoSuchFileException\"";
            }
        };

        while (filePath.charAt(filePath.length() - 1) != '/') {
            filePath = filePath.substring(0, filePath.length() - 2);
        }

        assertEquals("File is not transcribed yet.", serviceDownload.sendDownloadHttp("DATA", filePath));
    }

    @Test
    @DisplayName("Test serviceDownload when job name does not exist.")
    public void testJobNameDoesNotExist() {
        ServiceDownload serviceDownload = new ServiceDownload();
        File file = new File("Main.java");
        String filePath = file.getAbsolutePath();

        new MockUp<ProviderDownload>() {
            @Mock
            public String executeDownloadHttp(String jobName) {
                return "\"File does not exist\"";
            }
        };

        while (filePath.charAt(filePath.length() - 1) != '/') {
            filePath = filePath.substring(0, filePath.length() - 2);
        }

        assertEquals("Job name does not exist", serviceDownload.sendDownloadHttp("DATA", filePath));
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
