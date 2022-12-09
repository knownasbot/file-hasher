import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;

public class HashManager {
    public String path;

    public HashManager(String path) throws FileNotFoundException {
        this.path = path;
    }

    public String digest(String algo) throws NoSuchAlgorithmException, IOException {
        MessageDigest msgDigest = MessageDigest.getInstance(algo);
        FileInputStream fileStream = new FileInputStream(path);
        DigestInputStream digestStream = new DigestInputStream(fileStream, msgDigest);

        byte[] buffer = new byte[1024 * 8];
        while (true) {
            if (digestStream.read(buffer) < 0) break;
        }

        digestStream.close();

        return bytesToHex(msgDigest.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();

        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }

        return hex.toString();
    }
}
