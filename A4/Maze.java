import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Maze implements DisplayableMaze{

    private int height;
    private int width;
    private MazeLocation start;
    private MazeLocation end;
    private MazeContents [][] displayMaze;
    private MazeLocation myPos;

//Maze constructor 
    public Maze(){ //opening the file
        String[] choice = {"maze1", "maze2"}; //option maze
        Scanner filename = null;
        ArrayList<String> contents = new ArrayList<String>(); //storing that option into an arraylist so the computer understand what to do with it

        try { // doesn't know what the file is
            filename = new Scanner( new File(choice[1]));
        } catch(FileNotFoundException x) {
            System.err.println("File not found");
            System.exit(-1); 
        }
        while(filename.hasNextLine()){ // reading each line in the file
            String line = filename.nextLine();
            contents.add(line); //it's adding that info into the arraylist
        }
        this.height = contents.size();  //allowing the computer to figure what the height id
        this.width = contents.get(0).length(); //same for the width
    

        this.displayMaze = new MazeContents[this.height][this.width]; // it's creating the 2d array 

        for(int i=0; i<contents.size(); i++){
            String[] split = contents.get(i).split("");
            for(int j=0; j<split.length; j++){
                if(split[j].equals("#")){
                    displayMaze[i][j] = MazeContents.WALL;
                } else if(split[j].equals(".")){
                    displayMaze[i][j] = MazeContents.OPEN;
                } else if(split[j].equals("S")){
                   displayMaze[i][j] = MazeContents.OPEN;
                   this.start = new MazeLocation(i, j);
                } else if(split[j].equals("F")){
                   displayMaze[i][j] = MazeContents.OPEN;
                   this.end = new MazeLocation(i, j);
                }
            }
        }
        
    }
    
    /** @return height of maze grid */
    public int getHeight(){
        return this.height;
    }

    /** @return width of maze grid */
    public int getWidth(){
        return this.width;
    }

    /** @return contents of maze grid at row i, column j */
    public MazeContents getContents(int i, int j){
       return this.displayMaze[i][j];
    }

    /** @return location of maze start point */
    public MazeLocation getStart(){
        return this.start;
    }

    /** @return location of maze finish point */
    public MazeLocation getFinish(){
        return this.end;
    } 

    public boolean solveMaze(MazeContents[][] displayMaze, int pos){ //This is a recursive method that takes in the current position as a parameter make recursive calls to explore neighboring positions until it finds the exit.
     
        int numRows = displayMaze.length;    // Number of rows in the maze
        int numCols = displayMaze[0].length; // Number of columns in the maze
        
        int row = pos / numCols;  // Calculate row based on the number of columns
        int col = pos % numCols;  // Calculate col based on the remainder

         try { Thread.sleep(50);	} 
         catch (InterruptedException e) {};
       
        if( row == end.getRow() && col == end.getCol()){ // If you're at end report true because it's a success
            displayMaze[row][col]  = MazeContents.PATH;
            return true;    
        }
       
        if( displayMaze[row][col] == MazeContents.WALL ||  displayMaze[row][col]  == MazeContents.VISITED){ // if current location is a WALL or had already been VISITED 
            return false; //return false because this is a mistake
        }
        
       
        // moving one square up (North)  //moving one square down (South) // moving one to the left // moving one to the right )
        if( solveMaze(displayMaze, pos - numCols) || solveMaze(displayMaze, pos + numRows) || solveMaze(displayMaze, pos - 1 ) || solveMaze(displayMaze, pos + 1)){
            displayMaze[row][col] = MazeContents.PATH;
            return true;
        }
        return true;
   } 


   /* 
   public boolean solveMaze(MazeContents[][] displayMaze, int pos){ //This is a recursive method that takes in the current position as a parameter make recursive calls to explore neighboring positions until it finds the exit.
        int numRows = displayMaze.length;    // Number of rows in the maze
        int numCols = displayMaze[0].length; // Number of columns in the maze
        
        int row = pos / numCols;  // Calculate row based on the number of columns
        int col = pos % numCols;  // Calculate col based on the remainder

        if( displayMaze[row][col] == MazeContents.WALL || displayMaze[row][col] == MazeContents.VISITED){
            return false; //report failure
        } 

        if(row == end.getRow() && col == end.getCol()){
            return true; // report success
        } else {
            displayMaze[row][col] = MazeContents.VISITED;
        }
        
       

        if( row == end.getRow() && col == end.getCol()){ // If you're at end report true because it's a success
            return true;  
        } else {
            displayMaze[row][col] = MazeContents.VISITED;
        }
       
        if( displayMaze[row][col] == MazeContents.WALL ||  displayMaze[row][col]  == MazeContents.VISITED){ // if current location is a WALL or had already been VISITED 
            return false; //return false because this is a mistake
        }
        
        displayMaze[row][col] = MazeContents.VISITED;
        // moving one square up (North)  //moving one square down (South) // moving one to the left // moving one to the right )
        if( solveMaze(displayMaze, pos - numCols) || solveMaze(displayMaze, pos + numRows) || solveMaze(displayMaze, pos - 1 ) || solveMaze(displayMaze, pos + 1)){
            displayMaze[row][col] = MazeContents.PATH;
            return true;
        }
        return true;
   } */
   
}

