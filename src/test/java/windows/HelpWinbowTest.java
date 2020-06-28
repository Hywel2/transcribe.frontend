package windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.HelpWindow;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
}
