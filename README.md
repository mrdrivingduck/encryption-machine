# Encryption Machine

A Java framework for simple encryption/decryption.

Author : Mr Dk.

Date : 2018.10.25

---

### Symmetric Encryption

* Construct an `SymEcrptMachine` object

  * with specific __algorithm__ & randomly-generated _secret key_

  ```java
  SymEcrptMachine sem = new SymEcrptMachine("AES/ECB/PKCS5Padding");
  ```

  * with specific __algorithm__ & randomly-generated secret key of __specific length__

  ```java
  SymEcrptMachine sem = new SymEcrptMachine("AES/ECB/PKCS5Padding", 192);
  ```

  * with specific __algorithm__ & __secret key__

  ```java
  SymEcrptMachine sem = new SymEcrptMachine("DES/CBC/PKCS5Padding", "12345678");
  ```

  * with specific __algorithm__ & __secret key__ & __IV Parameter__

  ```java
  SymEcrptMachine sem = new SymEcrptMachine(
      "AES/CBC/PKCS5Padding",
      "fdsafdddddsesesd",
      "1234567812345678"
  );
  ```

* Encrypt the __plain text__

```java
byte[] cipherText = sem1.encrypt("I love u");
```

* Decrypt the __cipher text__

```java
String plainText = sem1.decrypt(cipherText);
```

* Get _secret-key_

```java
// Get byte array form of key
byte[] keyBytes = sem1.getKey();
// Get string form of key
String keyString = sem1.getKeyString();
```

* Supported working modes

| Algorithm | Encrypting Mode | Filling Mode | Key Length     |
| --------- | --------------- | ------------ | -------------- |
| AES       | ECB             | PKCS5Padding | 128            |
| AES       | ECB             | NoPadding    | 128            |
| AES       | CBC             | PKCS5Padding | 128            |
| AES       | CBC             | NoPadding    | 128            |
| DES       | ECB             | PKCS5Padding | 64（56 valid） |
| DES       | ECB             | NoPadding    | 64（56 valid） |
| DES       | CBC             | PKCS5Padding | 64（56 valid） |
| DES       | CBC             | NoPadding    | 64（56 valid） |
| DESede    | ECB             | PKCS5Padding | 168            |
| DESede    | ECB             | NoPadding    | 168            |
| DESede    | CBC             | PKCS5Padding | 168            |
| DESede    | CBC             | NoPadding    | 168            |

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

