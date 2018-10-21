package cn.zjt.encrypt.test;

import cn.zjt.encrypt.machine.SymmetricEncryptMachine;
import cn.zjt.encrypt.util.CoderUtil;

public class Test {

	public static void main(String[] args) {
		
		String message = 
			"Dear Irene.\n" +
			"U r a special girl.\n" +
			"U r ambitious with your career.\n" +
			"U r attractive in appearence.\n" +
			"It's a pleasure to meet u.\n" +
			"Wish u all the best in Germany.\n" +
			"Happy birthday!!!";

		SymmetricEncryptMachine sm1 = new SymmetricEncryptMachine("irenezhuyue97117", "AES");
		byte[] cipherText = sm1.Encrypt(message);
		String plainText = sm1.Decrypt(cipherText);

		System.out.println("key: " + sm1.getKeyString());
		System.out.println("cipher text: " + CoderUtil.encodeHex(cipherText));
		System.out.println("plain text: " + plainText);

	}

}
