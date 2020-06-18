package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.List;

public interface NodesFactory {
    /**
     * create the nodes that don't need modifications during the game
     * @param level the chosen level
     * @return the list of nodes (list is used instead of set for preserving the nodes order, witch determine the render order)
     */
    List<Node> createNodes(BallMinigameLevel level);

    /**
     * creates the ball
     * @param level the chosen level
     * @return the ball
     */
    Circle createBall(BallMinigameLevel level);

    /**
     * creates the text used for displaying the score
     */
    Text createScore();

    /**
     *  creates the text used for displaying the ready message
     */
    Text createReady();

    /**
     *  creates the text used for displaying the go message
     */
    Text createGo();

    /**
     *  creates the text used for displaying the victory message
     */
    Text createVictoryMessage();
}
