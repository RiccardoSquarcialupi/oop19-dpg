package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import it.dpg.minigames.base.view.MinigameView;

public interface BallMinigameView extends MinigameView {

    /**
     * shows the selected level
     * @param level the selected level
     */
    void setupLevel(BallMinigameLevel level);

    /**
     * position the ball at the given coordinates
     * @param xPos ball's horizontal position
     * @param yPos ball's vertical position
     */
    void positionBall(final double xPos, final double yPos);

    /**
     * sets the current score
     * @param score player's current score
     */
    void setScore(int score);

    /**
     * show the game has been beaten
     */
    void setVictory();
}
