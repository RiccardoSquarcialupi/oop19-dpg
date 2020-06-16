package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Set;
import java.util.function.Function;

public class NodesFactoryImpl implements NodesFactory {

    private final Function<Double, Double> mappingFunction;

    NodesFactoryImpl(Function<Double, Double> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }

    private double map(double c) {
        return mappingFunction.apply(c);
    }

    @Override
    public Set<Node> createNodes(BallMinigameLevel level) {
        return Set.of(
                new Line(map(0), map(0), map(50), map(50))
        );
    }

    @Override
    public Circle createBall(BallMinigameLevel level) {
        return new Circle(map(90), map(90), map(5), Color.RED);
    }

    @Override
    public Text createScore() {
        Text temp = new Text(map(75), map(100 - 95), "Score: 000");
        temp.setFont(new Font(map(4)));
        return temp;
    }

    @Override
    public Text createReady() {
        Text temp = new Text(map(35), map(52), "ready?");
        temp.setFont(new Font(map(10)));
        return temp;
    }

    @Override
    public Text createGo() {
        Text temp = new Text(map(42), map(54), "GO!");
        temp.setFont(new Font(map(10)));
        return temp;
    }

    @Override
    public Text createVictoryMessage() {
        Text temp = new Text(map(34), map(54), "GOAL!");
        temp.setFont(new Font(map(10)));
        return temp;
    }
}
