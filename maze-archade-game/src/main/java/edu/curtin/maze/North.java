package edu.curtin.maze;

public class North implements InputReader {
    private Player player;
    private Grid[][] grids;

    public North(Player player, Grid[][] grids) {
        this.player = player;
        this.grids = grids;
    }

    @Override
    public void move() {
        //nothing hehe, just move to up
        player.setMovement(new MoveUp(player, grids));
    }
}
