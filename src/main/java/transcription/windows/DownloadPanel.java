package transcription.windows;

import transcription.services.ServiceDownload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

public class DownloadPanel extends JPanel implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(DownloadPanel.class.getName());

    private JButton selectDirectoryButton;
    private JButton retrieveButton;
    private JButton menuButton;
    private JTextField jobField;
    private JTextField filePathField;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ServiceDownload serviceDownload = new ServiceDownload();

    public DownloadPanel() {

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.bottom = 1;
        gbc.insets.top = 1;
        gbc.insets.right = 1;
        gbc.insets.left = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        setLayout(new GridBagLayout());
        setJLabels();
        setJTextField();
        setButtons();
        setAction();
        setSize(700, 300);

    }

    /**
     * Method creates the attributes of the JLabels and adds them to the panel
     */
    void setJLabels() {
        JLabel filePathLabel = new JLabel("Directory:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(filePathLabel, gbc);

        JLabel jobNameLabel = new JLabel("Job name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(jobNameLabel, gbc);
    }

    /**
     * Method creates the attributes of the JTextFields and adds them to the panel
     */
    void setJTextField() {
        filePathField = new JTextField(20);
        filePathField.setText("Directory not yet selected");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(filePathField, gbc);

        jobField = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(jobField, gbc);
    }

    /**
     * Method creates the attributes of the JButtons and adds them to the panel
     */
    void setButtons() {

        selectDirectoryButton = new JButton("Select directory");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(selectDirectoryButton, gbc);

        menuButton = new JButton("Menu");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(menuButton, gbc);

        retrieveButton = new JButton("Retrieve");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(retrieveButton, gbc);
    }

    /**
     * Method sets the actionListener
     */
    void setAction() {
        selectDirectoryButton.addActionListener(this);
        menuButton.addActionListener(this);
        retrieveButton.addActionListener(this);
    }

    /**
     * Method sets the actions carried out when the JButtons are pressed
     * @param actionEvent actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == selectDirectoryButton) {
                File directory = chooseFile();

                filePathField.setText(directory.getAbsolutePath());
            }

            if (actionEvent.getSource() == menuButton) {
                new MenuFrame();
            }

            if (actionEvent.getSource() == retrieveButton){
                String jobName = jobField.getText();

                serviceDownload.sendDownloadHttp(jobName, filePathField.getText());
            }

        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    /**
     * This method creates the JFileChooser that allows the selection of the directory the transcription text file is
     * being put in
     * @return File
     */
    private File chooseFile() {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);

        return chooser.getSelectedFile();
    }
}
