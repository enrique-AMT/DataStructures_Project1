package ExceptionHandlers;
/**
 * Class that handles Invalid Block Number Exceptions triggered by the diskUtilities package.
 * @author Enrique A. Marrero Torres
 *
 */
public class InvalidBlockNumberException extends RuntimeException {
	/**
	 * Constructor for InvalidBlockNumberException.
	 */
	public InvalidBlockNumberException() {
		
	}
	/**
	 * Constructor for InvalidBlockNumberException with a message appended to it.
	 * @param arg0 the message to display whenever the exception is triggered.
	 */
	public InvalidBlockNumberException(String arg0) {
		super(arg0);
		
	}

}
