package windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.DownloadWindow;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class DownloadWindowTest {
    @Test
    @DisplayName("Test download window is created.")
    void testDownloadWindow(){
        DownloadWindow downloadWindow = new DownloadWindow();
        assertEquals(JFrame.class, downloadWindow.setDownloadWindow().getClass());
    }
}
