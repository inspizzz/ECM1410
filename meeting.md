## code references
- commit version
```  
git checkout 8a2606dff3ae77f29f0639443a98af407d3bd086  
```  
- [NoAccountsRegisteredException](src/socialmedia/NoAccountsRegisteredException.java)
- [NoPostsRegisteredException](src/socialmedia/NoPostsRegisteredException.java)

## reason
- [getMostEndorsedPost:493](src/socialmedia/SocialMedia.java)
- [getMostEndorsedAccount:523](src/socialmedia/SocialMedia.java)
- when calling these functions if there are no posts or accounts respectively then the  
  client is returned with -1 in the real world the front end would automatically request  
  to get that user however there is no user with id -1 meaning that it would throw an error,  
  I specifically did this to ensure this situation would not happen. or the program will try  
  to convert the account id -1 into an account handle which also does not exist, either way  
  an error is thrown later down the line.

## running
- compiled version of succesfull submission located in documents
```
java -cp .:socialmedia.jar SocialMediaPlatformTestApp.java
```
- shows all tests work flawlessly on my machine
```
(base) wiktor@wiktor-XPS-13-7390:~/Documents$ java -cp .:socialmedia.jar SocialMediaPlatformTestApp.java
The system compiled and started the execution...
[SYSTEM] -> Creating some accounts
[SYSTEM] -> Creating some accounts PASSED


[SYSTEM] -> Removing some accounts
[SYSTEM] -> Removing some accounts PASSED


[SYSTEM] -> Changing account handle
 * ID: 5 
 * Handle: user5 
 * Description: default account description 
 * Post count: 0 
 * Endorse count: 0
 * ID: 5 
 * Handle: user6 
 * Description: default account description 
 * Post count: 0 
 * Endorse count: 0
[SYSTEM] -> Changing account handle PASSED


[SYSTEM] -> Changing account description
 * ID: 5 
 * Handle: user6 
 * Description: default account description 
 * Post count: 0 
 * Endorse count: 0
 * ID: 5 
 * Handle: user6 
 * Description: new description 
 * Post count: 0 
 * Endorse count: 0
[SYSTEM] -> Changing account description PASSED


[SYSTEM] -> Creating some posts
creating post with id: 1
creating post with id: 2
creating post with id: 3
creating post with id: 4
creating post with id: 5
 * ID: 1
 * Account: user1
 * No. endorsements: 0 | No. comments: 0
 * this is post 1's message

 * ID: 2
 * Account: user1
 * No. endorsements: 0 | No. comments: 0
 * this is post 2's message

 * ID: 3
 * Account: user1
 * No. endorsements: 0 | No. comments: 0
 * this is post 3's message

 * ID: 4
 * Account: user2
 * No. endorsements: 0 | No. comments: 0
 * this is post 4's message

 * ID: 5
 * Account: user2
 * No. endorsements: 0 | No. comments: 0
 * this is post 5's message
[SYSTEM] -> Creating some posts PASSED


[SYSTEM] -> Creating some comments
creating post with id: 6
creating post with id: 7
creating post with id: 8
creating post with id: 9
creating post with id: 10
 * ID: 6
 * Account: user1
 * No. endorsements: 0 | No. comments: 1
 * comment1 of depth 1

 * ID: 7
 * Account: user1
 * No. endorsements: 0 | No. comments: 2
 * comment2 of depth 1

 * ID: 8
 * Account: user1
 * No. endorsements: 0 | No. comments: 0
 * comment3 of depth 2

 * ID: 9
 * Account: user1
 * No. endorsements: 0 | No. comments: 0
 * comment4 of depth 2

 * ID: 10
 * Account: user1
 * No. endorsements: 0 | No. comments: 0
 * comment5 of depth 2
[SYSTEM] -> Creating some comments PASSED


[SYSTEM] -> Endorsing some posts
creating post with id: 11
creating post with id: 12
creating post with id: 13
creating post with id: 14
creating post with id: 15
creating post with id: 16
creating post with id: 17
creating post with id: 18
creating post with id: 19
creating post with id: 20
creating post with id: 21
 * ID: 1
 * Account: user1
 * No. endorsements: 3 | No. comments: 2
 * this is post 1's message

 * ID: 2
 * Account: user1
 * No. endorsements: 1 | No. comments: 0
 * this is post 2's message

 * ID: 3
 * Account: user1
 * No. endorsements: 3 | No. comments: 0
 * this is post 3's message

 * ID: 4
 * Account: user2
 * No. endorsements: 1 | No. comments: 0
 * this is post 4's message

 * ID: 5
 * Account: user2
 * No. endorsements: 3 | No. comments: 0
 * this is post 5's message
[SYSTEM] -> Endorsing some posts PASSED


[SYSTEM] -> Showing all comments
-> ID: 1
-> Account: user1
-> No. endorsements: 3 | No. comments: 2
-> this is post 1's message

-> ID: 1
-> Account: user1
-> No. endorsements: 3 | No. comments: 2
-> this is post 1's message
	-> ID: 6
	-> Account: user1
	-> No. endorsements: 0 | No. comments: 1
	-> comment1 of depth 1
		-> ID: 8
		-> Account: user1
		-> No. endorsements: 0 | No. comments: 0
		-> comment3 of depth 2
	-> ID: 7
	-> Account: user1
	-> No. endorsements: 0 | No. comments: 2
	-> comment2 of depth 1
		-> ID: 9
		-> Account: user1
		-> No. endorsements: 0 | No. comments: 0
		-> comment4 of depth 2
		-> ID: 10
		-> Account: user1
		-> No. endorsements: 0 | No. comments: 0
		-> comment5 of depth 2
[SYSTEM] -> Showing all comments PASSED


[SYSTEM] -> Deleting some posts
[SYSTEM] -> Deleting some posts PASSED


[SYSTEM] -> Saving the platform
[SYSTEM] -> Saving the platform PASSED


[SYSTEM] -> Erasing the platform
[SYSTEM] -> Erasing the platform PASSED


[SYSTEM] -> Loading the platform
3
3
[SYSTEM] -> Loading the platform PASSED


[SYSTEM] -> Getting most endorsed post
the most endorsed post is the post with id 1
 * ID: 1
 * Account: user1
 * No. endorsements: 3 | No. comments: 2
 * this is post 1's message
[SYSTEM] -> Getting most endorsed post Passed


[SYSTEM] -> Getting most endorsed post
the most endorsed post is the account with id 1
[SYSTEM] -> Getting most endorsed post Passed
```