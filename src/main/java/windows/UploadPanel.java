package windows;

import services.ServiceUpload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadPanel extends JPanel implements ActionListener {

    private UploadFrame jFrame;
    private JButton sendButton;
    private JButton menuButton;
    private JTextField filePathField;
    private JTextField jobNameField;
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

        JLabel filePathLabel = new JLabel("File path:");
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
    }

    /**
     * Method creates the actionListeners
     */
    void setAction() {
        sendButton.addActionListener(this);
        menuButton.addActionListener(this);
    }

    /**
     * Metbod sets the actions that will take place when buttons are pressed
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == sendButton) {
                String filePath = filePathField.getText();
                String jobName = jobNameField.getText();
                serviceUpload.convertToBase64AndSend(filePath, jobName);
            }
            if (actionEvent.getSource() == menuButton) {
                new MenuFrame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}