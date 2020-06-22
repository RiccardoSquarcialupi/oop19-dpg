package it.dpg.maingame.view;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ViewNodesFactoryImpl implements ViewNodesFactory {

    @Override
    public Circle generateCell(Color color) {
        Circle circle = new Circle(40);
        circle.setFill(color);

        return circle;
    }

    @Override
    public Rectangle generatePlayer(Integer player) {
        Rectangle square = new Rectangle(30, 30);

        Random rand = new Random();     //for each player that exists, a new random color is generated and given to the rectangle

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        javafx.scene.paint.Color color = new javafx.scene.paint.Color(r, g, b, 1);
        square.setFill(color);
        return square;
    }

    @Override
    public Group generateLines(Map<StackPane, Set<Pair<Integer, Integer>>> cellsList, double modifierX, double modifierY) {

        Group linesGroup = new Group();
        for (var i : cellsList.entrySet()) {
            for (var j : i.getValue()) {
                Line line = new Line();
                line.setStroke(Color.FORESTGREEN);
                line.setStrokeWidth(10);
                line.setStartX(i.getKey().getLayoutX()+modifierX/3.3);
                line.setStartY(i.getKey().getLayoutY()+modifierX/3.3);
                line.setEndX(j.getLeft() * modifierX+modifierX/3.3);
                line.setEndY(j.getRight() * modifierY+modifierX/3.3);
                linesGroup.getChildren().add(line);
            }
        }

        return linesGroup;

    }
}
