package edu.curtin.maze;

public class West implements InputReader {
    private Player player;
    private Grid[][] grids;

    public West(Player player, Grid[][] grids) {
        this.player = player;
        this.grids = grids;
    }

    @Override
    public void move() {
        //nothing hehe, just move to left
        player.setMovement(new MoveLeft(player, grids));
    }
}
