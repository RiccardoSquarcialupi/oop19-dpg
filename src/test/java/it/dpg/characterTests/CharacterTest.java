package it.dpg.characterTests;

import it.dpg.model.Grid;
import it.dpg.model.character.Character;
import it.dpg.model.character.CharacterImpl;
import it.dpg.model.character.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.stream.IntStream;

public class CharacterTest {

    @Mock
    Grid grid;

    @Test
    public void startingValuesTest() {
        final int id = 0;
        final String name = "Bartz";
        final Character c = new CharacterImpl(id, name, grid);

        Assertions.assertEquals(id, c.getId());
        Assertions.assertEquals(name, c.getName());
        Assertions.assertEquals(Dice.D6, c.getDice());
        Assertions.assertEquals(0, c.getMinigameScore());
        Assertions.assertEquals(0, c.getTurn());
    }

    @Test
    public void diceTest() {
        final int id = 1;
        final String name = "Galuf";
        final Character c = new CharacterImpl(id, name, grid);

        for(int i = 0; i < 1000; i++) {
            Assertions.assertTrue(c.throwDice() <= c.getDice().getFaces());
            Assertions.assertTrue(c.throwDice() >= 1);
        }

        c.setDice(Dice.D4);
        for(int i = 0; i < 1000; i++) {
            Assertions.assertTrue(c.throwDice() <= c.getDice().getFaces());
            Assertions.assertTrue(c.throwDice() >= 1);
        }

        c.setDice(Dice.D8);
        for(int i = 0; i < 1000; i++) {
            Assertions.assertTrue(c.throwDice() <= c.getDice().getFaces());
            Assertions.assertTrue(c.throwDice() >= 1);
        }

        c.setDice(Dice.D10);
        for(int i = 0; i < 1000; i++) {
            Assertions.assertTrue(c.throwDice() <= c.getDice().getFaces());
            Assertions.assertTrue(c.throwDice() >= 1);
        }
    }
}
