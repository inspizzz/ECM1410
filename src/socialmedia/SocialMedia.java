package socialmedia;

import socialmedia.Accounts.User;

import java.io.IOException;
import java.util.ArrayList;

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
    public static ArrayList<User> accounts = new ArrayList<User>();

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        // generate a unique id that is not inside the accounts list
        int uniqueId = accounts.size() + 1;

        // create the user and give it a unique id and handle
        User user = new User(uniqueId, handle);

        // add this user to the array of current users
        accounts.add(user);

        // return the value of the users unique id
        return uniqueId;
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        // generate a unique id that is not inside the accounts list
        int uniqueId = accounts.size() + 1;

        // create the user and give it a unique id and handle
        User user = new User(uniqueId, handle, description);

        // add this user to the array of current users
        accounts.add(user);

        // return the value of the users unique id
        return uniqueId;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        // variable used for checking if the account has been found
        boolean found = false;

        // iterate over all registered accounts
        for (User user : accounts) {

            // check if account handles match
            if (user.getId() == id) {

                // remove the user and store that user has been found
                accounts.remove(user);
                found = true;

                // dont have to search any further
                break;
            }
        }

        // if the user has not been found throw error
        if (!found) {
            throw new AccountIDNotRecognisedException("this id does not exist");
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        // variable used for checking if the account has been found
        boolean found = false;

        // iterate over all registered accounts
        for (User user : accounts) {

            // check if account handles match
            if (user.getHandle().equals(handle)) {

                // remove the user and store that user has been found
                accounts.remove(user);
                found = true;

                // dont have to search any further
                break;
            }
        }

        // if the user has not been found throw error
        if (!found) {
            throw new HandleNotRecognisedException("this handle does not exist");
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        boolean exists = false;

        // iterate over all registered accounts
        for (User user : accounts) {
            if (user.getHandle().equals(newHandle)) {
                exists = true;

            }
        }


        // variable used for checking if the account has been found
        boolean found = false;

        // iterate over all registered accounts
        for (User user : accounts) {

            // check if account handle matches
            if (user.getHandle().equals(oldHandle)) {

                // change the account handle
                user.setHandle(newHandle);

                // found the account
                found = true;

                // dont have to search any further
                break;
            }
        }

        if (!found) {
            throw new HandleNotRecognisedException("handle has not been recognized");
        }
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
