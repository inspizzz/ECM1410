package socialmedia.Posts;

import socialmedia.Accounts.User;

import java.util.HashMap;

public class Post {
    protected int uniqueId;
    protected User author;
    protected HashMap<Integer, Comment> comments;
    protected HashMap<Integer, Endorsement> endorsements;

    protected String type;

    protected String message;


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
        return this.message;
    }

    public User getAuthor() {
        return this.author;
    }
}
