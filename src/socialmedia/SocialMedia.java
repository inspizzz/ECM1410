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
