package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import it.dpg.minigames.base.view.AbstractMinigameView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class BallViewImpl extends AbstractMinigameView implements BallMinigameView {
    private final double viewSize;
    private final Group panel = new Group();
    private Circle ball;
    private Text score;
    private final NodesFactory factory = new NodesFactoryImpl(this::mapCoordinate);
    private final Text readyText = factory.createReady();
    private final Text goText = factory.createGo();
    private final Text victoryText = factory.createVictoryMessage();

    public BallViewImpl(final double viewSize) {
        this.viewSize = viewSize;
    }

    /**
     * maps the coordinate in a range [0, 100] to a coordinate in a range [0, viewSize]
     * @param coordinate coordinate in range [0, 100]
     * @return mapped coordinate to match screen size
     */
    private double mapCoordinate(final double coordinate) {
        if(coordinate < 0 || coordinate > 100) {
            throw new IllegalArgumentException();
        }
        return (coordinate / 100) * this.viewSize;
    }

    @Override
    public Scene createScene() {
        return new Scene(panel, viewSize, viewSize);
    }

    @Override
    public void setupLevel(BallMinigameLevel level) {
        panel.getChildren().clear();
        ball = factory.createBall(level);
        score = factory.createScore();
        panel.getChildren().add(ball);
        panel.getChildren().add(score);
        factory.createNodes(level)
                .forEach(node -> panel.getChildren().add(node));
    }

    @Override
    public void positionBall(double xPos, double yPos) {
        ball.setCenterX(mapCoordinate(xPos));
        ball.setCenterY(mapCoordinate(yPos));
    }

    @Override
    public void setScore(int score) {
        this.score.setText("Score: " + score);
    }

    @Override
    public void setVictory() {
        panel.getChildren().add(this.victoryText);
    }

    @Override
    public void setReady() {
        panel.getChildren().add(this.readyText);
    }

    @Override
    public void removeReady() {
        panel.getChildren().remove(this.readyText);
    }

    @Override
    public void setGo() {
        panel.getChildren().add(this.goText);
    }

    @Override
    public void removeGo() {
        panel.getChildren().remove(this.goText);
    }
}
