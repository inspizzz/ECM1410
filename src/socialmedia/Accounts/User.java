package socialmedia.Accounts;

public class User {

    private int userId;
    private String userHandle;
    private String description;

    public User(int id, String handle) {
        this.userId = id;
        this.userHandle = handle;
    }

    public User(int id, String handle, String description) {
        this.userId = id;
        this.userHandle = handle;
        this.description = description;
    }

    public int getId() {
       return this.userId;
    }

    public String getHandle() {
        return this.userHandle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public void setHandle(String handle) {
        this.userHandle = handle;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
