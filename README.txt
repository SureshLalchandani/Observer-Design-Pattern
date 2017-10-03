# Assignment 2Õs README

Navigate to the folder of this README file:

Perform Below commands to compile/run/clean the program:


## To clean:
ant -buildfile studentCoursesBackup/src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile studentCoursesBackup/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=input.txt -Darg1=delete.txt -Darg2=Output1.txt -Darg3=Output2.txt -Darg4=Output3.txt

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: 10/03/2017] 

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

- Created Binary Search Tree to store nodes with B.Number and courses as it gives complexity of O(n) and also it is useful in printing Nodes in Ascending order of B.Number. Search in BST can be done with O(logn) in average case but worst case gives O(n) which is still better then using ArrayList.

- Used Set (HashSet) to store Courses in Node because it avoids duplication and also produces sorted order result. Everything is done with constant complexity i.e O(1).

- Used ArrayList for storing Observers in SubjectI implementation. ArrayList gives optimum performance when data structure needs to be mutable. Also it performs Insertion/Deletion in O(n) complexity.


-----------------------------------------------------------------------
