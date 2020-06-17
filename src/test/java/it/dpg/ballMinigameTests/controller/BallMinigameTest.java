package it.dpg.ballMinigameTests.controller;

import it.dpg.minigames.ballgame.controller.BallMinigame;
import it.dpg.minigames.base.controller.Minigame;
import org.junit.jupiter.api.Test;

public class BallMinigameTest {
    Thread worker = new Thread(() -> {
        int result;
        Minigame m = new BallMinigame();
        m.start();
    });

    @Test
    public void testMinigame() {
         worker.start();
    }
}
