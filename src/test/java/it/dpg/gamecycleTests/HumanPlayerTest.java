package it.dpg.gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;

import it.dpg.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.controller.gamecycle.turnmanagement.TurnStateImpl;
import it.dpg.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.controller.gamecycle.playercontroller.PlayerController;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.Set;


public class HumanPlayerTest {


    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new HumanPlayerController(state, new GridViewMock());

    @Test
    public void testDiceThrow() {
        state.newTurn();
        long waitingTime = 4000;

        Thread gameCycleMock = new Thread(() -> {
            long start = System.currentTimeMillis();
            pc.throwDice(6);
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
            pc.chooseDirection(Set.of(
                    new ImmutablePair<>(4, 8),
                    new ImmutablePair<>(3, 9),
                    new ImmutablePair<>(4, 9)));
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
