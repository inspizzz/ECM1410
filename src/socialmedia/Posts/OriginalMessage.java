package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;

public class OriginalMessage extends Posts {
    private String message;

    public OriginalMessage(int id, User author, String message) throws InvalidPostException {
        this.uniqueId = id;
        this.authorHandle = author.getHandle();
        this.authorId = author.getId();

        if (message.length() < 100) {
            this.message = message;
        } else {
            throw new InvalidPostException("message is too long");
        }
    }

    public String getMessage() {
        return this.message;
    }

}
