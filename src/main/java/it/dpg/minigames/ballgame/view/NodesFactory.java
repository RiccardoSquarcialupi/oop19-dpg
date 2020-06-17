package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Set;

public interface NodesFactory {
    /**
     * create the nodes that don't need modifications during the game
     * @param level the chosen level
     * @return the set of nodes
     */
    Set<Node> createNodes(BallMinigameLevel level);

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
