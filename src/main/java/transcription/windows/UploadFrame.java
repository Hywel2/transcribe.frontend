package transcription.windows;

import javax.swing.*;
import java.awt.*;

public class UploadFrame extends JFrame {
    private GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Creates a JFrame for the file uploading panel to sit in
     * @throws HeadlessException
     */
    public UploadFrame(){
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Download file");
        setSize(700, 300);
        setLocationRelativeTo(null);
        this.setContentPane(new UploadPanel());
        setVisible(true);
    }
}
