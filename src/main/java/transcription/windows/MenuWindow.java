package transcription.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class MenuWindow implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(MenuWindow.class.getName());

    private JButton downloadButton;
    private JButton uploadButton;
    private GridBagConstraints panelGbc = new GridBagConstraints();
    private GridBagConstraints frameGbc = new GridBagConstraints();
    private JPanel menuPanel = new JPanel();

    public JFrame setMenuWindow(){
        JFrame menuFrame = new JFrame();
        menuFrame.getContentPane().setLayout(new GridBagLayout());
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setTitle("Menu");
        menuFrame.setSize(350, 300);
        menuFrame.setLocationRelativeTo(null);
        menuPanel = setMenuPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuFrame.add(menuPanel,frameGbc);
        menuFrame.setVisible(true);
        return menuFrame;
    }

    public JPanel setMenuPanel() {

        panelGbc.fill = GridBagConstraints.HORIZONTAL;
        panelGbc.insets.bottom = 1;
        panelGbc.insets.top = 1;
        panelGbc.insets.right = 1;
        panelGbc.insets.left = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setSize(300, 250);
        setButtons(menuPanel);
        setAction();

        return menuPanel;
    }

    /**
     * Method sets and add the JButtons to the panel
     * @param menuPanel
     */
    void setButtons(JPanel menuPanel) {
        menuPanel.setLayout(new GridBagLayout());
        downloadButton = new JButton("Download");
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        panelGbc.fill = GridBagConstraints.RELATIVE;
        menuPanel.add(downloadButton, panelGbc);

        menuPanel.setLayout(new GridBagLayout());
        uploadButton = new JButton("  Upload   ");
        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        panelGbc.fill = GridBagConstraints.RELATIVE;
        menuPanel.add(uploadButton, panelGbc);

    }

    /**
     * Method creates the actionListeners
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
                UploadWindow uploadWindow = new UploadWindow();
                uploadWindow.setUploadWindow();
            }
            if (actionEvent.getSource() == downloadButton) {
                DownloadWindow downloadWindow = new DownloadWindow();
                downloadWindow.setDownloadWindow();
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }
}
