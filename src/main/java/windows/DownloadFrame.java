package windows;

import javax.swing.*;
import java.awt.*;

public class DownloadFrame extends JFrame  {
    private GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Creates a JFrame for the file uploading panel to sit in
     * @throws HeadlessException
     */
    public DownloadFrame(){
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLayout(new GridBagLayout());
        setTitle("Download file");
        setSize(700, 300);
        setLocationRelativeTo(null);
        add(new DownloadPanel(this),gbc);
        this.getContentPane().setLayout(null);
        setVisible(true);
    }
}
