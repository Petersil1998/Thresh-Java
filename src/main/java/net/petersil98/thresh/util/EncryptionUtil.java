package net.petersil98.thresh.util;

import net.petersil98.thresh.Thresh;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

    private static final String SECRET_KEY_1 = "u8x/A?D(G+KbPeSg";
    private static final String SECRET_KEY_2 = "WnZr4u7x!A%D*G-K";

    private static IvParameterSpec ivParameterSpec;
    private static SecretKeySpec secretKeySpec;
    private static Cipher cipher;

    private static final Marker MARKER = MarkerManager.getMarker(EncryptionUtil.class.getSimpleName());

    static {
        try {
            ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes(StandardCharsets.UTF_8));
            secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes(StandardCharsets.UTF_8), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String string) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(string.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            Thresh.LOGGER.error(MARKER, "Failed to encode data", e);
        }
        return "";
    }

    public static String decrypt(String string) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(string));
            return new String(decryptedBytes);
        } catch (Exception e) {
            Thresh.LOGGER.error(MARKER, "Failed to decode data", e);
        }
        return "";
    }
}
