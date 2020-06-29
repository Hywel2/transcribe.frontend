package windows;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.UploadWindow;

import javax.swing.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadWindowTest {
    @Test
    @DisplayName("Test upload window is created.")
    void testUploadWindow(){
        UploadWindow uploadWindow = new UploadWindow();
        assertEquals(JFrame.class, uploadWindow.setUploadWindow().getClass());
    }

    @Test
    @DisplayName("ActionListener test")
    void testActionListener(){
        UploadWindow uploadWindow = new UploadWindow();
        uploadWindow.setUploadWindow();

        new MockUp<UploadWindow>(){
            @Mock
            public File chooseFile(){
                return null;
            }
        };

        assertDoesNotThrow(() -> uploadWindow.getHelpButton().doClick());
        assertDoesNotThrow(() -> uploadWindow.getMenuButton().doClick());
        assertDoesNotThrow(() -> uploadWindow.getFilePathButton().doClick());
        assertDoesNotThrow(() -> uploadWindow.getSendButton().doClick());
    }
}
