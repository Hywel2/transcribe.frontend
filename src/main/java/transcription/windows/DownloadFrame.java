package transcription.windows;

import javax.swing.*;
import java.awt.*;

public class DownloadFrame extends JFrame  {

    /**
     * Creates a JFrame for the file uploading panel to sit in
     * @throws HeadlessException
     */
    public DownloadFrame(){
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Download file");
        setSize(700, 300);
        setLocationRelativeTo(null);
        this.setContentPane(new DownloadPanel());
        setVisible(true);
    }
}
