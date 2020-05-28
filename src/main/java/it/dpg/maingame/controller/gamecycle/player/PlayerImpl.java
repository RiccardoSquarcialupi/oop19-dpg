package it.dpg.maingame.controller.gamecycle.player;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Character;

public class PlayerImpl implements Player {

    private final Character character;
    private final PlayerController controller;

    public PlayerImpl(final Character character, final PlayerController controller) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerImpl player = (PlayerImpl) o;
        return character.getId() == player.character.getId();
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(character.getId()).hashCode();
    }
}
