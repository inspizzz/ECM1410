package socialmedia;

import socialmedia.Accounts.User;
import socialmedia.Posts.Comment;
import socialmedia.Posts.Endorsement;
import socialmedia.Posts.OriginalMessage;
import socialmedia.Posts.Post;

import java.io.IOException;
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

    public static Map<Integer, OriginalMessage> messages = new HashMap<Integer, OriginalMessage>();
    public static Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
    public static Map<Integer, Endorsement> endorsements = new HashMap<Integer, Endorsement>();



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
        if (accountHandles.containsKey(handle)) {
            if (!message.equals("") && message.length() < 100) {

                // get the user that is creating the post
                User user = accounts.get(accountHandles.get(handle));

                // create the post for the user and add it to the collection
                OriginalMessage post = new OriginalMessage(generatePostId(), accounts.get(accountHandles.get(handle)), message);
                messages.put(post.getUniqueId(), post);

                // add post to the user
                user.addMessage(post);

                return post.getUniqueId();
            } else {

                // invalid message
                throw new InvalidPostException("this message is invalid");
            }
        } else {

            // account handle not recognized
            throw new HandleNotRecognisedException(String.format("handle: %s not recognized", handle));
        }
    }

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

        // check if the handle exists
        if (accountHandles.containsKey(handle)) {
            if (messages.containsKey(id)) {

                // endorse the message
                OriginalMessage post = messages.get(id);
                Endorsement endorsement = new Endorsement(endorsements.size() + 1, accounts.get(accountHandles.get(handle)));
                post.addEndorsement(endorsement);

                return endorsement.getUniqueId();
            } else if (comments.containsKey(id)) {

                // endorse the comment
                Comment post = comments.get(id);
                Endorsement endorsement = new Endorsement(endorsements.size() + 1, accounts.get(accountHandles.get(handle)));
                post.addEndorsement(endorsement);

                return endorsement.getUniqueId();
            } else if (endorsements.containsKey(id)) {

                // post is an endorsement and cannot be endorsed
                throw new NotActionablePostException("this post cannot be endorsed");
            } else {

                // id does not exist
                throw new PostIDNotRecognisedException(String.format("a post with the id (%d) cannot be found", id));
            }
        } else {

            // throw exception as handle does not exist
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

        // check if the user exists
        if (accountHandles.containsKey(handle)) {

            // check if a post with the id exists
            if (messages.containsKey(id) || comments.containsKey(id) || endorsements.containsKey(id)) {

                // check if the post is an endorsement
                if (!endorsements.containsKey(id)) {

                    // get the user that commented
                    User user = accounts.get(accountHandles.get(handle));

                    // get the post to be commented
                    Post commentedPost = messages.containsKey(id) ? messages.get(id) : comments.get(id);

                    // generate a unique id
                    int commentId = generatePostId();

                    // create a new comment
                    Comment comment = new Comment(commentId, commentedPost, message, user);

                    // add comment to all comments on platform
                    comments.put(commentId, comment);

                    // add comment to the posts comments
                    commentedPost.addComment(comment);

                    // add comment to the users comments
                    user.addComment(comment);

                    return commentId;
                } else {

                    // cannot comment an endorsement
                    throw new NotActionablePostException("the post is an endorsement and cannot be commented");
                }
            } else {

                // cannot find the post id in all posts
                throw new PostIDNotRecognisedException("the post id does not exist");
            }
        } else {

            // cannot find the user with handle
            throw new HandleNotRecognisedException(String.format("the handle: %s does not exist", handle));
        }
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {

        // check if the post exists first
        if (messages.containsKey(id) || comments.containsKey(id) || endorsements.containsKey(id)) {

            // get the post
            Post post = messages.containsKey(id) ? (messages.get(id)) : (comments.containsKey(id) ?  comments.get(id) : endorsements.get(id));

            // remove posts endorsements
            for (Endorsement endorsement : post.getEndorsements().values()) {
                if (endorsements.containsKey(endorsement.getUniqueId())) {

                    // remove it from the endorsements collection
                    endorsements.remove(endorsement.getUniqueId());
                } else {

                    // not in array so no harm no foul
                    throw new PostIDNotRecognisedException("attempted to remove endorsement that hasnt been registered");
                }
            }

            // remove associations of the posts comments
            for (Comment comment : post.getComments().values()) {

                // check if the post exists
                if (comments.containsKey(comment.getUniqueId())) {

                    // remove the association but keep the post
                    comment.setReference(null);
                } else {

                    // the post is not in the program
                    throw new PostIDNotRecognisedException("this comment is not registered");
                }
            }

            // finally remove post, problem is i dont know which hashmap the post is inside
            Post post1 = messages.containsKey(post.getUniqueId()) ? messages.remove(post.getUniqueId()) : (comments.containsKey(post.getUniqueId()) ? comments.remove(post.getUniqueId()) : endorsements.remove(post.getUniqueId()));

        } else {

            // error, post id does not exist
            throw new PostIDNotRecognisedException("invalid post id, it doesnt exist");
        }
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {

        // check if post exists
        if (messages.containsKey(id) || comments.containsKey(id) || endorsements.containsKey(id)) {

            // get the post
            Post post = messages.containsKey(id) ? (messages.get(id)) : (comments.containsKey(id) ? comments.get(id) : endorsements.get(id));

            // create block of text
            String data = " * ID: %d\n"
                    + " * Account: %s\n"
                    + " * No. endorsements: %d | No. comments: %d\n"
                    + " * %s";

            // return this string formatted
            return String.format(data, id, post.getAuthor().getHandle(), post.getEndorsements().size(), post.getComments().size(), post.getMessage());
        } else {

            // post with id currently does not exist
            throw new PostIDNotRecognisedException("the post does not exist");
        }
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    @Override
    public int getTotalOriginalPosts() {
        return messages.size();
    }

    @Override
    public int getTotalEndorsmentPosts() {
        return endorsements.size();
    }

    @Override
    public int getTotalCommentPosts() {
        return comments.size();
    }

    @Override
    public int getMostEndorsedPost() {
        Map<Integer, Integer> endorsedPosts = new HashMap<Integer, Integer>();

        for (OriginalMessage post : messages.values()) {

            // check how many endorsements it has
            int size = post.getEndorsements().size();

            // add to the hashmap
            endorsedPosts.put(size, post.getUniqueId());
        }

        return endorsedPosts.get(Collections.max(endorsedPosts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
    }

    @Override
    public int getMostEndorsedAccount() {
        int max = 0;

        // for all users check how many endorsements it has
        for (User user : accounts.values()) {

            // check if the number of endorsements on this account
            if (user.getEndorsements().size() > max) {
                max = user.getEndorsements().size();
            }
        }

        return max;
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

    public int generatePostId() {
        return messages.size() + comments.size() + endorsements.size() + 1;
    }

}
