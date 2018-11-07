package bg.sofia.uni.fmi.mjt.git.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    static public String hexDigest(String input) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return convertBytesToHex(bytes);
    }

    static private String convertBytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte current : bytes) {
            hex.append(String.format("%02x", current));
        }

        return hex.toString();
    }
}
