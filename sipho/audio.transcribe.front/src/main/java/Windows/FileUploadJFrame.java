package Windows;

import javax.swing.*;
import java.awt.*;

public class FileUploadJFrame extends JFrame {

    private GridBagConstraints gbc = new GridBagConstraints();

    public FileUploadJFrame() throws HeadlessException {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLayout(new GridBagLayout());
        setTitle("Download file");
        setSize(700, 300);
        setLocationRelativeTo(null);
        add(new FileUploadJPanel(this),gbc);
        this.getContentPane().setLayout(null);
        setVisible(true);

    }

}
