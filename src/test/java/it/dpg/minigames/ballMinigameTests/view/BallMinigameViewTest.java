package it.dpg.minigames.ballMinigameTests.view;

import it.dpg.minigames.ballgame.controller.BallObserver;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BallMinigameViewTest {

    BallObserver observer = Mockito.mock(BallObserver.class);

    BallViewImpl view = new BallViewImpl(600, observer);

    @Test
    public void testView() {
    }

}
