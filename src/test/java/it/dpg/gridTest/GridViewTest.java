package it.dpg.gridTest;

import it.dpg.maingame.controller.GridViewGenerator;
import it.dpg.maingame.controller.GridViewGeneratorImpl;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.GridInitializer;
import it.dpg.maingame.model.GridInitializerImpl;
import it.dpg.maingame.model.GridType;
import javafx.application.Application;
import javafx.stage.Stage;

public class GridViewTest extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        GridInitializer gridInit = new GridInitializerImpl();
        Grid grid = gridInit.makeGrid(GridType.GRID_ONE);

        GridViewGenerator generator = new GridViewGeneratorImpl(grid);
        generator.generate(stage);
        stage.show();
    }
}
