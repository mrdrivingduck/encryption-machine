package iot.zjt.encrypt.machine;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;

/**
 * @author mrdrivingduck
 * @version 2019-05-11
 * 
 *  For generating secret key (pair)
 */

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
}
