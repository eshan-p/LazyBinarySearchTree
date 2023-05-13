Name:	   Eshan Patel
Class:   CS 3345

Project 2: Binary Search Tree with Lazy Deletion

Files to be used:
* MainLazyBST.java
* LazyBinarySearchTree.java

IDE used to create project: IntelliJ IDEA Ultimate (Version 2023.1)
Java version/SDK:	          13 Azul Zulu (Version 13.0.14)

The task of this project is to implement in Java a binary search tree with lazy deletion.

My project was able to function as intended by the structure and behavior listed in the prompt. In my main class (MainLazyBST.java), I was also able to run the program using two command line arguments by editing the run configurations in my IDE and entering two strings into the ‘CLI arguments’ text field: the absolute path for the input file followed by the absolute path for the output file (both with surrounding double quotes).

Sample commands used to run MainLazyBST.java:
Insert:98 
Insert:67 
Insert:55 
Insert:45 
PrintTree 
Delete:84 
Delete:45 
Contains:45 
FindMin 
FindMax 
PrintTree 
Height 
Size 
Insert:84 
Insert:32 
Insert:132 
PrintTree 
Insert:980 
Insert 
hih

Corresponding file output following input of sample commands:
true
true
true
true
98 67 55 45 
false
true
false
55
98
98 67 55 *45 
3
4
true
true
Error in insert: IllegalArgumentException raised
98 67 55 *45 32 84 
Error in insert: IllegalArgumentException raised
Error in line: Insert
Error in line: hih
