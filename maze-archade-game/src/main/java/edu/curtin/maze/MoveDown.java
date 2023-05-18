package edu.curtin.maze;


public class MoveDown extends Movement {
    private int currentRow;
    private int currentCol;

    public MoveDown(Player player, Grid[][] grids) {
        this.player = player;
        this.grids = grids;

        this.currentRow = player.getRow();
        this.currentCol = player.getCol();
    }

    @Override
    public boolean hasWall() {
        return grids[currentRow + 1][currentCol] instanceof Wall;
    }

    @Override
    public boolean hasDoor() {
        boolean hasDoor = grids[currentRow + 1][currentCol] instanceof Door;
        if (hasDoor) {
            //set door color
            doorColor = grids[currentRow + 1][currentCol].getColor();
        }
        return hasDoor;
    }

    @Override
    public boolean frontNoBlock() {
        return grids[currentRow + 1][currentCol] instanceof Empty;
    }

    @Override
    public boolean hasSpace() {
        return grids[currentRow + 2][currentCol] instanceof Empty;
    }

    @Override
    public boolean hasKey() {

        return grids[currentRow + 2][currentCol] instanceof Key;
    }

    //this return the condition to check if there is end location when moving down
    @Override
    public boolean isEndLocation() {
        return grids[currentRow + 2][currentCol] instanceof End;
    }

    //this will open the door when moving down
    @Override
    public void openDoor() {
    //it's a horizontal door = need to clear 3 grids instead of 1
    grids[currentRow + 1][currentCol-1] = new Empty(new PlainGrid());
    grids[currentRow + 1][currentCol] = new Empty(new PlainGrid());
        grids[currentRow + 1][currentCol+1] = new Empty(new PlainGrid());
    }

    @Override
    public void updatePlayerPosition() {
        //2. update the player at new grid
        player.setRow(currentRow + 2);
        player.setCol(currentCol);
    }


}
