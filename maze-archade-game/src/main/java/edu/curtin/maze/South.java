package edu.curtin.maze;

public class South implements InputReader {
    private Player player;
    private Grid[][] grids;

    public South(Player player, Grid[][] grids) {
        this.player = player;
        this.grids = grids;
    }

    @Override
    public void move() {
        //nothing hehe, just move to down
        player.setMovement(new MoveDown(player, grids));
    }
}
