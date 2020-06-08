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

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class UploadWindow implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(UploadWindow.class.getName());

    private JButton sendButton;
    private JButton menuButton;
    private JButton filePathButton;
    private JTextField filePathField;
    private JTextField jobNameField;
    private JTextField httpField;
    private JTextField emailField;
    private GridBagConstraints frameGbc = new GridBagConstraints();
    private GridBagConstraints panelGbc = new GridBagConstraints();
    private ServiceUpload serviceUpload = new ServiceUpload();
    private String filePath = "No file path selected";
    private JPanel uploadPanel = new JPanel();
    private String emptyField = "default";

    public JFrame setUploadWindow(){
        JFrame uploadFrame = new JFrame();
        uploadFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        uploadFrame.setLayout(new GridBagLayout());
        uploadFrame.setTitle("Upload file");
        uploadFrame.setSize(700, 300);
        uploadFrame.setLocationRelativeTo(null);
        uploadFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        uploadPanel = setUploadPanel();
        uploadFrame.add(uploadPanel,frameGbc);
        uploadFrame.getContentPane().setLayout(new GridBagLayout());
        uploadFrame.setVisible(true);
        return uploadFrame;
    }

    public JPanel setUploadPanel() {

        panelGbc.fill = GridBagConstraints.HORIZONTAL;
        panelGbc.insets.bottom = 1;
        panelGbc.insets.top = 1;
        panelGbc.insets.right = 1;
        panelGbc.insets.left = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        uploadPanel.setLayout(new GridBagLayout());
        setFilePath(uploadPanel);
        setHttp(uploadPanel);
        setJobName(uploadPanel);
        setEmail(uploadPanel);
        setButtons(uploadPanel);
        setAction();
        uploadPanel.setSize(700, 300);

        return uploadPanel;
    }

    private void setFilePath(JPanel uploadPanel) {
        JLabel filePathLabel = new JLabel("File path:");
        panelGbc.gridx = 1;
        panelGbc.gridy = 0;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(filePathLabel, panelGbc);

        filePathField = new JTextField();
        filePathField.setText(filePath);
        panelGbc.gridx = 2;
        panelGbc.gridy = 0;
        panelGbc.gridwidth = 2;
        panelGbc.gridheight = 1;
        uploadPanel.add(filePathField, panelGbc);

    }

    private void setHttp(JPanel uploadPanel) {
        JLabel httpLabel = new JLabel("YouTube http:");
        panelGbc.gridx = 1;
        panelGbc.gridy = 1;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(httpLabel, panelGbc);

        httpField = new JTextField();
        httpField.setText(emptyField);
        panelGbc.gridx = 2;
        panelGbc.gridy = 1;
        panelGbc.gridwidth = 2;
        panelGbc.gridheight = 1;
        uploadPanel.add(httpField, panelGbc);
    }

    private void setJobName(JPanel uploadPanel) {
        JLabel jobNameLabel = new JLabel("Job name:");
        panelGbc.gridx = 1;
        panelGbc.gridy = 2;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(jobNameLabel, panelGbc);

        jobNameField = new JTextField();
        jobNameField.setText(emptyField);
        panelGbc.gridx = 2;
        panelGbc.gridy = 2;
        panelGbc.gridwidth = 2;
        panelGbc.gridheight = 1;
        uploadPanel.add(jobNameField, panelGbc);

    }

    /**
     * Method adds the email label and textfield.
     * @param uploadPanel
     */

    private void setEmail(JPanel uploadPanel) {
        JLabel emailLabel = new JLabel("Email:");
        panelGbc.gridx = 1;
        panelGbc.gridy = 3;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(emailLabel, panelGbc);

        emailField = new JTextField();
        emailField.setText(emptyField);
        panelGbc.gridx = 2;
        panelGbc.gridy = 3;
        panelGbc.gridwidth = 2;
        panelGbc.gridheight = 1;
        uploadPanel.add(emailField, panelGbc);
    }

    /**
     * Method sets and add the JButtons to the panel
     * @param uploadPanel
     */
    void setButtons(JPanel uploadPanel) {
        sendButton = new JButton("Send");
        panelGbc.gridx = 0;
        panelGbc.gridy = 4;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(sendButton, panelGbc);

        menuButton = new JButton("Menu");
        panelGbc.gridx = 1;
        panelGbc.gridy = 4;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(menuButton, panelGbc);

        filePathButton = new JButton("Select file");
        panelGbc.gridx = 2;
        panelGbc.gridy = 4;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        uploadPanel.add(filePathButton, panelGbc);

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
                if (!httpField.getText().equals(emptyField)) {
                    serviceUpload.getMp4FromYoutube(httpField.getText(), httpField.getText(), jobNameField.getText(), emailField.getText());
                } else {
                    serviceUpload.convertToBase64AndSend(jobNameField.getText(), new File(filePathField.getText()), emailField.getText());
                }
            }

            if (actionEvent.getSource() == menuButton) {
                MenuWindow menuWindow = new MenuWindow();
                menuWindow.setMenuWindow();
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
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);

        return chooser.getSelectedFile();
    }
}
