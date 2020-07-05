package transcription.windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
