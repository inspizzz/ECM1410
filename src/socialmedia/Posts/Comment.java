package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;

import java.util.HashMap;

public class Comment extends Posts {
    private String message;
    private OriginalMessage post;

    public Comment(int id, OriginalMessage referenceMessage, String comment, User author) {
        this.uniqueId = id;
        this.post = referenceMessage;
        this.message = comment;
        this.author = author;
        this.type = "comment";
    }

    public String getComment() {
        return this.message;
    }

    public void setComment(String newComment) throws InvalidPostException {
        if (newComment.length() < 100) {
            this.message = newComment;
        } else {
            throw new InvalidPostException("comment is too long");
        }
    }
}
