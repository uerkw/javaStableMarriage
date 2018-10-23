package ArrayStack;
/**
 * 
 */

/**
 * Exception used to signal that a push on a full stack has been attempted.
 * 
 * @author Gerald R. Heuring
 * 
 * Written:  September 20, 2018
 * 
 * Revisions:
 * 
 * Bugs:
 *
 */
public class StackOverflowException extends Exception {

	/**
	 * 
	 */
	public StackOverflowException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public StackOverflowException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public StackOverflowException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StackOverflowException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StackOverflowException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
