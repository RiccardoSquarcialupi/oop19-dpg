package it.dpg.controller.gamecycle.turnmanagement;

import it.dpg.controller.gamecycle.player.Player;
import it.dpg.model.character.Dice;

import java.util.List;

public interface PlayerManagerBuilder {

    /**
     * set the list of reward dices, if not called creates a PlayerManager with no reward dices (only default)
     * @param rewardDices list of dices ordered by prize (first dice goes to the winner, second one goes to the second...)
     */
    PlayerManagerBuilder setRewardDices(final List<Dice> rewardDices);

    /**
     * @param player added to the manager (with distinct id)
     */
    PlayerManagerBuilder addPlayer(final Player player);

    /**
     * build method of the builder
     * @return the built manager
     * @exception IllegalStateException if called twice
     */
    PlayerManager build();
}
