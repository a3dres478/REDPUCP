/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.utils;

/**
 *
 * @author HECTOR
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.Key;

public class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "RedPUCP2025Key!!"; // Clave de 16 caracteres para AES-128
    
    private static Key generateKey() {
        return new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    }
    
    public static String encrypt(String data) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }
    
    public static String decrypt(String encryptedData) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar", e);
        }
    }
}
