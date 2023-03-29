package socialmedia;

import socialmedia.Accounts.User;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.OriginalMessage;
import socialmedia.Posts.Post;


import java.io.*;

import java.util.*;

/**
 * SocialMedia is a compiling and functioning implementor of
 * the SocialMediaPlatform interface that also extends
 * MiniSocialMediaPlatform interface, so functionality
 * is also extracted from this class.
 *
 * @author Wiktor Wiejak
 * @version 1.0
 */
public class SocialMedia implements SocialMediaPlatform, Serializable {
    // create local variables that contain data while the app is running
    public Map<Integer, User> accounts = new HashMap<Integer, User>();
    public Map<String, Integer> accountHandles = new HashMap<String, Integer>();
    public Map<Integer, Post> posts = new HashMap<Integer, Post>();


    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        // check if the handle is valid
        if (handle.contains(" ") || handle.length() > 100 || handle.equals("")) {

            // invalid account handle, is either empty, contains spaces or too long
            throw new InvalidHandleException(String.format("handle: %s is invalid", handle));
        }

        // check if the handle already exists
        if (accountHandles.containsKey(handle)) {

            // account handle already exists, throw error
            throw new IllegalHandleException(String.format("this handle: %s already exists", handle));
        }

        // generate unique ID
        int id = accounts.size() + 1;

        // create instance of user
        User user = new User(id, handle);

        // add user to the accounts and add to account handles
        accounts.put(id, user);
        accountHandles.put(handle, id);

        // return the id for later use
        return id;
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

        // check if the handle is valid
        if (handle.contains(" ") || handle.length() > 100 || handle.equals("")) {

            // invalid account handle, is either empty, contains spaces or too long
            throw new InvalidHandleException(String.format("handle: %s is invalid", handle));
        }

        // check if the handle already exists
        if (accountHandles.containsKey(handle)) {

            // account handle already exists, throw error
            throw new IllegalHandleException(String.format("this handle: %s already exists", handle));
        }

        // generate unique ID
        int id = accounts.size() + 1;

        // create instance of user
        User user = new User(id, handle, description);

        // add user to the accounts and add to account handles
        accounts.put(id, user);
        accountHandles.put(handle, id);

        // return the id for later use
        return id;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {

        // check if the account id exists inside of the list
        if (!accounts.containsKey(id)) {

            // account doesnt exist, throw error
            throw new AccountIDNotRecognisedException(String.format("account with id %d does not exist", id));
        }

        // remove the accounts from both dictionaries
        accountHandles.remove(accounts.get(id).getHandle());
        accounts.remove(id);
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {

        // check if the account id exists inside of the list
        if (!accountHandles.containsKey(handle)) {

            // account doesnt exist, throw error
            throw new HandleNotRecognisedException(String.format("account with handle %s does not exist", handle));
        }

        // remove the accounts from both dictionaries
        accounts.remove(accountHandles.get(handle));
        accountHandles.remove(handle);
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

        // check the new handle
        if (newHandle.contains(" ") || newHandle.length() > 100 || newHandle.equals("")) {

            // invalid account handle, is either empty, contains spaces or too long
            throw new InvalidHandleException(String.format("handle: %s is invalid", newHandle));
        }

        // if the new account handle does not already exist
        if (accountHandles.containsKey(newHandle)) {

            // handle already exists and cannot
            throw new IllegalHandleException(String.format("this handle: %s already exists", newHandle));
        }

        // if the handle to change doesnt exist
        if (!accountHandles.containsKey(oldHandle)) {

            // handle to change does not exist, throw error
            throw new HandleNotRecognisedException(String.format("the handle to change: %s does not exist", oldHandle));
        }

        // change the accounts handle
        User user = accounts.get(accountHandles.get(oldHandle));
        user.setHandle(newHandle);

        // update new handle in dictionary
        accountHandles.remove(oldHandle);
        accountHandles.put(newHandle, user.getId());
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

        // check if the handle exists
        if (!accountHandles.containsKey(handle)) {

            // account handle does not exist, throw an error
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

        // grab the user
        User user = accounts.get(accountHandles.get(handle));

        // set the users description
        user.setDescription(description);
    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {

        // check if account with this handle exists
        if (!accountHandles.containsKey(handle)) {

            // handle does not exist
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

        // begin by grabbing the user
        User user = accounts.get(accountHandles.get(handle));

        // begin grabbing information
        int id = user.getId();
        String description = user.getDescription();
        int postCount = user.getPostCount();
        int endorsementCount = User.getEndorsementCount();

        // format the string and return
        return String.format(" * ID: %d \n * Handle: %s \n * Description: %s \n * Post count: %d \n * Endorse count: %d", id, handle, description, postCount, endorsementCount);
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        // check if the account handle exists
        if (!accountHandles.containsKey(handle)) {

            // account handle does not exist, ahhhhhhhhh, the world is burning!
            throw new HandleNotRecognisedException(String.format("handle: %s not recognized", handle));
        }

        // check if the message is valid
        if (message.equals("") || message.length() > 100) {

            // message is not valid, ahhhhhhhhh
            throw new InvalidPostException("this message is invalid");
        }

        // grab the user
        User user = accounts.get(accountHandles.get(handle));

        // create the message
        OriginalMessage post = new OriginalMessage(generatePostId(), accounts.get(accountHandles.get(handle)), message);

        // add the post to storage
        posts.put(post.getUniqueId(), post);
        user.addMessage(post);

        return post.getUniqueId();
    }

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

        // check if the handle exists
        if (!accountHandles.containsKey(handle)) {

            // the handle does not exist, throw error
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

        // check if post id is recognised
        if (!posts.containsKey(id)) {

            // post does not exist, throw error
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        // grab the post and user
        User user = accounts.get(accountHandles.get(handle));
        Post post = posts.get(id);

        // check if the post is endorseable
        if (!post.isEndorseable()) {

            // post is not endorseable as it is an endorsement in itself, throw error
            throw new NotActionablePostException("this post cannot be endorsed");
        }

        // check if the user has already endorsed this post
        for (Endorsement endorsement : post.getEndorsements().values()) {
            if (endorsement.getAuthor().equals(accounts.get(accountHandles.get(handle)))) {

                // this user has already endorsed this post
                throw new NotActionablePostException("already endorsed this post");
            }
        }

        // create object of endorsement
        Endorsement endorsement = new Endorsement(generatePostId(), accounts.get(accountHandles.get(handle)), post);

        // add it to storage
        post.addEndorsement(endorsement);
        user.addEndorsement(endorsement);
        posts.put(endorsement.getUniqueId(), endorsement);

        return endorsement.getUniqueId();
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

        // check if the account handle exists
        if (!accountHandles.containsKey(handle)) {

            // cannot find the user with handle, throw error
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

        // check if the post id exists
        if (!posts.containsKey(id)) {

            // cannot find post with id, throw error
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        // grab the user and the post
        User user = accounts.get(accountHandles.get(handle));
        Post post = posts.get(id);

        // check if the post is endorseable
        if (!post.isEndorseable()) {

            // cannot comment this post as it is an endorsement
            throw new NotActionablePostException("this post cannot be commented");
        }

        // check if the message is valid
        if (message.equals("") || message.length() > 100) {

            // invalid message, throw error
            throw new InvalidPostException("the comments message is invalid");
        }

        // create the comment
        Comment comment = new Comment(generatePostId(), post, message, user);

        // add the comment to storage
        posts.put(comment.getUniqueId(), comment);
        user.addComment(comment);
        post.addComment(comment);

        return comment.getUniqueId();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {

        // chcek if the post exists
        if (!posts.containsKey(id)) {

            // error, post id does not exist, throw error
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        // grab the post
        Post post = posts.get(id);

        // remove all references of endorsements from the program
        for (Post endorsement : post.getEndorsements().values()) {

            // check if the post endorsement exists
            if (!posts.containsValue(endorsement)) {

                // reference post does not exist, throw error
                throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
            }

            // reset its reference post
            endorsement.setReferencePost(null);
        }

        // remove all references of comments from the program
        for (Post comment : post.getComments().values()) {

            // check if the post comment exists
            if (!posts.containsValue(comment)) {

                // reference post does not exist, throw error
                throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
            }

            // reset its reference post
            comment.setReferencePost(null);
        }

        // remove reference of the post from storage
        posts.remove(id);
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {

        // check if the post with id exists
        if (!posts.containsKey(id)) {

            //post id is not recognised, throw error
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        // get the post with id
        Post post = posts.get(id);

        // begin formatting the data
        String data = " * ID: %d\n"
                + " * Account: %s\n"
                + " * No. endorsements: %d | No. comments: %d\n"
                + " * %s";

        // return this string formatted
        return String.format(data, id, post.getAuthor().getHandle(), post.getEndorsements().size(), post.getComments().size(), post.getMessage());
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {

        // check if the post with id exists
        if (!posts.containsKey(id)) {

            //post id is not recognised, throw error
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        // begin string
        StringBuilder result = new StringBuilder();

        // grab the root post
        Post root = posts.get(id);

        // start the queue
        List<Post> list = new ArrayList<>();
        list.add(root);

        // begin creating the string, starting with the root post
        result.append(String.format("-> ID: %d\n", id));
        result.append(String.format("-> Account: %s\n", root.getAuthor().getHandle()));
        result.append(String.format("-> No. endorsements: %d | No. comments: %d\n", root.getEndorsements().size(), root.getComments().size()));
        result.append(String.format("-> %s\n\n", root.getMessage()));

        // do depth first search
        while (list.size() > 0) {

            // get the last element in the list
            Post currentPost = list.get(list.size() - 1);
            list.remove(list.size() - 1);

            // add to the string information about the current post
            String added = new String(new char[currentPost.getDepth()]).replace("\0", "\t");

            result.append(String.format("%s-> ID: %d\n", added, currentPost.getUniqueId()));
            result.append(String.format("%s-> Account: %s\n", added, currentPost.getAuthor().getHandle()));
            result.append(String.format("%s-> No. endorsements: %d | No. comments: %d\n", added, currentPost.getEndorsements().size(), currentPost.getComments().size()));
            result.append(String.format("%s-> %s\n", added, currentPost.getMessage()));

            // add current posts children to the queue
            List<Post> children = new ArrayList<>(currentPost.getComments().values());
            Collections.reverse(children);
            list.addAll(children);
        }

        return result;
    }

    @Override
    public int getNumberOfAccounts() {

        // get the size of the accounts list
        return accounts.size();
    }

    @Override
    public int getTotalOriginalPosts() {

        // store the number of original posts
        int counter = 0;

        // go over all posts and check if it has an isntance of an original message
        for (Post post : posts.values()) {
            if (post instanceof OriginalMessage) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public int getTotalEndorsmentPosts() {

        // store the number of endorsement posts
        int counter = 0;

        // go over all posts and check if they are instances of endorsements
        for (Post post : posts.values()) {
            if (post instanceof Endorsement) {
                counter ++;
            }
        }

        return counter;
    }

    @Override
    public int getTotalCommentPosts() {

        // store the number of comment posts
        int counter = 0;

        // go over all of the posts and check whether it is a comment
        for (Post post : posts.values()) {
            if (post instanceof Comment) {
                counter ++;
            }
        }

        return counter;
    }

    @Override
    public int getMostEndorsedPost() throws NoPostsRegisteredException {

        // avoid performing this task when there are no posts registered
        if (posts.size() == 0) {

            // throw exception
            throw new NoPostsRegisteredException("this action may not be performed as there are no posts registered");
        }

        // instantiate variables to keep track of post id and number of endorsements
        int currentPostId = -1;
        int currentMaxEndorsement = -1;

        // go over every post
        for (Post post : posts.values()) {

            // check how many endorsements it has
            int size = post.getEndorsements().size();

            // update if larger than current largest
            if (size > currentMaxEndorsement) {
                currentPostId = post.getUniqueId();
                currentMaxEndorsement = size;
            }
        }

        return currentPostId;
    }

    @Override
    public int getMostEndorsedAccount() throws NoAccountsRegisteredException {

        // avoid performing this task when there are no accounts registered
        if (accounts.size() == 0) {

            // throw error
            throw new NoAccountsRegisteredException("this action may not be performed as there are no accounts registered");
        }

        // keep track of account and endorsements
        int total;
        int currentAccount = -1;
        int currentMax = -1;

        // for all users check how many endorsements it has
        for (User user : accounts.values()) {

            // total for user starts off as 0
            total = 0;

            // check how many endorsements each message has and add to total
            for (OriginalMessage message : user.getMessages()) {
                total += message.getEndorsements().size();
            }

            // check how many endorsements each comment has and add to total
            for (Comment comment : user.getComments()) {
                total += comment.getEndorsements().size();
            }

            // update the users total linking it to the users id
            if (total > currentMax) {
                currentAccount = user.getId();
                currentMax = total;
            }
        }

        return currentAccount;
    }

    @Override
    public void erasePlatform() {

        // go over every user and erase their data
        for (User user : accounts.values()) {
            user.clearMessages();
            user.clearComments();
            user.clearEndorsements();
        }

        // remove accounts
        accounts.clear();
        accountHandles.clear();

        // remove posts
        posts.clear();
    }

    @Override
    public void savePlatform(String filename) throws IOException {

        // initialise the output streams
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        // serialise the whole socialmedia class
        out.writeObject(this);

        // close connections
        out.close();
        fileOut.close();
    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {

        // open input stream reader
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        // read user
        SocialMedia platform = (SocialMedia) in.readObject();

        // close connection
        in.close();
        fileIn.close();

        // load in all of the data into this instance of the class
        accounts = platform.getAccounts();
        accountHandles = platform.getAccountHandles();
        posts = platform.getPosts();
    }

    /**
     * this method generates a unique post id based on the number of
     * current posts inside of the platform. it will not add a post
     * into the gap created when another post is deleted.
     *
     * @return a unique id that may be used to create a post
     */
    public int generatePostId() {
        return posts.size() + 1;
    }

    /**
     * this method gets all accounts in this instance of the class, it
     * is used when loading the application back into the environment
     *
     * @return the accounts variable
     */
    public Map<Integer, User> getAccounts() {
        return accounts;
    }

    /**
     * this method gets all accounts in this instance of the class, it
     * is used when loading the application back into the environment
     *
     * @return the accountHandles variable
     */
    public Map<String, Integer> getAccountHandles() {
        return accountHandles;
    }

    /**
     * this method gets all accounts in this instance of the class, it
     * is used when loading the application back into the environment
     *
     * @return the posts variable
     */
    public Map<Integer, Post> getPosts() {
        return posts;
    }
}
