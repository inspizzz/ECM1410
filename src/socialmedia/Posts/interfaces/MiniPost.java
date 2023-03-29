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
     * get the comments that this post is associated to
     *
     * @return the hashmap of comment id's
     */
    public HashMap<Integer, Comment> getComments();

    public void addComment(Comment comment);

    public HashMap<Integer, Endorsement> getEndorsements();

    public void addEndorsement(Endorsement endorsement);

    public int getUniqueId();

    public String getType();

    public String getMessage();

    public User getAuthor();

    public void setType(String type);

    public void setReferencePost(Post post);

    public int getDepth();

    public boolean isEndorseable();
}
