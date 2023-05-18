package edu.curtin.maze;

public class MoveLeft extends Movement {
    private int currentRow;
    private int currentCol;

    public MoveLeft(Player player, Grid[][] grids) {
        this.player = player;
        this.grids = grids;

        this.currentRow = player.getRow();
        this.currentCol = player.getCol();
    }

    @Override
    public boolean hasWall() {
        return grids[currentRow][currentCol - 2] instanceof Wall;
    }

    @Override
    public boolean hasDoor() {
        boolean hasDoor = grids[currentRow][currentCol - 2] instanceof Door;
        if (hasDoor) {
            //set door color
            doorColor = grids[currentRow][currentCol - 2].getColor();
        }
        return hasDoor;
    }

    @Override
    public boolean frontNoBlock() {
        return grids[currentRow][currentCol - 2] instanceof Empty;
    }

    @Override
    public boolean hasSpace() {
        return grids[currentRow][currentCol - 4] instanceof Empty;
    }

    @Override
    public boolean hasKey() {
        return grids[currentRow][currentCol - 4] instanceof Key;
    }

    @Override
    public boolean isEndLocation() {
        return grids[currentRow][currentCol - 4] instanceof End;
    }

    @Override
    public void openDoor() {
        grids[currentRow][currentCol - 2] = new Empty(new PlainGrid());
    }

    @Override
    public void updatePlayerPosition() {
        //2. update the player at new grid
        player.setRow(currentRow);
        player.setCol(currentCol - 4);
    }
}
