package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.Asym;
import iot.zjt.encrypt.machine.KeyGeneratorMachine;
import iot.zjt.encrypt.machine.Asym.AsymAlgs;
import iot.zjt.encrypt.machine.Asym.AsymMode;
import iot.zjt.encrypt.machine.Asym.AsymPadding;

/**
 * @author Mr Dk.
 * @version 2019-05-11
 * 
 *  RSA support key of >= 512 bit (2048 default)
 */

public class TestRSA {

    public static void main(String[] args) {

        /**
         * RSA with default mode and padding
         * Encrypt with public key
         * Decrypt with private key
         */
        try {
            String plain = "hello";
            byte[][] keyPair = KeyGeneratorMachine.generateKeyPair(AsymAlgs.RSA);
            byte[] cipherText = Asym.publicKeyEncrypt(plain.getBytes(), keyPair[1], AsymAlgs.RSA);
            String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(cipherStr);

            byte[] plainText = Asym.privateKeyDecrypt(cipherText, keyPair[0], AsymAlgs.RSA);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * RSA with specific mode and padding
         * Encrypt with public key
         * Decrypt with private key
         */
        try {
            String plain = "hello";
            byte[][] keyPair = KeyGeneratorMachine.generateKeyPair(AsymAlgs.RSA);
            byte[] cipherText = Asym.publicKeyEncrypt(plain.getBytes(), keyPair[1], AsymAlgs.RSA, AsymMode.ECB, AsymPadding.OAEPWithSHA_256AndMGF1Padding);
            String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(cipherStr);

            byte[] plainText = Asym.privateKeyDecrypt(cipherText, keyPair[0], AsymAlgs.RSA, AsymMode.ECB, AsymPadding.OAEPWithSHA_256AndMGF1Padding);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * RSA with default mode and padding
         * Encrypt with private key
         * Decrypt with public key
         */
        try {
            String plain = "hello";
            byte[][] keyPair = KeyGeneratorMachine.generateKeyPair(AsymAlgs.RSA);
            byte[] cipherText = Asym.privateKeyEncrypt(plain.getBytes(), keyPair[0], AsymAlgs.RSA);
            String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(cipherStr);

            byte[] plainText = Asym.publicKeyDecrypt(cipherText, keyPair[1], AsymAlgs.RSA);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}