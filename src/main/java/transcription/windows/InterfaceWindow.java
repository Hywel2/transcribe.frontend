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
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
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

    public JMenuItem getUploadYouTubeJMenuItem() {
        return uploadYouTubeJMenuItem;
    }

    public JMenuItem getUploadFileJMenuItem() {
        return uploadFileJMenuItem;
    }

    public JMenuItem getDownloadJMenuItem() {
        return downloadJMenuItem;
    }

    public JMenuItem getHelpJMenuItem() {
        return helpJMenuItem;
    }

    /**
     * This method creates the settings for the interfaceWindow JFrame, adding the JMenuBar, setting the contentPane,
     * and several other features.
     *
     * @return JFrame
     */
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

    /**
     * This method sets the contentPane Container that has the various components in a GridBagConstraints layout.
     * It adds the JTextFields, JLabels, JButtons and JTextArea using several methods.
     *
     * @return Container
     */
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

    /**
     * This method take the JMenuBar provided when setting up the JFrame and adds the various JMenus and
     * JMenuItems
     *
     * @param toolBarJMenuBar JMenuBar
     * @return JMenuBar
     */
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

    /**
     * This method sets the JLabel and JTextfield associated with the file path/http
     *
     * @param interfaceContainer Container
     */
    private void setPath(Container interfaceContainer) {

        containerGbc.gridx = 2;
        containerGbc.gridy = 1;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        interfaceContainer.add(pathJLabel, containerGbc);

        pathJTextField = new JTextField();
        pathJTextField.setColumns(40);
        pathJTextField.setText(filePath);
        containerGbc.gridx = 5;
        containerGbc.gridy = 1;
        containerGbc.gridwidth = 8;
        containerGbc.gridheight = 1;
        interfaceContainer.add(pathJTextField, containerGbc);
    }

    /**
     * This method sets the JTextField and JLabel associated with the jobName
     *
     * @param interfaceContainer Container
     */
    private void setJobName(Container interfaceContainer) {
        JLabel jobNameLabel = new JLabel("Job name:");

        containerGbc.gridx = 2;
        containerGbc.gridy = 3;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        interfaceContainer.add(jobNameLabel, containerGbc);

        jobNameJTextField = new JTextField();
        jobNameJTextField.setColumns(40);
        jobNameJTextField.setText(emptyField);
        containerGbc.gridx = 5;
        containerGbc.gridy = 3;
        containerGbc.gridwidth = 8;
        containerGbc.gridheight = 1;
        interfaceContainer.add(jobNameJTextField, containerGbc);
    }

    /**
     * This method sets the JTextFiled and JLabel associated with the email
     *
     * @param interfaceContainer Container
     */
    private void setEmail(Container interfaceContainer) {

        containerGbc.gridx = 2;
        containerGbc.gridy = 5;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        interfaceContainer.add(emailJLabel, containerGbc);

        emailJTextField = new JTextField();

        emailJTextField.setText(emptyField);
        emailJTextField.setColumns(40);
        containerGbc.gridx = 5;
        containerGbc.gridy = 5;
        containerGbc.gridwidth = 8;
        containerGbc.gridheight = 1;
        interfaceContainer.add(emailJTextField, containerGbc);
    }

    /**
     * This method sets the JTextArea
     *
     * @param interfaceContainer Container
     */
    private void setTextArea(Container interfaceContainer) {
        jTextArea.setText("Default");
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.append("");
        JScrollPane scroll = new JScrollPane(jTextArea,
                VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        containerGbc.gridx = 2;
        containerGbc.gridy = 9;
        containerGbc.gridheight = 8;
        containerGbc.gridwidth = 11;
        interfaceContainer.add(scroll, containerGbc);
    }

    /**
     * This method creates sets the JButtons for the container
     *
     * @param interfaceContainer Container
     */
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

    /**
     * This method adds ActionListeners to the JButtons and JMenuItems
     */
    void setAction() {
        copyToClipBoardJButton.addActionListener(this);
        enterJButton.addActionListener(this);
        selectFileJButton.addActionListener(this);
        uploadYouTubeJMenuItem.addActionListener(this);
        helpJMenuItem.addActionListener(this);
        uploadFileJMenuItem.addActionListener(this);
        downloadJMenuItem.addActionListener(this);
    }

    /**
     * This method creates the correct response according to the JButton or JMenuItem pressed. It calls the correct
     * method depending on the component, or, in the case of the copyToClipBoardJButton it uses the Clipboard object
     * to copy the content of the JTextArea
     *
     * @param actionEvent ActionEvent
     */
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
                StringSelection stringSelection = new StringSelection(jTextArea.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
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

    /**
     * This method creates the correct settings when the download JMenuItem is pressed. It sets the flags so the
     * program knows which way buttons should behave and sets visibility/text on JButtons and JLabels
     */
    private void downloadJMenuItemAction() {
        pathJTextField.setText(emptyField);

        downloadFlag = true;
        youTubeFlag = false;

        pathJLabel.setText("Directory");
        emailJLabel.setVisible(false);
        emailJTextField.setVisible(false);

        selectFileJButton.setVisible(true);
        selectFileJButton.setText("Select directory");
    }

    /**
     * This method creates the correct settings when the uploadFile JMenuItem is pressed. It sets the flags so the
     * program knows which way buttons should behave and sets visibility/text on JButtons and JLabels
     */
    private void setUploadFileJMenuItemAction() {
        pathJTextField.setText(emptyField);

        downloadFlag = false;
        youTubeFlag = false;

        pathJLabel.setText("File");
        emailJLabel.setVisible(true);
        emailJTextField.setVisible(true);

        pathJTextField.setVisible(true);
        selectFileJButton.setText("Select file");
        selectFileJButton.setVisible(true);
    }

    /**
     * This method creates the correct settings when the uploadYouTube JMenuItem is pressed. It sets the flags so the
     * program knows which way buttons should behave and sets visibility/text on JButtons and JLabels
     */
    private void setUploadYouTubeJMenuItemAction() {
        pathJTextField.setText(emptyField);

        downloadFlag = false;
        youTubeFlag = true;

        pathJLabel.setText("http");
        emailJLabel.setVisible(true);
        emailJTextField.setVisible(true);

        selectFileJButton.setVisible(false);
    }

    /**
     * This method creates the HelpWindow object, sending a parameter to allow the HelpWindow to display the
     * correct information.
     */
    private void helpJMenuItemAction() {
        String previousWindow;
        if (downloadFlag) {
            previousWindow = "download";
        } else {
            previousWindow = "upload";
        }
        HelpWindow helpWindow = new HelpWindow(previousWindow);
        helpWindow.setHelpWindow();
    }

    /**
     * This creates the response of the pathJButton. It uses the downloadFlag to work out whether directories or
     * files should be selected in the JFileChooser.
     */
    private void selectFileJButtonAction() {
        JFileChooser chooser = new JFileChooser();
        if (downloadFlag) {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        } else {
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
        chooser.showOpenDialog(null);
        File directoryOrFile = chooser.getSelectedFile();
        if (directoryOrFile != null) {
            pathJTextField.setText(directoryOrFile.getAbsolutePath());
        }
    }

    /**
     * This method creates the response of the enterJButton. It uses the downloadFlag and youTubeFlag to create
     * the correct response.
     */
    private void enterJButtonAction() {
        if (downloadFlag) {
            String jobName = jobNameJTextField.getText();
            String job = serviceDownload.sendDownloadHttp(jobName, pathJTextField.getText());
            jTextArea.setText(job);
        } else if (youTubeFlag) {
            serviceUpload.getMp4FromYoutube(pathJTextField.getText());
            if ((serviceUpload.convertToBase64AndSend(jobNameJTextField.getText(), new File("src/main/resources/audio.mp3"), emailJTextField.getText(), true)).equals("\"{FailureReason: The requested job name already exists,}\"")){
                jTextArea.setText("The requested job name already exists. Please choose another.");
            }
        } else {
            if ((serviceUpload.convertToBase64AndSend(jobNameJTextField.getText(), new File(pathJTextField.getText()), emailJTextField.getText(), false)).equals("\"{FailureReason: The requested job name already exists,}\"")){
                jTextArea.setText("The requested job name already exists. Please choose another job name.");
            }
        }
    }
}