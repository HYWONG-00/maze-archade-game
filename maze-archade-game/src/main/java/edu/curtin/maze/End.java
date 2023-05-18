package edu.curtin.maze;

public class End extends GridDecorator{
    public static final String END_SYMBOL = "E";
    private String symbol;
    private String message;

    public End(Grid grid) {

        super(grid);
        this.symbol = END_SYMBOL;
        this.message = NOMESSAGE;
        //message = new NoMessage();
    }

    @Override
    public String getSymbol() {
        return grid.getSymbol() + symbol;
    }

    @Override
    public void setMessage(String message){this.message = message;}

    @Override
	public String getMessage(){
	return grid.getMessage() + this.message;
	}

}
