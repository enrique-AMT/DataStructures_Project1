package ExceptionHandlers;
/**
 * Class that handles exceptions for currently existing disk instances.
 * @author Enrique
 *
 */
public class ExistingDiskException extends RuntimeException {
	
	/**
	 * Simple constructor for ExistingDiskException.
	 */
	public ExistingDiskException() {
		
	}
	
	/**
	 * Constructor for ExistingDiskException with a message.
	 * @param arg0 the message to display when the exception is triggered.
	 */
	public ExistingDiskException(String arg0) {
		super(arg0);
		
	}

}
