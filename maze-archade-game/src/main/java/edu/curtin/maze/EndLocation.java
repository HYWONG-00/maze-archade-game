package edu.curtin.maze;

import java.util.logging.Logger;

public class EndLocation implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(EndLocation.class.getName());
    protected Grid[][] grids;
    private String[] lines;

    public EndLocation(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create(){
        try {
            //only set the middle space in the column as end location, Otherwise print EEE
            grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2] = new End(new PlainGrid());
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Row or column of the object is outside the bound of the maze.");
        }catch (NumberFormatException e) {
            LOGGER.warning("Row or column should be a integer, not string");
        }
    }
}
