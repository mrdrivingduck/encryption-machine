package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.SymEcrptMachine;

public class TestSymmetrySpecific {

	public static void main(String[] args) {
		
		String message = "hello world";

		SymEcrptMachine sem = new SymEcrptMachine(
			"AES",
			"fdsafdddddsesesd",
			"1234567812345678"
		);
		byte[] cipherText = sem.encrypt(message);
		String plainText = sem.decrypt(cipherText);
		
		System.out.println(plainText);

		sem = new SymEcrptMachine("DES", "12345678", "23123212");
		cipherText = sem.encrypt(message);
		plainText = sem.decrypt(cipherText);
		System.out.println(plainText);

		sem = new SymEcrptMachine("DES/CBC/PKCS5Padding", "12345678");
		cipherText = sem.encrypt(message);
		plainText = sem.decrypt(cipherText);
		System.out.println(plainText);

		sem = new SymEcrptMachine("AES/CBC/PKCS5Padding", "1234567812345678");
		cipherText = sem.encrypt(message);
		plainText = sem.decrypt(cipherText);
		System.out.println(plainText);

	}

}
