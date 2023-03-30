package socialmedia;

/**
 * when something function should not be called whenever there
 * is no posts currently registered in the application
 *
 * @author Wiktor Wiejak
 * @version 1.0
 */
public class NoPostsRegisteredException extends Exception {

    /**
     * Constructs an instance of the exception with no message
     */
    public NoPostsRegisteredException() {
        // do nothing
    }

    /**
     * Constructs an instance of the exception containing the message argument
     *
     * @param message error message
     */
    public NoPostsRegisteredException(String message) {
        super(message);
    }

}
