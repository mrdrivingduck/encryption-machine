# Encryption Machine

A Java framework for simple encryption/decryption.

Author : Mr Dk.

Date : 2018.10.25

Environment : `JDK` & `JRE` - Versions above 1.8

---

### Symmetric Encryption

* Construct a `SymEcrptMachine` object

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

  * with specific __algorithm__ & __secret key__ & __IV Parameter__ (Needed by _CBC_ mode)

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

| Algorithm | Encrypting Mode | Filling Mode | Parameter                                |
| --------- | --------------- | ------------ | ---------------------------------------- |
| AES       | ECB             | PKCS5Padding | `"AES"` / `"AES/ECE/PKCS5Padding"`       |
| AES       | ECB             | NoPadding    | `"AEC/ECB/NoPadding"`                    |
| AES       | CBC             | PKCS5Padding | `"AES/CBC/PKCS5Padding"`                 |
| AES       | CBC             | NoPadding    | `"AEC/CBC/NoPadding"`                    |
| DES       | ECB             | PKCS5Padding | `"DES"` / `"DES/ECB/PKCS5Padding"`       |
| DES       | ECB             | NoPadding    | `"DES/ECB/NoPadding"`                    |
| DES       | CBC             | PKCS5Padding | `"DES/CBC/PKCS5Padding"`                 |
| DES       | CBC             | NoPadding    | `"DES/CBC/NoPadding"`                    |
| DESede    | ECB             | PKCS5Padding | `"DESede"` / `"DESede/ECB/PKCS5Padding"` |
| DESede    | ECB             | NoPadding    | `"DESede/ECB/NoPadding"`                 |
| DESede    | CBC             | PKCS5Padding | `"DESede/CBC/PKCS5Padding"`              |
| DESede    | CBC             | NoPadding    | `"DESede/CBC/NoPadding"`                 |

* Supported key length

| Algorithm | Key Length (bit)          |
| --------- | ------------------------- |
| AES       | 128 (default) / 192 / 256 |
| DES       | 56 (default)              |
| DESede    | 112 / 168 (default)       |

---

### Asymmetric Encryption

* Construct an `AsymEcrptMachine` object

  * with specific __algorithm__ & randomly-generated private/public key-pair

  ```java
  AsymEcrptMachine aem = new AsymEcrptMachine("RSA/ECB/PKCS1Padding");
  ```

  * with specific algorithm & randomly-generated private/public key-pair of __specific length__

  ```java
  AsymEcrptMachine aem = new AsymEcrptMachine("RSA/ECB/PKCS1Padding", 1024);
  ```

* Encrypt with _internal_ __public key__ & decrypt with _internal_ __private key__

```java
byte[] cipherText = aem.publicKeyEncrypt("I love u");
String plainText = aem.privateKeyDecrypt(cipherText);
```

* Encrypt with _internal_ __private key__ & decrypt with _internal_ __public key__

```java
byte[] cipherText = aem.privateKeyEncrypt("I love u");
String plainText = aem.publicKeyDecrypt(cipherText);
```

* Encrypt with _external_ __public key__ & decrypt with _external_ __private key__

```java
AsymEcrptMachine another = new AsymEcrptMachine("RSA", 1024);
byte[] cipherText = aem.publicKeyEncrypt("I love u", another.getPublicKey());
String plainText = aem.privateKeyDecrypt(cipherText, another.getPrivateKey());
```

* Encrypt with _external_ __private key__ & decrypt with _external_ __public key__

```java
AsymEcrptMachine another = new AsymEcrptMachine("RSA/ECB/PKCS1Padding", 1024);
byte[] cipherText = aem.privateKeyEncrypt("I love u", another.getPrivateKey());
String plainText = aem.publicKeyDecrypt(cipherText, another.getPublicKey());
```

* Get __public key__ or __private key__

```java
byte[] publicKey = aem.getPublicKey();
byte[] privateKey = aem.getPrivateKey();
```

* Supported working modes

| Algorithm | Encrypting Mode | Filling Mode | Parameter                      |
| --------- | --------------- | ------------ | ------------------------------ |
| RSA       | ECB             | PKCS1Padding | `RSA` / `RSA/ECB/PKCS1Padding` |

* Supported key length

| Algorithm | Key Length (bit)         |
| --------- | ------------------------ |
| RSA       | ≥ 512 (Suggested ≥ 1024) |

---

### Encoding Utilities

I also provide several encoding & decoding algorithms to make byte arrays printable & readable.

They are defined as _static_ methods in class `CoderUtil`.

#### 1. Byte Array <-> Hexadecimal String

* To encode a byte array into a hexadecimal string

```java
public static final String encodeHex(final byte[] src);
```

* To decode a hexadecimal string into a byte array

```java
public static final byte[] decodeHex(final String hexStr) throws IllegalHexCharacterException;
```

#### 2. Byte Array <-> Base64 String

* To encode a byte array into a base64 string

```java
public static final String encodeBase64(final byte[] src);
```

* To decode a base64 string into a byte array

```java
public static final byte[] decodeBase64(final String base64Str);
```

---

