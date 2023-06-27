package com.example.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Encryption {

    private static SecretKey secretKey = generateKey();

    public static SecretKey getSecretKey(){
        return secretKey;
    }
    private static SecretKey generateKey(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encrypt(String input, SecretKey key){
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(byte[] encrypted, SecretKey key){
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] answer = cipher.doFinal(encrypted);
            return new String(answer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
