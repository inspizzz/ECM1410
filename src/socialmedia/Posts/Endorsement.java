package socialmedia.Posts;

import socialmedia.Accounts.User;

public class Endorsement extends Post {
    public Endorsement(int id, User author, Post reference) {
        this.type = "endorsement";
        this.uniqueId = id;
        this.author = author;
        this.referencePost = reference;
    }

}
