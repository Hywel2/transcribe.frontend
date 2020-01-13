package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MenuPanel.class);

    private MenuFrame jFrame;
    private JButton downloadButton;
    private JButton uploadButton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public MenuPanel(MenuFrame jFrame) {
        this.jFrame = jFrame;

        setButtons();
        setAction();
        setSize(350, 300);
    }

    void setButtons() {
        this.setLayout(new GridBagLayout());
        downloadButton = new JButton("Download");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(downloadButton, gbc);

        this.setLayout(new GridBagLayout());
        uploadButton = new JButton("Upload");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(uploadButton, gbc);
    }

    void setAction() {
        downloadButton.addActionListener(this);
        uploadButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == uploadButton) {
                UploadFrame uploadFrame = new UploadFrame();

            }
            if (actionEvent.getSource() == downloadButton) {
                DownloadFrame downloadFrame = new DownloadFrame();
            }
        } catch(Exception e){
                LOGGER.error(e);
        }
    }
}
