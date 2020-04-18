package it.dpg.gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.controller.gamecycle.TurnStateImpl;
import it.dpg.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.controller.gamecycle.playercontroller.PlayerController;
import org.junit.jupiter.api.Test;

import java.util.Set;


public class HumanPlayerTest {


    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new HumanPlayerController(state, new GridViewTestImpl());

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

        state.setDiceThrown();
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
            pc.chooseDirection(Set.of(3, 7, 9));
            long stop = System.currentTimeMillis();
            assertTrue((stop - start) >= waitingTime);
        });

        gameCycleMock.start();
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            fail();
        }

        state.choiceCompleted();
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
