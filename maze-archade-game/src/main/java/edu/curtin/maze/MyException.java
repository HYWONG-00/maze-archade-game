package edu.curtin.maze;

//exception chaining: https://www.youtube.com/watch?v=JbxY19n1MCU

public class MyException extends Exception
{
    private String error;

    //constructor methods for throwing exception.
    public MyException(String message)
    {
        super(message);
    }

    // See 'exception chaining' for the purpose of a 'cause'.
    public MyException(String message,Throwable cause) {
        super(message, cause); }

    public String getError()
    {
        return this.error;
    }
}
