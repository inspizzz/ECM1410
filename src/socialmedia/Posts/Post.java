package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.InvalidPostException;
import socialmedia.Posts.interfaces.MiniPost;

import java.util.HashMap;

public class Post implements MiniPost {
    protected int uniqueId;
    protected User author;
    protected String type;
    protected String message;

    protected Post referencePost;


    protected HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
    protected HashMap<Integer, Endorsement> endorsements = new HashMap<Integer, Endorsement>();


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

    public void setType(String type) {
        this.type = type;
    }

    public void setReferencePost(Post post) {
        this.referencePost = post;
    }

    public void setMessage(String message) throws InvalidPostException {
        if (message.length() < 100) {
            this.message = message;
        } else {
            throw new InvalidPostException("message is too long");
        }
    }

    public int getDepth() {
        int counter = 0;
        Post current = this;

        while (current.referencePost != null) {
            current = current.referencePost;
            counter ++;
        }

        return counter;
    }

    public boolean isEndorseable() {
        return !this.type.equals("endorsement");
    }
}
