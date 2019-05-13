/**
 * @version 2018.10.29
 * @author Mr Dk.
 */

package iot.zjt.encrypt.util;

import java.util.Base64;

import iot.zjt.encrypt.exception.IllegalHexCharacterException;

public class CoderUtil {

    private static final char[] characters = {
        '0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f'
    };

    private static final byte[] bytes = {
        0, 1, 2, 3,
        4, 5, 6, 7,
        8, 9, 10, 11,
        12, 13, 14, 15
    };

    private static final String hex = "0123456789abcdef";

    /**
     * @param src
     * @return A hexadecimal string of source byte array
     */
    public static final String toHex(final byte[] src) {
        char[] encode = new char[src.length * 2];
        int index = 0;

        for (byte b : src) {
            encode[index++] = characters[(b >> 4) & 0x0F];
            encode[index++] = characters[b & 0x0F];
        }

        return new String(encode);
    }

    /**
     * @param hexStr
     * @return A byte array parsed from source hexadecimal string
     * @throws IllegalHexCharacterException
     */
    public static final byte[] fromHex(final String hexStr) throws IllegalHexCharacterException {
        byte[] decode = new byte[hexStr.length() / 2];
        char[] charArray = hexStr.toLowerCase().toCharArray();
        
        for (int i = 0; i < decode.length; i++) {
            char highFourBit = charArray[2 * i];
            char lowFourBit = charArray[2 * i + 1];
            if (-1 == hex.indexOf(highFourBit)) {
                throw new IllegalHexCharacterException(highFourBit);
            }
            if (-1 == hex.indexOf(lowFourBit)) {
                throw new IllegalHexCharacterException(lowFourBit);
            }

            decode[i] = (byte) (
                (bytes[hex.indexOf(highFourBit)] << 4) |
                (bytes[hex.indexOf(lowFourBit)])
            );
        }

        return decode;
    }

    /**
     * @param src
     * @return An encoded Base64 string
     */
    public static final String toBase64(final byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    /**
     * @param base64Str
     * @return An decoded byte array from a Base64 string
     */
    public static final byte[] fromBase64(final String base64Str) {
        return Base64.getDecoder().decode(base64Str);
    }
}
