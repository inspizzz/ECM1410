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
			System.out.println("[SYSTEM] -> Creating some accounts");
			int user1 = platform.createAccount("user1", "this is the description");
			int user2 = platform.createAccount("user2");
			int user3 = platform.createAccount("user3", "this is the description");
			int user4 = platform.createAccount("user4", "this is the description");
			int user5 = platform.createAccount("user5", "default account description");
			assert (platform.getNumberOfAccounts() == 5) : "number of accounts registered in the system does not match";

			// remove accounts
			System.out.println("\n\n[SYSTEM] -> Removing some accounts");
			platform.removeAccount("user3");
			platform.removeAccount(user4);
			assert (platform.getNumberOfAccounts() == 3) : "number of accounts registered in the system does not match";

			// changing account handle
			System.out.println("\n\n[SYSTEM] -> Changing account handle");
			String account = platform.showAccount("user5");
			System.out.println(account);
			platform.changeAccountHandle("user5", "user6");
			String new_account = platform.showAccount("user6");
			System.out.println(new_account);

			//changing account description
			System.out.println("\n\n[SYSTEM] -> Changing account description");
			String account2 = platform.showAccount("user6");
			System.out.println(account2);
			platform.updateAccountDescription("user6", "new description");
			String new_account2 = platform.showAccount("user6");
			System.out.println(new_account2);

			// create some posts
			System.out.println("\n\n[SYSTEM] -> Creating some posts");
			int post1 = platform.createPost("user1", "this is post 1's message");
			int post2 = platform.createPost("user1", "this is post 2's message");
			int post3 = platform.createPost("user1", "this is post 3's message");

			int post4 = platform.createPost("user2", "this is post 4's message");
			int post5 = platform.createPost("user2", "this is post 5's message");

			System.out.println(platform.showIndividualPost(post1));
			System.out.println("\n" + platform.showIndividualPost(post2));
			System.out.println("\n" + platform.showIndividualPost(post3));
			System.out.println("\n" + platform.showIndividualPost(post4));
			System.out.println("\n" + platform.showIndividualPost(post5));


			// add comments to users posts
			System.out.println("\n\n[SYSTEM] -> Creating some comments");
			int comment1User1 = platform.commentPost("user1", post1, "comment1 of depth 1");
			int commen2tUser1 = platform.commentPost("user1", post1, "comment2 of depth 1");
			int commen3tUser1 = platform.commentPost("user1", comment1User1, "comment3 of depth 2");
			int commen4tUser1 = platform.commentPost("user1", commen2tUser1, "comment4 of depth 2");
			int commen5tUser1 = platform.commentPost("user1", commen2tUser1, "comment5 of depth 2");

			System.out.println(platform.showIndividualPost(comment1User1));
			System.out.println("\n" + platform.showIndividualPost(commen2tUser1));
			System.out.println("\n" + platform.showIndividualPost(commen3tUser1));
			System.out.println("\n" + platform.showIndividualPost(commen4tUser1));
			System.out.println("\n" + platform.showIndividualPost(commen5tUser1));

			// show all posts
			System.out.println("\n\n[SYSTEM] -> Showing all comments");
			StringBuilder result = platform.showPostChildrenDetails(1);
			System.out.print(result);



		} catch (IllegalHandleException e) {
			System.out.println(e);
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			System.out.println(e);
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			System.out.println(e);
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (HandleNotRecognisedException | InvalidPostException | PostIDNotRecognisedException |
				 NotActionablePostException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}

		assert (platform.getNumberOfAccounts() == 4) : "Wrong number of accounts";
		assert (platform.getTotalOriginalPosts() == 0) : "Wrong number of total original posts";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
	}

}
