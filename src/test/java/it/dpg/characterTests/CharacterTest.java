package it.dpg.characterTests;

import it.dpg.model.Cell;
import it.dpg.model.CellImpl;
import it.dpg.model.CellType;
import it.dpg.model.Grid;
import it.dpg.model.character.*;
import it.dpg.model.character.Character;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterTest {

    @Mock
    Grid grid;

    ImmutablePair<Integer, Integer> startPos = new ImmutablePair<>(1, 1);
    Cell startFork;

    @BeforeEach
    void setupMock() {
        final Set<Cell> singleNext = new HashSet<>();
        singleNext.add(new CellImpl(false, new ImmutablePair<>(2, 2), CellType.NORMAL));

        final Cell startNoFork = new CellImpl(false, startPos, CellType.START);
        startNoFork.setNext(singleNext);

        final Set<Cell> forkNext = new HashSet<>();
        forkNext.add(new CellImpl(false, new ImmutablePair<>(2, 2), CellType.NORMAL));
        forkNext.add(new CellImpl(false, new ImmutablePair<>(3, 3), CellType.NORMAL));

        startFork = new CellImpl(true, startPos, CellType.START);
        startFork.setNext(forkNext);

        setFirst(startNoFork);
    }

    @Test
    public void startingValuesTest() {
        final int id = 0;
        final String name = "Bartz";
        final Character c = new CharacterImpl(id, name, grid);

        assertEquals(id, c.getId());
        assertEquals(name, c.getName());
        assertEquals(Dice.D6, c.getDice());
        assertEquals(0, c.getMinigameScore());
        assertEquals(0, c.getTurn());
    }

    @Test
    public void diceTest() {
        final int id = 1;
        final String name = "Galuf";
        final Character c = new CharacterImpl(id, name, grid);

        for(int i = 0; i < 1000; i++) {
            int n = c.throwDice();
            assertTrue(n <= c.getDice().getFaces() && n >= 1);
        }

        c.setDice(Dice.D4);
        for(int i = 0; i < 1000; i++) {
            int n = c.throwDice();
            assertTrue(n <= c.getDice().getFaces() && n >= 1);
        }

        c.setDice(Dice.D8);
        for(int i = 0; i < 1000; i++) {
            int n = c.throwDice();
            assertTrue(n <= c.getDice().getFaces() && n >= 1);
        }

        c.setDice(Dice.D10);
        for(int i = 0; i < 1000; i++) {
            int n = c.throwDice();
            assertTrue(n <= c.getDice().getFaces() && n >= 1);
        }
    }

    @Test
    public void stepForwardTestNoFork() {
        final int id = 2;
        final String name = "Lena";
        final Character c = new CharacterImpl(id, name, grid);

        assertEquals(c.getPosition().left, startPos.left);
        assertEquals(c.getPosition().right, startPos.right);

        assertEquals(c.getAdjacentPositions().size(), 1);

        c.throwDice();
        c.stepForward();
        assertEquals(c.getPosition().left, 2);
        assertEquals(c.getPosition().right, 2);
    }

    @Test
    public void stepForwardTestFork() {
        setFirst(startFork);

        final int id = 2;
        final String name = "Lena";
        final Character c = new CharacterImpl(id, name, grid);

        assertEquals(c.getPosition().left, startPos.left);
        assertEquals(c.getPosition().right, startPos.right);

        assertTrue(c.getAdjacentPositions().size() > 1);

        c.throwDice();
        assertThrows(UnsupportedOperationException.class, c::stepForward);
    }

    @Test
    public void stepToDirectionOk() {
        final int id = 3;
        final String name = "Faris";
        Character c = new CharacterImpl(id, name, grid);

        c.throwDice();
        c.stepInDirection(new ImmutablePair<>(2, 2));
    }

    @Test
    public void stepToDirectionKo() {
        setFirst(startFork);

        final int id = 3;
        final String name = "Faris";
        Character c = new CharacterImpl(id, name, grid);

        c.throwDice();
        c.stepInDirection(new ImmutablePair<>(3, 3));

        c.throwDice();
        assertThrows(
                IllegalArgumentException.class,
                () -> c.stepInDirection(new ImmutablePair<>(4, 4))
        );
    }

    @Test
    public void getCpuRandomDirectionTest() {
        setFirst(startFork);

        final int id = 4;
        final String name = "Gilgamesh";
        final Character c = new CharacterImpl(id, name, grid);
        final Cpu cpu = new CpuImpl(c, Difficulty.NORMAL);

        ImmutablePair<Integer, Integer> randomDirection = cpu.getRandomDirection();
        assertTrue(
                randomDirection.left == 2 && randomDirection.right == 2 ||
                        randomDirection.left == 3 && randomDirection.right == 3
        );
    }

    private void setFirst(final Cell first) {
        when(grid.getFirst()).thenReturn(first);
    }
}
