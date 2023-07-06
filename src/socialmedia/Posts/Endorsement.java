package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.Posts.interfaces.MiniEndorsement;

import java.io.Serializable;

public class Endorsement extends Post implements MiniEndorsement, Serializable {
    public Endorsement(int id, User author, Post reference) {
        this.type = "endorsement";
        this.uniqueId = id;
        this.author = author;
        this.referencePost = reference;

        System.out.println("creating post with id: " + uniqueId);
    }
}
