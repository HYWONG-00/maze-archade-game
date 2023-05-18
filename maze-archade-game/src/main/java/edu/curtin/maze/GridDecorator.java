package edu.curtin.maze;

public abstract class GridDecorator implements Grid{

    protected Grid grid;

    public GridDecorator(Grid grid){
        this.grid =grid;
    }

    @Override
    public String getSymbol(){
        return grid.getSymbol();
    }

    @Override
	public String getMessage(){
	    return grid.getMessage();
	}

    @Override
    public String getColor(){return grid.getColor();}
}
