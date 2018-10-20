package cn.zjt.encrypt.test;

import cn.zjt.encrypt.machine.SymmetricEncryptMachine;
import cn.zjt.encrypt.util.CoderUtil;

public class Test {

	public static void main(String[] args) {
		
		SymmetricEncryptMachine sm1 = new SymmetricEncryptMachine("1a2b3c4d5a6b7c8d", "AES");
		byte[] cipherText = sm1.Encrypt("I love u");
		String clearText = sm1.Decrypt(cipherText);

		System.out.println("key: " + sm1.getKeyString());
		System.out.println("cipher text: " + CoderUtil.encodeHex(cipherText));
		System.out.println("clear text: " + clearText);

	}

}
