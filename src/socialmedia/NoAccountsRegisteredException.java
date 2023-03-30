package socialmedia;

/**
 * when something function should not be called whenever there
 * is no users currently registered in the application
 *
 * @author Wiktor Wiejak
 * @version 1.0
 */
public class NoAccountsRegisteredException extends Exception {

    /**
     * Constructs an instance of the exception with no message
     */
    public NoAccountsRegisteredException() {
        // do nothing
    }

    /**
     * Constructs an instance of the exception containing the message argument
     *
     * @param message error message
     */
    public NoAccountsRegisteredException(String message) {
        super(message);
    }
}
