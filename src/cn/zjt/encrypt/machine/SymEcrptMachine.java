package cn.zjt.encrypt.machine;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.zjt.encrypt.exception.IllegalHexCharacterException;
import cn.zjt.encrypt.util.CoderUtil;

/**
 * @author Mr Dk.
 * @version 2018.10.25
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

public class SymEcrptMachine {

    private KeyGenerator kGenerator;
    private SecretKey key;
    private Cipher cipher;
    private IvParameterSpec ivSpec; // CBC mode only

    private String algorithmName;
    private String encryptMode;

    /**
     * @version 2018.10.25
     * @param algorithm
     */
    public SymEcrptMachine(String algorithm) {
        paramInitialize(algorithm);

        try {
            kGenerator = KeyGenerator.getInstance(algorithmName);
            key = kGenerator.generateKey();
            cipher = Cipher.getInstance(algorithm);
            ivParameterGenerate();

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @version 2018.10.26
     * @param algorithm
     * @param keySize
     */
    public SymEcrptMachine(String algorithm, int keySize) {
        paramInitialize(algorithm);

        try {
            kGenerator = KeyGenerator.getInstance(algorithmName);
            kGenerator.init(keySize);
            key = kGenerator.generateKey();
            cipher = Cipher.getInstance(algorithm);
            ivParameterGenerate();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * ATTENTION : One character in keyStr represents for 8-bit
     * @version 2018.10.21
     * @param keyStr
     * @param algorithm
     */
    public SymEcrptMachine(String algorithm, String keyStr, String ivSpecStr) {
        paramInitialize(algorithm);

        try {
            key = new SecretKeySpec(keyStr.getBytes("utf-8"), algorithmName);
            cipher = Cipher.getInstance(algorithm);
            ivSpec = new IvParameterSpec(ivSpecStr.getBytes());
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public SymEcrptMachine(String algorithm, String keyStr) {
        paramInitialize(algorithm);

        try {
            key = new SecretKeySpec(keyStr.getBytes("utf-8"), algorithmName);
            cipher = Cipher.getInstance(algorithm);
            ivParameterGenerate();
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @version 2018.10.25
     * @param algorithm
     */
    private void paramInitialize(String algorithm) {
        String[] param = algorithm.split("/");
        algorithmName = param[0];
        if (param.length == 3) {
            encryptMode = param[1];
        } else {
            encryptMode = "ECB";
        }
    }

    /**
     * @version 2018.10.25
     * @throws UnsupportAlgorithmException
     */
    private void ivParameterGenerate() throws NoSuchAlgorithmException {
        byte[] ivRand = null;
        if (algorithmName.equals("DES") ||
            algorithmName.equals("DESede")) {
            ivRand = new byte[8];
        } else if (algorithmName.equals("AES")) {
            ivRand = new byte[16];
        } else {
            throw new NoSuchAlgorithmException(algorithmName);
        }

        SecureRandom rand = new SecureRandom();
        rand.nextBytes(ivRand);
        ivSpec = new IvParameterSpec(ivRand);
    }

    /**
     * @version 2018.10.25
     * @param src
     * @return The byte form of cipher text
     */
    public byte[] encrypt(String src) {
        try {
            if (encryptMode.equals("CBC")) {
                cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }
            
            return cipher.doFinal(src.getBytes());
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * @version 2018.10.25
     * @param cipherText
     * @return The clear text
     */
    public String decrypt(byte[] cipherText) {
        try {
            if (encryptMode.equals("CBC")) {
                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key);
            }
            
            byte[] clearBytes = cipher.doFinal(cipherText);
            return new String(clearBytes, "utf-8");

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @version 2018.10.20
     * @param hexCipherText
     * @return The clear text
     * @throws IllegalHexCharacterException
     */
    public String decrypt(String hexCipherText) throws IllegalHexCharacterException {
        return decrypt(CoderUtil.decodeHex(hexCipherText));
    }

    /**
     * @version 2018.10.21
     * @return The byte array form of secret key
     */
    public byte[] getKey() {
        return key.getEncoded();
    }

    /**
     * @version 2018.10.21
     * @return The string form of secret key
     */
    public String getKeyString() {
        return new String(key.getEncoded());
    }
}
