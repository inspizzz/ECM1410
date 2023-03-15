package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;

public class OriginalMessage extends Post {


    public OriginalMessage(int id, User author, String message) throws InvalidPostException {
        this.uniqueId = id;
        this.author = author;
        this.type = "message";
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) throws InvalidPostException {
        if (message.length() < 100) {
            this.message = message;
        } else {
            throw new InvalidPostException("message is too long");
        }
    }
}
