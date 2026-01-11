/**
 * Pledge: I have neither given nor received unauthorized aid on this program.
 *
 * Description: MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of .'s.
 * Constants are used to represent locations in the maze that have been TRIED,'*',
 * and that are part of the solution PATH, 'o'. This class also keeps track of how many times the
 * traverse method was successfully called.
 *
 * @author Java Foundations, Justin Ho
 *
 * 03/07/2025
 *
 * CSC 1120
 *
 * Input: This class does not take in any direct user input, but recieves input from the MazeTester
 * class. The class's constructor takes in an object of type Maze, and the traverse method takes in
 * integer inputs.
 *
 * Output: This class returns a boolean indicating if the maze has been successfuly traversed, and
 * returns the amount of times the travers method was called.
 *
 * @version 5.0
 */
public class MazeSolver
{
    private Maze maze;
    private int solveCount;

    /**
     * Constructor for the MazeSolver class.
     */
    public MazeSolver(Maze maze)
    {
        this.maze = maze;
    }

    /**
     * Attempts to recursively traverse the maze. Inserts special
     * characters indicating locations that have been TRIED and that
     * eventually become part of the solution PATH.
     *
     * @param row row index of current location
     * @param column column index of current location
     * @return true if the maze has been solved
     */
    public boolean traverse(int row, int column)
    {
        boolean done = false;
        //This checks to see if the position entered is a valid one.
        if (maze.validPosition(row, column))
        {
            //prints out the rat's current location
            System.out.println("Rat is at "+row+","+column);

            maze.tryPosition(row, column);   // mark this cell as tried

            //This will check to see if the position the rat has moved to is the cheese and if it
            //is than it will return done
            if (maze.getValue(row,column).equals('C')) {
                done = true;  // the maze is solved
            }else {
                //If the position is not the cheese then it will check if done is true, and
                //if the positions adjacent to the current position in the orders, north, south
                //east, then west. It will recall the traverse method using the position of
                //the way it went, as well as increasing the solve count.
                if(!done && maze.validPosition(row-1,column)){
                    done = traverse(row - 1, column);// up
                    solveCount++;
                }
                if (!done && maze.validPosition(row+1,column)) {
                    done = traverse(row+1, column );  // down
                    solveCount++;
                }
                if (!done && maze.validPosition(row,column+1)) {
                    done = traverse(row, column+1);  // right
                    solveCount++;
                }
                if (!done && maze.validPosition(row,column-1)) {
                    done = traverse(row, column - 1);  // left
                    solveCount++;
                }
            }
            if (done)  // this location is part of the final path
                maze.markPath(row, column);
        }
        return done;// if the maze if not possible than it will return this.
    }

    /**
     * This method returns the value of the solveCount, which is how many times the traverse
     * method was successfully called.
     * @return an integer which is the amount of times traverse was called
     */
    public int getSolveCount(){
        return solveCount;
    }
}
