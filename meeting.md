
## key project points
[remove account function contains AccountIDNoteRecognisedException 96](src/socialmedia/SocialMedia.java) removeAccount
[remove account function contains HandleNotRecognisedException 111](src/socialmedia/SocialMedia.java) removeAccount
[the AccountIDNoteRecognisedException file](src/socialmedia/AccountIDNotRecognisedException.java) AccountIDNoteRecognisedException
[the HandleNotRecognisedException file](src/socialmedia/HandleNotRecognisedException.java) HandleNotRecognisedException

## in the case of 96 (AccountIDNoteRecognisedException)
- when function removeAccount is called with an ID that does not exist inside of the object
- line 106 get called with an id that does not exist
- line 106 throws null pointer exception that is not handled
- I've created a custom exception that handles that

## in the case of 111 (HandleNotRecognisedException)
- when function removeAccount is called with a handle that does not exist inside of the object
- line 121 get called with a handle that does not exist
- line 121 throws null pointer exception that is not handled

## to get back to updated version of the project without these changes
$ git checkout wiktors


