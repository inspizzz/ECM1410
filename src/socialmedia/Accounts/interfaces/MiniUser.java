package socialmedia.Accounts.interfaces;

import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.OriginalMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A User class to keep all data about the user, including
 * functions that change and modify this data. Each user is
 * given their own unique id and handle, as well as an
 * optional description
 *
 * @author Wiktor Wiejak
 * @version 2.9
 */
public interface MiniUser extends Serializable {
    /**
     * get the id of the user
     *
     * @return the id of the user
     */
    public int getId();

    /**
     * get the handle of the user
     *
     * @return the handle of the user
     */
    public String getHandle();

    /**
     * get the description of the user
     *
     * @return the description of the user
     */
    public String getDescription();

    /**
     * set the id of the user
     *
     * @param id the id to update the user with
     */
    public void setId(int id);

    /**
     *  set the handle of the user
     *
     * @param handle the handle to update the user with
     */
    public void setHandle(String handle);

    /**
     * set the description of the user
     *
     * @param description the description to update the user with
     */
    public void setDescription(String description);

    /**
     * add a message to the user, this would be called when this user
     * has posted a message
     *
     * @param message added message instance
     */
    public void addMessage(OriginalMessage message);

    /**
     * get all messages that the user has posted
     *
     * @return the messages that the user has posted
     */
    public ArrayList<OriginalMessage> getMessages();

    /**
     * add a comment to the user, this would be called when this user
     * has posted a comment
     *
     * @param comment the comment that the user has posted
     */
    public void addComment(Comment comment);

    /**
     * get all comments that the user has posted
     *
     * @return all the comments that the user has posted
     */
    public ArrayList<Comment> getComments();

    /**
     *  add an endorsement to the user, this would be called when this user
     *  has posted an endorsement
     *
     * @param endorsement the endorsement that the user has posted
     */
    public void addEndorsement(Endorsement endorsement);

    /**
     * returns all endorsements that the user has posted
     *
     * @return an arraylist of all endorsements the user has posted
     */
    public ArrayList<Endorsement> getEndorsements();

    /**
     * returns the number of posts that the user has posted in total
     *
     * @return the total number of messages, comments and endorsements
     */
    public int getPostCount();

    /**
     * get the total number of endorsements that the users posts
     * that can be endorsed contains, this is both messages and
     * comments
     *
     * @return the number of endorsements the users posts contain
     */
    public static int getEndorsementCount() {
        return 0;
    }

    /**
     * clear the messages of the user
     */
    public void clearMessages();

    /**
     * clear the users comments
     */
    public void clearComments();

    /**
     * clear the users endoresements
     */
    public void clearEndorsements();
}