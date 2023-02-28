package socialmedia.Accounts;

import com.sun.management.VMOption;
import socialmedia.InvalidPostException;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.OriginalMessage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    private int userId;
    private String userHandle;
    private String description;

    private ArrayList<OriginalMessage> messages;
    private ArrayList<Comment> comments;
    private ArrayList<Endorsement> endorsements;

    public User(int id, String handle) {
        this.userId = id;
        this.userHandle = handle;
    }

    public User(int id, String handle, String description) {
        this.userId = id;
        this.userHandle = handle;
        this.description = description;
    }

    public int getId() {
       return this.userId;
    }

    public String getHandle() {
        return this.userHandle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public void setHandle(String handle) {
        this.userHandle = handle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OriginalMessage addMessage(int id, String content) throws InvalidPostException {
        OriginalMessage message = new OriginalMessage(id, this, content);

        this.messages.add(message);

        return message;
    }

    public ArrayList<OriginalMessage> getMessages() {
        return this.messages;
    }

    public Comment addComment(int id, OriginalMessage message, String content) {

        // create the new comment
        Comment comment = new Comment(id, message, content, this);

        // add this to the users comments
        this.comments.add(comment);

        // return the created comment
        return comment;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void addEndorsement() {

    }

    public ArrayList<Endorsement> getEndorsements() {
        return this.endorsements;
    }

    public int getPostCount() {
        return this.messages.size() + this.comments.size() + this.endorsements.size();
    }
}
