package transcription.windows;

import javax.swing.*;

import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class PopUpWindow {

    private JLabel pleaseWaitJLabel = new JLabel("Please wait");
    private GridBagConstraints containerGbc = new GridBagConstraints();
    private Container contentPaneContainer = new Container();
    private JFrame pleaseWaitJFrame;
    private long timeForCall;
    private Integer numberOfPiecesMinusEnd;

    public JLabel getPleaseWaitJLabel() {
        return pleaseWaitJLabel;
    }

    public JFrame getPleaseWaitJFrame() {
        return pleaseWaitJFrame;
    }

    public JFrame setPleaseWaitWindow() {
        pleaseWaitJFrame = new JFrame();
        contentPaneContainer = setContentPane();
        pleaseWaitJFrame.setContentPane(contentPaneContainer);
        pleaseWaitJFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pleaseWaitJFrame.setTitle("");
        pleaseWaitJFrame.setSize(350, 150);
        pleaseWaitJFrame.setLocationRelativeTo(null);
        pleaseWaitJFrame.setVisible(true);

        return pleaseWaitJFrame;
    }

    private Container setContentPane() {
        containerGbc.insets.bottom = 1;
        containerGbc.insets.top = 2;
        containerGbc.insets.right = 1;
        containerGbc.insets.left = 1;
        containerGbc.weightx = 1;
        containerGbc.weighty = 1;

        contentPaneContainer.setLayout(new GridBagLayout());
        contentPaneContainer.setSize(800, 700);
        setPleaseWaitJLabel();

        return contentPaneContainer;
    }

    private void setPleaseWaitJLabel() {
        containerGbc.gridx = 2;
        containerGbc.gridy = 5;
        containerGbc.gridwidth = 2;
        containerGbc.gridheight = 1;
        contentPaneContainer.add(pleaseWaitJLabel, containerGbc);
    }

    public void setJLabelDisplay(String displayTime) {
        pleaseWaitJLabel.setText(displayTime);
    }

    public void closeWindow() {
        pleaseWaitJFrame.dispose();
    }


}