package iot.zjt.encrypt.machine;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;

/**
 * @author mrdrivingduck
 * @version 2019-05-10
 * 
 * @description Encryption machine for symmetrical encryption
 * 
 * @support
 *  AES/CBC/NoPadding (128)
 *  AES/CBC/PKCS5Padding (128)
 *  AES/ECB/NoPadding (128)
 *  AES/ECB/PKCS5Padding (128)
 *  DES/CBC/NoPadding (56)
 *  DES/CBC/PKCS5Padding (56)
 *  DES/ECB/NoPadding (56)
 *  DES/ECB/PKCS5Padding (56)
 *  DESede/CBC/NoPadding (168)
 *  DESede/CBC/PKCS5Padding (168)
 *  DESede/ECB/NoPadding (168)
 *  DESede/ECB/PKCS5Padding (168)
 */

public class Sym {

    public static enum SymAlgs {
        AES,
        DES,
        DESede
    }

    public static enum SymMode {
        ECB,
        CBC
    }

    public static enum SymPadding {
        PKCS5Padding,
        NoPadding
    }

    /**
     * @default_mode ECB
     * @default_padding PKCS5Padding
     */
    public static byte[] encrypt(byte[] plainText, byte[] key, SymAlgs algs)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, BadPaddingException, 
        IllegalBlockSizeException, DestroyFailedException {

        SecretKey secretKey = new SecretKeySpec(key, algs.name());
        Cipher cipher = Cipher.getInstance(algs.name());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText);
        // secretKey.destroy();
        return cipherText;
    }

    /**
     * @default_mode ECB
     * @default_padding PKCS5Padding
     */
    public static byte[] decrypt(byte[] cipherText, byte[] key, SymAlgs algs)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = new SecretKeySpec(key, algs.name());
        Cipher cipher = Cipher.getInstance(algs.name());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = cipher.doFinal(cipherText);
        // secretKey.destroy();
        return plainText;
    }

    public static byte[] encrypt(byte[] plainText, byte[] key, SymAlgs algs, SymMode mode, SymPadding padd)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, IllegalBlockSizeException,
        BadPaddingException, InvalidAlgorithmParameterException {
        
        SecretKey secretKey = new SecretKeySpec(key, algs.name());
        Cipher cipher = Cipher.getInstance(algs.name() + "/" + mode.name() + "/" + padd.name());
        
        byte[] ivRand = null;
        if (algs == SymAlgs.DES || algs == SymAlgs.DESede) {
            ivRand = new byte[8];
        } else if (algs == SymAlgs.AES) {
            ivRand = new byte[16];
        }
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(ivRand);
        IvParameterSpec ivSpec = new IvParameterSpec(ivRand);

        if (mode == SymMode.CBC) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        }

        byte[] cipherText = cipher.doFinal(plainText);

        return cipherText;
    }

    public static byte[] decrypt(byte[] plainText, byte[] key, SymAlgs algs, SymMode mode, SymPadding padd)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidAlgorithmParameterException {

        SecretKey secretKey = new SecretKeySpec(key, algs.name());
        Cipher cipher = Cipher.getInstance(algs.name() + "/" + mode.name() + "/" + padd.name());
        
        byte[] ivRand = null;
        if (algs == SymAlgs.DES || algs == SymAlgs.DESede) {
            ivRand = new byte[8];
        } else if (algs == SymAlgs.AES) {
            ivRand = new byte[16];
        }
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(ivRand);
        IvParameterSpec ivSpec = new IvParameterSpec(ivRand);

        if (mode == SymMode.CBC) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        }

        return null;
    }
        

    /**
     * @iv CBC mode only
     */
    public static byte[] encrypt(byte[] plainText, byte[] key, byte[] iv, SymAlgs algs, SymMode mode, SymPadding fill) {

        return null;
    }

}
