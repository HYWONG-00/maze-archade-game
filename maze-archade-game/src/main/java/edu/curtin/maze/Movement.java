package edu.curtin.maze;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//https://medium.com/@nweiyue/strategise-your-code-with-the-strategy-pattern-b716e3fbd3c
//template method(cuz strategy method will have a lot of duplicate code,
// imagine u just implement can't fly and it flys, is like separate one)
public abstract class Movement {
    public static final Logger LOGGER = Logger.getLogger(Movement.class.getName());
    protected Player player;
    protected Grid[][] grids;
    protected String doorColor = Grid.DEFAULTCOLOR;//set it to default colour first
    public final void move(Maze maze) throws MyException {
        //in front got block
        if(hasWall()){
            LOGGER.log(Level.INFO,"front has wall");
            throw new MyException("In front got wall, Cannot move forward");
        }
        else if(hasDoor()){
            LOGGER.log(Level.INFO,"front has door");
            //check if player has key
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,"Player has key" + doorColor + ":" + player.hasKey(doorColor));
                LOGGER.log(Level.FINE,"Player:" + player.toString());
            }

            if(player.hasKey(doorColor)){
                //open the door(door disappear on map)
                openDoor();

                //remove the key for the player(if got 2 same colour key, then only remove the one u find first)
                player.removeKey(doorColor);

                //move across the door
                moveForward();
            }
            else{
                throw new MyException("No key, cannot open the door so cannot move forward");
            }
        }
        //does not have anything block in front
        else if(frontNoBlock()){
            if (hasSpace()) {
                LOGGER.log(Level.INFO,"front has space");
                //just move forward
                moveForward();
            } else if (hasKey()) {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.INFO,"front has key");
                    LOGGER.log(Level.FINE,"Player b4 get key" + player);
                }


                //just move forward

                //1. Save the keys on-hold by player
                List<String> playerKeys = player.getKeys();//key hold by the player
                //LOGGER.info("The key:" + playerKeys);

                //2. remove the player at current grid
                String message = grids[player.getRow()][player.getCol()].getMessage();
                grids[player.getRow()][player.getCol()] = new Empty(new PlainGrid());
                grids[player.getRow()][player.getCol()].setMessage(message);

                updatePlayerPosition();//update current position

                //before u replace the current grid, get the keys copies first
                if(grids[player.getRow()][player.getCol()] instanceof Key){

                    //the keys exists in that grid
                    List<String> colors = ((Key) grids[player.getRow()][player.getCol()]).getColors();

                    //I need this as I need to show updated player position in maze game
                    message = grids[player.getRow()][player.getCol()].getMessage();
                    grids[player.getRow()][player.getCol()] = new Player(new PlainGrid(), player.getRow(),player.getCol());
                    grids[player.getRow()][player.getCol()].setMessage(message);

                    //!!Need to replace the empty pocket first(with the player's on-hold key)
                    player.setKeys(playerKeys);
                    //LOGGER.log(Level.FINE,"The keys on-hold by player right now:" + player);

                    //and get all key
                    player.addKey(colors);
                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.log(Level.FINE,"Show me the player aft get key" + player);
                    }
                }
            } else if (isEndLocation()) {
                LOGGER.log(Level.INFO,"front has end location");
                //move forward
                moveForward();

                /*
                MazeDisplay mazeDisplay = new MazeDisplay(grids,player);
                //print the map again
                mazeDisplay.clearScreen();
                mazeDisplay.printMaze();

                //end the game(clear screen n congrat user)
                System.exit(0);*/
                maze.setContinueGame(false);
            }
        }
        else{
            //throw exception
            //most probably in front is null odi, null means when u try to jump to impossible place(cuz not equals to wall/door/empty space)
            throw new MyException("Cannot move in that direction!As you are edge of the map");
        }

    }

    public void moveForward(){
        //dun forget to set the message of the grid back after replacing the grid
        //1. remove the player at current grid
        String message = grids[player.getRow()][player.getCol()].getMessage();
        grids[player.getRow()][player.getCol()] = new Empty(new PlainGrid());
        grids[player.getRow()][player.getCol()].setMessage(message);

        updatePlayerPosition();//update current position

        message = grids[player.getRow()][player.getCol()].getMessage();
        grids[player.getRow()][player.getCol()] = new Player(new PlainGrid(), player.getRow(),player.getCol());
        grids[player.getRow()][player.getCol()].setMessage(message);
    }

    //do all checking in the subclass
    public abstract boolean hasWall();
    public abstract boolean hasDoor();
    public abstract boolean hasSpace();
    public abstract boolean hasKey();
    public abstract boolean frontNoBlock();
    public abstract boolean isEndLocation();
    public abstract void openDoor();
    public abstract void updatePlayerPosition();
}


