package transcription.windows;

import transcription.services.ServiceDownload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class DownloadWindow implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(DownloadWindow.class.getName());

    private JButton selectDirectoryButton;
    private JButton retrieveButton;
    private JButton menuButton;
    private JButton helpButton;
    private JTextField jobField;
    private JTextField filePathField;
    private GridBagConstraints panelGbc = new GridBagConstraints();
    private GridBagConstraints frameGbc = new GridBagConstraints();
    private ServiceDownload serviceDownload = new ServiceDownload();
    private JPanel downloadPanel = new JPanel();

    public JButton getSelectDirectoryButton() {
        return selectDirectoryButton;
    }

    public JButton getRetrieveButton() {
        return retrieveButton;
    }

    public JButton getMenuButton() {
        return menuButton;
    }

    public JButton getHelpButton() {
        return helpButton;
    }

    public JFrame setDownloadWindow() {
        JFrame downloadFrame = new JFrame();
        downloadFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        downloadFrame.setLayout(new GridBagLayout());
        downloadFrame.setTitle("Download file");
        downloadFrame.setSize(700, 300);
        downloadFrame.setLocationRelativeTo(null);
        downloadFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        downloadPanel = setDownloadPanel();
        downloadFrame.add(downloadPanel, frameGbc);
        downloadFrame.getContentPane().setLayout(new GridBagLayout());
        downloadFrame.setVisible(true);
        return downloadFrame;
    }

    public JPanel setDownloadPanel() {

        panelGbc.fill = GridBagConstraints.HORIZONTAL;
        panelGbc.insets.bottom = 1;
        panelGbc.insets.top = 1;
        panelGbc.insets.right = 1;
        panelGbc.insets.left = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        downloadPanel.setLayout(new GridBagLayout());
        setJobName(downloadPanel);
        setDirectory(downloadPanel);
        setButtons(downloadPanel);
        setAction();
        downloadPanel.setSize(700, 300);

        return downloadPanel;
    }

    /**
     * This method sets the JLabel and textField that allows selection of the directory to download into
     * @param downloadPanel
     */
    void setDirectory(JPanel downloadPanel) {
        JLabel filePathLabel = new JLabel("Directory:");
        panelGbc.gridx = 0;
        panelGbc.gridy = 2;
        panelGbc.weightx = 0.5;
        panelGbc.weighty = 0.5;
        downloadPanel.add(filePathLabel, panelGbc);

        filePathField = new JTextField(20);
        filePathField.setText("Directory not yet selected");
        panelGbc.gridx = 2;
        panelGbc.gridy = 2;
        panelGbc.weightx = 0;
        panelGbc.weighty = 0;
        panelGbc.gridwidth = 4;
        downloadPanel.add(filePathField, panelGbc);
    }

    /**
     * Method creates JLabel and JTextField that allows input of the jobName variable
     */
    void setJobName(JPanel downLoadPanel) {

        JLabel jobNameLabel = new JLabel("Job name:");
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panelGbc.weightx = 0.5;
        panelGbc.weighty = 0.5;
        downLoadPanel.add(jobNameLabel, panelGbc);

        jobField = new JTextField(20);
        panelGbc.gridx = 2;
        panelGbc.gridy = 0;
        panelGbc.weightx = 0;
        panelGbc.weighty = 0;
        panelGbc.gridwidth = 4;
        downloadPanel.add(jobField, panelGbc);
    }

    /**
     * Method creates the attributes of the JButtons and adds them to the panel
     *
     * @param downloadPanel
     */
    void setButtons(JPanel downloadPanel) {

        selectDirectoryButton = new JButton("Select directory");
        panelGbc.gridx = 1;
        panelGbc.gridy = 5;
        panelGbc.weightx = 0;
        panelGbc.weighty = 0;
        downloadPanel.add(selectDirectoryButton, panelGbc);

        menuButton = new JButton("Menu");
        panelGbc.gridx = 1;
        panelGbc.gridy = 3;
        panelGbc.weightx = 0;
        panelGbc.weighty = 0;
        downloadPanel.add(menuButton, panelGbc);

        retrieveButton = new JButton("Retrieve");
        panelGbc.gridx = 1;
        panelGbc.gridy = 7;
        panelGbc.weightx = 0;
        panelGbc.weighty = 0;
        downloadPanel.add(retrieveButton, panelGbc);

        helpButton = new JButton("Help");
        panelGbc.gridx = 1;
        panelGbc.gridy = 9;
        panelGbc.weightx = 0;
        panelGbc.weighty = 0;
        downloadPanel.add(helpButton, panelGbc);
    }

    /**
     * Method sets the actionListener
     */
    void setAction() {
        selectDirectoryButton.addActionListener(this);
        menuButton.addActionListener(this);
        retrieveButton.addActionListener(this);
        helpButton.addActionListener(this);
    }

    /**
     * Method sets the actions carried out when the JButtons are pressed
     *
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
                MenuWindow menuWindow = new MenuWindow();
                menuWindow.setMenuWindow();
            }

            if (actionEvent.getSource() == retrieveButton) {
                String jobName = jobField.getText();

                serviceDownload.sendDownloadHttp(jobName, filePathField.getText());
            }

            if (actionEvent.getSource() == helpButton) {
                HelpWindow helpWindow = new HelpWindow("download");
                helpWindow.setHelpWindow();
            }

        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    /**
     * This method creates the JFileChooser that allows the selection of the directory the transcription text file is
     * being put in
     *
     * @return File
     */
    public File chooseFile() {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);

        return chooser.getSelectedFile();
    }
}
