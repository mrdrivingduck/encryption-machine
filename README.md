# Encryption Machine

🔒 A Java wrapper for simple encryption/decryption.

Created by : Mr Dk.

2018.10.25 @Nanjing, Jiangsu, China

Refactored by : Mr Dk.

2019.05.12 @Nanjing, Jiangsu, China

---

## Dependency

Java 8 with JDK & JRE

---

## Symmetric Encryption

### Encrypt/Decrypt with __Key__ & __Algorithm__ in default mode & padding

```java
public static byte[] encrypt(byte[] plainText, byte[] key, SymAlgs algs);
public static byte[] decrypt(byte[] cipherText, byte[] key, SymAlgs algs);
```

### Encrypt/Decrypt with __Key__ & __Algorithm__ in specific __mode__ & __padding__

```java
public static byte[] encrypt(byte[] plainText, byte[] key, SymAlgs algs, SymMode mode, SymPadding padd);
public static byte[] decrypt(byte[] cipherText, byte[] key, SymAlgs algs, SymMode mode, SymPadding padd);
```

### Encrypt/Decrypt with __Key__ & __Algorithm__ with __IV__ in specific __mode__ & __padding__

```java
public static byte[] encrypt(byte[] plainText, byte[] key, byte[] iv, SymAlgs algs, SymMode mode, SymPadding padd);
public static byte[] decrypt(byte[] cipherText, byte[] key, byte[] iv, SymAlgs algs, SymMode mode, SymPadding padd);
```

### Algorithms

* `SysAlgs.AES`
* `SysAlgs.DES`
* `SysAlgs.DESede`

### Modes

* `SysMode.ECB`
* `SysMode.CBC`

### Paddings

* `SysPadding.PKCS5Padding`
* `SysPadding.NoPadding`

### Support

| Algorithm        | Encrypting Mode | Filling Mode | IV Length |
| ---------------- | --------------- | ------------ | --------- |
| AES (default)    | ECB             | PKCS5Padding | /         |
| AES              | ECB             | NoPadding    | /         |
| AES              | CBC             | PKCS5Padding | 16 Bytes  |
| AES              | CBC             | NoPadding    | 16 Bytes  |
| DES (default)    | ECB             | PKCS5Padding | /         |
| DES              | ECB             | NoPadding    | /         |
| DES              | CBC             | PKCS5Padding | 8 Bytes   |
| DES              | CBC             | NoPadding    | 8 Bytes   |
| DESede (default) | ECB             | PKCS5Padding | /         |
| DESede           | ECB             | NoPadding    | /         |
| DESede           | CBC             | PKCS5Padding | 8 Bytes   |
| DESede           | CBC             | NoPadding    | 8 Bytes   |

### Key Length

| Algorithm | Key Length (bit)                      |
| --------- | ------------------------------------- |
| AES       | 128 (default) / 192 / 256             |
| DES       | 64 (default, 56 in effect)            |
| DESede    | 112(?) / 192 (default, 168 in effect) |

---

## Asymmetric Encryption

### Encrypt/Decrypt with __Key__ & __Algorithm__ in default __mode__ & __padding__

```java
public static byte[] publicKeyEncrypt(byte[] plainText, byte[] publicKey, AsymAlgs algs);
public static byte[] privateKeyEncrypt(byte[] plainText, byte[] privateKey, AsymAlgs algs)
public static byte[] publicKeyDecrypt(byte[] cipherText, byte[] publicKey, AsymAlgs algs)
public static byte[] privateKeyDecrypt(byte[] cipherText, byte[] privateKey, AsymAlgs algs);
```

### Encrypt/Decrypt with __Key__ & __Algorithm__ in specific __mode__ & __padding__

```java
public static byte[] publicKeyEncrypt(byte[] plainText, byte[] publicKey, AsymAlgs algs, AsymMode mode, AsymPadding padd);
public static byte[] privateKeyEncrypt(byte[] plainText, byte[] privateKey, AsymAlgs algs, AsymMode mode, AsymPadding padd);
public static byte[] publicKeyDecrypt(byte[] cipherText, byte[] publicKey, AsymAlgs algs, AsymMode mode, AsymPadding padd);
public static byte[] privateKeyDecrypt(byte[] cipherText, byte[] privateKey, AsymAlgs algs, AsymMode mode, AsymPadding padd);
```

### Algorithms

* `AsymAlgs.RSA`

### Modes

* `AsymMode.ECB`

### Paddings

* `AsymPadding.PKCS1Padding`
* `AsymPadding.OAEPWithSHA_1AndMGF1Padding`
* `AsymPadding.OAEPWithSHA_256AndMGF1Padding`

### Support

| Algorithm     | Encrypting Mode | Filling Mode                  |
| ------------- | --------------- | ----------------------------- |
| RSA (default) | ECB             | PKCS1Padding                  |
| RSA           | ECB             | OAEPWithSHA-1AndMGF1Padding   |
| RSA           | ECB             | OAEPWithSHA-256AndMGF1Padding |

### Key Length

| Algorithm | Key Length (bit)         |
| --------- | ------------------------ |
| RSA       | ≥ 512 (Suggested ≥ 1024) |

---

## Key Generator

### Generating Symmetric Key

```java
public static byte[] generateKey(SymAlgs algs);
public static byte[] generateKey(SymAlgs algs, int keySize)
```

### Generating Asymmetric Key Pair

```java
public static byte[][] generateKeyPair(AsymAlgs algs);
public static byte[][] generateKeyPair(AsymAlgs algs, int keySize);
```

* `byte[0]` for __private__ key
* `byte[1]` for __public__ key

---

## Encoding Utilities

Providing several encoding & decoding algorithms to make byte arrays printable & readable.

They are defined as _static_ methods in class `CoderUtil`.

### Byte Array &harr; Hexadecimal String

* To encode a byte array into a hexadecimal string

  ```java
  public static final String encodeHex(final byte[] src);
  ```

* To decode a hexadecimal string into a byte array

  ```java
  public static final byte[] decodeHex(final String hexStr) throws IllegalHexCharacterException;
  ```

### Byte Array &harr; Base64 String

* To encode a byte array into a base64 string

  ```java
  public static final String encodeBase64(final byte[] src);
  ```

* To decode a base64 string into a byte array

  ```java
  public static final byte[] decodeBase64(final String base64Str);
  ```

---

## License

Copyright © 2018-2020, Jingtang Zhang. ([MIT License](LICENSE))

---

