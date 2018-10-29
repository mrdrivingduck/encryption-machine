package cn.zjt.encrypt.machine;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
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
 * @version 2018.10.29
 * @author Mr Dk.
 * 
 * @support
 *  RSA/ECB/PKCS1Padding (≥512)
 */

public class AsymEcrptMachine {

    private KeyPair keyPair;
    private Cipher cipher;

    private String algorithmName;

    public AsymEcrptMachine(String algorithm) {

        paramInitialize(algorithm);
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance(algorithmName);
            this.keyPair = gen.generateKeyPair();
            this.cipher = Cipher.getInstance(algorithm);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public AsymEcrptMachine(String algorithm, int keySize) {

        paramInitialize(algorithm);
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance(algorithmName);
            gen.initialize(keySize);
            this.keyPair = gen.generateKeyPair();
            this.cipher = Cipher.getInstance(algorithm);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public byte[] publicKeyEncrypt(byte[] plainText) {
        try {
            this.cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            return this.cipher.doFinal(plainText);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public byte[] publicKeyEncrypt(String plainText) {
        return publicKeyEncrypt(plainText.getBytes());
    }

    public byte[] publicKeyEncrypt(byte[] plainText, byte[] publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            PublicKey key = keyFactory.generatePublic(keySpec);
            this.cipher.init(Cipher.ENCRYPT_MODE, key);
            return this.cipher.doFinal(plainText);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] publicKeyEncrypt(String plainText, byte[] publicKey) {
        return publicKeyEncrypt(plainText.getBytes(), publicKey);
    }

    public byte[] privateKeyEncrypt(byte[] plainText) {
        try {
            this.cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
            return this.cipher.doFinal(plainText);

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] privateKeyEncrypt(String plainText) {
        return privateKeyEncrypt(plainText.getBytes());
    }

    public byte[] privateKeyEncrypt(byte[] plainText, byte[] privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
            PrivateKey key = keyFactory.generatePrivate(keySpec);
            this.cipher.init(Cipher.ENCRYPT_MODE, key);
            return this.cipher.doFinal(plainText);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] privateKeyEncrypt(String plainText, byte[] privateKey) {
        return privateKeyEncrypt(plainText.getBytes(), privateKey);
    }

    public String publicKeyDecrypt(byte[] cipherText, byte[] publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            PublicKey key = keyFactory.generatePublic(keySpec);
            this.cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(this.cipher.doFinal(cipherText));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String publicKeyDecrypt(byte[] cipherText) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
            return new String(this.cipher.doFinal(cipherText));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String privateKeyDecrypt(byte[] cipherText, byte[] privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
            PrivateKey key = keyFactory.generatePrivate(keySpec);
            this.cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(this.cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String privateKeyDecrypt(byte[] cipherText) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            return new String(this.cipher.doFinal(cipherText));

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] getPublicKey() {
        return keyPair.getPublic().getEncoded();
    }

    public byte[] getPrivateKey() {
        return keyPair.getPrivate().getEncoded();
    }

    private void paramInitialize(String algorithm) {
        String[] param = algorithm.split("/");
        this.algorithmName = param[0];
    }
}
