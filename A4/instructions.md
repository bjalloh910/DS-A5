# Assignment 4:  Recursive Maze Solving

For this assignment, you will write a program for solving mazes using **recursion**. The "big idea" is as follows: each time you explore a location in the maze will correspond to a recursive call, so that when you find the exit, the path taken there from the start will be stored _implicitly on the call stack_. 

This assignment pulls together many pieces that we have learned separately into a closely interacting whole. We have also provided some support code to drive the graphics, described in greater detail below.

## Support code

Mazes are best enjoyed visually, and thus we have provided a `MazeViewer` class that will display your maze in a window.  Your own code will interact with it via the `DisplayableMaze` interface (meaning you will write a class that implements `DisplayableMaze`).  Next, you will create an instance of your class and pass it to the `MazeViewer` constructor.  The viewer will take it from there, drawing an image of your maze in a newly opened window.  If you complete all phases of the assignment, it will animate the solution of your maze.

## Phase One: Basic Maze Class

Your first goal should be to get a working `Maze` class that can compile and be displayed.  It will implement the `DisplayableMaze` interface, so start by copying all those method call signatures and begin to fill them out.  For this phase, your maze grid storage could be hard-coded in the class itself, or it could be passed in to a constructor from `Main`.  Store the maze contents as a **2D array** (an array of arrays) of type `MazeContents`.  (_Using nested `ArrayList`s is also an option but since we don't expect the mazes to resize, it's not really necessary and the bare array has less overhead._)

Once you have filled out the maze grid, the other methods should be pretty straightforward.  Try to write them in a **general way** so that later on when you create maze grids with different contents, everything will still work.  (Eventually you will want to read mazes in from a file, even if for now they are fixed in the code.)  You're done with this phase when your program compiles and you see your maze displayed in the graphics window.

## Phase Two: Recursive Exploration

For phase two you will implement a recursive "solver" for the maze.  Here are details of the process:

**Problem Statement**: 
Given a location and a map (showing walls, open regions, and areas that have been already explored), determine whether the exit can be reached by moving through adjacent open squares without passing through a wall or previously visited square.

**Starting Condition**:
To begin searching for a solution, the map of the maze should show every open square as unexplored, and the location will be the starting point.

**Stop Criterion**:
There are two possibilities, one for success, and one for failure.
* If the current location is the **finish** of the maze then report success (return `true`).  Also mark the current square as part of the path before returning.
* If the current location is **already visited or a wall** then just return `false` (indicating this is not the correct path to the exit).

**Simplification Step**:
Mark the current square as visited, thus simplifying the problem by reducing the number of "open" squares we still need to search. The exit is reachable from the current location if and only if it is reachable through the square adjacent to the north, or the south, or the east, or the west. Practically speaking, this will take up to four recursive calls to determine, combining the results using a `||` operator (logical or). Because the `||` operator uses "short-circuiting" logic, if any one of these calls returns `true` the remainder will not be evaluated.  Just before returning, you can mark the current square as either on the path or dead end, depending on whether the result you've computed is `true` or `false`.

If you include a short delay in your program at the start of each recursive call, you will see the recursion animated by the viewer.  Here is code that will delay for 50 milliseconds:

    try { Thread.sleep(50);	} catch (InterruptedException e) {};

**Note**: Before turning in your assignment, please set the delay to **less than 10 milliseconds**, to facilitate faster grading.

When the recursive solver finishes, the program should print a message indicating whether or not a solution was found.

## Phase Three: Arbitrary Maze Files

Solving the same maze over and over again isn't all that interesting.  In this phase, you will make your program read maze files similar to the two examples included here, named `maze1` and `maze2`.  The easiest way to do this is via a command line instruction, and the discussion below will assume you are runing from the command line.  Most development environments provide ways to specify command line arguments and redirected input, so if you're determined to start within an IDE you can explore those options on your own.

For full credit, your program should read the maze in either of two ways :
* if a command line argument is provided, it should load the maze from the file of that name:
    `java MazeSolver maze1`
* if no command line argument is provided, it should read the maze from the standard input. (Presumably this could also be the contents of a file, via input redirection.)
    `java MazeSolver < maze1`

Both of these input sources can be converted to a `Scanner`, so you should try to read them in as much as possible using the same code.

Your program should be able to read maps in the following format, without prompting. A valid input file will contain a character grid representing the maze. In this grid, a `#` represents a wall, and a `.` or ' ' (a space character) represents a passageway.  The symbols `S` and `F`, respectively, represent the start and finish of the maze, and should appear exactly once. Here is an example of a simple 6x9 maze file:

    #########
    #S..#...#
    #.#.#.#.#
    #.###.#.#
    #.....#F#
    #########

_Note: although this example shows walls completely surrounding the outer perimeter, your program should not assume this will always be the case. Don't wander off the edge of the maze! One way to accomplish this is to make sure that `getContents` returns a wall for any coordinates outside the maze boundary._

### Starter Files
Besides `MazeViewer` and `DisplayableMaze`, the other files provided define enumerated types that may be useful to you. You may feel free to modify anything in these files as you wish; you will submit all files (modified or not) with your assignment so that we can compile your work.