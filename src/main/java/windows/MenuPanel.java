package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
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

    /**
     * Method sets and add the JButtons to the panel
     */
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

    /**
     * This method sets the actionListeners
     */
    void setAction() {
        downloadButton.addActionListener(this);
        uploadButton.addActionListener(this);
    }

    /**
     * This method sets the actions that will be performed when the JButtons are pressed
     * @param actionEvent
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == uploadButton) {

                new UploadFrame();
            }
            if (actionEvent.getSource() == downloadButton) {
                new DownloadFrame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

