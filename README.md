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

