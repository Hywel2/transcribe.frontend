package transcription.windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

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

//        assertDoesNotThrow(() -> interfaceWindow.getSelectFileJButton().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getCopyToClipBoardJButton().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getEnterJButton().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getDownloadJMenuItem().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getHelpJMenuItem().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getUploadFileJMenuItem().doClick());
        assertDoesNotThrow(() -> interfaceWindow.getUploadYouTubeJMenuItem().doClick());
    }
}
