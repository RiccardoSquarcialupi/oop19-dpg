package it.dpg.maingame.view.grid;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Set;

public interface ViewNodesFactory {

    /**
     * generates a circle corresponding to a cell
     */
    Circle generateCell(Color color);

    /**
     * generates a square corresponding to a player
     */
    Rectangle generatePlayer(Integer player);

    /**
     * generates a group of lines based on the cells
     */
    Group generateLines(Map<StackPane, Set<Pair<Integer, Integer>>> cellsList, double modifierX, double modifierY);
}
