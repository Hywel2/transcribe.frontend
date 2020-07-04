package transcription.windows;

import transcription.services.ServiceDownload;
import transcription.services.ServiceUpload;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class InterfaceWindow implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(InterfaceWindow.class.getName());

    private JButton copyToClipBoardJButton;
    private JButton enterJButton;
    private JButton selectFileJButton;
    private JTextField pathJTextField;
    private JTextField jobNameJTextField;
    private JTextField emailJTextField;
    private JMenuBar toolBarJMenuBar = new JMenuBar();
    private JMenuItem uploadYouTubeJMenuItem;
    private JMenuItem uploadFileJMenuItem;
    private JMenuItem downloadJMenuItem;
    private JMenuItem helpJMenuItem;
    private GridBagConstraints containerGbc = new GridBagConstraints();
    private Container contentPaneContainer = new Container();
    private String emptyField = "default";
    private String filePath = "default";
    private boolean youTubeFlag = true;
    private boolean downloadFlag = false;
    private ServiceUpload serviceUpload = new ServiceUpload();
    private ServiceDownload serviceDownload = new ServiceDownload();
    private JLabel pathJLabel = new JLabel("http:");
    private JLabel emailJLabel = new JLabel("Email:");
    private JTextArea jTextArea = new JTextArea(30, 60);

    public JButton getCopyToClipBoardJButton() {
        return copyToClipBoardJButton;
    }

    public JButton getEnterJButton() {
        return enterJButton;
    }

    public JButton getSelectFileJButton() {
        return selectFileJButton;
    }

    public JFrame setInterfaceWindow() {
        JFrame interfaceJFrame = new JFrame();
        interfaceJFrame.setJMenuBar(setToolBar(toolBarJMenuBar));
        contentPaneContainer = setContentPane();
        interfaceJFrame.setContentPane(contentPaneContainer);
        interfaceJFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        interfaceJFrame.setTitle("Audio Transcribe");
        interfaceJFrame.setSize(800, 800);
        interfaceJFrame.setLocationRelativeTo(null);
        interfaceJFrame.setVisible(true);

        return interfaceJFrame;
    }

    private Container setContentPane() {
        containerGbc.insets.bottom = 1;
        containerGbc.insets.top = 2;
        containerGbc.insets.right = 1;
        containerGbc.insets.left = 1;
        containerGbc.weightx = 1;
        containerGbc.weighty = 1;

        contentPaneContainer.setLayout(new GridBagLayout());
        contentPaneContainer.setSize(800, 700);
        setJobName(contentPaneContainer);
        setPath(contentPaneContainer);
        setEmail(contentPaneContainer);
        setButtons(contentPaneContainer);
        setTextArea(contentPaneContainer);
        setAction();

        return contentPaneContainer;
    }

    public JMenuBar setToolBar(JMenuBar toolBarJMenuBar) {

        JMenu accountJMenu = new JMenu("Accounts");
        JMenu jobsJMenu = new JMenu("Task");
        JMenu helpJMenu = new JMenu("Help");

        toolBarJMenuBar.add(accountJMenu);
        toolBarJMenuBar.add(jobsJMenu);
        toolBarJMenuBar.add(helpJMenu);

        downloadJMenuItem = new JMenuItem("Download transcript");
        uploadFileJMenuItem = new JMenuItem("Transcript from file");
        uploadYouTubeJMenuItem = new JMenuItem("Transcript from YouTube");
        helpJMenuItem = new JMenuItem("Help");

        jobsJMenu.add(downloadJMenuItem);
        jobsJMenu.addSeparator();
        jobsJMenu.add(uploadFileJMenuItem);
        jobsJMenu.addSeparator();
        jobsJMenu.add(uploadYouTubeJMenuItem);
        helpJMenu.add(helpJMenuItem);

        return toolBarJMenuBar;
    }

    private void setPath(Container interfaceJPanel) {

        containerGbc.gridx = 2;
        containerGbc.gridy = 1;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        interfaceJPanel.add(pathJLabel, containerGbc);

        pathJTextField = new JTextField();
        pathJTextField.setColumns(40);
        pathJTextField.setText(filePath);
        containerGbc.gridx = 5;
        containerGbc.gridy = 1;
        containerGbc.gridwidth = 8;
        containerGbc.gridheight = 1;
        interfaceJPanel.add(pathJTextField, containerGbc);
    }

    private void setJobName(Container interfaceJPanel) {
        JLabel jobNameLabel = new JLabel("Job name:");

        containerGbc.gridx = 2;
        containerGbc.gridy = 3;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        interfaceJPanel.add(jobNameLabel, containerGbc);

        jobNameJTextField = new JTextField();
        jobNameJTextField.setColumns(40);
        jobNameJTextField.setText(emptyField);
        containerGbc.gridx = 5;
        containerGbc.gridy = 3;
        containerGbc.gridwidth = 8;
        containerGbc.gridheight = 1;
        interfaceJPanel.add(jobNameJTextField, containerGbc);
    }

    private void setEmail(Container interfaceJPanel) {

        containerGbc.gridx = 2;
        containerGbc.gridy = 5;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        interfaceJPanel.add(emailJLabel, containerGbc);

        emailJTextField = new JTextField();

        emailJTextField.setText(emptyField);
        emailJTextField.setColumns(40);
        containerGbc.gridx = 5;
        containerGbc.gridy = 5;
        containerGbc.gridwidth = 8;
        containerGbc.gridheight = 1;
        interfaceJPanel.add(emailJTextField, containerGbc);
    }

    private void setTextArea(Container interfaceContainer) {
        jTextArea.setText("Default");
        jTextArea.setEditable(false);
        jTextArea.append("");
        JScrollPane scroll = new JScrollPane(jTextArea,
                VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS);
        containerGbc.gridx = 2;
        containerGbc.gridy = 9;
        containerGbc.gridheight = 8;
        containerGbc.gridwidth = 11;
        interfaceContainer.add(scroll, containerGbc);
    }

    private void setButtons(Container interfaceContainer) {
        copyToClipBoardJButton = new JButton("Copy");
        containerGbc.gridx = 2;
        containerGbc.gridy = 7;
        containerGbc.gridwidth = 3;
        containerGbc.gridheight = 1;
        interfaceContainer.add(copyToClipBoardJButton, containerGbc);

        enterJButton = new JButton("Submit");
        containerGbc.gridx = 6;
        containerGbc.gridy = 7;
        containerGbc.gridwidth = 3;
        containerGbc.gridheight = 1;
        interfaceContainer.add(enterJButton, containerGbc);

        selectFileJButton = new JButton("Select file");
        containerGbc.gridx = 10;
        containerGbc.gridy = 7;
        containerGbc.gridwidth = 3;
        containerGbc.gridheight = 1;
        getSelectFileJButton().setVisible(false);
        interfaceContainer.add(selectFileJButton, containerGbc);
    }

    void setAction() {
        copyToClipBoardJButton.addActionListener(this);
        enterJButton.addActionListener(this);
        selectFileJButton.addActionListener(this);
        uploadYouTubeJMenuItem.addActionListener(this);
        helpJMenuItem.addActionListener(this);
        uploadFileJMenuItem.addActionListener(this);
        downloadJMenuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == enterJButton) {
                enterJButtonAction();
            }
            if (actionEvent.getSource() == selectFileJButton) {
                selectFileJButtonAction();
            }
            if (actionEvent.getSource() == copyToClipBoardJButton) {
                StringSelection stringSelection = new StringSelection (jTextArea.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                clpbrd.setContents (stringSelection, null);
            }
            if (actionEvent.getSource() == helpJMenuItem) {
                helpJMenuItemAction();
            }
            if (actionEvent.getSource() == uploadYouTubeJMenuItem) {
                setUploadYouTubeJMenuItemAction();
            }
            if (actionEvent.getSource() == uploadFileJMenuItem) {
                setUploadFileJMenuItemAction();
            }
            if (actionEvent.getSource() == downloadJMenuItem) {
                downloadJMenuItemAction();
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    private void downloadJMenuItemAction() {
        downloadFlag = true;
        youTubeFlag = false;

        pathJLabel.setText("Directory");
        emailJLabel.setVisible(false);
        emailJTextField.setVisible(false);

        selectFileJButton.setVisible(true);
        selectFileJButton.setText("Select directory");
    }

    private void setUploadFileJMenuItemAction() {
        downloadFlag = false;
        youTubeFlag = false;

        pathJLabel.setText("File");
        emailJLabel.setVisible(true);
        emailJTextField.setVisible(true);

        pathJTextField.setVisible(true);
        selectFileJButton.setText("Select file");
        selectFileJButton.setVisible(true);
    }

    private void setUploadYouTubeJMenuItemAction(){
        downloadFlag = false;
        youTubeFlag = true;

        pathJLabel.setText("http");
        emailJLabel.setVisible(true);
        emailJTextField.setVisible(true);

        selectFileJButton.setVisible(false);
    }

    private void helpJMenuItemAction() {
        String previousWindow;
        if (downloadFlag){
            previousWindow = "download";
        }
        else {
            previousWindow = "upload";
        }
        HelpWindow helpWindow = new HelpWindow(previousWindow);
        helpWindow.setHelpWindow();
    }

    private void selectFileJButtonAction() {
        JFileChooser chooser = new JFileChooser();
        if (downloadFlag) {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        } else {
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
        chooser.showOpenDialog(null);
        File directoryOrFile = chooser.getSelectedFile();
        pathJTextField.setText(directoryOrFile.getAbsolutePath());
    }

    private void enterJButtonAction() {
        if (downloadFlag) {
            String jobName = jobNameJTextField.getText();
            String job = serviceDownload.sendDownloadHttp(jobName, pathJTextField.getText());
            jTextArea.setText(job);
        } else if (youTubeFlag) {
            serviceUpload.getMp4FromYoutube(pathJTextField.getText());
            serviceUpload.convertToBase64AndSend(jobNameJTextField.getText(), new File("src/main/resources/audio.mp3"), emailJTextField.getText(), true);
        } else {
            serviceUpload.convertToBase64AndSend(jobNameJTextField.getText(), new File(pathJTextField.getText()), emailJTextField.getText(), false);
        }
    }
}