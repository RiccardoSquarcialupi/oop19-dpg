package it.dpg.controller.gamecycle.turnmanagement;

import it.dpg.model.character.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerManagerImpl implements PlayerManager {

    private int remainingTurns;
    private final List<Player> players;
    private final Dice defaultDice;
    private final List<Dice> rewardDices;

    /**
     * @param defaultDice the dice everyone gets in the first turn
     * @param rewardDices the list of dices set as reward, assigned in order of score (following the list order),
     *                    if nPlayers < rewardDices.size() use only the nPlayers highest dices,
     *                    if nPlayers > rewardDices.size() use the last dice for all the remaining players
     */
    public PlayerManagerImpl(final Dice defaultDice, final List<Dice> rewardDices, final int nTurns, final Set<Player> players) {
        this.defaultDice = defaultDice;
        this.rewardDices = rewardDices;
        this.remainingTurns = nTurns;
        this.players = new ArrayList<>(players);
        Collections.shuffle(this.players);
        for(int i = 0; i < this.players.size(); i++) {
            this.players.get(i).getCharacter().setTurn(i);
        }
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
    public Set<Player> getPlayers() {
        return null;
    }
}
