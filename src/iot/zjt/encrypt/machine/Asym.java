package iot.zjt.encrypt.machine;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author mrdrivingduck
 * @version 2019-05-11
 * 
 * @decription Encryption machine for asymmetrical encryption
 * 
 * @support RSA/ECB/PKCS1Padding (1024, 2048)
 *          RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
 *          RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
 */

public class Asym {

    public static enum AsymAlgs {
        RSA
    }

    public static enum AsymMode {
        ECB
    }

    public static enum AsymPadding {
        PKCS1Padding,
        OAEPWithSHA_1AndMGF1Padding,
        OAEPWithSHA_256AndMGF1Padding
    }

    public static byte[] publicKeyEncrypt(byte[] plainText, byte[] publicKey, AsymAlgs algs)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algs.name());
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey key = keyFactory.generatePublic(keySpec);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText);
        return cipherText;
    }

    public static byte[] privateKeyDecrypt(byte[] cipherText, byte[] privateKey, AsymAlgs algs)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algs.name());
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
    }

    public static byte[] publicKeyEncrypt(byte[] plainText, byte[] publicKey, AsymAlgs algs, AsymMode mode, AsymPadding padd)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        String algsPadd = (algs.name() + "/" + mode.name() + "/" + padd.name()).replace('_', '-');
        Cipher cipher = Cipher.getInstance(algsPadd);
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey key = keyFactory.generatePublic(keySpec);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText);
        return cipherText;
    }

    public static byte[] privateKeyDecrypt(byte[] cipherText, byte[] privateKey, AsymAlgs algs, AsymMode mode, AsymPadding padd)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        String algsPadd = (algs.name() + "/" + mode.name() + "/" + padd.name()).replace('_', '-');
        Cipher cipher = Cipher.getInstance(algsPadd);
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
    }

    public static byte[] privateKeyEncrypt(byte[] plainText, byte[] privateKey, AsymAlgs algs)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algs.name());
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey key = keyFactory.generatePrivate(keySpec);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText);
        return cipherText;
    }

    public static byte[] publicKeyDecrypt(byte[] cipherText, byte[] publicKey, AsymAlgs algs)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algs.name());
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey key = keyFactory.generatePublic(keySpec);

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
    }

    public static byte[] privateKeyEncrypt(byte[] plainText, byte[] privateKey, AsymAlgs algs, AsymMode mode, AsymPadding padd)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        String algsPadd = (algs.name() + "/" + mode.name() + "/" + padd.name().replace('_', '-'));
        Cipher cipher = Cipher.getInstance(algsPadd);
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey key = keyFactory.generatePrivate(keySpec);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText);
        return cipherText;
    }

    public static byte[] publicKeyDecrypt(byte[] cipherText, byte[] publicKey, AsymAlgs algs, AsymMode mode, AsymPadding padd)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidKeySpecException,
        IllegalBlockSizeException, BadPaddingException {

        String algsPadd = (algs.name() + "/" + mode.name() + "/" + padd.name()).replace('_', '-');
        Cipher cipher = Cipher.getInstance(algsPadd);
        KeyFactory keyFactory = KeyFactory.getInstance(algs.name());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey key = keyFactory.generatePublic(keySpec);

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
    }
}