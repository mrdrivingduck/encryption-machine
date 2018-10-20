package cn.zjt.encrypt.util;

import cn.zjt.encrypt.exception.IllegalHexCharacterException;

public class CoderUtil {

    private static char[] characters = {
        '0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f'
    };

    private static byte[] bytes = {
        0, 1, 2, 3,
        4, 5, 6, 7,
        8, 9, 10, 11,
        12, 13, 14, 15
    };

    private static String hex = "0123456789abcdef";

    /**
     * @version 2018.10.20
     * @param src
     * @return A hexadecimal string of source byte array
     */
    public static String encodeHex(final byte[] src) {
        char[] encode = new char[src.length * 2];
        int index = 0;

        for (byte b : src) {
            encode[index++] = characters[(b >> 4) & 0x0F];
            encode[index++] = characters[b & 0x0F];
        }

        return new String(encode);
    }

    /**
     * @version 2018.10.20
     * @param hexStr
     * @return A byte array parsed from source hexadecimal string
     * @throws IllegalHexCharacterException
     */
    public static byte[] decodeHex(final String hexStr) throws IllegalHexCharacterException {
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

}
