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

    private UploadFrame jFrame;
    private JButton sendButton;
    private JButton menuButton;
    private JButton filePathButton;
    private JTextField filePathField;
    private JTextField jobNameField;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ServiceUpload serviceUpload = new ServiceUpload();
    private String filePath = "No file path selected";
    private File mp4 = null;

    public UploadPanel(UploadFrame jFrame) {
        this.jFrame = jFrame;

        setJLabels();
        setJTextField();
        setButtons();
        setAction();
        setSize(700, 300);

    }

    /**
     * Method sets and add the JLabels to the panel
     */

    void setJLabels() {
        JLabel jobNameLabel = new JLabel("Job name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(jobNameLabel, gbc);

        JLabel filePathLabel = new JLabel("Select file path:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(filePathLabel, gbc);

    }

    /**
     * Method sets and add the JTextFields to the panel
     */
    void setJTextField() {

        filePathField = new JTextField(20);
        filePathField.setText(filePath);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(filePathField, gbc);

        jobNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(jobNameField, gbc);
    }

    /**
     * Method sets and add the JButtons to the panel
     */
    void setButtons() {
        sendButton = new JButton("Send");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(sendButton, gbc);

        menuButton = new JButton("Menu");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(menuButton, gbc);

        filePathButton = new JButton("Select file");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 0;
        gbc.weighty = 0;
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
                String jobName = jobNameField.getText();
                serviceUpload.convertToBase64AndSend(jobName, mp4);
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