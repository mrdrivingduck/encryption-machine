/**
 * @author Mr Dk.
 * @version 2018.10.xx
 */

package iot.zjt.encrypt.exception;

public class IllegalHexCharacterException extends Exception {

	private static final long serialVersionUID = 571146201183796105L;

	public IllegalHexCharacterException(char c) {
        super("Illegal hexadecimal character: '" + c + "'");
    }

}
