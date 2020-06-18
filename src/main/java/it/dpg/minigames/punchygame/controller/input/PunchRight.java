package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.WorldImpl;

public class PunchRight implements Input {

    @Override
    public void execute(WorldImpl world) {
        Direction d = world.getNextSack();
        if(d == Direction.RIGHT) {
            world.getScore().incrementScore();
            world.getTimer().timerIncrease();
        } else {
            world.triggerGameOver();
        }
    }
}
