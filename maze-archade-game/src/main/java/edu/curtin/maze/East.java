package edu.curtin.maze;

public class East implements InputReader {
    private Player player;
    private Grid[][] grids;

    public East(Player player, Grid[][] grids) {
        this.player = player;
        this.grids = grids;
    }

    @Override
    public void move() {
        //nothing hehe, just move to right
        player.setMovement(new MoveRight(player, grids));
    }
}
