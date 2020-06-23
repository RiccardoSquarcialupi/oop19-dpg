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
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class GameCycleImpl implements GameCycle {

    private final TurnState turnState;
    private final GridView view;
    private final TurnManager turnManager;
    private final Thread backgroundThread;

    GameCycleImpl(final int nTurns, final Dice defaultDice, final List<Dice> rewardDices, final Set<String> humanPlayers, final Set<Pair<String, Difficulty>> cpuPlayers) {
        this.backgroundThread = new Thread(this::mainCycle);
        this.backgroundThread.setDaemon(true);
        this.turnState = new TurnStateImpl();
        GridType level = GridType.GRID_ONE;//randomize when multiple levels are present
        var pair = new GridViewGeneratorImpl(level, this).generate();
        this.view = pair.getRight();
        PlayerFactory playerFactory = new PlayerFactoryImpl(turnState, view, pair.getLeft());
        TurnManagerBuilder turnManagerBuilder = new TurnManagerBuilderImpl(nTurns, turnState);
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

    private void mainCycle() {
        view.setView();
        int turnCounter = 1;
        boolean turnRemaining = true;
        updatePlayersInView();
        while (turnRemaining) {
            waitNextStep("turn " + turnCounter + " has started");
            turnCounter++;
            boolean hasArrived;
            while (turnManager.hasNextPlayer()) {
                PlayerController currentPlayer = turnManager.nextPlayer();
                turnStart(currentPlayer);
                hasArrived = movePlayer(currentPlayer);
                if (hasArrived) {
                    waitNextStep(currentPlayer.getCharacter().getName() + " wins!");
                    view.closeView();
                    return;
                }
                if (currentPlayer.getCharacter().getCellType().equals(CellType.GO_BACK)) {
                    view.showText(currentPlayer.getCharacter().getName() + " ended up on a red cell \nand fell one step backwards");
                    sleepMillis(1500);
                    currentPlayer.getCharacter().stepBackward();
                    updatePlayersInView();
                }
            }
            turnRemaining = turnManager.hasNextTurn();
            if (turnManager.hasNextTurn()) {
                waitNextStep("minigames are starting");
                turnManager.nextTurn();
            }
            displayMinigameResults();
        }
        waitNextStep("no more turns remaining, game over");
        view.closeView();
    }

    private void updatePlayersInView() {
        var positions = new HashMap<Integer, Pair<Integer, Integer>>();
        turnManager.getPlayers().forEach(p -> positions.put(p.getCharacter().getId(), p.getCharacter().getPosition()));
        this.view.updatePlayers(positions);
    }

    private void turnStart(PlayerController player) {
        view.showText("it's " + player.getCharacter().getName() + "'s turn");
        view.setCurrentPlayerName(player.getCharacter().getName());
        sleepMillis(1000);
        view.removeText();
        int roll = player.throwDice();
        waitNextStep(player.getCharacter().getName() + " rolled a " + roll);
    }

    private void waitNextStep(String message) {
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
            sleepMillis(700);
            movesRemaining = singleStep(player);
            updatePlayersInView();
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
        if (turnState.getLastDirectionChoice().isEmpty()) {
            throw new IllegalStateException("Turn state wasn't set properly");
        }
        return player.getCharacter().stepInDirection(turnState.getLastDirectionChoice().get());
    }

    private void displayMinigameResults() {
        StringBuilder results = new StringBuilder();
        for (PlayerController p : turnManager.getPlayers()) {
            results.append(p.getCharacter().getName()).append(" won a ").append(p.getCharacter().getDice()).append("\n");
        }
        waitNextStep(results.toString());
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
