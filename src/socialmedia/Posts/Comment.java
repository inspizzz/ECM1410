package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.Posts.interfaces.MiniComment;

import java.io.Serializable;

public class Comment extends Post implements MiniComment, Serializable {
    public Comment(int id, Post referenceMessage, String comment, User author) {
        this.uniqueId = id;
        this.referencePost = referenceMessage;
        this.author = author;
        this.type = "comment";
        this.message = comment;

        System.out.println("creating post with id: " + uniqueId);
    }
}

