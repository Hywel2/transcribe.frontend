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

public class HelpWindow implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(MenuWindow.class.getName());

    private JTextArea jTextArea;
    private JFrame helpFrame;
    private JButton returnButton;
    private GridBagConstraints panelGbc = new GridBagConstraints();
    private GridBagConstraints frameGbc = new GridBagConstraints();
    private JPanel helpPanel = new JPanel();
    private String previousWindow;

    public HelpWindow(String previousWindow) {
        this.previousWindow = previousWindow;
    }

    public JFrame setHelpWindow() {
        helpFrame = new JFrame();
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

    private void setText(JPanel helpPanel) {
        jTextArea = new JTextArea(10, 50);
        jTextArea.setEditable(false);
        jTextArea.append(readTxtFile(previousWindow));
        JScrollPane scroll = new JScrollPane(jTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        panelGbc.weightx = 0.5;
        panelGbc.weighty = 0.5;
        panelGbc.gridheight = 1;
        panelGbc.gridwidth = 1;
        helpPanel.add(scroll, panelGbc);
    }

    private void setButtons(JPanel helpPanel) {
        returnButton = new JButton("Return");
        panelGbc.gridx = 0;
        panelGbc.gridy = 2;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        helpPanel.add(returnButton, panelGbc);
    }

    private void setAction() {
        returnButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == returnButton && previousWindow.equals("menu")) {
                MenuWindow menuWindow = new MenuWindow();
                menuWindow.setMenuWindow();
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    private String readTxtFile(String previousWindow) {
        try {
            String menuHelpText = null;
            if (previousWindow.equals("menu")) {
                menuHelpText = new String(Files.readAllBytes(Paths.get("src/main/resources/menuHelp.txt")));
            }
            if (previousWindow.equals("download")){
                menuHelpText = new String(Files.readAllBytes(Paths.get("src/main/resources/downloadHelp.txt")));
            }
            return menuHelpText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

