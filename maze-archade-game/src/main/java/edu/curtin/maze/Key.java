package edu.curtin.maze;

import java.util.List;

public class Key extends GridDecorator{
    public static final String KEYSYMBOL = "\u2555";
    private List<String> colors;
    private String symbol;
    private String message;

    public Key(Grid grid,List<String> keys) {
        super(grid);
        this.colors = keys;
        this.symbol = KEYSYMBOL;
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

    //overwrite grid.getColor() with its own color
    @Override
    public String getColor() {
        String returnedString = "";
        for(String clr : colors){
            //returnedString += "," + clr;
            returnedString += clr;
        }
        return returnedString;
    }

    public List<String> getColors() {
        return colors;
    }

    @Override
    public String toString() {
        return "Key{" +
                "colors=" + colors +
                ", symbol='" + symbol + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
