package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.WorldImpl;

public abstract class AbstractPunch implements Input {

    @Override
    public boolean execute(WorldImpl world) {
        return world.checkSackHit(getPunchDirection());
    }

    protected abstract Direction getPunchDirection();
}