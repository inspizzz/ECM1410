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
public class SocialMedia implements SocialMediaPlatform {
    // create local variables that contain data while the app is running
    public static Map<Integer, User> accounts = new HashMap<Integer, User>();
    public static Map<String, Integer> accountHandles = new HashMap<String, Integer>();
    public static Map<Integer, Post> posts = new HashMap<Integer, Post>();


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
        // perform required checks on the handles and throw any errors that may occurr
        if (!handle.contains(" ") && handle.length() < 100 && !handle.equals("")) {
            if (!accountHandles.containsKey(handle)) {

                // generate unique ID
                int id = accounts.size() + 1;

                // create instance of user
                User user = new User(id, handle, description);

                // add user to the accounts and add to account handles
                accounts.put(id, user);
                accountHandles.put(handle, id);

                // return the id for later use
                return id;
            } else {

                // account handle already exists, throw error
                throw new IllegalHandleException(String.format("this handle: %s already exists", handle));
            }
        } else {

            // invalid account handle, is either empty, contains spaces or too long
            throw new InvalidHandleException(String.format("handle: %s is invalid", handle));
        }
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {

        // check if the account id exists inside of the
        if (accounts.containsKey(id)) {

            // remove the accounts from both dictionaries
            accountHandles.remove(accounts.get(id).getHandle());
            accounts.remove(id);

        } else {
            throw new AccountIDNotRecognisedException(String.format("account with id %d does not exist", id));
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        if (accountHandles.containsKey(handle)) {

            // remove the accounts from both dictionaries
            accounts.remove(accountHandles.get(handle));
            accountHandles.remove(handle);

        } else {
            throw new HandleNotRecognisedException(String.format("account with handle %s does not exist", handle));
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        // perform checks on the new handle
        if (!newHandle.contains(" ") && newHandle.length() < 100 && !newHandle.equals("")) {

            // if the new account handle does not already exist
            if (!accountHandles.containsKey(newHandle)) {

                // if the handle to change exists
                if (accountHandles.containsKey(oldHandle)) {

                    // change the accounts handle
                    User user = accounts.get(accountHandles.get(oldHandle));
                    user.setHandle(newHandle);

                    // update new handle in dictionary
                    accountHandles.remove(oldHandle);
                    accountHandles.put(newHandle, user.getId());

                } else {
                    throw new HandleNotRecognisedException(String.format("the handle to change: %s does not exist", oldHandle));
                }
            } else {

                // handle already exists and cannot
                throw new IllegalHandleException(String.format("this handle: %s already exists", newHandle));
            }
        } else {

            // invalid account handle, is either empty, contains spaces or too long
            throw new InvalidHandleException(String.format("handle: %s is invalid", newHandle));
        }

    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

        // check if the handle exists
        if (accountHandles.containsKey(handle)) {

            // grab the user
            User user = accounts.get(accountHandles.get(handle));

            // set the users description
            user.setDescription(description);

        } else {

            // account handle does not exist, throw an error
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {

        // check if account with this handle exists
        if (accountHandles.containsKey(handle)) {

            // begin by grabbing the user
            User user = accounts.get(accountHandles.get(handle));

            // begin grabbing information
            int id = user.getId();
            String description = user.getDescription();
            int postCount = user.getPostCount();
            int endorsementCount = User.getEndorsementCount();

            // return the formatted string
            return String.format(" * ID: %d \n * Handle: %s \n * Description: %s \n * Post count: %d \n * Endorse count: %d", id, handle, description, postCount, endorsementCount);

        } else {

            // handle does not exist
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        if (!accountHandles.containsKey(handle)) {
            throw new HandleNotRecognisedException(String.format("handle: %s not recognized", handle));
        }

        if (message.equals("") || message.length() > 100) {
            throw new InvalidPostException("this message is invalid");
        }

        User user = accounts.get(accountHandles.get(handle));
        OriginalMessage post = new OriginalMessage(generatePostId(), accounts.get(accountHandles.get(handle)), message);
        posts.put(post.getUniqueId(), post);
        user.addMessage(post);

        return post.getUniqueId();
    }

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

        // check if the handle exists
        if (accountHandles.containsKey(handle)) {
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

        // check if post id is recognised
        if (!posts.containsKey(id)) {
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        Post post = posts.get(id);

        if (post.getType().equals("endorsement")) {
            throw new NotActionablePostException("this post cannot be endorsed");
        }

        Endorsement endorsement = new Endorsement(generatePostId(), accounts.get(accountHandles.get(handle)), post);
        post.addEndorsement(endorsement);
        posts.put(endorsement.getUniqueId(), endorsement);

        return endorsement.getUniqueId();
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

        if (!accountHandles.containsKey(handle)) {
            // cannot find the user with handle
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }

        User user = accounts.get(accountHandles.get(handle));

        if (!posts.containsKey(id)) {
            // cannot find post with id
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        Post post = posts.get(id);

        if (post.getType().equals("endorsement")) {
            // cannot comment this post
            throw new NotActionablePostException("this post cannot be commented");
        }

        if (message.equals("") || message.length() > 100) {
            // invalid message
            throw new InvalidPostException("the comments message is invalid");
        }

        Comment comment = new Comment(generatePostId(), post, message, user);
        posts.put(comment.getUniqueId(), comment);
        user.addComment(comment);
        post.addComment(comment);

        return comment.getUniqueId();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {

        // chcek if the post exists
        if (!posts.containsKey(id)) {
            // error, post id does not exist
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        // get the post
        Post post = posts.get(id);

        // remove all references of it from the program
        for (Post endorsement : post.getEndorsements().values()) {

            // reset its reference post
            if (!posts.containsValue(endorsement)) {
                // reference post does not exist
                throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
            }

            endorsement.setReferencePost(null);
        }

        for (Post comment : post.getComments().values()) {

            // reset its reference post
            if (!posts.containsValue(comment)) {
                // reference post does not exist
                throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
            }

            comment.setReferencePost(null);
        }

        posts.remove(id);
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {

        if (!posts.containsKey(id)) {

            //post id is not recognised
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        Post post = posts.get(id);

        String data = " * ID: %d\n"
                + " * Account: %s\n"
                + " * No. endorsements: %d | No. comments: %d\n"
                + " * %s";

        // return this string formatted
        return String.format(data, id, post.getAuthor().getHandle(), post.getEndorsements().size(), post.getComments().size(), post.getMessage());
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {

        // catch errors
        if (!posts.containsKey(id)) {
            //post id is not recognised
            throw new PostIDNotRecognisedException(String.format("the post with id %d does not exist", id));
        }

        StringBuilder result = new StringBuilder();

        Post root = posts.get(id);
        List<Post> list = new ArrayList<>();

        list.add(root);

        result.append(String.format("-> ID: %d\n", id));
        result.append(String.format("-> Account: %s\n", root.getAuthor().getHandle()));
        result.append(String.format("-> No. endorsements: %d | No. comments: %d\n", root.getEndorsements().size(), root.getComments().size()));
        result.append(String.format("-> %s\n", root.getMessage()));

        while (list.size() > 0) {
            // get the last element
            Post currentPost = list.get(list.size() - 1);
            list.remove(list.size() - 1);

            String added = new String(new char[currentPost.getDepth()]).replace("\0", "\t");

            result.append(String.format("%s-> ID: %d\n", added, currentPost.getUniqueId()));
            result.append(String.format("%s-> Account: %s\n", added, currentPost.getAuthor().getHandle()));
            result.append(String.format("%s-> No. endorsements: %d | No. comments: %d\n", added, currentPost.getEndorsements().size(), currentPost.getComments().size()));
            result.append(String.format("%s-> %s\n", added, currentPost.getMessage()));


            List<Post> children = new ArrayList<>(currentPost.getComments().values());
            Collections.reverse(children);
            list.addAll(children);
        }
        return result;
    }

    @Override
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    @Override
    public int getTotalOriginalPosts() {
        int counter = 0;

        for (Post post : posts.values()) {
            if (post instanceof OriginalMessage) {
                counter ++;
            } else {
                // debug
                System.out.println("somethings wrong with this if statement");
            }
        }
        return counter;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        int counter = 0;

        for (Post post : posts.values()) {
            if (post instanceof Endorsement) {
                counter ++;
            } else {
                // debug
                System.out.println("somethings wrong with this if statement");
            }
        }
        return counter;
    }

    @Override
    public int getTotalCommentPosts() {
        int counter = 0;

        for (Post post : posts.values()) {
            if (post instanceof Comment) {
                counter ++;
            } else {
                // debug
                System.out.println("somethings wrong with this if statement");
            }
        }
        return counter;
    }

    @Override
    public int getMostEndorsedPost() {
        Map<Integer, Integer> endorsedPosts = new HashMap<Integer, Integer>();

        for (Post post : posts.values()) {

            // check how many endorsements it has
            int size = post.getEndorsements().size();

            // add to the hashmap
            endorsedPosts.put(size, post.getUniqueId());
        }

        return endorsedPosts.get(Collections.max(endorsedPosts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
    }

    @Override
    public int getMostEndorsedAccount() {

        int total;
        Map<Integer, Integer> endorsedUser = new HashMap<Integer, Integer>();

        // for all users check how many endorsements it has
        for (User user : accounts.values()) {
            total = 0;

            // check how many endorsements each message has
            for (OriginalMessage message : user.getMessages()) {
                total += message.getEndorsements().size();
            }

            // check how many endorsements each comment has
            for (Comment comment : user.getComments()) {
                total += comment.getEndorsements().size();
            }

            // update the users total linking it to the users id
            endorsedUser.put(total, user.getId());
        }

        return endorsedUser.get(Collections.max(endorsedUser.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
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

        // for every user serialising it
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


        System.out.println("Deserialized platform...");
        System.out.println("description: " + platform.getNumberOfAccounts());

        // load in all of the data into this instance of the class
        accounts = platform.getAccounts();
        accountHandles = platform.getAccountHandles();

        posts = platform.getPosts();
    }

    public int generatePostId() {
        return posts.size() + 1;
    }

    public Map<Integer, User> getAccounts() {
        return accounts;
    }

    public Map<String, Integer> getAccountHandles() {
        return accountHandles;
    }

    public Map<Integer, Post> getPosts() {
        return posts;
    }


}
