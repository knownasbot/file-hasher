import javax.swing.*;
import java.security.*;
import java.util.HashMap;
import java.io.IOException;

public class Main {
    private static final HashMap<String, String[]> cache = new HashMap<>();

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Window app = new Window();

        app.getOpenButton().addActionListener((l) -> {
            String path = app.openFile();

            if (path != null) {
                app.setFilePath(path);
                app.getHashButton().setEnabled(true);
            }
        });

        app.getHashButton().addActionListener((l) -> {
            String path = app.getFilePath();

            try {
                String md5 = null;
                String sha1 = null;
                String sha256 = null;

                if (app.isCacheEnabled()) {
                    String[] hashes = cache.get(path);

                    if (hashes != null) {
                        md5 = hashes[0];
                        sha1 = hashes[1];
                        sha256 = hashes[2];
                    }
                }

                if (md5 == null) {
                    HashManager hashManager = new HashManager(path);

                    md5 = hashManager.digest("MD5");
                    sha1 = hashManager.digest("SHA-1");
                    sha256 = hashManager.digest("SHA-256");

                    cache.put(path, new String[]{ md5, sha1, sha256 });
                }

                app.setMD5Text(md5);
                app.setSHA1Text(sha1);
                app.setSHA256Text(sha256);
            } catch (NoSuchAlgorithmException | IOException e) {
                app.setMD5Text("...");
                app.setSHA1Text("...");
                app.setSHA256Text("...");

                JOptionPane.showMessageDialog(
                        app.frame,
                        "The program cannot read the file.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                e.printStackTrace();
            }
        });
    }
}