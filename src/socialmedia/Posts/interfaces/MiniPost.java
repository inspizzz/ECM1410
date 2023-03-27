package socialmedia.Posts.interfaces;

import socialmedia.Accounts.User;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.Post;

import java.util.HashMap;

public interface MiniPost {
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
