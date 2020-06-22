package it.dpg.gamecycleTests;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnStateImpl;
import it.dpg.maingame.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.Cell;
import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.*;
import it.dpg.maingame.model.character.Character;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CpuPlayerControllerTest {

    private static class CpuMock implements Cpu{

        @Override
        public Character getControlledCharacter() {
            return null;
        }

        @Override
        public Difficulty getDifficulty() {
            return null;
        }

        @Override
        public ImmutablePair<Integer, Integer> getRandomDirection() {
            return new ImmutablePair<>(4, 8);
        }
    }

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
        public Map<Cell, Pair<Integer, Integer>> getCellList() {
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
        public void setPosition(Pair<Integer, Integer> coordinates) {

        }

        @Override
        public ImmutablePair<Integer, Integer> getPosition() {
            return null;
        }

        @Override
        public Set<Pair<Integer, Integer>> getAdjacentPositions() {
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
        public boolean stepBackward() {
            return false;
        }

        @Override
        public boolean stepInDirection(Pair<Integer, Integer> coordinates) {
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

    private final Cpu cpuMock = new CpuMock();
    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new CpuPlayerController(state, new GridViewMock() , cMock, Difficulty.NORMAL);

    @Test
    public void testDiceThrow() {
        state.newTurn();
        pc.throwDice();
        assertTrue(state.wasDiceThrown());
    }

    @Test
    public void testDirectionChoice() {
        state.newTurn();
        var choices = Set.of(
                new ImmutablePair<>(4, 8),
                new ImmutablePair<>(3, 9),
                new ImmutablePair<>(4, 9));
        pc.chooseDirection();
        if(state.getLastDirectionChoice().isEmpty()) {
            fail();
        }
        ImmutablePair<Integer, Integer> temp = (ImmutablePair<Integer, Integer>) state.getLastDirectionChoice().get();
        assertTrue(choices.contains(temp));
        assertFalse(state.isChoosing());
    }
}
