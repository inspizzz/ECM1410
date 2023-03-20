package socialmedia.Accounts;

import socialmedia.InvalidPostException;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.OriginalMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int userId;
    private String userHandle;
    private String description;

    private static final ArrayList<OriginalMessage> messages = new ArrayList<>();
    private static final ArrayList<Comment> comments = new ArrayList<>();
    private static final ArrayList<Endorsement> endorsements = new ArrayList<>();

    /**
     * create an instance of a user using an id and handle
     *
     * @param id
     * @param handle
     */
    public User(int id, String handle) {
        this.userId = id;
        this.userHandle = handle;
    }

    /**
     * create an instance of a user using an id and handle and description
     *
     * @param id
     * @param handle
     * @param description
     */
    public User(int id, String handle, String description) {
        this.userId = id;
        this.userHandle = handle;
        this.description = description;
    }

    /**
     * get the id of the user
     *
     * @return the id of the user
     */
    public int getId() {
       return this.userId;
    }

    /**
     * get the handle of the user
     *
     * @return the handle of the user
     */
    public String getHandle() {
        return this.userHandle;
    }

    /**
     * get the description of the user
     *
     * @return the description of the user
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the id of the user
     *
     * @param id the id to update the user with
     */
    public void setId(int id) {
        this.userId = id;
    }

    /**
     *  set the handle of the user
     *
     * @param handle the handle to update the user with
     */
    public void setHandle(String handle) {
        this.userHandle = handle;
    }

    /**
     * set the description of the user
     *
     * @param description the description to update the user with
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * add a message to the user, this would be called when this user
     * has posted a message
     *
     * @param message added message instance
     */
    public void addMessage(OriginalMessage message) {
        messages.add(message);
    }

    /**
     * get all messages that the user has posted
     *
     * @return the messages that the user has posted
     */
    public ArrayList<OriginalMessage> getMessages() {
        return messages;
    }

    /**
     * add a comment to the user, this would be called when this user
     * has posted a comment
     *
     * @param comment the comment that the user has posted
     */
    public void addComment(Comment comment) {
        // add this to the users comments
        comments.add(comment);
    }

    /**
     * get all comments that the user has posted
     *
     * @return all the comments that the user has posted
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     *
     */
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
}
