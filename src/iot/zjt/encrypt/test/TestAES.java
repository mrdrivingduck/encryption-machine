/**
 * @author Mr Dk.
 * @version 2019-05-12
 * 
 *  AES support key of 128(default)/192/256:
 *      if specify a string as key
 *      the length of a string should be: 16/24/32
 * 
 *  IV must be 16 bytes long
 */

package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.KeyGeneratorMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymMode;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymPadding;
import iot.zjt.encrypt.util.CoderUtil;

public class TestAES {

    public static void main(String[] args) {
        
        /**
         * Testing with key and IV specifier
         */
        try {
            String key = "zhangjingtang666";
            String iv = "1234567812345678";
            String plain = "hello";

            byte[] cipherText = SymEncrpMachine.encrypt(plain.getBytes(), key.getBytes(), iv.getBytes(),
                SymAlgs.AES, SymMode.CBC, SymPadding.PKCS5Padding);
            // String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(CoderUtil.toHex(cipherText));

            byte[] plainText = SymEncrpMachine.decrypt(cipherText, key.getBytes(), iv.getBytes(),
                SymAlgs.AES, SymMode.CBC, SymPadding.PKCS5Padding);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Testing with key specifier only
         */
        try {
            String key = "zhangjingtang666";
            String plain = "hello";

            byte[] cipherText = SymEncrpMachine.encrypt(plain.getBytes(), key.getBytes(),
                SymAlgs.AES, SymMode.ECB, SymPadding.PKCS5Padding);
            // String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(CoderUtil.toHex(cipherText));

            byte[] plainText = SymEncrpMachine.decrypt(cipherText, key.getBytes(),
                SymAlgs.AES, SymMode.ECB, SymPadding.PKCS5Padding);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Testing with generated key
         */
        try {
            String plain = "hello";
            byte[] key = KeyGeneratorMachine.generateKey(SymAlgs.AES, 256);

            byte[] cipherText = SymEncrpMachine.encrypt(plain.getBytes(), key, SymAlgs.AES);
            // String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(CoderUtil.toHex(cipherText));
            
            byte[] plainText = SymEncrpMachine.decrypt(cipherText, key, SymAlgs.AES);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}