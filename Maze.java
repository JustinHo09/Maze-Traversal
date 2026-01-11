import java.util.*;
import java.io.*;

/**
 * Pledge: I have neither given nor received unauthorized aid on this program.
 *
 * Description: Maze represents a maze of characters, which are '.', 'o', '#', and '*'.
 * The goal is to get from the rat's location, indicated by R, to the cheese's
 * location, indicated by C, by following a path of .'s. Other characters
 * are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH. The characters '*' indicates tried, while the
 * characters 'o' indicates a breadcrumb which means it is a part of the path.
 * The maze created is a 2D array of Characters. This class takes in a String input, which should
 * be the file name, and then reads the file to convert it into a maze by storing the values in
 * a 2D array.
 *
 *
 * @author Java Foundations, Justin Ho
 *
 * 03/07/2025
 *
 * CSC 1120
 *
 * Input: This class does not take in any direct user input, but the constructor for this class
 * needs a string, which should be the file name, as the input. The input for this class comes from
 * the MazeSolver and MazeTester classes.
 *
 * Output: This class outputs the string representation of the maze, and returns important
 * information about position, and other details, like number of crumbs.
 *
 * @version 5.0
 */
public class Maze
{
    private static final char TRIED = '*';
    private static final char PATH = 'o';

    private int numberRows, numberColumns;
    private Character[][] grid;


    /**
     * Constructor for the Maze class. Loads a maze from the given file.
     * Throws a FileNotFoundException if the given file is not found.
     *
     * @param filename the name of the file to load
     * @throws FileNotFoundException if the given file is not found
     */
    public Maze(String filename) throws FileNotFoundException
    {
        /*
         * This initializes a vector and a scanner, which is used to read the file.
         * then it scans through the entire file and adds each line to the vector.
         */
        Vector<String> lines= new Vector<>();
        Scanner scan1 = new Scanner(new File(filename));
        while(scan1.hasNextLine()){
            lines.add(scan1.nextLine());
        }

        /*This sets the dimensions of the maze. Since each element of the vector is a line from the
         * maze file, then the vector size is the number of rows. The number of columns is how many
         * characters there are in the row.
         */
        numberRows= lines.size();
        numberColumns=lines.getFirst().length();
        grid= new Character[numberRows][numberColumns];

        /*
         * This goes through each value of the array and inserts it into the grid array.
         */
        for(int i=0; i<numberRows;i++){
            for(int j=0;j<numberColumns;j++){
                grid[i][j]=(lines.get(i).charAt(j));
            }
        }
    }

    /**
     * Marks the specified position in the maze as TRIED, which is indicated by '*'.
     *
     * @param row the index of the row to try
     * @param col the index of the column to try
     */
    public void tryPosition(int row, int col)
    {
        if(grid[row][col].equals('.')) {
            grid[row][col] = TRIED;
        }
    }

    /**
     * This method returns the amount of crumbs that are in the maze by going through the
     * maze and increasing the crumb count each time it encounters a crumb.
     * @return an integer that represents how many crumbs are in the maze.
     */
    public int numCrumb(){
        int crumb=0;
        for(int i=0; i<numberRows;i++){
            for(int j=0; j<numberColumns;j++){
                if(grid[i][j].equals('o')){
                    crumb++;
                }
            }
        }
        return crumb;
    }

    /**
     * Marks a given position in the maze as part of the PATH and changes it to 'o' when the
     * maze is solved.
     *
     * @param row the index of the row to mark as part of the PATH
     * @param col the index of the column to mark as part of the PATH
     */
    public void markPath(int row, int col)
    {
        if(!grid[row][col].equals('C')) {
            grid[row][col] = PATH;
        }
    }
    /**
     * Determines if a specific location is valid. A valid location
     * is one that is on the grid, is not blocked, and has not been TRIED.
     *
     * @param row the row to be checked
     * @param column the column to be checked
     * @return true if the location is valid
     */
    public boolean validPosition(int row, int column)
    {
        boolean result = false;

        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < grid.length &&
                column >= 0 && column < grid[row].length)

            //  check if cell is not blocked and not previously tried
            if(grid[row][column].equals('R')){
                result=true;
            }else if(grid[row][column].equals('C')){
                result=true;
            }else if (grid[row][column].equals('.')) {
                result = true;
            }

        return result;
    }

    /**
     * This method find the rat's starting row.
     * @return an integer that indicates the rat's starting row and null if the row the rat is in
     * is not found.
     */
    public Integer findStartRow(){
        for(int i=0; i<numberRows-1;i++){
            for(int j=0; j<numberColumns-1; j++){
                if(grid[i][j].equals('R')){
                    return i;
                }
            }
        }
        return null;
    }

    /**
     * This method returns the column the rat is at initially.
     * @return an integer which is the column number the rat is at and null, if the rat is
     * not found in any column.
     */
    public Integer findStartColumn(){
        /*
         * Since R indicates the rat's starting point, this goes through the maze and stops
         * and returns the column where the rat is.
         */
        for(int i=0; i<numberRows-1;i++){
            for(int j=0; j<numberColumns-1; j++){
                if(grid[i][j].equals('R')){
                    return j;
                }
            }
        }
        return null;
    }

    /**
     * This method gets the value at that location, using row and column, in the maze.
     * @param row the row of the value to be retrieved
     * @param column the column of the value to be retrieved.
     * @return the value of the entered location of the maze.
     */
    public Character getValue(int row, int column){
        return grid[row][column];
    }

    /**
     * Returns the maze as a string by going through the 2D array.
     *
     * @return a string representation of the maze
     */
    public String toString()
    {
        String result = "\n";
        /*
        This goes through each value of the maze one by one and puts the characters together and
        separates each character by a space. At the end of every row, \n starts a new line in
        the string.
         */
        for (int row=0; row < grid.length; row++) {
            for (int column=0; column < grid[row].length; column++)
                result += grid[row][column] + "";
            result += "\n";
        }
        return result;
    }

    /**
     * This method prints the maze using the toString method's representation of it.
     */
    public void printMaze(){
        System.out.println(toString());
    }
}

