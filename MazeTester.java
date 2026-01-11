import java.util.*;
import java.io.*;

/**
 * Pledge: I have neither given nor received unauthorized aid on this program.
 *
 * Description: MazeTester uses recursion to determine if a maze can be traversed. This class
 * makes use of the maze class to create the Maze, and then uses the MazeSolver class
 * to solve the created maze.
 *
 * @author Java Foundations, Justin Ho
 *
 * 03/07/2025
 *
 * CSC 1120
 *
 * Input: This class takes in user input, which should be the file name that contains the maze
 * that they want to solve.
 *
 * Output: This returns outputs that promt the user what to do, and then prints the maze, the rat's
 * location, the final maze. If it succesfully traversed than it will also print the
 * amount of times traverses was called and how many breadcrumbs there are. If it is not successful
 * than it will print out a message indicating that
 *
 * @version 5.0
 */
public class MazeTester
{
    /**
     * Creates a new maze by taking in user input to search for a maze file,
     * then it prints its original form. Then it attempts to
     * solve it, and prints out its final form, whether it is solvable or not.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the name of the file containing the maze: ");
        String filename =null; //scan.nextLine();
        boolean valid=false;
        //This checks to see if the filename is valid and will loop until a valid one is entered.
        while(!valid){
            filename= scan.nextLine();
            if(exists(filename)){
                valid=true;
            }else{
                System.out.println("File not found please enter another one.");
            }
        }

        //creates the maze using the filename and then prints it out
        Maze labyrinth = new Maze(filename);

        labyrinth.printMaze();

        //this creates an instance of MazeSovler that will solve the inputed maze
        MazeSolver solver = new MazeSolver(labyrinth);

        //This will check to see if the maze if possible by solving the maze using the
        //travserse method using the rats position.
        if (solver.traverse(labyrinth.findStartRow(), labyrinth.findStartColumn())) {
            //if it is possible than it will print a success message, traverse count, and
            //the number of breadcrumbs.
            System.out.println("The maze was successfully traversed!");
            System.out.println("traverse was called: "+solver.getSolveCount()+" times.");
            System.out.println("There are "+labyrinth.numCrumb()+" breadcrumbs.");
            System.out.println("Solution:");
        }else {
            //if it is not posisible it will print this message.
            System.out.println("There is no possible path.");
        }

        labyrinth.printMaze();
    }

    /**
     * This checks to see if the file entered exists.
     * @param filename the name of the file to see if it exists.
     * @return true if the file does exist.
     */
    private static Boolean exists(String filename){
        boolean doesExist;
        File test= new File(filename);
        doesExist=test.exists();
        return doesExist;
    }

}
