package cn.zjt.encrypt.test;

import cn.zjt.encrypt.machine.SymEcrptMachine;

public class TestSymmetryRandom {

	public static void main(String[] args) {

		String message = "hello world";

		SymEcrptMachine sem = new SymEcrptMachine("DES/ECB/PKCS5Padding ");
		byte[] cipherText = sem.Encrypt(message);
		String plainText = sem.Decrypt(cipherText);
		
		System.out.println(plainText);

	}

}
