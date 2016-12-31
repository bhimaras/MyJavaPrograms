Backtracking algorithms for solving a problem are ones that attempt to complete a search for a solution by constructing partial solutions. 
The algorithm then attempts to extend a partial solution toward completion, but if it gets stuck, the algorithm backs up (backtracks) by removing the most recently constructed part of the solution, and trying another possibility. 
Backtracking problems lend themselves well to recursion. 
In this program we'll use backtracking to try to find a path through a maze.

The program takes as input (from a text file) a maze with the following format:

9 10
0111111111
0110000000
0000111110
1011101010
1000001000
1011011101
1010001111
0000100000
1111111110
5 8
8 9

The first line gives the total size of the maze to come; this is a maze with 9 rows and 10 columns. 
On the following lines, a 1 in a particular position means that there's a wall at that position. A 0 in a particular position means that that position is free. 
The last two lines of the file are the maze coordinates for the entry location, followed by the maze coordinates for the exit location. (Note that these maze coordinates are 0-based, just like Java arrays.) 

Maze files are provided in the testfiles folder.

How to run the program:

javac MazeViewer.java
java MazeViewer ./testfiles/bigMaze