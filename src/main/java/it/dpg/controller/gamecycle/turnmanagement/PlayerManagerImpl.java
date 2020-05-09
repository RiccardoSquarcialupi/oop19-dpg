package it.dpg.controller.gamecycle.turnmanagement;

import java.util.Set;

public class PlayerManagerImpl implements PlayerManager {

    private int remainingTurns;

    public PlayerManagerImpl(final int nTurns) {
        this.remainingTurns = nTurns;
    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void startGame() {

    }

    @Override
    public Player nextPlayer() {
        return null;
    }

    @Override
    public boolean hasNextPlayer() {
        return false;
    }

    @Override
    public void nextTurn() {

    }

    @Override
    public boolean hasNextTurn() {
        return false;
    }

    @Override
    public void playMinigames() {

    }

    @Override
    public Set<Player> getPlayers() {
        return null;
    }
}
