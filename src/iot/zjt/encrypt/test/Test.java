package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.KeyGeneratorMachine;
import iot.zjt.encrypt.machine.Sym;
import iot.zjt.encrypt.machine.Sym.SymAlgs;
import iot.zjt.encrypt.machine.Sym.SymMode;
import iot.zjt.encrypt.machine.Sym.SymPadding;

public class Test {

    public static void main(String[] args) {

        try {
            String key = "zhangjingtang666";
            String plain = "hello";

            byte[] cipherText = Sym.encrypt(plain.getBytes(), key.getBytes(), 
                SymAlgs.AES, SymMode.CBC, SymPadding.PKCS5Padding);
            String cipherStr = new String(cipherText);
            System.out.println(cipherStr);

            // byte[] plainText = Sym.decrypt(cipherText, key.getBytes(), SymAlgs.AES);
            // String plainStr = new String(plainText);
            // System.out.println(plainStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // try {
        //     String key = "zhangjingtang666";
        //     String plain = "hello";

        //     byte[] cipherText = Sym.encrypt(plain.getBytes(), key.getBytes(), SymAlgs.AES);
        //     String cipherStr = new String(cipherText);
        //     System.out.println(cipherStr);

        //     byte[] plainText = Sym.decrypt(cipherText, key.getBytes(), SymAlgs.AES);
        //     String plainStr = new String(plainText);
        //     System.out.println(plainStr);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // try {
        //     String plain = "hello";

        //     byte[] key = KeyGeneratorMachine.generateKey(SymAlgs.AES, 256);

        //     byte[] cipherText = Sym.encrypt(plain.getBytes(), key, SymAlgs.AES);
        //     String cipherStr = new String(cipherText);
        //     System.out.println(cipherStr);
            
        //     byte[] plainText = Sym.decrypt(cipherText, key, SymAlgs.AES);
        //     String plainStr = new String(plainText);
        //     System.out.println(plainStr);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}