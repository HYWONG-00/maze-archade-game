package edu.curtin.maze;

public class Empty extends GridDecorator{
    public static final String EMPTY_SYMBOL = " ";
    private String symbol;
    private String message;
 
    public Empty(Grid grid) {
        super(grid);
        this.symbol = EMPTY_SYMBOL;
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
}
