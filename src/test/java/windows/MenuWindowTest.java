package windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.MenuWindow;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MenuWindowTest {
    @Test
    @DisplayName("Test menu window is created.")
    void testMenuWindow(){
        MenuWindow menuWindow = new MenuWindow();
        assertEquals(JFrame.class, menuWindow.setMenuWindow().getClass());
    }

    @Test
    @DisplayName("ActionListener test")
    void testActionListener(){
        MenuWindow menuWindow = new MenuWindow();
        menuWindow.setMenuWindow();

        assertDoesNotThrow(() -> menuWindow.getHelpButton().doClick());
        assertDoesNotThrow(() -> menuWindow.getDownloadButton().doClick());
        assertDoesNotThrow(() -> menuWindow.getUploadButton().doClick());
    }
}
