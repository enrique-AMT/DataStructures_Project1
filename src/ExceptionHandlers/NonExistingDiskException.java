package ExceptionHandlers;

/**
 * Class that handles Non-existing Disk Exceptions for the diskUtilities package.
 * @author Enrique A. Marrero Torres
 *
 */
public class NonExistingDiskException extends RuntimeException {
	/**
	 * Constructor for NonExistingDiskException
	 */
	public NonExistingDiskException() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor for NonExistingDiskException with a message appended to it.
	 * @param arg0 the message to display whenever the exception is triggered.
	 */
	public NonExistingDiskException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
