package it.dpg.minigames.punchygame;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.punchygame.controller.PunchygameCycle;
import it.dpg.minigames.punchygame.view.PunchygameView;
import it.dpg.minigames.punchygame.view.PunchygameViewImpl;

import java.util.Objects;

/**
 * Minigame in which the players use the directional arrow left and right
 * to punch boxing sacks that approaches with each correct punch
 * @author Davide Picchiotti
 * */

public class PunchyMinigame extends AbstractMinigame {

    private PunchygameView view = new PunchygameViewImpl();

    @Override
    public int getMaxScore() {
        return 400;
    }

    @Override
    public MinigameView createView() {
        return Objects.requireNonNull(view);
    }

    @Override
    public MinigameCycle createCycle() {
        return new PunchygameCycle(Objects.requireNonNull(view));
    }
}
