package socialmedia.Posts;

import socialmedia.Accounts.User;

public class Endorsement extends Post {
    private static int postId;

    public Endorsement(int id, User author) {
        this.type = "endorsement";
        this.uniqueId = id;
        this.author = author;
    }

}
