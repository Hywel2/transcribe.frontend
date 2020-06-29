package windows;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.DownloadWindow;

import javax.swing.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadWindowTest {
    @Test
    @DisplayName("Test download window is created.")
    void testDownloadWindow(){
        DownloadWindow downloadWindow = new DownloadWindow();

        assertEquals(JFrame.class, downloadWindow.setDownloadWindow().getClass());
    }

    @Test
    @DisplayName("ActionListener test")
    void testActionListener(){
        DownloadWindow downloadWindow = new DownloadWindow();
        downloadWindow.setDownloadWindow();

        new MockUp<DownloadWindow>(){
            @Mock
            public File chooseFile(){
                return null;
            }
        };

        assertDoesNotThrow(() -> downloadWindow.getHelpButton().doClick());
        assertDoesNotThrow(() -> downloadWindow.getMenuButton().doClick());
        assertDoesNotThrow(() -> downloadWindow.getRetrieveButton().doClick());
        assertDoesNotThrow(() -> downloadWindow.getSelectDirectoryButton().doClick());

    }
}
