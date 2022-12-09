import java.awt.*;
import java.io.File;
import javax.swing.*;

public class Window {
    public final JFrame frame = new JFrame("File Hasher");

    private final JTextField pathField = new JTextField();
    private final JButton openButton = new JButton("Open");
    private final JButton hashButton = new JButton("Hash");
    private final JTextPane md5Field = new JTextPane();
    private final JTextPane sha1Field = new JTextPane();
    private final JTextPane sha256Field = new JTextPane();
    private final JCheckBox cacheCheckBox = new JCheckBox();

    public Window() {
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 280);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel pathLabel = new JLabel("Select the file:");
        pathLabel.setForeground(Color.BLACK);
        pathLabel.setBounds(10, 5, 100, 20);
        frame.add(pathLabel);

        pathField.setEditable(false);
        pathField.setBounds(10, 25, 390, 25);
        openButton.setBounds(405, 25, 70, 25);
        hashButton.setEnabled(false);
        hashButton.setBounds(10, 60, 465, 25);

        frame.add(pathField);
        frame.add(openButton);
        frame.add(hashButton);

        JLabel md5Label = new JLabel("MD5");
        md5Label.setForeground(Color.BLACK);
        md5Label.setBounds(10, 100, 50, 20);
        frame.add(md5Label);

        JLabel sha1Label = new JLabel("SHA-1");
        sha1Label.setForeground(Color.BLACK);
        sha1Label.setBounds(10, 140, 50, 20);
        frame.add(sha1Label);

        JLabel sha256Label = new JLabel("SHA-256");
        sha256Label.setForeground(Color.BLACK);
        sha256Label.setBounds(10, 180, 50, 20);
        frame.add(sha256Label);

        md5Field.setEditable(false);
        md5Field.setBackground(null);
        md5Field.setContentType("text/html");
        md5Field.setText("...");
        md5Field.setBounds(10, 115, 470, 20);
        frame.add(md5Field);

        sha1Field.setEditable(false);
        sha1Field.setBackground(null);
        sha1Field.setContentType("text/html");
        sha1Field.setText("...");
        sha1Field.setBounds(10, 155, 465, 20);
        frame.add(sha1Field);

        sha256Field.setEditable(false);
        sha256Field.setBackground(null);
        sha256Field.setContentType("text/html");
        sha256Field.setText("...");
        sha256Field.setBounds(10, 195, 465, 20);
        frame.add(sha256Field);

        JLabel tipLabel = new JLabel("Double-click the text to select the hash.");
        tipLabel.setForeground(Color.DARK_GRAY);
        tipLabel.setBounds(10, 220, 465, 20);
        tipLabel.setFont(tipLabel.getFont().deriveFont(Font.ITALIC));
        frame.add(tipLabel);

        cacheCheckBox.setSelected(true);
        cacheCheckBox.setBounds(360, 220, 20, 20);
        frame.add(cacheCheckBox);

        JLabel cacheLabel = new JLabel("Cache the hash");
        cacheLabel.setForeground(Color.BLACK);
        cacheLabel.setBounds(380, 220, 100, 20);
        frame.add(cacheLabel);

        frame.repaint();
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getHashButton() {
        return hashButton;
    }

    public void setFilePath(String path) {
        pathField.setText(path);
    }

    public String getFilePath() {
        return pathField.getText();
    }

    public void setMD5Text(String text) {
        md5Field.setText(text);
    }

    public void setSHA1Text(String text) {
        sha1Field.setText(text);
    }

    public void setSHA256Text(String text) {
        sha256Field.setText(text);
    }

    public boolean isCacheEnabled() {
        return cacheCheckBox.isSelected();
    }

    public String openFile() {
        FileDialog dialog =  new FileDialog(frame, "Select a file", FileDialog.LOAD);
        dialog.setVisible(true);

        String path = null;
        File[] files = dialog.getFiles();
        dialog.dispose();

        if (files.length > 0) {
            path = files[0].getPath();
        }

        return path;
    }
}
