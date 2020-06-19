package it.dpg.gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnStateImpl;
import it.dpg.maingame.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.Cell;
import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.model.character.CharacterImpl;
import it.dpg.maingame.model.character.Dice;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;


public class HumanPlayerControllerTest {

    public final Grid gridMock = new Grid() {
        @Override
        public Cell getFirst() {
            return null;
        }

        @Override
        public Cell getLast() {
            return null;
        }

        @Override
        public Cell getCellByCoordinates(Integer X, Integer Y) {
            return null;
        }

        @Override
        public Map<Cell, ImmutablePair<Integer, Integer>> getCellList() {
            return null;
        }
    };

    public Character cMock = new Character() {
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public String getName() {
            return "franco";
        }

        @Override
        public void setTurn(int turn) {

        }

        @Override
        public int getTurn() {
            return 0;
        }

        @Override
        public void setPosition(ImmutablePair<Integer, Integer> coordinates) {

        }

        @Override
        public ImmutablePair<Integer, Integer> getPosition() {
            return null;
        }

        @Override
        public Set<ImmutablePair<Integer, Integer>> getAdjacentPositions() {
            return Set.of(
                    new ImmutablePair<>(4, 8),
                    new ImmutablePair<>(3, 9),
                    new ImmutablePair<>(4, 9));
        }

        @Override
        public CellType getCellType() {
            return null;
        }

        @Override
        public boolean stepForward() {
            return false;
        }

        @Override
        public boolean stepInDirection(ImmutablePair<Integer, Integer> coordinates) {
            return false;
        }

        @Override
        public void setDice(Dice dice) {

        }

        @Override
        public Dice getDice() {
            return null;
        }

        @Override
        public int throwDice() {
            return 0;
        }

        @Override
        public void setMinigameScore(int score) {

        }

        @Override
        public int getMinigameScore() {
            return 0;
        }
    };

    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new HumanPlayerController(state, new GridViewMock(), cMock);

    @Test
    public void testDiceThrow() {
        state.newTurn();
        long waitingTime = 4000;

        Thread gameCycleMock = new Thread(() -> {
            long start = System.currentTimeMillis();
            pc.throwDice(Dice.D6);
            long stop = System.currentTimeMillis();
            assertTrue((stop - start) >= waitingTime);
        });

        gameCycleMock.start();
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            fail();
        }

        state.setDiceThrown(true);
        synchronized (state) {
            state.notify();
        }

        try {
            gameCycleMock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDirectionChoice() {
        state.newTurn();
        long waitingTime = 4000;

        Thread gameCycleMock = new Thread(() -> {
            long start = System.currentTimeMillis();
            pc.chooseDirection();
            long stop = System.currentTimeMillis();
            assertTrue((stop - start) >= waitingTime);
        });

        gameCycleMock.start();
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            fail();
        }

        state.setChoice(false);
        synchronized (state) {
            state.notify();
        }

        try {
            gameCycleMock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTurnPause() {
        state.newTurn();
        long waitingTime = 4000;

        Thread gameCycleMock = new Thread(() -> {
            long start = System.currentTimeMillis();
            pc.waitNextStep();
            long stop = System.currentTimeMillis();
            assertTrue((stop - start) >= waitingTime);
        });

        gameCycleMock.start();
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            fail();
        }

        state.setTurnPause(false);
        synchronized (state) {
            state.notify();
        }

        try {
            gameCycleMock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
