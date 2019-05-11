package iot.zjt.encrypt.machine;

/**
 * @author mrdrivingduck
 * @version 2019-05-10
 * 
 * @decription Encryption machine for asymmetrical encryption
 * 
 * @support
 *  RSA/ECB/PKCS1Padding (1024, 2048)
 *  RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
 *  RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
 */

class Asym {

    public static enum AsymAlgs {
        RSA
    }

    public static enum AsymMode {
        ECB
    }

    public static enum AsymPadding {
        PKCS1Padding
    }

}