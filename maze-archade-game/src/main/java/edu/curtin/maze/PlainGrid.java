package edu.curtin.maze;

public class PlainGrid implements Grid{

    public PlainGrid(){
    }


    //return one space only (for those empty space)
    @Override
    public String getSymbol() {
        return NOSYMBOL;
    }

    @Override
    public void setMessage(String message) {}

    //return empty message again(cuz initially grid hv no message)
    @Override
    public String getMessage() {
        return NOMESSAGE;
    }

    @Override
    public String getColor() {return DEFAULTCOLOR;}

}
