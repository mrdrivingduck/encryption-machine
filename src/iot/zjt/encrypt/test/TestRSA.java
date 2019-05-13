/**
 * @author Mr Dk.
 * @version 2019-05-12
 * 
 *  RSA support key of >= 512 bit (2048 default)
 */

package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.AsymEncrpMachine;
import iot.zjt.encrypt.machine.AsymEncrpMachine.AsymAlgs;
import iot.zjt.encrypt.machine.AsymEncrpMachine.AsymMode;
import iot.zjt.encrypt.machine.AsymEncrpMachine.AsymPadding;
import iot.zjt.encrypt.util.CoderUtil;
import iot.zjt.encrypt.machine.KeyGeneratorMachine;

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
            byte[] cipherText = AsymEncrpMachine.publicKeyEncrypt(plain.getBytes(), keyPair[1], AsymAlgs.RSA);
            // String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(CoderUtil.toHex(cipherText));

            byte[] plainText = AsymEncrpMachine.privateKeyDecrypt(cipherText, keyPair[0], AsymAlgs.RSA);
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
            byte[] cipherText = AsymEncrpMachine.publicKeyEncrypt(plain.getBytes(), keyPair[1], AsymAlgs.RSA, AsymMode.ECB, AsymPadding.OAEPWithSHA_256AndMGF1Padding);
            // String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(CoderUtil.toHex(cipherText));

            byte[] plainText = AsymEncrpMachine.privateKeyDecrypt(cipherText, keyPair[0], AsymAlgs.RSA, AsymMode.ECB, AsymPadding.OAEPWithSHA_256AndMGF1Padding);
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
            byte[] cipherText = AsymEncrpMachine.privateKeyEncrypt(plain.getBytes(), keyPair[0], AsymAlgs.RSA);
            // String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(CoderUtil.toHex(cipherText));

            byte[] plainText = AsymEncrpMachine.publicKeyDecrypt(cipherText, keyPair[1], AsymAlgs.RSA);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}