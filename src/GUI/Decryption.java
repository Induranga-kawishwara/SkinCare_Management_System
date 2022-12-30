package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Decryption {
    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        // String key = "Bar12345Bar12345Bar12345"; // 192 bit key
        // String key = "Bar12345Bar12345Bar12345Bar12345"; // 256 bit key
        FileInputStream inFile = null;
        FileOutputStream outFile = null;
        try {
            inFile = new FileInputStream("output.png");
            outFile = new FileOutputStream("decrypted.png");
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] input = new byte[64];
            int bytesRead;
            while ((bytesRead = inFile.read(input)) != -1) {
                byte[] output = cipher.update(input, 0, bytesRead);
                outFile.write(output);
            }
            byte[] output = cipher.doFinal();
            outFile.write(output);
            inFile.close();
            outFile.flush();
            outFile.close();
            System.out.println("File Decrypted.");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                 | InvalidKeyException | IOException
                 | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e.getMessage());
        }
    }
}
