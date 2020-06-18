package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;

public class PunchLeft extends AbstractPunch {

    @Override
    protected Direction getPunchDirection() {
        return Direction.LEFT;
    }
}
