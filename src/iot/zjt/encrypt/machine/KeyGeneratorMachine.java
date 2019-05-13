/**
 * @author mrdrivingduck
 * @version 2019-05-11
 * 
 *  For generating secret key (pair)
 */

package iot.zjt.encrypt.machine;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

import iot.zjt.encrypt.machine.AsymEncrpMachine.AsymAlgs;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;

public class KeyGeneratorMachine {

    /**
     * AES - 128(default) / 192 / 256
     * DES - 56(default)
     * DESede - 112 / 168(default)
     */
    public static byte[] generateKey(SymAlgs algs)
        throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algs.name());
        byte[] key = keyGenerator.generateKey().getEncoded();
        return key;
    }

    /**
     * AES - 128(default) / 192 / 256
     * DES - 56(default)
     * DESede - 112 / 168(default)
     */
    public static byte[] generateKey(SymAlgs algs, int keySize)
        throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algs.name());
        keyGenerator.init(keySize);
        byte[] key = keyGenerator.generateKey().getEncoded();
        return key;
    }

    /**
     * RSA - 2048(default) / >= 512
     * [0] for private key
     * [1] for public key
     */
    public static byte[][] generateKeyPair(AsymAlgs algs)
        throws NoSuchAlgorithmException {

        KeyPairGenerator gen = KeyPairGenerator.getInstance(algs.name());
        KeyPair pair = gen.generateKeyPair();
        byte[][] keyBytes = { pair.getPrivate().getEncoded(), pair.getPublic().getEncoded() };
        return keyBytes;
    }

    /**
     * RSA - 2048(default) / >= 512
     * [0] for private key
     * [1] for public key
     */
    public static byte[][] generateKeyPair(AsymAlgs algs, int keySize)
        throws NoSuchAlgorithmException {

        KeyPairGenerator gen = KeyPairGenerator.getInstance(algs.name());
        gen.initialize(keySize);
        KeyPair pair = gen.generateKeyPair();
        byte[][] keyBytes = { pair.getPrivate().getEncoded(), pair.getPublic().getEncoded() };
        return keyBytes;
    }
}
