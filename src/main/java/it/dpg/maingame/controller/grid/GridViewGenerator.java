package it.dpg.maingame.controller.grid;

import it.dpg.maingame.model.grid.Grid;
import it.dpg.maingame.view.grid.GridView;
import org.apache.commons.lang3.tuple.Pair;

public interface GridViewGenerator {

    /**
     * generates a new Grid and corresponding GridView
     *
     * @return created Grid and corresponding GridView
     */
    Pair<Grid, GridView> generate();

}
