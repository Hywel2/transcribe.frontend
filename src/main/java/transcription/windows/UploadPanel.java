package transcription.windows;

import transcription.services.ServiceUpload;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

public class UploadPanel extends JPanel implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(UploadPanel.class.getName());

    private JButton sendButton;
    private JButton menuButton;
    private JButton filePathButton;
    private JTextField filePathField;
    private JTextField jobNameField;
    private JTextField httpField;
    private JTextField emailField;
    private JLabel jobNameLabel;
    private JLabel filePathLabel;
    private JLabel httpLabel;
    private JLabel emailLabel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ServiceUpload serviceUpload = new ServiceUpload();
    private String filePath = "No file path selected";

    public UploadPanel() {

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.bottom = 1;
        gbc.insets.top = 1;
        gbc.insets.right = 1;
        gbc.insets.left = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        setLayout(new GridBagLayout());
        setFilePath();
        setHttp();
        setJobName();
        setEmail();
        setButtons();
        setAction();
    }

    private void setFilePath() {
        filePathLabel = new JLabel("File path:");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(filePathLabel, gbc);

        filePathField = new JTextField();
        filePathField.setText(filePath);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(filePathField, gbc);

    }

    private void setHttp() {
        httpLabel = new JLabel("YouTube http:");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(httpLabel, gbc);

        httpField = new JTextField();
        httpField.setText("Default");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(httpField, gbc);
    }

    private void setJobName() {
        jobNameLabel = new JLabel("Job name:");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(jobNameLabel, gbc);

        jobNameField = new JTextField();
        jobNameField.setText("Default");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(jobNameField, gbc);

    }

    private void setEmail() {
        emailLabel = new JLabel("Email:");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(emailLabel, gbc);

        emailField = new JTextField();
        emailField.setText("Default");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(emailField, gbc);
    }

    /**
     * Method sets and add the JButtons to the panel
     */
    void setButtons() {
        sendButton = new JButton("Send");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(sendButton, gbc);

        menuButton = new JButton("Menu");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(menuButton, gbc);

        filePathButton = new JButton("Select file");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(filePathButton, gbc);

    }

    /**
     * Method creates the actionListeners
     */
    void setAction() {
        sendButton.addActionListener(this);
        menuButton.addActionListener(this);
        filePathButton.addActionListener(this);
    }

    /**
     * Method sets the actions that will take place when buttons are pressed
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == sendButton) {
                if (!httpField.getText().equals("Default")) {
                    serviceUpload.getMp4FromYoutube(httpField.getText(), httpField.getText(), jobNameField.getText(), emailField.getText());
                } else {
                    serviceUpload.convertToBase64AndSend(jobNameField.getText(), new File (filePathField.getText()), emailField.getText());
                }
            }

            if (actionEvent.getSource() == menuButton) {
                new MenuFrame();
            }

            if (actionEvent.getSource() == filePathButton) {
                filePathField.setText(chooseFile().getAbsolutePath());
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    /**
     * This method creates the JFileChooser that allows the selection of the file being sent to AWS
     *
     * @return File
     */
    private File chooseFile() {
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter(null,
                "MP4");

        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showOpenDialog(null);

        return chooser.getSelectedFile();
    }
}