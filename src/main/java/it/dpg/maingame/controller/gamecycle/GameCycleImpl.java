package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.controller.GridViewGeneratorImpl;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactory;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactoryImpl;
import it.dpg.maingame.controller.gamecycle.turnmanagement.*;
import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.GridType;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.view.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class GameCycleImpl implements GameCycle {

    private final TurnState turnState;
    private final GridView view;
    private final TurnManager turnManager;
    private final Thread backgroundThread;

    GameCycleImpl(final int nTurns, final Dice defaultDice, final List<Dice> rewardDices, final Set<String> humanPlayers, final Set<Pair<String, Difficulty>> cpuPlayers) {
        this.backgroundThread = new Thread(createRunnable());
        this.backgroundThread.setDaemon(true);
        this.turnState = new TurnStateImpl();
        GridType level = GridType.GRID_ONE;//randomize when multiple levels are present
        var pair = new GridViewGeneratorImpl(level, this).generate();
        this.view = pair.getRight();
        PlayerFactory playerFactory = new PlayerFactoryImpl(turnState, view, pair.getLeft());
        TurnManagerBuilder turnManagerBuilder = new TurnManagerBuilderImpl(nTurns);
        turnManagerBuilder
                .setDefaultDice(defaultDice)
                .setRewardDices(rewardDices);
        for (String humanName : humanPlayers) {
            turnManagerBuilder.addPlayer(playerFactory.createHumanPlayer(humanName));
        }
        for (var nameDiff : cpuPlayers) {
            turnManagerBuilder.addPlayer(playerFactory.createCpu(nameDiff.getLeft(), nameDiff.getRight()));
        }
        this.turnManager = turnManagerBuilder.build();
    }

    private Runnable createRunnable() {
        return () -> {
            int turnCounter = 1;
            boolean turnRemaining = true;
            while (turnRemaining) {
                waitNextStep("turn " + turnCounter + " has started");
                turnCounter++;
                while (turnManager.hasNextPlayer()) {
                    PlayerController currentPlayer = turnManager.nextPlayer();
                    turnStart(currentPlayer);
                    boolean hasArrived = movePlayer(currentPlayer);
                    if (hasArrived) {
                        view.showText(currentPlayer.getCharacter().getName() + " wins!");
                        //view.close()
                        return;
                    }
                    //events control
                    turnRemaining = turnManager.hasNextTurn();
                    if (turnManager.hasNextTurn()) {
                        waitNextStep("minigames are starting");
                        turnManager.nextTurn();
                    }
                    turnManager.getPlayers().forEach(player -> {
                        view.showText(player.getCharacter().getName() + " won a " + player.getCharacter().getDice());
                        sleepMillis(1000);
                        view.removeText();
                    });
                }
                waitNextStep("no mo turns remaining, game over");
            }
        };
    }

    private void turnStart(PlayerController player) {
        view.showText("it's " + player.getCharacter().getName() + "'s turn");
        sleepMillis(1000);
        view.removeText();
        int roll = player.throwDice();
        view.showText(player.getCharacter().getName() + " rolled a " + roll);//add number rolled
        sleepMillis(700);
        view.removeText();
    }

    public void waitNextStep(String message) {
        turnState.setTurnPause(true);
        view.showText(message + "   continue ►");
        synchronized (this.turnState) {
            try {
                while (turnState.isPaused()) {
                    turnState.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted during turn step wait");
            }
        }
        view.removeText();
    }

    /**
     * @return true if the character reached the and of the level, false otherwise
     */
    private boolean movePlayer(PlayerController player) {
        boolean movesRemaining = true;
        while (movesRemaining) {
            movesRemaining = singleStep(player);
            if (player.getCharacter().getCellType().equals(CellType.END)) {
                return true;
            }
        }
        return false;
    }

    private boolean singleStep(PlayerController player) {
        if (player.getCharacter().getAdjacentPositions().size() == 1) {
            return player.getCharacter().stepForward();
        }
        player.chooseDirection();
        synchronized (turnState) {
            if (turnState.getLastDirectionChoice().isEmpty()) {
                throw new IllegalStateException("Turn state wasn't set properly");
            }
            return player.getCharacter().stepInDirection(turnState.getLastDirectionChoice().get());
        }
    }

    private void sleepMillis(final int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startGameCycle() {
        this.backgroundThread.start();
    }

    @Override
    public void signalDiceThrown() {
        synchronized (turnState) {
            turnState.setDiceThrown(true);
            turnState.notify();
        }
    }

    @Override
    public void signalPathChosen(Pair<Integer, Integer> coordinates) {
        synchronized (turnState) {
            turnState.setChoice(false);
            turnState.setLastDirectionChoice(coordinates);
            turnState.notify();
        }
    }

    @Override
    public void signalNextStep() {
        synchronized (turnState) {
            turnState.setTurnPause(false);
            turnState.notify();
        }
    }
}
