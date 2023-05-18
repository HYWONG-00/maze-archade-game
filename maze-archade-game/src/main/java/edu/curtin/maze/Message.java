package edu.curtin.maze;

import java.util.logging.Logger;

public class Message implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(Message.class.getName());
    private Grid[][] grids;
    private String[] lines;

    public Message(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create(){
        try {
            for (int i = 1; i < 4; i++) {
                //a Grid confirm will be PlainGrid
                grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + i].setMessage(lines[3]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Row or column of the object is outside the bound of the maze.");
        }catch (NumberFormatException e) {
            LOGGER.warning("Row or column should be a integer, not string");
        }
    }
}
