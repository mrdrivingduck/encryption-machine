

# Encryption Machine

A Java framework for simple encryption/decryption.

Author : Mr Dk.

Date : 2018.10.21

---

### Symmetric Encryption

* Initialize an `SymmetricEncryptionMachine`
  * with specific _algorithm type_ and _secret key_
  * with only _algorithm_ and generate a random _secret key_

```java
// algorithm-type & secret-key
SymmetricEncryptMachine sem1 = new SymmetricEncryptMachine("1a2b3c4d5a6b7c8d", "AES");
// algorithm-type & auto-generated key
SymmetricEncryptMachine sem2 = new SymmetricEncryptMachine("AES");
```

* Encrypt the _plain text_

```java
byte[] cipherText = sem1.Encrypt("I love u");
```

* Decrypt the _cipher text_

```java
String plainText = sem1.Decrypt(cipherText);
```

* Get _secret-key_

```java
// Get byte array form of key
byte[] keyBytes = sem1.getKey();
// Get string form of key
String keyString = sem1.getKeyString();
```

---

### Asymmetric Encryption

* Developing

---

### Encoding Utilities

I also provide several encoding & decoding algorithms to make byte arrays printable & readable.

They are defined as _static_ methods in class `CoderUtil`.

#### 1. Byte Array <-> Hexadecimal String

* To encode a byte array into a hexadecimal string

```java
public static String encodeHex(final byte[] src);
```

* To decode a hexadecimal string into a byte array

```java
public static byte[] decodeHex(final String hexStr) throws IllegalHexCharacterException;
```

---

