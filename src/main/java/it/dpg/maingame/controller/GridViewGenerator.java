package it.dpg.maingame.controller;

import it.dpg.maingame.model.Grid;
import it.dpg.maingame.view.GridView;
import org.apache.commons.lang3.tuple.Pair;

public interface GridViewGenerator {

    /**
     * generates a new Grid and corresponding GridView
     *
     * @return created Grid and corresponding GridView
     */
    Pair<Grid, GridView> generate();

}
