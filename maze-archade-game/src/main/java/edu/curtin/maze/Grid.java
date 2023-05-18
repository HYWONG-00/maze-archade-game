package edu.curtin.maze;

public interface Grid
{
	//final variables
	String NOSYMBOL = "";
	String NOMESSAGE = "";
	String DEFAULTCOLOR = "\033[m";//terminal color

	//method
    String getSymbol();
	void setMessage(String message);
	String getMessage();
	String getColor();
}
