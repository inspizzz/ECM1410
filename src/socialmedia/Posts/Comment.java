package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;

public class Comment extends Post {
    private String message;

    public Comment(int id, Post referenceMessage, String comment, User author) {
        this.uniqueId = id;
        this.referencePost = referenceMessage;
        this.author = author;
        this.type = "comment";
        this.message = comment;
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

    public void setReference(Post reference) {
        this.referencePost = reference;
    }
}
