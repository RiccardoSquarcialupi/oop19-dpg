package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.WorldImpl;

public class PunchRight implements Input {
    @Override
    public void execute(WorldImpl world) {
        if(world.getNextSack() == Direction.RIGHT) {
            world.getScore().incrementScore();
        }
    }
}
