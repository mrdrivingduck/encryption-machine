package cn.zjt.encrypt.test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestAsymmetryRandom {

	public static void main(String[] args)
		throws NoSuchAlgorithmException, 
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		String msg = new String("hello world");
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] cipherText = cipher.doFinal(msg.getBytes());

		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		String plainText = new String(cipher.doFinal(cipherText));

		System.out.println(plainText);
	}

}
