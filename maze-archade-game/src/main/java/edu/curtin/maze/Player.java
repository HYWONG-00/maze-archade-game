package edu.curtin.maze;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Player extends GridDecorator{
    public static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    public static final String PLAYER_SYMBOL = "P";
    private String symbol;
    private String message;

    private Movement movement;
    private int row;
    private int col;
    private List<String> keys;

    public Player(Grid grid,int row,int col) {
        super(grid);
        this.row = row;
        this.col = col;
        this.symbol = PLAYER_SYMBOL;
        this.message = NOMESSAGE;
        this.keys = new LinkedList<>();
        //initially do nothing(just stay in your position)
        this.movement = new Movement() {
            @Override
            public boolean hasWall() {
                return false;
            }

            @Override
            public boolean hasDoor() {
                return false;
            }

            @Override
            public boolean hasSpace() {
                return false;
            }

            @Override
            public boolean hasKey() {
                return false;
            }

            @Override
            public boolean frontNoBlock() {
                return false;
            }

            @Override
            public boolean isEndLocation() {
                return false;
            }
            @Override
            public void openDoor() {

            }
            @Override
            public void updatePlayerPosition() {

            }
        };
    }

    public void tryToMove(Maze maze) throws MyException{
        movement.move(maze);
    }

    public void setMovement(Movement newMove){
        this.movement = newMove;
    }

    public void addKey(List<String> newKeys){
        //LOGGER.log(Level.FINE,"My key length:" + newKeys.size());
        //LOGGER.log(Level.FINE,"All keys:" + newKeys);

        for(String newKey : newKeys){
            this.keys.add(newKey);
        }
    }

    public boolean hasKey(String doorColor){
        return this.keys.contains(doorColor);
    }

    public void removeKey(String key){
        this.keys.remove(key);
    }

    @Override
    public String getSymbol() {
        return grid.getSymbol() + symbol;
    }

    @Override
    public void setMessage(String message){
	this.message = message;
	}

    @Override
	public String getMessage(){
	return grid.getMessage() + this.message;
	}

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    @Override
    public String toString() {
        return "Player{" +
                "PLAYER_SYMBOL='" + PLAYER_SYMBOL + '\'' +
                ", symbol='" + symbol + '\'' +
                ", message='" + message + '\'' +
                ", movement=" + movement +
                ", row=" + row +
                ", col=" + col +
                ", keys=" + keys +
                '}';
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getKeys() {
        return keys;
    }
}
