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
    private String filePath = "/Users/hywelgriffiths/Documents/Personal/pictures/BiggerFile.mp4";
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

        assertEquals("/Users/hywelgriffiths/Documents/Personal/pictures/Audio.mp3", serviceUpload.convertToMp3(filePath));
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
            public String executePost(String mp3Base64, String jobName, String tag) {
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
            public String executePost(String mp3Base64, String jobName, String tag) {
                return null;
            }
        };

        assertNull(serviceUpload.cuttingLoop("SHORTBASE64", "JOBNAME", null));
    }

    @Test
    @DisplayName("Test convertToBase64AndSend")
    void testConvertToBase64AndSend(){
        ServiceUpload serviceUpload = new ServiceUpload();
        File file = new File ("/Users/hywelgriffiths/Documents/IntellijProjects/sipho/transcriptionSoftware/audio.transcribe.front/src/test/java/resources/fakeMp4.mp4");
        String jobName = "JOBNAME";

        new MockUp<ServiceUpload>() {
            @Mock
            public String convertToMp3(String mp4File) {
                return "/Users/hywelgriffiths/Documents/IntellijProjects/sipho/transcriptionSoftware/audio.transcribe.front/src/test/java/resources/fakeMp4.mp4";
            }
        };

        assertNull(serviceUpload.convertToBase64AndSend(jobName, file, null));
    }

    @Test
    @DisplayName("Test convertToBase64andSendCatchBlock")
    void testConvertToBase64AndSendCatch(){
        ServiceUpload serviceUpload = new ServiceUpload();
        File file = new File ("/Users/hywelgriffiths/Documents/IntellijProjects/sipho/transcriptionSoftware/audio.transcribe.front/src/test/java/resources/fakeMp4.mp4");
        String jobName = "JOBNAME";

        new MockUp<ServiceUpload>() {
            @Mock
            public String convertToMp3(String mp4File) throws Exception {
                throw new Exception("Forced Exception");
            }
        };

        assertNull(serviceUpload.convertToBase64AndSend(jobName, file, null));
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
