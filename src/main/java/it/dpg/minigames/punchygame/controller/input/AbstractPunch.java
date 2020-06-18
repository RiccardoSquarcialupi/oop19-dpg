package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.WorldImpl;

public abstract class AbstractPunch implements Input {

    @Override
    public void execute(WorldImpl world) {
        if(world.getNextSack() == getPunchDirection()) {
            world.getBoxer().setDirection(getPunchDirection());
            world.getScore().incrementScore();
            world.getTimer().timerIncrease();
        } else {
            world.triggerGameOver();
        }
    }

    protected abstract Direction getPunchDirection();
}
