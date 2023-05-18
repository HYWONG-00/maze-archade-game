package edu.curtin.maze;


import java.util.logging.Level;
import java.util.logging.Logger;

public class HWall implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(HWall.class.getName());
    private Grid[][] grids;
    private String[] lines;

    public HWall(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create() {
        try {
            for (int i = 1; i < 4; i++) {
                Grid grid = grids[Integer.parseInt(lines[1]) * 2][Integer.parseInt(lines[2]) * 4 + i];

                grids[Integer.parseInt(lines[1]) * 2][Integer.parseInt(lines[2]) * 4 + i] = new Wall(new PlainGrid(), "\u2500");

                if (!(grid instanceof Empty)) {
                    if (LOGGER.isLoggable(Level.WARNING)) {
                        LOGGER.warning("Trying to replace the current " + grid.getClass().getSimpleName() + " grid at row:" + lines[1] + " col:" + lines[2] + " with horizontal wall !!!");
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
