package windows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import transcription.windows.MenuWindow;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class MenuWindowTest {
    @Test
    @DisplayName("Test menu window is created.")
    void testMenuWindow(){
        MenuWindow menuWindow = new MenuWindow();
        assertEquals(JFrame.class, menuWindow.setMenuWindow().getClass());
    }
}
