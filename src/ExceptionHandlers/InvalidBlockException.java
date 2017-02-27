package ExceptionHandlers;
/**
 * Class for handling Invalid Block Exceptions triggered in the diskUtilities package.
 * @author Enrique A. Marrero Torres
 *
 */
public class InvalidBlockException extends RuntimeException {
	/**
	 * Constructor for InvalidBlockException.
	 */
	public InvalidBlockException() {
		
	}
	/**
	 * Constructor for InvalidBlockException with a message.
	 * @param arg0 the message to display whenever the exception is triggered.
	 */
	public InvalidBlockException(String arg0) {
		super(arg0);
		
	}
}
