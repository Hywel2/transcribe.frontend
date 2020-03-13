package windows;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Creates a JFrame for the file uploading panel to sit in
     * @throws HeadlessException
     */
    public MenuFrame(){
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLayout(new GridBagLayout());
        setTitle("Menu");
        setSize(350, 300);
        setLocationRelativeTo(null);
        add(new MenuPanel(this),gbc);
        this.getContentPane().setLayout(null);
        setVisible(true);
    }
}