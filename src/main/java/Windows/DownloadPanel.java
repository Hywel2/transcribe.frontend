package Windows;

import services.ServiceDownload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloadPanel extends JPanel implements ActionListener {

    private DownloadFrame jFrame;
    private JButton sendButton;
    private JButton menuButton;
    private JTextField jobField;
    private JLabel filePathLabel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ServiceDownload serviceDownload = new ServiceDownload();

    public DownloadPanel(DownloadFrame jFrame) {

        this.jFrame = jFrame;

        setJLabels();
        setJTextField();
        setButtons();
        setAction();
        setSize(700, 300);

    }

    void setJLabels() {

        filePathLabel = new JLabel("Name of job:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(filePathLabel, gbc);

    }

    void setJTextField() {

        this.setLayout(new GridBagLayout());
        jobField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        add(jobField, gbc);
    }

    void setButtons() {

        this.setLayout(new GridBagLayout());
        sendButton = new JButton("Retrieve");
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
        if (actionEvent.getSource() == sendButton) {
            String jobName = jobField.getText();
            try {
                serviceDownload.sendDownloadHttp(jobName+".json");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == menuButton) {
            try {
                MenuFrame menuFrame = new MenuFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
