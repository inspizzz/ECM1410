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

		Integer id;
		try {

			// create a user
			id = platform.createAccount("my_handle", "this is the description");
			System.out.println(platform.showAccount("my_handle"));

			// check the user was created
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			// add the user with a post
			int postid = platform.createPost("my_handle", "here is this posts message");
			System.out.println(platform.showIndividualPost(postid));

			// add a comment to this post
			int commentid = platform.commentPost("my_handle", postid, "this is a comment");
			System.out.println(platform.showIndividualPost(commentid));

			// save the file here
//			platform.savePlatform("saved.txt");
//			platform.loadPlatform("saved.txt");

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
