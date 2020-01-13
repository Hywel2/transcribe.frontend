package Windows;

import services.ServiceUpload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadPanel extends JPanel implements ActionListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UploadPanel.class);

    private UploadFrame jFrame;
    private JButton sendButton;
    private JButton menuButton;
    private JLabel jobNameLabel;
    private JTextField filePathField;
    private JTextField jobNameField;
    private JLabel filePathLabel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ServiceUpload serviceUpload = new ServiceUpload();

    public UploadPanel(UploadFrame jFrame) {

        this.jFrame = jFrame;

        setJLabels();
        setJTextField();
        setButtons();
        setAction();
        setSize(700, 300);

    }

    void setJLabels() {

        filePathLabel = new JLabel("File path:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(filePathLabel, gbc);

        jobNameLabel = new JLabel("Job name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(jobNameLabel, gbc);

    }

    void setJTextField() {

        this.setLayout(new GridBagLayout());
        filePathField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(filePathField, gbc);

        this.setLayout(new GridBagLayout());
        jobNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(jobNameField, gbc);
    }

    void setButtons() {

        this.setLayout(new GridBagLayout());
        sendButton = new JButton("Send");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(sendButton, gbc);

        this.setLayout(new GridBagLayout());
        menuButton = new JButton("Menu");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(menuButton, gbc);
    }

    void setAction() {
        sendButton.addActionListener(this);
        menuButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
        if (actionEvent.getSource() == sendButton) {
            String filePath = filePathField.getText();
            String jobName = jobNameField.getText();
                serviceUpload.convertToBase64AndSend(filePath, jobName);
            }
        if (actionEvent.getSource() == menuButton) {
                MenuFrame menuFrame = new MenuFrame();
            }
        } catch(Exception e){
            LOGGER.error(e);
        }
    }
}