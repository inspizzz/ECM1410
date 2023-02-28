package socialmedia;

import socialmedia.Accounts.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        // perform required checks on the handles and throw any errors that may occurr
        if (!handle.contains(" ") && handle.length() < 100 && !handle.equals("")) {
            if (!accountHandles.containsKey(handle)) {

                // generate unique ID
                int id = accounts.size() + 1;

                // create instance of user
                User user = new User(id, handle);

                // add user to the accounts and add to account handles
                accounts.put(id, user);
                accountHandles.put(handle, id);

                return id;
            } else {

                // account handle already exists, throw error
                throw new IllegalHandleException("this handle already exists");
            }
        } else {

            // invalid account handle, is either empty, contains spaces or too long
            throw new InvalidHandleException("handle is invalid");
        }
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {

    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {

    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfAccounts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalOriginalPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalCommentPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedPost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void erasePlatform() {
        // TODO Auto-generated method stub

    }

    @Override
    public void savePlatform(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }

}
