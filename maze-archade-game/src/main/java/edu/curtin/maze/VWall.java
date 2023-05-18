package edu.curtin.maze;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VWall implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(VWall.class.getName());
    private Grid[][] grids;
    private String[] lines;

    public VWall(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create() {
        try {
            Grid grid = grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4];

            grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4] = new Wall(new PlainGrid(), "\u2502");

            if (!(grid instanceof Empty)) {
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.warning("Trying to replace the current " + grid.getClass().getSimpleName() + " grid at row:" + lines[1] + " col:" + lines[2] + " with vertical wall !!!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Row or column of the object is outside the bound of the maze.");
        }
        catch (NumberFormatException e) {
            LOGGER.warning("Row or column should be a integer, not string");
        }

    }
}
