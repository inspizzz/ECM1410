package socialmedia.Accounts;

import com.sun.management.VMOption;
import socialmedia.InvalidPostException;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.OriginalMessage;
import socialmedia.Posts.Post;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private int userId;
    private String userHandle;
    private String description;

    private static final ArrayList<OriginalMessage> messages = new ArrayList<>();
    private static final ArrayList<Comment> comments = new ArrayList<>();
    private static final ArrayList<Endorsement> endorsements = new ArrayList<>();

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

    public void addMessage(OriginalMessage message) throws InvalidPostException {
        messages.add(message);
    }

    public ArrayList<OriginalMessage> getMessages() {
        return messages;
    }

    public void addComment(Comment comment) {
        // add this to the users comments
        comments.add(comment);
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

    public void clearMessages() {
        messages.clear();
    }

    public void clearComments() {
        comments.clear();
    }

    public void clearEndorsements() {
        endorsements.clear();
    }

    @Override
    public String toString() {
        return String.format("{%d, %s, %s, messages:{%s}, comments:{%s}, endorsements{%s}}", userId, userHandle, description, messages, comments, endorsements);
    }
}
