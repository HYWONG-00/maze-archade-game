package edu.curtin.maze;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


//The strategy pattern allows you to change the implementation of something used at runtime.
//The decorator pattern allows you augment (or add to) existing functionality with additional functionality at run time.
//The key difference is in the change vs augment

public class Maze {

    public static final Logger LOGGER = Logger.getLogger(Maze.class.getName());
    //0=Red,1=Green,2=Yellow,3=Blue,4=Magenta,5=Cyan
    public static final String[] COLORS = {"\033[31m","\033[32m","\033[33m","\033[34m","\033[35m","\033[36m"};
    private Map<String,InputReader> inputs = new HashMap<>();
    private Map<String,FileInputReader> objects = new HashMap<>();
    private boolean continueGame = true;

    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);


    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }

    public boolean isContinueGame() {
        return continueGame;
    }

    public static void main(String[] args){
        Grid[][] grids;

        // setup the LOGGER
        //Logging.setupLogger();

        System.out.print("Enter filename: ");
        String fileName = input.nextLine();

        try
        {
            Maze maze = new Maze();
            //grids = maze.readInputFile(fileName,maze);
            ReadFile readFile = new ReadFile();
            grids = readFile.readInputFile(fileName, maze);
            grids = maze.addJunction(grids);

            int playerNum = 0;//this is to keep tracking of the number of players exists in the game(will warn user if we have more than one player)
            //find the player + get its position(a bit redundant actually)
            Player player = null;
            for (Grid[] i : grids) {
                for (Grid grid : i) {
                    if(grid instanceof Player) {
                        player = (Player) grid;
                        playerNum++;
                    }
                }
            }

                if (player == null) {
                    LOGGER.warning("There is no player in the maze! Will use default player(row:0,col:0) in this case");
                    //initialize a default player
                    player = new Player(new PlainGrid(),1,2);
                    if(grids[1][2] instanceof Key){
                        player.addKey(((Key) grids[1][2]).getColors());
                    }
                }

                if(playerNum >= 2){
                    LOGGER.warning("There is more than one player found in the maze!");
                }

            try {
                maze.checkEndLocation(grids);
            }
            catch(MyException e)
            {
                grids[grids.length - 2][grids[0].length - 3] = new End(new PlainGrid());
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.warning(e.getMessage());
                }

            }

            //initialize all of the commands with their own InputReader
            maze.initOptions(player, grids);

            MazeDisplay mazeDisplay = new MazeDisplay(grids, player);

            Reader reader = new Reader();
            //print the map
            mazeDisplay.printMaze();
                do {
                    System.out.print("Enter movement(n/s/e/w): ");
                    String movement = String.valueOf(input.next().charAt(0));

                    //can start do yr action liao(I think need to put after the user enter the movement)
                    InputReader inputReader = maze.inputs.get(movement);

                    try{
                        if (inputReader == null) {
                            //"this.inputReader" is null only when the selected movement is not valid
                            throw new MyException("Invalid movement");
                        } else {
                            reader.setInputReader(inputReader);
                            reader.doOption();
                            //show me your move!Just do it
                            player.tryToMove(maze);

                            //Must put inside try-catch as if there is any exception caught when player tries to move, does not need to redisplay the map
                            //print the map again
                            mazeDisplay.clearScreen();
                            mazeDisplay.printMaze();
                        }
                    }catch(MyException e){
                        if (LOGGER.isLoggable(Level.WARNING)) {
                            LOGGER.warning(e.getMessage());
                        }
                    }

                } while (maze.isContinueGame());

        }
        catch(IOException e)
        {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.warning("Could not read from " + fileName + ": " + e.getMessage());
            }

        }
    }

    private void checkEndLocation(Grid[][] grids) throws MyException{
            End endLocation = null;

            for (Grid[] i : grids) {
                for (Grid grid : i) {
                    if(grid instanceof End) {
                        endLocation = (End) grid;
                    }
                }
            }
            if (endLocation == null) {
                throw new MyException("There is no end location in the maze! Will initialize a default end location in the bottom-right corner of the maze");
            }
    }

    private void initOptions(Player player,Grid[][] grids)
    {
        inputs.put("N",new North(player,grids));
        inputs.put("n",new North(player,grids));
        inputs.put("S",new South(player,grids));
        inputs.put("s",new South(player,grids));
        inputs.put("E",new East(player,grids));
        inputs.put("e",new East(player,grids));
        inputs.put("W",new West(player,grids));
        inputs.put("w",new West(player,grids));

    }

    public void initObjects(Grid[][] grids,String[] lines)
    {
        objects.put("WH",new HWall(grids,lines));
        objects.put("WV",new VWall(grids,lines));
        objects.put("S",new StartLocation(grids,lines));
        objects.put("E",new EndLocation(grids,lines));
        objects.put("M",new Message(grids,lines));
        objects.put("DH",new HDoor(grids,lines));
        objects.put("DV",new VDoor(grids,lines));
        objects.put("K",new AddKey(grids,lines));
    }
    
    private Grid[][] addJunction(Grid[][] grids){
    	int row  = (grids.length - 1) / 2;//user input for row(for looping times)
    	int col  = (grids[row].length - 1) / 4;//user input for column(for looping times)

    	//do for all junction in the most top row + most bottom row only(Map: 最上面那条线跟最下面的那条线)
    	for(int j = 1 ; j < col ; j++){// ┬ or ┴ only
    		//most top border in map
    		if(!grids[1][j*4].getSymbol().equals(" ")){
    			grids[0][j*4] = new Wall(new PlainGrid(), "\u252c");// ┬
    		}
    		
    		//most bottom border in map
    		if(!grids[grids.length - 2][j*4].getSymbol().equals(" ")){
    			grids[grids.length - 1][j*4] = new Wall(new PlainGrid(), "\u2534");// ┴
    		}
    	
    	}
    	
    	//do for all junction in the most left column + most right column only(Map: 最左边那条线跟最右边的那条线)
    	for(int j = 1 ; j < row ; j++){// ┬ or ┴ only
    		//most left column in map
    		if(!grids[j*2][1].getSymbol().equals(" ")){
    			grids[j*2][0] = new Wall(new PlainGrid(), "\u251c");//replace "│" with "├"
    		}
    		
    		//most right column in map
    		if(!grids[j*2][grids[0].length - 2].getSymbol().equals(" ")){
    			grids[j*2][grids[0].length - 1] = new Wall(new PlainGrid(), "\u2524");//replace "│" with "┤"
    		}
    	
    	}
    	
    
    	//do for all junctions in the middle of the map
    	for(int i = 1 ; i < row ; i++){
    		int rowJunction = i * 2;
	    	for(int j = 1 ; j < col ; j++){	    		
	    		int columnJunction = j * 4;//this is a junction(just in what symbol is another world)

	    		// !!!ONLY WORK FOR middle part (cuz if row=0 / first row, u cannot [i-1], sames goes to column)
                boolean up = !grids[rowJunction-1][columnJunction].getSymbol().equals(" ");
                boolean down = !grids[rowJunction+1][columnJunction].getSymbol().equals(" ");
                boolean left = !grids[rowJunction][columnJunction-1].getSymbol().equals(" ");
                boolean right = !grids[rowJunction][columnJunction+1].getSymbol().equals(" ");

                //if up n down got thing(wall/door/...)
                if(up && down){
                    //if left n right got thing(wall/door/...)
                    if(left && right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u253c");//┼
                    }
                    //if left got thing(wall/door/...)
                    else if(left){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2524");//┤
                    }
                    //if right got thing(wall/door/...)
                    else if(right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u251c");//├
                    }
                    //if left n right is empty(wall/door/...)
                    else{
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2502");//│  
                    }
                }
                //if up got thing(wall/door/...)
                else if(up){

                    //if left n right got thing(wall/door/...)
                    if(left && right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2534");//┴
                    }
                    //if left got thing(wall/door/...)
                    else if(left){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2518");// ┘
                    }
                    //if right got thing(wall/door/...)
                    else if(right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2514");//└
                    }
                    else{
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2502");//│  
                    }

                }
                //if down got thing(wall/door/...)
                else if(down){

                    //if left n right got thing(wall/door/...)
                    if(left && right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u252c");//┬
                    }
                    //if left got thing(wall/door/...)
                    else if(left){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2510");//┐
                    }
                    //if right got thing(wall/door/...)
                    else if(right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u250c");//┌
                    }
                    else{
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2502");//│
                    }
                }
                //up n down got nothing
                else{
                    if(left || right){
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), "\u2500");//─ 
                    }
                    else{
                        grids[rowJunction][columnJunction] = new Wall(new PlainGrid(), " ");
                    }

                }
	    	} 
    	}
    return grids;	
    }

    public Map<String, FileInputReader> getObjects() {
        return objects;
    }
}
