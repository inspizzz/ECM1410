package socialmedia.Posts;

import socialmedia.Accounts.User;

import java.util.HashMap;

public class Posts {
    protected int uniqueId;
    protected User author;
    protected HashMap<Integer, Comment> comments;
    protected HashMap<Integer, Endorsement> endorsements;


    public Posts() {
        System.out.println("created an instance of posts");
    }

    public HashMap<Integer, Comment> getComments() {
        return this.comments;
    }

    public HashMap<Integer, Endorsement> getEndorsements() {
        return this.endorsements;
    }

    public int getUniqueId() {
        return uniqueId;
    }
}
