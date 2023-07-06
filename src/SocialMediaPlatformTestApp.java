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

			assert (platform.getNumberOfAccounts() == 5) : "[SYSTEM] -> Creating some accounts FAILED";

			System.out.println("[SYSTEM] -> Creating some accounts PASSED");


			// remove accounts
			System.out.println("\n\n[SYSTEM] -> Removing some accounts");

			platform.removeAccount("user3");
			platform.removeAccount(user4);

			assert (platform.getNumberOfAccounts() == 3) : "[SYSTEM] -> Removing some accounts FAILED";

			System.out.println("[SYSTEM] -> Removing some accounts PASSED");


			// changing account handle
			System.out.println("\n\n[SYSTEM] -> Changing account handle");

			String account = platform.showAccount("user5");
			System.out.println(account);

			platform.changeAccountHandle("user5", "user6");

			String new_account = platform.showAccount("user6");
			System.out.println(new_account);

			assert (new_account.contains("user6")) : "[SYSTEM] -> Changing account handle FAILED";

			System.out.println("[SYSTEM] -> Changing account handle PASSED");


			//changing account description
			System.out.println("\n\n[SYSTEM] -> Changing account description");

			String account2 = platform.showAccount("user6");
			System.out.println(account2);

			platform.updateAccountDescription("user6", "new description");

			String new_account2 = platform.showAccount("user6");
			System.out.println(new_account2);

			assert (new_account2.contains("new description")) : "[SYSTEM] -> Changing account description FAILED";
			System.out.println("[SYSTEM] -> Changing account description PASSED");


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

			assert (platform.getTotalOriginalPosts() == 5) : "\n[SYSTEM] -> Creating some posts FAILED";

			System.out.println("[SYSTEM] -> Creating some posts PASSED");


			// add comments to users posts
			System.out.println("\n\n[SYSTEM] -> Creating some comments");
			int comment1User1 = platform.commentPost("user1", post1, "comment1 of depth 1");
			int comment2tUser1 = platform.commentPost("user1", post1, "comment2 of depth 1");
			int comment3tUser1 = platform.commentPost("user1", comment1User1, "comment3 of depth 2");
			int comment4tUser1 = platform.commentPost("user1", comment2tUser1, "comment4 of depth 2");
			int comment5tUser1 = platform.commentPost("user1", comment2tUser1, "comment5 of depth 2");

			System.out.println(platform.showIndividualPost(comment1User1));
			System.out.println("\n" + platform.showIndividualPost(comment2tUser1));
			System.out.println("\n" + platform.showIndividualPost(comment3tUser1));
			System.out.println("\n" + platform.showIndividualPost(comment4tUser1));
			System.out.println("\n" + platform.showIndividualPost(comment5tUser1));

			assert (platform.getTotalCommentPosts() == 5) : "[SYSTEM] -> Creating some comments FAILED";

			System.out.println("[SYSTEM] -> Creating some comments PASSED");


			// endorse posts
			System.out.println("\n\n[SYSTEM] -> Endorsing some posts");
			platform.endorsePost("user1", post1);
			platform.endorsePost("user1", post2);
			platform.endorsePost("user1", post3);
			platform.endorsePost("user1", post4);
			platform.endorsePost("user1", post5);

			platform.endorsePost("user2", post1);
			platform.endorsePost("user2", post3);
			platform.endorsePost("user2", post5);

			platform.endorsePost("user6", post1);
			platform.endorsePost("user6", post3);
			platform.endorsePost("user6", post5);

			System.out.println(platform.showIndividualPost(post1));
			System.out.println("\n" + platform.showIndividualPost(post2));
			System.out.println("\n" + platform.showIndividualPost(post3));
			System.out.println("\n" + platform.showIndividualPost(post4));
			System.out.println("\n" + platform.showIndividualPost(post5));

			assert (platform.getTotalEndorsmentPosts() == 11) : "\n[SYSTEM] -> Endorsing some posts FAILED";
			System.out.println("[SYSTEM] -> Endorsing some posts PASSED");


			// show all posts
			System.out.println("\n\n[SYSTEM] -> Showing all comments");

			StringBuilder result = platform.showPostChildrenDetails(1);
			System.out.print(result);

			System.out.println("[SYSTEM] -> Showing all comments PASSED");


			// deleting some posts
			System.out.println("\n\n[SYSTEM] -> Deleting some posts");

			platform.deletePost(post4);
			platform.deletePost(post5);

			platform.deletePost(comment4tUser1);
			platform.deletePost(comment5tUser1);

			assert(platform.getTotalOriginalPosts() == 3) : "\n[SYSTEM] -> Deleting some posts FAILED";
			assert(platform.getTotalCommentPosts() == 3) : "\n[SYSTEM] -> Deleting comments posts FAILED";

			System.out.println("[SYSTEM] -> Deleting some posts PASSED");


			// saving the platform
			System.out.println("\n\n[SYSTEM] -> Saving the platform");
			platform.savePlatform("saved");
			System.out.println("[SYSTEM] -> Saving the platform PASSED");


			// erasing the platform
			System.out.println("\n\n[SYSTEM] -> Erasing the platform");

			platform.erasePlatform();

			assert (platform.getNumberOfAccounts() == 0) : "[SYSTEM] -> Erasing the platform FAILED";
			assert (platform.getTotalOriginalPosts() == 0) : "[SYSTEM] -> Erasing the platform FAILED";
			assert (platform.getTotalCommentPosts() == 0) : "[SYSTEM] -> Erasing the platform FAILED";
			assert (platform.getTotalEndorsmentPosts() == 0) : "[SYSTEM] -> Erasing the platform FAILED";

			System.out.println("[SYSTEM] -> Erasing the platform PASSED");


			// loading the platform
			System.out.println("\n\n[SYSTEM] -> Loading the platform");

			platform.loadPlatform("saved");

			System.out.println(platform.getNumberOfAccounts());
			System.out.println(platform.getTotalOriginalPosts());

			assert (platform.getNumberOfAccounts() == 3) : "[SYSTEM] -> Loading the platform FAILED";
			assert (platform.getTotalOriginalPosts() == 3) : "[SYSTEM] -> Loading the platform FAILED";
			assert (platform.getTotalCommentPosts() == 3) : "[SYSTEM] -> Loading the platform FAILED";
			assert (platform.getTotalEndorsmentPosts() == 11) : "[SYSTEM] -> Loading the platform FAILED";

			System.out.println("[SYSTEM] -> Loading the platform PASSED");


			// get most endorsed post
			System.out.println("\n\n[SYSTEM] -> Getting most endorsed post");

			int id = platform.getMostEndorsedPost();

			System.out.println("the most endorsed post is the post with id " + id);
			System.out.println(platform.showIndividualPost(id));

			System.out.println("[SYSTEM] -> Getting most endorsed post Passed");


			// get most endorsed post
			System.out.println("\n\n[SYSTEM] -> Getting most endorsed post");

			int accountId = platform.getMostEndorsedAccount();

			System.out.println("the most endorsed post is the account with id " + accountId);

			System.out.println("[SYSTEM] -> Getting most endorsed post Passed");


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
				 NotActionablePostException | IOException | ClassNotFoundException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}


	}

}
