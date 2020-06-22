package it.dpg.minigames.jumpgame.controller.input;

import it.dpg.minigames.jumpgame.model.World;

public class MoveLeft implements Input {
    @Override
    public void execute(World world) {
        world.setPlayerSpeedX(-5);
    }
}
