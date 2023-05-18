package edu.curtin.maze;

public class Wall extends GridDecorator{
    private String symbol;
    private String message;
    public Wall(Grid grid, String symbol) {
        super(grid);
        this.symbol = symbol;
        this.message = NOMESSAGE;
        //message = new NoMessage();
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

 
}
