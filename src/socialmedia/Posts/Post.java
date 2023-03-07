package socialmedia.Posts;

import socialmedia.Accounts.User;

import java.io.Serializable;
import java.util.HashMap;

public class Post implements Serializable {
    protected int uniqueId;
    protected User author;
    protected HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
    protected HashMap<Integer, Endorsement> endorsements = new HashMap<Integer, Endorsement>();

    protected String type;

    protected String message;

    protected Post referencePost;


    public Post() {
        System.out.println("created an instance of posts");
    }

    public HashMap<Integer, Comment> getComments() {
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.put(comment.uniqueId, comment);
    }

    public HashMap<Integer, Endorsement> getEndorsements() {
        return this.endorsements;
    }

    public void addEndorsement(Endorsement endorsement) {
        this.endorsements.put(endorsement.getUniqueId(), endorsement);
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getType() {
        return this.type != null ? this.type : "";
    }

    public String getMessage() {
        return this.message != null ? this.message : "no message";
    }

    public User getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return String.format("{%d, author:{%s}, comments:{%s}, endorsements:{%s}, %s, %s}", uniqueId, author, comments, endorsements, type, message);
    }
}
