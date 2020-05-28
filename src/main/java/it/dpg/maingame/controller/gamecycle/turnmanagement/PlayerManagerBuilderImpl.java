package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.player.Player;
import it.dpg.maingame.model.character.Dice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerManagerBuilderImpl implements PlayerManagerBuilder {

    private final int nTurns;
    private final Dice defaultDice;
    private List<Dice> rewardDices = new ArrayList<>();
    private final Set<Player> players = new HashSet<>();

    public PlayerManagerBuilderImpl(final int nTurns, final Dice defaultDice) {
        this.nTurns = nTurns;
        this.defaultDice = defaultDice;
    }

    @Override
    public PlayerManagerBuilder setRewardDices(final List<Dice> rewardDices) {
        this.rewardDices = rewardDices;
        return this;
    }

    @Override
    public PlayerManagerBuilder addPlayer(final Player player) {
        this.players.add(player);
        return this;
    }

    @Override
    public PlayerManager build() {
        return new PlayerManagerImpl(defaultDice, rewardDices, nTurns, players);
    }
}
