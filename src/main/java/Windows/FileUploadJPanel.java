package Windows;

import services.ServiceMP3Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FileUploadJPanel extends JPanel implements ActionListener {

    private FileUploadJFrame jFrame;
    private JButton sendButton;
    private JTextField filePathField;
    private JLabel filePathLabel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ServiceMP3Converter serviceMP3Converter = new ServiceMP3Converter();

    public FileUploadJPanel(FileUploadJFrame jFrame) {

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
    }

    void setButtons() {

        this.setLayout(new GridBagLayout());
        sendButton = new JButton("Send");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(sendButton, gbc);

    }

    void setAction() {
        sendButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == sendButton) {
            String filePath = filePathField.getText();
            try {
                serviceMP3Converter.convertToBase64(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    "/Users/hywel/Documents/home/pictures/Test.mp4"
}
