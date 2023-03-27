import socialmedia.*;

import java.io.IOException;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";


		try {

			// create some users
			int user1 = platform.createAccount("user1", "this is the description");
			int user2 = platform.createAccount("user2", "this is the description");
			int user3 = platform.createAccount("user3", "this is the description");
			assert (platform.getNumberOfAccounts() == 3) : "number of accounts registered in the system does not match";

			// create some posts
			int post1 = platform.createPost("user1", "here is this posts message");
			int post2 = platform.createPost("user1", "here is this posts message");
			int post3 = platform.createPost("user1", "here is this posts message");

			int post4 = platform.createPost("user2", "here is this posts message");
			int post5 = platform.createPost("user2", "here is this posts message");

			int post6 = platform.createPost("user3", "here is this posts message");

			// add comments to users posts
			int comment1User1 = platform.commentPost("user1", post1, "comment1 of depth 1");
			int commen2tUser1 = platform.commentPost("user1", post1, "comment2 of depth 1");
			int commen3tUser1 = platform.commentPost("user1", comment1User1, "comment3 of depth 2");
			int commen4tUser1 = platform.commentPost("user1", commen2tUser1, "comment4 of depth 2");
			int commen5tUser1 = platform.commentPost("user1", commen2tUser1, "comment5 of depth 2");

			assert (platform.get)


			// show all posts
			StringBuilder result = platform.showPostChildrenDetails(1);
			System.out.print(result);

			// remove account
			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (HandleNotRecognisedException | InvalidPostException | PostIDNotRecognisedException |
				 NotActionablePostException e) {
			throw new RuntimeException(e);
		}

	}

}
