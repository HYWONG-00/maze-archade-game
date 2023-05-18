package edu.curtin.maze;

import java.util.logging.Logger;

public class StartLocation implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(StartLocation.class.getName());
    private Grid[][] grids;
    private String[] lines;

    public StartLocation(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create() {
        try {
            //just store the grid(for later use)
            Grid grid = grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2];

            //only set the middle space in the column as player, Otherwise print PPP
            grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2] = new Player(new PlainGrid(), Integer.parseInt(lines[1]) * 2 + 1, Integer.parseInt(lines[2]) * 4 + 2);

            //check if the start location already has key
            if (grid instanceof Key) {
                ((Player) grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2]).addKey(((Key) grid).getColors());
            }
            //if start location has no key yet then it's fine bro
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Row or column of the object is outside the bound of the maze.");
        }catch (NumberFormatException e) {
            LOGGER.warning("Row or column should be a integer, not string");
        }
    }
}
