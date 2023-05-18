package edu.curtin.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile {
    public static final Logger LOGGER = Logger.getLogger(ReadFile.class.getName());
    public Grid[][] readInputFile(String inputFile,Maze maze) throws IOException
    {
        Reader reader1 = new Reader();
        Grid[][] grids = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
        {
            String line = reader.readLine();
            //first line = row n column
            String[] lines = line.split(" ",2);
            int row  = Integer.parseInt(lines[0]) * 2 + 1;
            int col = Integer.parseInt(lines[1]) * 4 + 1;
            if(row <= 0 || col <= 0){
                throw new NegativeArraySizeException("Row or Column value must be at least one");
            }
            else {
                grids = new Grid[row][col];

                //build the 4 corners
                grids[0][0] = new Wall(new PlainGrid(), "\u250c");
                grids[0][col - 1] = new Wall(new PlainGrid(), "\u2510");
                grids[row - 1][0] = new Wall(new PlainGrid(), "\u2514");
                grids[row - 1][col - 1] = new Wall(new PlainGrid(), "\u2518");

                //top n bottom horizontal wall
                for (int j = 1; j < col - 1; j++) {

                    grids[0][j] = new Wall(new PlainGrid(), "\u2500");
                    grids[row - 1][j] = new Wall(new PlainGrid(), "\u2500");
                }
                //left n right vertical wall
                for (int i = 1; i < row - 1; i++) {
                    grids[i][0] = new Wall(new PlainGrid(), "\u2502");
                    grids[i][col - 1] = new Wall(new PlainGrid(), "\u2502");
                }

                //just initialize all the grids in maze only
                for (int i = 1; i < row - 1; i++) {
                    for (int j = 1; j < col - 1; j++) {
                        grids[i][j] = new Empty(new PlainGrid());
                    }
                }

                line = reader.readLine();//read 2nd line
                while (line != null) {
                    if (line.trim().length() > 0) // Ignore blank lines
                    {
                        lines = line.split(" ", 4);
                        //can start do yr action liao(I think need to put after the user enter the movement)
                        maze.initObjects(grids, lines);//this line must inside the loop, cuz need to pass updated grids n lines
                        Map<String, FileInputReader> objects = maze.getObjects();
                        FileInputReader fileInputReader = objects.get(lines[0]);
                        try{
                            if (fileInputReader == null) {
                                //"this.inputReader" is null only when the selected movement is not valid
                                throw new MyException("Invalid object type: " + lines[0]);
                            } else {
                                reader1.setFileInputReader(fileInputReader);
                                reader1.addObject();
                            }
                        }catch(MyException e){
                            if (LOGGER.isLoggable(Level.WARNING)) {
                                LOGGER.warning(e.getMessage());
                            }

                        }
                    }
                    line = reader.readLine();//read next line
                }
            }
        }
        catch(NegativeArraySizeException e){
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.warning(e.getMessage());
            }

        }

        return grids;
    }
}
