package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Dice;
import it.dpg.minigames.MinigameType;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerManagerImpl implements PlayerManager {

    private int remainingTurns;
    private final List<PlayerController> players;
    private final List<Dice> rewardDices;
    private Iterator<PlayerController> iterator;

    /**
     * @param defaultDice the dice everyone gets in the first turn
     * @param rewardDices the list of dices set as reward, assigned in order of score (following the list order),
     *                    if nPlayers < rewardDices.size() use only the nPlayers highest dices,
     *                    if nPlayers > rewardDices.size() use the last dice for all the remaining players
     */
    public PlayerManagerImpl(final Dice defaultDice, final List<Dice> rewardDices, final int nTurns, final Set<PlayerController> playerSet) {
        this.rewardDices = new ArrayList<>(rewardDices);//in case the list is immutable
        this.remainingTurns = nTurns - 1;
        this.players = new ArrayList<>(playerSet);
        Collections.shuffle(players);
        //set the turns in the characters
        for(int i = 0; i < players.size(); i++) {
            players.get(i).getCharacter().setTurn(i);
            players.get(i).getCharacter().setDice(defaultDice);
        }
        if(rewardDices.size() < playerSet.size()) {//if the condition is true, extend the list of dice with the lowest one
            Dice lowestDice = rewardDices.isEmpty() ? defaultDice : rewardDices.get(rewardDices.size() - 1);
            while(rewardDices.size() < playerSet.size()) {
                rewardDices.add(lowestDice);
            }
        }
        iterator = players.iterator();
        System.out.println();
    }

    @Override
    public PlayerController nextPlayer() {
        return iterator.next();
    }

    @Override
    public boolean hasNextPlayer() {
        return iterator.hasNext();
    }

    @Override
    public void nextTurn() {
        if(remainingTurns <= 0) {
            throw new IllegalStateException();
        }
        MinigameType endTurnMinigame = getRandomMinigame();
        for(PlayerController player : players) {
            player.playMinigame(endTurnMinigame);
        }
        List<PlayerController> ranking = players.stream()
                .sorted(Comparator.comparingInt(p -> p.getCharacter().getMinigameScore()))
                .collect(Collectors.toList());

        for(int i = 0; i < players.size(); i++) {
            ranking.get(i).getCharacter().setDice(rewardDices.get(i));
        }
        remainingTurns--;
        this.iterator = players.iterator();
    }

    private MinigameType getRandomMinigame() {
        var types = MinigameType.values();
        int i = new Random().nextInt(types.length);
        return types[i];
    }

    @Override
    public boolean hasNextTurn() {
        return remainingTurns > 0;
    }

    @Override
    public List<PlayerController> getPlayers() {
        return List.copyOf(players);
    }
}
