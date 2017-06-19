package com.lazycouple.restapiclient.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by whylee on 2015. 12. 30..
 */
public class EncryptionUtils {
    public static byte[] encryptAES(byte[] secret, byte[] data) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] result = null;
        try {
            SecretKeySpec key = new SecretKeySpec(secret, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            result = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ignored) {
        }
        return result;
    }

    public static byte[] decryptAES(byte[] secret, byte[] data) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] result = null;
        try {
            SecretKeySpec key = new SecretKeySpec(secret, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ignored) {
        }
        return result;
    }

    public static byte[] sha512(byte[] data) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(data);
            result = md.digest();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return result;
    }

    public static byte[] sha256(byte[] data) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(data);
            result = md.digest();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return result;
    }

    public static byte[] md5(byte[] data) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(data);
            result = md.digest();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return result;
    }
}
