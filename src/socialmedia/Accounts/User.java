package socialmedia.Accounts;

public class User {

    private int userId;
    private String userHandle;

    public User(int id, String handle) {
        this.userId = id;
        this.userHandle = handle;
    }

    public int getId() {
       return this.userId;
    }

    public String getHandle() {
        return this.userHandle;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public void setHandle(String handle) {
        this.userHandle = handle;
    }
}
