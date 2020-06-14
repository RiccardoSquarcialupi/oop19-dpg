package it.dpg.gridTest;

import it.dpg.maingame.controller.GridViewGenerator;
import it.dpg.maingame.controller.GridViewGeneratorImpl;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.GridInitializer;
import it.dpg.maingame.model.GridInitializerImpl;
import it.dpg.maingame.model.GridType;
import it.dpg.maingame.view.GridView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GridViewTest extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        GridInitializer gridInit = new GridInitializerImpl();
        Grid grid = gridInit.makeGrid(GridType.GRID_ONE);
        GridView view;

        GridViewGenerator generator = new GridViewGeneratorImpl(grid);
        view = generator.generate(stage);
        stage.show();

        view.setCurrentPlayerName("Giovanna");
        view.setRemainingMoves(3);

        view.setCurrentPlayerName("Marco");
        view.setRemainingMoves(2);

        view.setCurrentPlayerName("Davide");
        view.setRemainingMoves(1);

        Set<ImmutablePair<Integer, Integer>> cellSet = new HashSet<>();
        cellSet.add(new ImmutablePair<>(1,5));
        cellSet.add(new ImmutablePair<>(2,4));
        view.enableDirectionChoice(cellSet);
        view.disableDirectionChoice();

        view.showText("hello");
        view.showText("this is a test");

        /*
        problema con il passaggio della lista di coordinate tramite mappa: si bugga nel caso in cui due pedine hanno la stessa X, sovrapponendole
         */

        Map<Integer, Integer> players = new HashMap<>();
        players.put(1,0);
        view.updatePlayers(players);
        Map<Integer, Integer> players2 = new HashMap<>();
        players2.put(1,2);
        view.updatePlayers(players2);
    }
}
