package it.dpg.maingame.model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.management.ImmutableDescriptor;

import java.util.Map;

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
    Group generateLines(Map<Circle, ImmutablePair<Integer, Integer>> cellsList);
}
