package transcription.services;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.providers.ProviderUpload;
import ws.schild.jave.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ServiceUploadTest {
    private String filePath = "src/test/resources/AudioTestFile.mp3";
    private ServiceUpload serviceUpload = new ServiceUpload();

    @Test
    @DisplayName("TestConversionOfMp4ToMp3")
    void testConversionOfMp4ToMp3() {

        new MockUp<Encoder>() {
            @Mock
            public void encode(MultimediaObject multimediaObject, File target, EncodingAttributes attributes) throws IllegalArgumentException, InputFormatException, EncoderException {
                ;
            }
        };

        assertEquals("src/test/resources/Audio.mp3", serviceUpload.convertToMp3(filePath));
    }

    @Test
    @DisplayName("Test cutting loop when length is over 5000000")
    void testLongCuttingLoop() throws IOException {
        InputStream inputStream = new FileInputStream("/Users/hywelgriffiths/Documents/IntellijProjects/sipho/transcriptionSoftware/audio.transcribe.front/src/test/java/resources/base64.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String base64 = bufferedReader.readLine();
        ServiceUpload serviceUpload = new ServiceUpload();

        new MockUp<ProviderUpload>() {
            @Mock
            public String executeUploadHttp(String mp3Base64, String jobName, String tag, String email) {
                return null;
            }
        };

        assertNull(serviceUpload.cuttingLoop(base64, "JOBNAME", null));
    }

    @Test
    @DisplayName("Test cutting loop when length is under 5000000")
    void testShortCuttingLoop() throws IOException {
        ServiceUpload serviceUpload = new ServiceUpload();

        new MockUp<ProviderUpload>() {
            @Mock
            public String executeUploadHttp(String mp3Base64, String jobName, String tag, String email) {
                return null;
            }
        };

        assertNull(serviceUpload.cuttingLoop("SHORTBASE64", "JOBNAME", null));
    }

    @Test
    @DisplayName("Test convertToBase64AndSend")
    void testConvertToBase64AndSend(){
        ServiceUpload serviceUpload = new ServiceUpload();
        File file = new File ("src/test/java/resources/fakeMp4.txt");
        String jobName = "JOBNAME";

        new MockUp<ServiceUpload>() {
            @Mock
            public String convertToMp3(String mp4File) {
                return "src/test/java/resources/fakeMp4.txt";
            }
        };

        assertEquals("\"complete\"", serviceUpload.convertToBase64AndSend(jobName, file, null, false));
    }

    @Test
    @DisplayName("Test convertToBase64andSendCatchBlock")
    void testConvertToBase64AndSendCatch(){
        ServiceUpload serviceUpload = new ServiceUpload();
        File file = new File ("src/test/java/resources/fakeMp4.txt");
        String jobName = "JOBNAME";

        new MockUp<ServiceUpload>() {
            @Mock
            public String convertToMp3(String mp4File) throws Exception {
                throw new Exception("Forced Exception");
            }
        };

        assertEquals("\"complete\"", serviceUpload.convertToBase64AndSend(jobName, file, null, false));
    }

    @Test
    @DisplayName("Test convertToMp3 catch block")
    void testConvertToMp3CatchBlock() {

        new MockUp<ServiceUpload>() {
            @Mock
            public String createMp3(String mp4file) throws Exception {
                throw new Exception("Forced Exception");
            }
        };

        assertNull(serviceUpload.convertToMp3(filePath));
    }
}
