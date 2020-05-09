package it.dpg.controller.gamecycle.turnmanagement;

import it.dpg.controller.gamecycle.playercontroller.PlayerController;

public class PlayerImpl implements Player {

    private final Character character;
    private final PlayerController controller;

    public PlayerImpl(Character character, PlayerController controller) {
        this.character = character;
        this.controller = controller;
    }

    @Override
    public PlayerController getPlayerController() {
        return controller;
    }

    @Override
    public Character getCharacter() {
        return character;
    }
}
