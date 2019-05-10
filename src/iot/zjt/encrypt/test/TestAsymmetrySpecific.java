package iot.zjt.encrypt.test;

import iot.zjt.encrypt.machine.AsymEcrptMachine;

public class TestAsymmetrySpecific {

	public static void main(String[] args) {

		String msg = new String("hello world");

		AsymEcrptMachine another = new AsymEcrptMachine("RSA/ECB/PKCS1Padding", 1024);

		AsymEcrptMachine aee = new AsymEcrptMachine("RSA");
		byte[] cipherText = aee.publicKeyEncrypt(msg, another.getPublicKey());
		String plainText = aee.privateKeyDecrypt(cipherText, another.getPrivateKey());
		System.out.println(plainText);

		cipherText = aee.privateKeyEncrypt(msg, another.getPrivateKey());
		plainText = aee.publicKeyDecrypt(cipherText, another.getPublicKey());
		System.out.println(plainText);

	}

}
