package edu.curtin.maze;

import java.util.List;

//for displaying maze
public class MazeDisplay {

    public static final String CLEAR_SCREEN = "\033[2J";
    private Grid[][] grids;
    private Player player;
    public MazeDisplay(Grid[][] grids,Player player){
        this.grids = grids;
        this.player = player;
    }

    public void clearScreen(){
        //clear the screen first b4 printing new map
        System.out.println(MazeDisplay.CLEAR_SCREEN);
    }

    public void printMaze(){
        //print the map
        for (Grid[] i : grids) {
            for (Grid grid : i) {
                //aiya nvm lh, if there is multiple keys then just show the last color key on map
                System.out.print(grid.getColor() + grid.getSymbol().charAt(0));
            }
            System.out.print("\n");
        }

        System.out.print("My On-Hold Key(s):");
        List<String> keys = player.getKeys();
        for (String key: keys) {
            System.out.print(key + Key.KEYSYMBOL);
        }
        System.out.print(Grid.DEFAULTCOLOR);//clear the color
        System.out.print("\n");
        System.out.print("Message:" + grids[player.getRow()][player.getCol()].getMessage());
        System.out.print("\n");
    }
}
