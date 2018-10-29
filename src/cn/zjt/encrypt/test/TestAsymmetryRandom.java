package cn.zjt.encrypt.test;

import cn.zjt.encrypt.machine.AsymEcrptMachine;

public class TestAsymmetryRandom {

	public static void main(String[] args) {

		String msg = new String("hello world");

		AsymEcrptMachine aem = new AsymEcrptMachine("RSA/ECB/PKCS1Padding", 1024);
		byte[] cipherText = aem.publicKeyEncrypt(msg);
		String plainText = aem.privateKeyDecrypt(cipherText);
		System.out.println(plainText);

		cipherText = aem.privateKeyEncrypt(msg);
		plainText = aem.publicKeyDecrypt(cipherText);
		System.out.println(plainText);
		
	}

}
