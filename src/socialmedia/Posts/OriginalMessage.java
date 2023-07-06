package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.Posts.interfaces.MiniOriginalMessage;

import java.io.Serializable;

public class OriginalMessage extends Post implements MiniOriginalMessage, Serializable {
    public OriginalMessage(int id, User author, String message) {
        this.uniqueId = id;
        this.author = author;
        this.type = "message";
        this.message = message;

        System.out.println("creating post with id: " + uniqueId);
    }
}
