package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.WorldImpl;

public class PunchLeft implements Input {

    @Override
    public void execute(WorldImpl world) {
        Direction d = world.getNextSack();
        if(d == Direction.LEFT) {
            world.getScore().incrementScore();
            world.getTimer().timerIncrease();
            System.out.println("left");
        } else {
            world.triggerGameOver();
            System.out.println("over");
        }
    }
}
