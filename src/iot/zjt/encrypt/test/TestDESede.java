package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.KeyGeneratorMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymMode;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymPadding;

/**
 * @author Mr Dk.
 * @version 2019-05-11
 * 
 *  DESede support key of 112 / 168(default):
 *      if specify a string as key
 *      the length of a string should be: (24?)
 * 
 *  IV must be 8 bytes long
 */

public class TestDESede {

    public static void main(String[] args) {
        
        /**
         * Testing with key and IV specifier
         */
        try {
            String key = "123456781234567812345678";
            String iv = "12345678";
            String plain = "hello";

            byte[] cipherText = SymEncrpMachine.encrypt(plain.getBytes(), key.getBytes(), iv.getBytes(),
                SymAlgs.DESede, SymMode.CBC, SymPadding.PKCS5Padding);
            String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(cipherStr);

            byte[] plainText = SymEncrpMachine.decrypt(cipherText, key.getBytes(), iv.getBytes(),
                SymAlgs.DESede, SymMode.CBC, SymPadding.PKCS5Padding);
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
            String key = "123456781234567812345678";
            String plain = "hello";

            byte[] cipherText = SymEncrpMachine.encrypt(plain.getBytes(), key.getBytes(),
                SymAlgs.DESede, SymMode.ECB, SymPadding.PKCS5Padding);
            String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(cipherStr);

            byte[] plainText = SymEncrpMachine.decrypt(cipherText, key.getBytes(),
                SymAlgs.DESede, SymMode.ECB, SymPadding.PKCS5Padding);
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
            byte[] key = KeyGeneratorMachine.generateKey(SymAlgs.DESede, 112);

            byte[] cipherText = SymEncrpMachine.encrypt(plain.getBytes(), key, SymAlgs.DESede);
            String cipherStr = new String(cipherText);
            System.out.println("---- CIPHER TEXT ----");
            System.out.println(cipherStr);
            
            byte[] plainText = SymEncrpMachine.decrypt(cipherText, key, SymAlgs.DESede);
            String plainStr = new String(plainText);
            System.out.println("---- PLAIN TEXT ----");
            System.out.println(plainStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}