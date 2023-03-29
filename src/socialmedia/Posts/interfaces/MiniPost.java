package socialmedia.Posts.interfaces;

import socialmedia.Accounts.User;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.Post;

import java.util.HashMap;

/**
 * this is the post class, every post extends this
 * class and it contains the key functionality that
 * changes and modifies aspects of the post such as
 * its unique id and type as well as whether it is
 * endorseable or not.
 */
public interface MiniPost {

    /**
     * get the comments that this post is associated to in hashmap format
     * id of the comment is linked to the comment instance
     *
     * @return the hashmap of comment id's linked to comments
     */
    public HashMap<Integer, Comment> getComments();

    /**
     * add a comment to the post
     *
     * @param comment the comment to be added to the post
     */
    public void addComment(Comment comment);

    /**
     * get the endorsement that this post is associated to in hashmap format
     * id of the endorsement is linked to the endorsement instance
     *
     * @return the hashmap of endorsements id's linked to endorsements
     */
    public HashMap<Integer, Endorsement> getEndorsements();

    /**
     * add an endorsement to the post
     *
     * @param endorsement the endorsement to be added to the post
     */
    public void addEndorsement(Endorsement endorsement);

    /**
     * get the unique id of the post
     *
     * @return the unique id of the post
     */
    public int getUniqueId();

    /**
     * get the type of the post, whether it is a comment, endorsement
     * or an original message, this helps to distinguish this object
     * between the different types of posts
     *
     * @return the type of the post
     */
    public String getType();

    /**
     * get the message that is associated with the post, not that
     * endorsements do not have a message associated with the post
     *
     * @return the message of the post
     */
    public String getMessage();

    /**
     * get the author of the post, who is responsible for the
     * creation of the post
     *
     * @return the instance of the user that created the post
     */
    public User getAuthor();

    /**
     * set the type of the post, it can be either an original
     * message, an endorsement or a comment
     *
     * @param type the new type of the post
     */
    public void setType(String type);

    /**
     * set the reference post, this is a post that this post references
     *
     * @param post post to be referenced by this instance
     */
    public void setReferencePost(Post post);

    /**
     * get the depth of this post, this helps in displaying the comments
     * with the correct indentation, the further down the tree a comment
     * is then the higher the number returned by this function is going
     * to be
     *
     * @return the depth of this post
     */
    public int getDepth();

    /**
     * check if the current post is endorseable, this means checking that
     * the current post is not an instance of an endorsement
     *
     * @return whether or not the post is endorseable
     */
    public boolean isEndorseable();
}
