package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;

public class OriginalMessage extends Post {
    private String message;


    public OriginalMessage(int id, User author, String message) throws InvalidPostException {
        this.uniqueId = id;
        this.author = author;
        this.message = message;
        this.type = "message";

        //add the post to the users posts
        author.addMessage(id, message);
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

    public void endorseMessage() {
        // endorse the post
    }
}
