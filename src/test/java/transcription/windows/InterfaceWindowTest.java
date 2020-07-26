package transcription.windows;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterfaceWindowTest {
    @Test
    @DisplayName("Test upload window is created.")
    void testUploadWindow(){
        InterfaceWindow interfaceWindow = new InterfaceWindow();
        assertEquals(JFrame.class, interfaceWindow.setInterfaceWindow().getClass());
    }

    @Test
    @DisplayName("ActionListener test")
    void testActionListener(){
        InterfaceWindow interfaceWindow = new InterfaceWindow();
        interfaceWindow.setInterfaceWindow();

        new MockUp<JFileChooser>(){
            @Mock
            public int showOpenDialog(Component parent) throws HeadlessException{
                return 0;
            }
        };

        assertDoesNotThrow(() -> interfaceWindow.getSelectFileJButton().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getCopyToClipBoardJButton().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getEnterJButton().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getDownloadJMenuItem().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getHelpJMenuItem().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getUploadFileJMenuItem().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getUploadYouTubeJMenuItem().doClick());
    }

    @Test
    @DisplayName("ActionListener test for enterJButton when download flag is true")
    void testActionListenerEnterJButtonDownloadFlagTrue(){
        InterfaceWindow interfaceWindow = new InterfaceWindow();
        interfaceWindow.setInterfaceWindow();
        interfaceWindow.setDownloadFlag(true);

        assertDoesNotThrow(() -> interfaceWindow.getEnterJButton().doClick());
    }

    @Test
    @DisplayName("ActionListener test for enterJButton when youtube flag and download flag are false")
    void testActionListenerEnterJButtonDownloadAndYouTubeFlagFalse(){
        InterfaceWindow interfaceWindow = new InterfaceWindow();
        interfaceWindow.setInterfaceWindow();
        interfaceWindow.setYouTubeFlag(false);

        assertDoesNotThrow(() -> interfaceWindow.getEnterJButton().doClick());
    }
}
