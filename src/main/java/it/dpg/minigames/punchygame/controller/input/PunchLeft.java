package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.WorldImpl;

public class PunchLeft implements Input {

    @Override
    public void execute(WorldImpl world) {
        if(world.getNextSack() == Direction.LEFT) {
            world.getScore().incrementScore();
        }
    }
}
