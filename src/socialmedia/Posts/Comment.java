package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.Posts.interfaces.MiniComment;


public class Comment extends Post implements MiniComment {
    public Comment(int id, Post referenceMessage, String comment, User author) {
        this.uniqueId = id;
        this.referencePost = referenceMessage;
        this.author = author;
        this.type = "comment";
        this.message = comment;
    }
}

