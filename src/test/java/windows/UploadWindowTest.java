package windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.UploadWindow;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class UploadWindowTest {
    @Test
    @DisplayName("Test upload window is created.")
    void testUploadWindow(){
        UploadWindow uploadWindow = new UploadWindow();
        assertEquals(JFrame.class, uploadWindow.setUploadWindow().getClass());
    }
}
