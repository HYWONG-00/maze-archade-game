package edu.curtin.maze;

public class Door extends GridDecorator{
    public static final String DOOR_SYMBOL = "\u2592";
    
    private String color;
    private String symbol;
    private String message;
 
    public Door(Grid grid,String color) {
        super(grid);
        this.color = color;
        this.symbol = DOOR_SYMBOL;
        this.message = NOMESSAGE;
        //message = new NoMessage();
    }

    @Override
    public String getSymbol() {
        return grid.getSymbol() + symbol;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
	public String getMessage(){
	return grid.getMessage() + this.message;
	}

    //overwrite grid.getColor() with its own color
    @Override
    public String getColor() {
        return this.color;
    }
}
