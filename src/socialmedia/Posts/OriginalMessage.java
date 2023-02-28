package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;

import java.util.HashMap;

public class OriginalMessage extends Posts {
    private String message;
    protected HashMap<Integer, Comment> comments;
    protected HashMap<Integer, Endorsement> endorsements;

    public OriginalMessage(int id, User author, String message) throws InvalidPostException {
        this.uniqueId = id;
        this.author = author;

        if (message.length() < 100) {
            this.message = message;
        } else {
            throw new InvalidPostException("message is too long");
        }
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
