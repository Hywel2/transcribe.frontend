package windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.HelpWindow;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class HelpWinbowTest {
    @Test
    @DisplayName("Test help window is created.")
    void testHelpWindow(){
        HelpWindow helpWindow = new HelpWindow("menu");
        assertEquals(JFrame.class, helpWindow.setHelpWindow().getClass());

    }

    @Test
    @DisplayName("Test readTxtFile catch block when previous window is null.")
    void testReadTextFileCatchBlockWhenPreviousWindowIsNull(){
        HelpWindow helpWindow = new HelpWindow("menu");
        assertNull(helpWindow.readTxtFile(null));

    }

    @Test
    @DisplayName("ActionListener test")
    void testActionListener(){
        HelpWindow helpWindow = new HelpWindow("menu");
        helpWindow.setHelpWindow();

        assertDoesNotThrow(() -> helpWindow.getReturnButton().doClick());
    }
}
