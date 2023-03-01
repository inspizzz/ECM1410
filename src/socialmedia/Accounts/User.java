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

    private static ArrayList<OriginalMessage> messages;
    private static ArrayList<Comment> comments;
    private static ArrayList<Endorsement> endorsements;

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

        messages.add(message);

        return message;
    }

    public ArrayList<OriginalMessage> getMessages() {
        return messages;
    }

    public Comment addComment(int id, OriginalMessage message, String content) {

        // create the new comment
        Comment comment = new Comment(id, message, content, this);

        // add this to the users comments
        comments.add(comment);

        // return the created comment
        return comment;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addEndorsement() {

    }

    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    public int getPostCount() {
        return messages.size() + comments.size() + endorsements.size();
    }

    public static int getEndorsementCount() {
        // posts can contain endorsements, count up all of them
        // check the messages and comments as endorsements cannot endorse endorsements

        // count of all endorsements
        int count = 0;

        // iterate over message
        for (OriginalMessage message : messages) {
            count += message.getEndorsements().size();
        }

        // iterate over comments
        for (Comment comment : comments) {
            count += comment.getEndorsements().size();
        }

        // return the count of endorsements
        return count;
    }
}
