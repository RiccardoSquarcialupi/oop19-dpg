package it.dpg.gamecycleTests;

import it.dpg.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.controller.gamecycle.turnmanagement.*;
import it.dpg.model.Cell;
import it.dpg.model.Grid;
import it.dpg.model.character.Character;
import it.dpg.model.character.CharacterImpl;
import it.dpg.model.character.Cpu;
import it.dpg.model.character.Difficulty;
import it.dpg.view.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerManagerImplTest {

    private class CpuMock implements Cpu {

        @Override
        public Character getControlledCharacter() {
            return null;
        }

        @Override
        public void setDifficulty(Difficulty difficulty) {

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
    };

    private final TurnState state = new TurnStateImpl();
    private final GridView view = new GridViewMock();
    private PlayerManager manager;

    @BeforeEach
    void setup() {
        initialize();
        addPlayers();
    }

    void initialize() {
        manager = new PlayerManagerImpl(10);
    }

    void addPlayers() {
        Player p1 = new PlayerImpl(new CharacterImpl(1, "Franco", gridMock), new HumanPlayerController(state, view));
        Player p2 = new PlayerImpl(new CharacterImpl(2, "Alberto", gridMock), new HumanPlayerController(state, view));
        Player p3 = new PlayerImpl(new CharacterImpl(3, "CPU1", gridMock), new CpuPlayerController(state, view, new CpuMock()));
        manager.addPlayer(p1);
        manager.addPlayer(p2);
        manager.addPlayer(p3);
    }

    @Test
    void basicTest() {

    }
}
