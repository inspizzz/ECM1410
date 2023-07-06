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