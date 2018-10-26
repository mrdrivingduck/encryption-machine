package cn.zjt.encrypt.test;

import cn.zjt.encrypt.machine.SymEcrptMachine;

public class TestSymmetryRandom {

	public static void main(String[] args) {

		String message = "hello world";

		SymEcrptMachine sem = new SymEcrptMachine("AES/ECB/PKCS5Padding");
		byte[] cipherText = sem.encrypt(message);
		String plainText = sem.decrypt(cipherText);
		
		System.out.println(plainText);

	}

}
