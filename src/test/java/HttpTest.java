import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.encryption.Encryption;
import transcription.resources.Http;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;

public class HttpTest {

    @DisplayName("Test http sendPost catch block")
    @Test
    void testCatchBlock() throws IOException {
        Http http = new Http();
        String json = "";

        new MockUp<Encryption>() {
            @Mock
            public String decrypt(String data) throws IOException {
                throw new IOException();
            }
        };
        assertNull(http.sendPost(json));
    }
}
