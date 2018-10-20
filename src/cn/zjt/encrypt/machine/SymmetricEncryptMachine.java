package cn.zjt.encrypt.machine;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.zjt.encrypt.exception.IllegalHexCharacterException;
import cn.zjt.encrypt.util.CoderUtil;

public class SymmetricEncryptMachine {

    private KeyGenerator kGenerator;
    private SecretKey key;
    private Cipher cipher;

    /**
     * @version 2018.10.20
     * @param algorithm
     */
    public SymmetricEncryptMachine(String algorithm) {
        try {
            kGenerator = KeyGenerator.getInstance(algorithm);
            key = kGenerator.generateKey();
            cipher = Cipher.getInstance(algorithm);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @version 2018.10.20
     * @param algorithm
     * @param keySize
     */
    public SymmetricEncryptMachine(String algorithm, int keySize) {
        try {
            kGenerator = KeyGenerator.getInstance(algorithm);
            kGenerator.init(keySize);
            key = kGenerator.generateKey();
            cipher = Cipher.getInstance(algorithm);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @version 2018.10.20
     * @param keyBytes
     * @param offset
     * @param len
     * @param algorithm
     */
    public SymmetricEncryptMachine(byte[] keyBytes, int offset, int len, String algorithm) {
        key = new SecretKeySpec(keyBytes, offset, len, algorithm);
        try {
            cipher = Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @version 2018.10.20
     * @param keyBytes
     * @param algorithm
     */
    public SymmetricEncryptMachine(byte[] keyBytes, String algorithm) {
        key = new SecretKeySpec(keyBytes, algorithm);
        try {
            cipher = Cipher.getInstance(algorithm);
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
    public SymmetricEncryptMachine(String keyStr, String algorithm) {
        try {
            key = new SecretKeySpec(keyStr.getBytes("utf-8"), algorithm);
            cipher = Cipher.getInstance(algorithm);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @version 2018.10.20
     * @param src
     * @return The byte form of cipher text
     */
    public byte[] Encrypt(String src) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(src.getBytes());
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * @version 2018.10.20
     * @param cipherText
     * @return The clear text
     */
    public String Decrypt(byte[] cipherText) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] clearBytes = cipher.doFinal(cipherText);
            return new String(clearBytes, "utf-8");
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
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
    public String Decrypt(String hexCipherText) throws IllegalHexCharacterException {
        return Decrypt(CoderUtil.decodeHex(hexCipherText));
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
        // return CoderUtil.encodeHex(key.getEncoded());
        return new String(key.getEncoded());
    }
}
