package edu.curtin.maze;

//we need strategy pattern to avoid using if-else cuz if-else
//1. hard to maintain, if got some changes, we need to rewrite for entire method
//2.mixing of responsibility(GG)
//https://www.youtube.com/watch?v=Nrwj3gZiuJU
//after using strategy pattern, u go see main method, no visibility of our methods anymore (like if go down do what, go up do what). All r done at here
//combime this command factory example = more understand(https://stackoverflow.com/questions/28049094/replacing-if-else-statement-with-pattern)

public interface InputReader{
    void move();
}

