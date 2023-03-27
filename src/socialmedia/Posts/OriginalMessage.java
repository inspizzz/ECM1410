package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;
import socialmedia.Posts.interfaces.MiniOriginalMessage;

public class OriginalMessage extends Post implements MiniOriginalMessage {
    public OriginalMessage(int id, User author, String message) throws InvalidPostException {
        this.uniqueId = id;
        this.author = author;
        this.type = "message";
        this.message = message;
    }
}
