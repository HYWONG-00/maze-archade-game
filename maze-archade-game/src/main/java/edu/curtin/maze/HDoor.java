package edu.curtin.maze;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HDoor implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(HDoor.class.getName());
    private Grid[][] grids;
    private String[] lines;

    public HDoor(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create(){
        try {
            for (int i = 1; i < 4; i++) {
                Grid grid = grids[Integer.parseInt(lines[1]) * 2][Integer.parseInt(lines[2]) * 4 + i];
                //I allow it to be replaced
                grids[Integer.parseInt(lines[1]) * 2][Integer.parseInt(lines[2]) * 4 + i] = new Door(new PlainGrid(), Maze.COLORS[Integer.parseInt(lines[3]) - 1]);

                if (!(grid instanceof Empty)) {//grid is not empty
                    if (LOGGER.isLoggable(Level.WARNING)) {
                        LOGGER.warning("Trying to replace the current " + grid.getClass().getSimpleName() + " grid at row:" + lines[1] + " col:" + lines[2] + " with horizontal door !!!");
                    }
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Row or column of the object is outside the bound of the maze.");
        }catch (NumberFormatException e) {
            LOGGER.warning("Row or column should be a integer, not string");
        }
    }
}
