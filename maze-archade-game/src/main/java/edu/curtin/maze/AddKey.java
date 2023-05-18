package edu.curtin.maze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddKey implements FileInputReader {
    public static final Logger LOGGER = Logger.getLogger(AddKey.class.getName());
    private Grid[][] grids;
    private String[] lines;

    public AddKey(Grid[][] grids, String[] lines) {
        this.lines = lines;
        this.grids = grids;
    }

    @Override
    public void create() {
        try {
            //B4 u do anything, we need to check if the player is in that grid or not(we do not want to replace player if player exists in that grid)
            //if we have no key in that grid yet(but we have player in that grid odi)
            if (grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2] instanceof Player) {
                //a bit weird if I add player here, but we need to initialize the Player n put those keys inside the player's pocket
                //This happens when in text file we have created player first then read the keys in the same grid
                //S 0 0
                //K 0 0 1
                List<String> colours = new LinkedList<>();
                colours.add(Maze.COLORS[Integer.parseInt(lines[3]) - 1]);
                ((Player) grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2]).addKey(colours);
            } else {
                //if we already have key in that grid
                if (grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2] instanceof Key) {
                    List<String> keys = ((Key) grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2]).getColors();
                    keys.add(Maze.COLORS[Integer.parseInt(lines[3]) - 1]);
                    //exist_colors += "," + colors[Integer.parseInt(lines[3]) - 1];

                    //get the colour list first before replace the old one
                    grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2] =
                            new Key(grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2], keys);
                }
                //instanceof Empty
                else {
                    List<String> keys = new ArrayList<>();
                    keys.add(Maze.COLORS[Integer.parseInt(lines[3]) - 1]);
                    grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2] =
                            new Key(new PlainGrid(), keys);
                }
            }
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("Insert these keys:" + grids[Integer.parseInt(lines[1]) * 2 + 1][Integer.parseInt(lines[2]) * 4 + 2].getColor());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Row or column of the object is outside the bound of the maze.");
        } catch (NumberFormatException e) {
            LOGGER.warning("Row or column should be a integer, not string");
        }
    }
}
