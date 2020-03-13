package windows;

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
    private JTextField filePathField;
    private JLabel jobNameLabel;
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

    /**
     * Method creates the attributes of the JLabels and adds them to the panel
     */

    void setJLabels() {
        filePathLabel = new JLabel("File path:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(filePathLabel, gbc);

        jobNameLabel = new JLabel("Job name:");
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

        sendButton = new JButton("Retrieve");
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
     * Method sets the actionListener
     */
    void setAction() {
        sendButton.addActionListener(this);
        menuButton.addActionListener(this);
    }

    /**
     * Method sets the actions carried out when the JButtons are pressed
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == sendButton) {
                String jobName = jobField.getText();
                String filePath = filePathField.getText();
                serviceDownload.sendDownloadHttp(jobName, filePath);
            }
            if (actionEvent.getSource() == menuButton) {
                new MenuFrame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
