# Library
A Java program that stores library references (as entered by a user) and allows user to search the library. 


This is program that creates library acquisitions based on user input and then allows the user 
to search using a key phrase/year/call number
The program implements a GUI

Assumptions
- user will know the data for all the acquisitions
- a file by the name of "output.txt" is present in the project folder
 

Limitations
- no known limitations

User Guide
- more methods can be added to all classes to further implement all the functions of a library catalogue (Book, Journal, LibrarySearch)
- user can build a library with as many acquisitions as need be


TEST PLAN

Text Fields
- title/keyword is not case sensitive (i.e. JAVA = JaVA = java etc.)
- does not crash when an incorrect value is entered (lets user know what they did wrong)

Adding references
- does not crash when an incorrect entry is made

Search
- accounts for missing input
	i.e. if the user wants to search by only journal and does not care what the call 
	number/ year/ title is
	or if the user wants to search only by a title/keyword
- does not crash when no reference is found to match that search (lets user know)

