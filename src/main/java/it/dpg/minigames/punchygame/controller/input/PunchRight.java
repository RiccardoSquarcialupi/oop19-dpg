package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;

public class PunchRight extends AbstractPunch {

    @Override
    protected Direction getPunchDirection() {
        return Direction.RIGHT;
    }
}
