package it.dpg.maingame.controller;

import it.dpg.maingame.model.Grid;
import it.dpg.maingame.view.GridView;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

public interface GridViewGenerator {

    /**
     * generates a new Grid and corresponding GridView
     *
     * @return created Grid and corresponding GridView
     */
    ImmutablePair<Grid, GridView> generate();

}
