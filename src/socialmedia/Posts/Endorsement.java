package socialmedia.Posts;

import socialmedia.Accounts.User;
import socialmedia.Posts.interfaces.MiniEndorsement;

public class Endorsement extends Post implements MiniEndorsement {
    public Endorsement(int id, User author, Post reference) {
        this.type = "endorsement";
        this.uniqueId = id;
        this.author = author;
        this.referencePost = reference;
    }
}
