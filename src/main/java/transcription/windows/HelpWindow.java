package transcription.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class HelpWindow implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(HelpWindow.class.getName());

    private JButton returnButton;
    private GridBagConstraints panelGbc = new GridBagConstraints();
    private GridBagConstraints frameGbc = new GridBagConstraints();
    private JPanel helpPanel = new JPanel();
    private String previousWindow;

    public HelpWindow(String previousWindow) {
        this.previousWindow = previousWindow;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * This method sets the variables of the frame to be put in the help window
     * @return
     */
    public JFrame setHelpWindow() {
        JFrame helpFrame = new JFrame();
        helpFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        helpFrame.setLayout(new GridBagLayout());
        helpFrame.setTitle("Help");
        helpFrame.setSize(700, 300);
        helpFrame.setLocationRelativeTo(null);
        helpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        helpPanel = setHelpPanel();
        helpFrame.add(helpPanel, frameGbc);
        helpFrame.getContentPane().setLayout(new GridBagLayout());
        helpFrame.setVisible(true);
        return helpFrame;
    }

    /**
     * This method sets the variables of the panel to be put in the frame
     * @return
     */
    private JPanel setHelpPanel() {
        panelGbc.fill = GridBagConstraints.HORIZONTAL;
        panelGbc.insets.bottom = 1;
        panelGbc.insets.top = 1;
        panelGbc.insets.right = 1;
        panelGbc.insets.left = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        helpPanel.setLayout(new GridBagLayout());
        setText(helpPanel);
        setButtons(helpPanel);
        setAction();
        helpPanel.setSize(700, 300);

        return helpPanel;
    }

    /**
     * This method creates the text panel that the help information is placed in
     * @param helpPanel
     */
    private void setText(JPanel helpPanel) {
        JTextArea jTextArea = new JTextArea(10, 50);
        jTextArea.setEditable(false);
        jTextArea.append(readTxtFile(previousWindow));
        JScrollPane scroll = new JScrollPane(jTextArea,
                VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS);
        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        panelGbc.weightx = 0.5;
        panelGbc.weighty = 0.5;
        panelGbc.gridheight = 1;
        panelGbc.gridwidth = 1;
        helpPanel.add(scroll, panelGbc);
    }

    /**
     * This method sets the variables of the buttons
     * @param helpPanel
     */
    private void setButtons(JPanel helpPanel) {
        returnButton = new JButton("Return");
        panelGbc.gridx = 0;
        panelGbc.gridy = 2;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        helpPanel.add(returnButton, panelGbc);
    }

    /**
     * This method gives and actionListener to the button so tha it can respond to being pressed.
     */
    private void setAction() {
        returnButton.addActionListener(this);
    }

    /**
     * This method sets the actions taken by buttons when pressed. It carries out this according the the window it has come from, allowing it to return to the right window.
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == returnButton && previousWindow.equals("menu")) {
                MenuWindow menuWindow = new MenuWindow();
                menuWindow.setMenuWindow();
            }
            if (actionEvent.getSource() == returnButton && previousWindow.equals("download")) {
                DownloadWindow downloadWindow = new DownloadWindow();
                downloadWindow.setDownloadWindow();
            }
            if (actionEvent.getSource() == returnButton && previousWindow.equals("upload")) {
                UploadWindow uploadWindow = new UploadWindow();
                uploadWindow.setUploadWindow();
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    /**
     * This menu reads the information from the text file to put it in the jTextArea. The file can be modified to change the text.
     * @param previousWindow
     * @return
     */
    public String readTxtFile(String previousWindow) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/"+previousWindow+"Help.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

